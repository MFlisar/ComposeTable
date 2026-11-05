import com.michaelflisar.kmpdevtools.BuildFileUtil
import com.michaelflisar.kmpdevtools.Targets
import com.michaelflisar.kmpdevtools.configs.AndroidLibraryConfig
import com.michaelflisar.kmpdevtools.configs.LibraryModuleConfig
import com.michaelflisar.kmpdevtools.core.Target

plugins {
    alias(libs.plugins.jetbrains.kotlin.multiplatform)
    alias(libs.plugins.android.library.kmp)
    alias(libs.plugins.jetbrains.kotlin.compose)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.dokka)
    alias(libs.plugins.vanniktech.maven.publish.base)
    alias(libs.plugins.binary.compatibility.validator)
    alias(mflisar.plugins.kmpdevtools.buildplugin)
}

val module = LibraryModuleConfig.read(project)
val buildTargets = Targets(Target.ANDROID, Target.IOS, Target.JVM, Target.MACOS, Target.WASM)
val androidConfig = AndroidLibraryConfig.create(
    libraryModuleConfig = module,
    compileSdk = app.versions.compileSdk,
    minSdk = app.versions.minSdk,
    enableAndroidResources = false,
)

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }

    buildTargets.setupTargetsLibrary(module)
    android {
        buildTargets.setupTargetsAndroidLibrary(module, androidConfig, this)
    }

    sourceSets {
        commonMain.dependencies {
            api(project(":composetable:core"))
            api(libs.jetbrains.compose.components.resources)
            api(libs.jetbrains.compose.foundation)
            api(libs.jetbrains.kotlinx.coroutines.core)
            implementation(libs.jetbrains.compose.material3)
            implementation(libs.jetbrains.compose.material.icons.extended)
        }
    }
}

BuildFileUtil.setupMavenPublish(module)
