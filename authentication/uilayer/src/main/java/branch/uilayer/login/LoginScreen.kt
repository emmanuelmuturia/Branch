package branch.uilayer.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import branch.authentication.uilayer.R
import branch.commons.components.BranchBackgroundImage
import branch.commons.theme.BranchDarkBlue
import branch.commons.theme.BranchLightBlue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navigateToMessagesScreen: () -> Unit
) {

    var username by rememberSaveable { mutableStateOf(value = "") }

    var password by rememberSaveable { mutableStateOf(value = "") }

    val loginScreenViewModel: LoginScreenViewModel = hiltViewModel()

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 21.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                LoginScreenLogo()
            }

            item {
                LoginTextFields(
                    username = username,
                    onUsernameChanged = { username = it },
                    password = password,
                    onPasswordChanged = { password = it }
                )
            }

            item {
                Spacer(modifier = Modifier.height(height = 14.dp))
            }

            item {
                LoginButton(
                    username = username,
                    password = password,
                    loginScreenViewModel = loginScreenViewModel,
                    navigateToMessagesScreen = navigateToMessagesScreen
                )
            }

        }

    }

}


@Composable
fun LoginScreenLogo() {

    Image(
        modifier = Modifier.size(size = 140.dp),
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Login Screen Logo",
        contentScale = ContentScale.Crop
    )

}


@Composable
fun LoginTextFields(
    username: String,
    password: String,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
) {

    OutlinedTextField(
        value = username,
        onValueChange = { onUsernameChanged(it) },
        label = {
            Text(text = "Enter username", style = MaterialTheme.typography.bodyLarge, color = BranchDarkBlue)
        },
        shape = RoundedCornerShape(size = 21.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        )
    )

    Spacer(modifier = Modifier.height(height = 14.dp))

    OutlinedTextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        label = {
            Text(text = "Enter password", style = MaterialTheme.typography.bodyLarge, color = BranchDarkBlue)
        },
        shape = RoundedCornerShape(size = 21.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent
        ),
        visualTransformation = PasswordVisualTransformation()
    )

}


@Composable
fun LoginButton(
    username: String,
    password: String,
    loginScreenViewModel: LoginScreenViewModel,
    navigateToMessagesScreen: () -> Unit
) {

    val context = LocalContext.current

    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {

                if (password == username.reversed()) {
                    loginScreenViewModel.login(username = username, password = password)
                    navigateToMessagesScreen()
                } else {
                    Toast.makeText(context, "Wrong Credentials!!!", Toast.LENGTH_LONG).show()
                }

            }
        },
        shape = RoundedCornerShape(size = 21.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BranchDarkBlue)
    ) {
        Text(text = "Login", style = MaterialTheme.typography.bodyLarge)
    }

}