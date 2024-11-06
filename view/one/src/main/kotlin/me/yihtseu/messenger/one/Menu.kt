package me.yihtseu.messenger.one

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import me.yihtseu.messenger.one.theme.samsungSans

object MenuDefaults {
    val menuTextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = samsungSans
    )
    val itemPadding = PaddingValues(
        top = 13.dp,
        end = 24.dp,
        bottom = 13.dp,
        start = 24.dp
    )
    val menuShape = RoundedCornerShape(26.dp)
    val menuElevation = 5.dp
    val menuMargin = 16.dp
    val menuOffset = IntOffset(37, -37)

    @Composable
    fun menuColors(): CardColors {
        return CardColors(
            containerColor = colorResource(R.color.sesl_popup_menu_blur_background_light),
            contentColor = colorResource(R.color.sesl_popup_menu_item_text_color_normal_light),
            disabledContainerColor = colorResource(R.color.sesl_popup_menu_blur_background_light),
            disabledContentColor = colorResource(R.color.sesl_popup_menu_item_text_color_disabled_light)
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Menu(
    visible: Boolean,
    onVisibleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    offset: IntOffset = MenuDefaults.menuOffset,
    colors: CardColors = MenuDefaults.menuColors(),
    content: @Composable ColumnScope.() -> Unit
) {
    AnimatedVisibility(visible) {
        Popup(
            offset = offset,
            onDismissRequest = { onVisibleChange(!visible) },
            properties = PopupProperties(
                focusable = true
            ),
        ) {
            Card(
                modifier = modifier
                    .padding(MenuDefaults.menuMargin)
                    .width(IntrinsicSize.Max)
                    .shadow(
                        elevation = MenuDefaults.menuElevation,
                        shape = MenuDefaults.menuShape,
                        clip = true
                    ),
                shape = MenuDefaults.menuShape,
                colors = colors,
                content = content
            )
        }
    }
}

@Composable
fun ColumnScope.MenuItem(
    text: String,
    action: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = action),
    ) {
        Text(
            text = text,
            style = MenuDefaults.menuTextStyle,
            modifier = Modifier
                .padding(MenuDefaults.itemPadding)
        )
    }
}