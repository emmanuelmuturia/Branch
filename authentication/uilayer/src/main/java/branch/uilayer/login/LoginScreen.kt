package branch.uilayer.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import branch.authentication.uilayer.R
import branch.commons.components.BranchBackgroundImage
import branch.commons.theme.BranchLightBlue

@Composable
fun LoginScreen(
    navigateToMessagesScreen: () -> Unit
) {

    var username by rememberSaveable { mutableStateOf(value = "") }

    var password by rememberSaveable { mutableStateOf(value = "") }

    val loginScreenViewModel: LoginScreenViewModel = hiltViewModel()

    Box(modifier = Modifier.fillMaxSize()) {

        BranchBackgroundImage()

        Column(modifier = Modifier.fillMaxSize()) {

            LoginScreenLogo()

            Spacer(modifier = Modifier.weight(weight = 1f))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 21.dp),
                verticalArrangement = Arrangement.spacedBy(space = 7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                LoginTextFields(
                    username = username,
                    onUsernameChanged = { username = it },
                    password = password,
                    onPasswordChanged = { password = it }
                )

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
        modifier = Modifier.padding(top = 21.dp),
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
        label = {},
        shape = RoundedCornerShape(size = 21.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )

    OutlinedTextField(
        value = password,
        onValueChange = { onPasswordChanged(it) },
        label = {},
        shape = RoundedCornerShape(size = 21.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

    val isLoginSuccessful = loginScreenViewModel.isLoginSuccessful

    val context = LocalContext.current

    Button(
        onClick = {
            loginScreenViewModel.login(username = username, password = password)
            if (isLoginSuccessful) navigateToMessagesScreen() else Toast.makeText(
                context,
                "Wrong Credentials!!!",
                Toast.LENGTH_LONG
            ).show()
        },
        shape = RoundedCornerShape(size = 21.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BranchLightBlue)
    ) {

        Text(text = "Login", style = MaterialTheme.typography.bodyLarge)

    }

}