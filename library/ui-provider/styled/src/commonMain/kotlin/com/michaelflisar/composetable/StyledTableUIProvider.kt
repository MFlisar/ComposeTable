package com.michaelflisar.composetable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Deselect
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FirstPage
import androidx.compose.material.icons.filled.LastPage
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SelectAll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michaelflisar.composestyled.StyledTheme
import com.michaelflisar.composestyled.classes.toIconComposable
import com.michaelflisar.composestyled.ui.components.StyledButton
import com.michaelflisar.composestyled.ui.components.StyledCheckbox
import com.michaelflisar.composestyled.ui.components.StyledDropdownMenuItem
import com.michaelflisar.composestyled.ui.components.StyledIcon
import com.michaelflisar.composestyled.ui.components.StyledIconButton
import com.michaelflisar.composestyled.ui.components.StyledInput
import com.michaelflisar.composestyled.ui.components.StyledPopupMenu
import com.michaelflisar.composestyled.ui.components.StyledProgressIndicator
import com.michaelflisar.composestyled.ui.components.StyledRadioButton
import com.michaelflisar.composestyled.ui.components.StyledSelectableChip
import com.michaelflisar.composestyled.ui.components.StyledSurface
import com.michaelflisar.composestyled.ui.layout.StyledDialog
import com.michaelflisar.composestyled.ui.layout.StyledLazyColumn
import com.michaelflisar.composestyled.ui.overlay.StyledPopupContainer
import com.michaelflisar.composestyled.ui.primitives.StyledHorizontalDivider
import com.michaelflisar.composestyled.ui.primitives.StyledText
import com.michaelflisar.composestyled.ui.primitives.StyledVerticalDivider
import com.michaelflisar.composestyled.ui.state.rememberDialogState
import com.michaelflisar.composetable.implementation.TableIcon
import com.michaelflisar.composetable.implementation.TableTextStyle
import com.michaelflisar.composetable.implementation.TableUI
import com.michaelflisar.composestyled.classes.IconComposable as StyledIconContent

private val LocalTableContentColor = compositionLocalOf { Color.Unspecified }

object StyledTableUIProvider : TableUI.Provider {

    @Composable
    override fun tableColors() = TableColors(
        container = StyledTheme.colorScheme.surface,
        content = StyledTheme.colorScheme.onSurface,
        header = StyledTheme.colorScheme.surfaceVariant,
        headerContent = StyledTheme.colorScheme.onSurfaceVariant,
        selected = StyledTheme.colorScheme.primarySurface,
        selectedContent = StyledTheme.colorScheme.onPrimarySurface,
        divider = StyledTheme.colorScheme.outlineVariant,
    )

    @Composable
    override fun tableShape(): Shape = StyledTheme.shapes.medium

    @Composable
    override fun scrollbarWidth(): Dp = 16.dp

    @Composable
    override fun primaryColor(): Color = StyledTheme.colorScheme.primary

    @Composable
    override fun errorColor(): Color = StyledTheme.colorScheme.error

    @Composable
    override fun textStyle(style: TableTextStyle): TextStyle = when (style) {
        TableTextStyle.LabelSmall -> StyledTheme.typography.labelSmall
        TableTextStyle.TitleMedium -> StyledTheme.typography.titleMedium
    }

    @Composable
    override fun buttonIconSize(): Dp = StyledTheme.sizes.iconSize

    @Composable
    override fun buttonIconSpacing(): Dp = StyledTheme.spacing.small

    @Composable
    override fun contentColor(): Color =
        LocalTableContentColor.current.takeUnless { it == Color.Unspecified }
            ?: StyledTheme.currentContentColor

    @Composable
    override fun ProvideContentColor(color: Color, content: @Composable () -> Unit) {
        CompositionLocalProvider(LocalTableContentColor provides color, content = content)
    }

    @Composable
    override fun HorizontalDivider(modifier: Modifier, color: Color) {
        StyledHorizontalDivider(
            modifier = modifier,
            color = color.takeUnless { it == Color.Unspecified }
                ?: StyledTheme.colorScheme.outlineVariant,
        )
    }

    @Composable
    override fun VerticalDivider(modifier: Modifier, color: Color) {
        StyledVerticalDivider(
            modifier = modifier,
            color = color.takeUnless { it == Color.Unspecified }
                ?: StyledTheme.colorScheme.outlineVariant,
        )
    }

    @Composable
    override fun CircularProgressIndicator(modifier: Modifier) {
        StyledProgressIndicator(
            type = StyledProgressIndicator.Type.Circular,
            modifier = modifier,
        )
    }

    @Composable
    override fun LinearProgressIndicator(modifier: Modifier) {
        StyledProgressIndicator(
            type = StyledProgressIndicator.Type.Linear,
            modifier = modifier,
        )
    }

    @Composable
    override fun LazyColumn(
        state: LazyListState,
        modifier: Modifier,
        paddingForScrollbar: Dp,
        itemSpacing: Dp,
        horizontalAlignment: Alignment.Horizontal,
        content: LazyListScope.() -> Unit,
    ) {
        StyledLazyColumn(
            itemSpacing = itemSpacing,
            modifier = modifier.fillMaxWidth().padding(end = paddingForScrollbar),
            state = state,
            horizontalAlignment = horizontalAlignment,
            content = content,
        )
    }

    @Composable
    override fun Surface(color: Color, contentColor: Color, content: @Composable () -> Unit) {
        ProvideContentColor(contentColor) {
            StyledSurface(
                color = color,
                contentColor = contentColor,
                content = content,
            )
        }
    }

