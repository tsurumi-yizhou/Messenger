package me.yihtseu.messenger.one

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.yihtseu.messenger.one.theme.samsungSans


data class TabNavBarColors(
    val containerColor: Color,
    val contentColor: Color,
    val labelColor: Color,
    val selectedLabelColor: Color,
)

object TabBarDefaults {
    val barPadding = PaddingValues(
        top = 10.dp, bottom = 25.dp, start = 20.dp, end = 20.dp
    )
    val indicatorPadding = PaddingValues(
        bottom = 4.dp, top = 1.dp
    )
    val indicatorHeight = 3.dp
    val itemShape = RoundedCornerShape(26.dp)
    val itemTextStyle = TextStyle(
        fontSize = 14.sp,
        fontFamily = samsungSans
    )
    val itemPadding = PaddingValues(top = 12.dp)
    val customPadding = PaddingValues(
        top = 10.dp, bottom = 6.dp
    )

    @Composable
    fun tanNavBarColors(): TabNavBarColors {
        return TabNavBarColors(
            containerColor = colorResource(R.color.sesl_navigation_bar_background_light),
            contentColor = colorResource(R.color.sesl_navigation_bar_text_light),
            labelColor = MaterialTheme.colorScheme.outline,
            selectedLabelColor = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabNavBar(
    action: (() -> Unit)? = null,
    colors: TabNavBarColors = TabBarDefaults.tanNavBarColors(),
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = colors.containerColor
    ) {
        Row(
            modifier = Modifier
                .padding(TabBarDefaults.barPadding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            content()

            action?.let {
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .clip(TabBarDefaults.itemShape)
                        .clickable(onClick = action),
                    color = colors.containerColor
                ) {
                    Row(
                        modifier = Modifier
                            .clip(TabBarDefaults.itemShape)
                            .padding(TabBarDefaults.customPadding)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.one_drawer),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = colors.contentColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RowScope.TabNavItem(
    selected: Boolean,
    text: String,
    colors: TabNavBarColors = TabBarDefaults.tanNavBarColors(),
    onClick: () -> Unit
) {
    Surface(
        shape = TabBarDefaults.itemShape,
        modifier = Modifier
            .weight(1f)
            .clip(TabBarDefaults.itemShape)
            .clickable(
                role = Role.Tab,
                onClick = onClick
            ),
        color = colors.containerColor
    ) {
        Column(
            modifier = Modifier
                .clip(TabBarDefaults.itemShape)
                .wrapContentWidth()
                .width(IntrinsicSize.Max),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = text,
                color = if (selected) colors.selectedLabelColor else colors.labelColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(TabBarDefaults.itemPadding)
                    .fillMaxWidth(),
                style = TabBarDefaults.itemTextStyle
            )
            if (selected) {
                HorizontalDivider(
                    modifier = Modifier
                        .padding(TabBarDefaults.indicatorPadding)
                        .fillMaxWidth(),
                    thickness = TabBarDefaults.indicatorHeight,
                    color = colors.selectedLabelColor
                )
            } else {
                Spacer(
                    modifier = Modifier
                        .padding(TabBarDefaults.indicatorPadding)
                        .height(TabBarDefaults.indicatorHeight)
                        .fillMaxWidth()
                )
            }
        }
    }
}