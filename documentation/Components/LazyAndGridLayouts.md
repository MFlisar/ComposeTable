The common layout helpers include `StyledLazyColumn`, `StyledLazyRow`,
`StyledLazyVerticalGrid` and `StyledLazyHorizontalGrid`. They expose the
corresponding Foundation state, content padding, layout, fling and scroll
parameters without requiring theme registration. Their `StyledSpaced*`
counterparts use `StyledTheme.spacing.medium` as the default item spacing.
Grid variants take `GridCells` through `columns` or `rows`.

```kotlin
StyledSpacedLazyColumn {
    items(messages) { message ->
        StyledText(message.title)
    }
}

StyledSpacedLazyVerticalGrid(columns = GridCells.Adaptive(160.dp)) {
    items(products) { product ->
        ProductCard(product)
    }
}
```
