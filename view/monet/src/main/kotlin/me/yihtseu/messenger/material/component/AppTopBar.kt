package me.yihtseu.messenger.material.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
@ExperimentalMaterial3Api
fun AppTopBar(
    navigation: @Composable () -> Unit,
    search: @Composable ColumnScope.(String) -> Unit,
    menu: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var input by rememberSaveable { mutableStateOf("") }
    var query by rememberSaveable { mutableStateOf("") }

    Surface {
        SearchBar(
            inputField = {
                SearchBarDefaults.InputField(
                    query = input,
                    onQueryChange = {
                        input = it
                    },
                    expanded = input.isNotEmpty(),
                    onExpandedChange = {
                        expanded = it
                    },
                    onSearch = {
                        query = it
                    },
                    leadingIcon = {
                        AnimatedContent(
                            targetState = expanded,
                            label = "search bar nav icon animation"
                        ) { target ->
                            if (target) {
                                IconButton(
                                    onClick = {
                                        expanded = false
                                        input = ""
                                        query = ""
                                    }
                                ) {
                                    Icon(Icons.Outlined.ArrowBackIosNew, null)
                                }
                            } else {
                                navigation()
                            }
                        }
                    },
                    trailingIcon = {
                        AnimatedVisibility(
                            visible = !expanded
                        ) {
                            menu()
                        }
                    },
                    modifier = modifier
                        .fillMaxWidth()
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it }
        ) {
            search(query)
        }
    }
}