    @Composable
    override fun Text(
        text: String,
        modifier: Modifier,
        color: Color,
        style: TextStyle?,
        maxLines: Int,
    ) {
        StyledText(
            text = text,
            modifier = modifier,
            color = color.takeUnless { it == Color.Unspecified } ?: contentColor(),
            style = style ?: StyledTheme.currentTextStyle,
            maxLines = maxLines,
        )
    }

    @Composable
    override fun Icon(
        icon: TableIcon,
        contentDescription: String?,
        modifier: Modifier,
        tint: Color?,
    ) {
        StyledIcon(
            icon = icon.imageVector.toIconComposable(),
            contentDescription = contentDescription,
            modifier = modifier,
            tint = tint ?: contentColor(),
        )
    }

    @Composable
    override fun TextButton(
        onClick: () -> Unit,
        enabled: Boolean,
        content: @Composable RowScope.() -> Unit,
    ) {
        StyledButton(
            onClick = onClick,
            enabled = enabled,
            role = StyledButton.Role.Tertiary,
            content = content,
        )
    }

    @Composable
    override fun OutlinedButton(onClick: () -> Unit, content: @Composable RowScope.() -> Unit) {
        StyledButton(
            onClick = onClick,
            role = StyledButton.Role.Secondary,
            content = content,
        )
    }

    @Composable
    override fun IconButton(
        onClick: () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        content: @Composable () -> Unit,
    ) {
        StyledIconButton(
            onClick = onClick,
            modifier = modifier,
            enabled = enabled,
            content = content,
        )
    }

    @Composable
    override fun OutlinedTextField(
        value: String,
        onValueChange: (String) -> Unit,
        modifier: Modifier,
        label: (@Composable () -> Unit)?,
        leadingIcon: (@Composable () -> Unit)?,
        singleLine: Boolean,
        isError: Boolean,
        supportingText: (@Composable () -> Unit)?,
    ) {
        StyledInput(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier,
            singleLine = singleLine,
            isError = isError,
            label = label,
            leadingIcon = leadingIcon,
            supportingText = supportingText,
        )
    }

    @Composable
    override fun InputChip(
        selected: Boolean,
        onClick: () -> Unit,
        label: @Composable () -> Unit,
        trailingIcon: (@Composable () -> Unit)?,
    ) {
        val styledTrailingIcon: StyledIconContent? = trailingIcon?.let { icon ->
            { _, modifier, _ ->
                Box(modifier) {
                    icon()
                }
            }
        }
        StyledSelectableChip(
            selected = selected,
            onClick = onClick,
            trailingIcon = styledTrailingIcon,
            content = label,
        )
    }

    @Composable
    override fun DropdownMenu(
        expanded: Boolean,
        onDismissRequest: () -> Unit,
        content: @Composable ColumnScope.() -> Unit,
    ) {
        StyledPopupContainer(
            popup = {
                StyledPopupMenu(
                    expanded = expanded,
                    onDismissRequest = onDismissRequest,
                    widthConfig = StyledPopupMenu.WidthConfig.WrapContent,
                    content = content,
                )
            },
            content = {
                Spacer(Modifier.width(1.dp).height(1.dp))
            },
        )
    }

    @Composable
    override fun DropdownMenuItem(
        text: @Composable () -> Unit,
        onClick: () -> Unit,
        leadingIcon: (@Composable () -> Unit)?,
        trailingIcon: (@Composable () -> Unit)?,
    ) {
        StyledDropdownMenuItem(
            text = text,
            onClick = onClick,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
        )
    }

    @Composable
    override fun AlertDialog(
        onDismissRequest: () -> Unit,
        title: (@Composable () -> Unit)?,
        text: (@Composable () -> Unit)?,
        confirmButton: @Composable () -> Unit,
        dismissButton: (@Composable () -> Unit)?,
    ) {
        val state = rememberDialogState(initialVisible = true)
        StyledDialog(
            state = state,
            onDismissRequest = {
                state.dismiss()
                onDismissRequest()
            },
            title = title,
            content = { padding ->
                Column(
                    modifier = Modifier.fillMaxWidth().padding(padding),
                    verticalArrangement = Arrangement.spacedBy(StyledTheme.spacing.medium),
                ) {
                    text?.invoke()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(
                            StyledTheme.spacing.small,
                            Alignment.End,
                        ),
                    ) {
                        dismissButton?.invoke()
                        confirmButton()
                    }
                }
            },
        )
    }

    @Composable
    override fun Checkbox(checked: Boolean, onCheckedChange: ((Boolean) -> Unit)?) {
        StyledCheckbox(checked = checked, onCheckedChange = onCheckedChange)
    }

    @Composable
    override fun RadioButton(selected: Boolean, onClick: (() -> Unit)?) {
        StyledRadioButton(selected = selected, onClick = onClick)
    }
}

private val TableIcon.imageVector: ImageVector
    get() = when (this) {
        TableIcon.Search -> Icons.Default.Search
        TableIcon.Clear -> Icons.Default.Clear
        TableIcon.ArrowUpward -> Icons.Default.ArrowUpward
        TableIcon.ArrowDownward -> Icons.Default.ArrowDownward
        TableIcon.FilterAlt -> Icons.Default.FilterAlt
        TableIcon.Check -> Icons.Default.Check
        TableIcon.SelectAll -> Icons.Default.SelectAll
        TableIcon.Deselect -> Icons.Default.Deselect
        TableIcon.FirstPage -> Icons.Default.FirstPage
        TableIcon.ChevronLeft -> Icons.Default.ChevronLeft
        TableIcon.ChevronRight -> Icons.Default.ChevronRight
        TableIcon.LastPage -> Icons.Default.LastPage
        TableIcon.Refresh -> Icons.Default.Refresh
    }
