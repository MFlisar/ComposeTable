require "json"
require "yaml"

def mapping(config, key)
  value = config.fetch(key, {})
  value = {} if value.nil?
  abort "#{key} must be a mapping." unless value.is_a?(Hash)
  value
end

def config_value(value)
  unless value.nil? || value.is_a?(String) || value.is_a?(Numeric)
    abort "Config values written to workflow outputs must be scalar values."
  end
  result = value.to_s.strip
  abort "Config values written to workflow outputs must be single-line values." if result.match?(/[\r\n]/)
  result
end

config = YAML.safe_load_file(ENV.fetch("CONFIG_FILE"))
abort "The config file must contain a mapping." unless config.is_a?(Hash)

publish = mapping(config, "publish")
project = mapping(config, "project")
general = mapping(config, "config")
publish_github = mapping(publish, "github")

publish_target = config_value(publish["target"])
unless %w[none maven-central github].include?(publish_target)
  abort "publish.target must be one of: none, maven-central, github."
end

publish_enabled = publish_target != "none"
maven_central_enabled = publish_target == "maven-central"
github_enabled = publish_target == "github"
java_version = config_value(general["java-version"])
abort "config.java-version must be set." if java_version.empty?

page_title = config_value(project["repo-name"])
abort "project.repo-name must be set." if page_title.empty?

target_repository = config_value(publish_github["repository"])
if target_repository.empty?
  target_owner = config_value(publish_github["user"])
  target_repo = config_value(publish_github["repo"])
  target_repository = "#{target_owner}/#{target_repo}" unless target_owner.empty? || target_repo.empty?
end
target_branch = config_value(publish_github["branch"])
target_path = config_value(publish_github["path"])

if github_enabled
  unless target_repository.match?(/\A[[:alnum:]_.-]+\/[[:alnum:]_.-]+\z/)
    abort "publish.github.repository or publish.github.user and publish.github.repo must identify owner/repository."
  end
  abort "publish.github.branch must be set when GitHub publishing is enabled." if target_branch.empty?
  unless target_path.empty? || (!target_path.include?("\\") && target_path.split("/").all? { |part| !part.empty? && part != "." && part != ".." })
    abort "publish.github.path must be a relative path without empty, '.' or '..' segments."
  end
end

apps = config.fetch("apps", [])
abort "apps must be a list." unless apps.is_a?(Array)
abort "Each entry in apps must be a mapping." unless apps.all? { |app| app.is_a?(Hash) }
demos = apps.select { |app| app["publish-demo"] == true }
abort "Only one app can have apps.publish-demo enabled." if demos.length > 1

demo_task = ""
demo_html_path = ""
demo_apps = []
unless demos.empty?
  demo = demos.first
  demo_path = config_value(demo["path"])
  unless demo_path.match?(/\A[A-Za-z0-9._-]+(?:\/[A-Za-z0-9._-]+)*\z/) &&
      demo_path.split("/").none? { |part| part == "." || part == ".." }
    abort "apps.path must be a relative path without empty, '.' or '..' segments for a published demo."
  end

  demo_task = ":#{demo_path.split("/").join(":")}:wasmJsBrowserDistribution"
  demo_html_path = "#{demo_path}/build/dist/wasmJs/productionExecutable"
  demo_apps = demos.map do |app|
    {
      "name" => config_value(app["name"]),
      "description" => config_value(app["publish-description"])
    }
  end
  abort "apps.name must be set for a published demo." if demo_apps.any? { |app| app["name"].empty? }
end

outputs = {
  "publish-enabled" => publish_enabled,
  "publish-target" => publish_target,
  "maven-central-enabled" => maven_central_enabled,
  "github-enabled" => github_enabled,
  "java-version" => java_version,
  "target-repository" => target_repository,
  "target-branch" => target_branch,
  "target-path" => target_path,
  "page-title" => page_title,
  "demo-enabled" => !demos.empty?,
  "demo-task" => demo_task,
  "demo-html-path" => demo_html_path,
  "demo-apps-json" => JSON.generate(demo_apps)
}

File.open(ENV.fetch("GITHUB_OUTPUT"), "a") do |output|
  outputs.each { |key, value| output.puts("#{key}=#{value}") }
end
