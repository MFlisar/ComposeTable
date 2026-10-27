`StyledVerticalScrollbarContainer` and `StyledHorizontalScrollbarContainer`
share their scrolling, alignment and overlap behavior across platforms. Android
uses a custom draggable scrollbar from `featureCustomScrollbarMain`. iOS,
Windows (JVM), macOS and Wasm use Compose Foundation scrollbars from
`featureComposeScrollbarMain`, selected through an internal `expect`/`actual`
function. The public container APIs are identical on all targets.
