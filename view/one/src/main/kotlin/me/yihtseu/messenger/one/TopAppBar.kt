package me.yihtseu.messenger.one

import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.windowInsets
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.yihtseu.messenger.one.theme.samsungSans


@OptIn(ExperimentalMaterial3Api::class)
object AppTopBarDefaults {
    val maxHeight = 320f.dp
    val minHeight = 84f.dp
    val expandedTextStyle = TextStyle(
        fontSize = 34.sp,
        fontWeight = FontWeight.Light,
        fontFamily = samsungSans
    )
    val collapsedTextStyle = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = samsungSans
    )
    val collapsedPadding = PaddingValues(start = 25.dp)

    @Composable
    fun appTopBarColors(): TopAppBarColors {
        return TopAppBarColors(
            containerColor = colorResource(R.color.sesl_navigation_bar_background_light),
            scrolledContainerColor = colorResource(R.color.sesl_navigation_bar_background_light),
            navigationIconContentColor = colorResource(R.color.sesl_navigation_bar_text_light),
            titleContentColor = colorResource(R.color.sesl_navigation_bar_icon_light),
            actionIconContentColor = colorResource(R.color.sesl_navigation_bar_icon_light)
        )
    }
}

@Composable
@ExperimentalMaterial3Api
fun AppTopBar(
    title: String,
    subtitle: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    colors: TopAppBarColors = AppTopBarDefaults.appTopBarColors(),
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(),
) {
    SideEffect {
        scrollBehavior.state.heightOffsetLimit = -AppTopBarDefaults.maxHeight.value
    }

    val animatedHeight by animateDpAsState(
        targetValue = (AppTopBarDefaults.maxHeight.value + scrollBehavior.state.heightOffset)
            .coerceAtLeast(AppTopBarDefaults.minHeight.value).dp,
        label = "Collapsed Animated TopBar",
        animationSpec = tween(
            easing = CubicBezierEasing(0.22f, 0.25f, 0.00f, 1.00f)
        )
    )

    Surface(
        color = colors.containerColor
    ) {
        Column(
            modifier = Modifier
                .windowInsetsPadding(windowInsets)
                .clipToBounds()
                .height(animatedHeight)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = scrollBehavior.state.heightOffset > scrollBehavior.state.heightOffsetLimit * 0.4,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(animatedHeight - AppTopBarDefaults.minHeight),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(AppTopBarDefaults.minHeight / 2))
                    Text(
                        text = title,
                        style = AppTopBarDefaults.expandedTextStyle,
                        modifier = Modifier.padding(),
                        color = colors.titleContentColor
                    )
                    CompositionLocalProvider(
                        LocalContentColor provides colors.titleContentColor
                    ) {
                        subtitle()
                    }
                }
            }
            Spacer(modifier = Modifier.fillMaxHeight())
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTopBarDefaults.minHeight),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.navigationIconContentColor
                    ) {
                        navigationIcon()
                    }
                    AnimatedVisibility(
                        visible = scrollBehavior.state.heightOffset < scrollBehavior.state.heightOffsetLimit * 0.6,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        Text(
                            text = title,
                            style = AppTopBarDefaults.collapsedTextStyle,
                            modifier = Modifier.padding(AppTopBarDefaults.collapsedPadding),
                            color = colors.titleContentColor
                        )
                    }
                }
                CompositionLocalProvider(
                    LocalContentColor provides colors.actionIconContentColor
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End,
                        content = actions
                    )
                }
            }
        }
    }
}