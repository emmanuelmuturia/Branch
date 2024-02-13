package branch.uilayer.login

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import branch.authentication.uilayer.R
import branch.commons.components.BranchBackgroundImage
import branch.commons.theme.BranchLightBlue

@Composable
fun LoginScreen() {

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

                LoginTextFields()

                LoginButton()

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
fun LoginTextFields() {

    var userName by rememberSaveable { mutableStateOf(value = "") }

    var password by rememberSaveable { mutableStateOf(value = "") }

    OutlinedTextField(
        value = userName,
        onValueChange = { userName = it },
        label = {},
        shape = RoundedCornerShape(size = 21.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
    )

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        label = {},
        shape = RoundedCornerShape(size = 21.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )

}


@Composable
fun LoginButton() {

    Button(
        onClick = { },
        shape = RoundedCornerShape(size = 21.dp),
        colors = ButtonDefaults.buttonColors(containerColor = BranchLightBlue)
    ) {

        Text(text = "Login", style = MaterialTheme.typography.bodyLarge)

    }

}