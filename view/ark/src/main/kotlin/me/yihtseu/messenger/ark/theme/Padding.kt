package me.yihtseu.messenger.ark.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Paddings(
    val contentHorizontalPadding: Dp,
    val contentVerticalPadding: Dp,
    val textHorizontalMargin: Dp,
    val textVerticalMargin: Dp,
    val paragraphExtraLargeMargin: Dp,
    val paragraphLargeMargin: Dp,
    val paragraphMediumMargin: Dp,
    val paragraphSmallMargin: Dp,
    val paragraphExtraSmallMargin: Dp,
    val cardMargin: Dp,
    val elementLargeHorizontalPadding: Dp,
    val elementLargeVerticalPadding: Dp,
    val elementMediumHorizontalPadding: Dp,
    val elementMediumVerticalPadding: Dp
)

val harmonyPadding = Paddings(
    contentHorizontalPadding = 12.dp,
    contentVerticalPadding = 24.dp,
    textHorizontalMargin = 8.dp,
    textVerticalMargin = 2.dp,
    paragraphExtraLargeMargin = 48.dp,
    paragraphLargeMargin = 24.dp,
    paragraphMediumMargin = 16.dp,
    paragraphSmallMargin = 8.dp,
    paragraphExtraSmallMargin = 4.dp,
    cardMargin = 12.dp,
    elementLargeVerticalPadding = 16.dp,
    elementLargeHorizontalPadding = 16.dp,
    elementMediumVerticalPadding = 8.dp,
    elementMediumHorizontalPadding = 8.dp
)