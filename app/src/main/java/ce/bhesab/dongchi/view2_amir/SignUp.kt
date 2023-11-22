package ce.bhesab.dongchi.view2_amir
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.MailOutline
import androidx.compose.material3.icons.filled.Visibility
import androidx.compose.material3.icons.filled.VisibilityOff
import androidx.compose.material3.textfield.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardControllerProvider
import androidx.compose.ui.platform.LocalSoftwareKeyboardControllerProviderKt
import androidx.compose.ui.platform.LocalSoftwareKeyboardControllerProviderKt.LocalSoftwareKeyboardControllerProvider
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class SignUp {
    @Composable
    fun SignUpScreen() {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isPasswordVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.h4
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Email") },
                leadingIcon = {
                    Icon(Icons.Default.MailOutline, contentDescription = null)
                }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                label = { Text("Password") },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                trailingIcon = {
                    val icon = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                    IconButton(
                        onClick = { isPasswordVisible = !isPasswordVisible }
                    ) {
                        Icon(icon, contentDescription = null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Handle sign-up logic here */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Already have an account? ")
                Text(
                    "Log In",
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* Handle navigation to login screen */ }
                )
            }
        }
    }

    @Composable
    fun SignUpPage() {
        val context = LocalContext.current
        val keyboardController = LocalSoftwareKeyboardControllerProviderKt.LocalSoftwareKeyboardControllerProvider().invoke(context)

        // Set up the content of your signup page here
        // For example, you can use the SignUpScreen composable function

        // Use LocalSoftwareKeyboardController to show or hide the keyboard when needed
        // keyboardController?.hide() or keyboardController?.show()
    }

    @Composable
    fun SplitwiseSignUpPage() {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            SignUpPage()
        }
    }

}