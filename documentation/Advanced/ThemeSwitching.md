Keep the host state and coroutine scope above theme switches. Scaffold passes
its slots directly to the renderer, without `movableContentOf`, to avoid moving
them between Scaffold subcompositions when the renderer changes. Keep state that
must survive such changes above Scaffold as well; remembered state inside its
slots can reset when switching implementations. Only `StyledTheme` wraps its
content in remembered `movableContentOf` to preserve the caller subtree when
the theme root changes. All component slots are forwarded directly; their
remembered state can reset when a renderer replaces the slot subtree.
Internal implementation state is not shared.
