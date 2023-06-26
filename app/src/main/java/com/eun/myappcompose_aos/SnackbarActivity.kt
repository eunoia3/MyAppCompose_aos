package com.eun.myappcompose_aos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
class SnackbarActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var (text, setValue) = remember {
                mutableStateOf("")
            }
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current

            Scaffold(
                scaffoldState = scaffoldState
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextField(value = text, onValueChange = setValue )
                    Button(onClick = {
                        keyboardController?.hide()
                        scope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello -$text ")
                        }
                    }) {
                        Text(text = "show Snackbar")
                    }
                }

            }

        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun SnackbarLayoutPreview() {

    var (text, setValue) = remember {
        mutableStateOf("")
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        scaffoldState = scaffoldState
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TextField(value = text, onValueChange = setValue )
            Button(onClick = {
                keyboardController?.hide()
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar("Hello -$text ")
                }
            }) {
                Text(text = "show Snackbar")
            }
        }

    }

}
