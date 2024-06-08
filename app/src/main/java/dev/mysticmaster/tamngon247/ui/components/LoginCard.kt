package dev.mysticmaster.tamngon247.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.mysticmaster.tamngon247.ui.theme.BackgroundColor
import dev.mysticmaster.tamngon247.util.Route
import dev.mysticmaster.tamngon247.viewmodel.LoginViewModel

@Composable
fun LoginCard(
    navController: NavController,
    loginViewModel: LoginViewModel,
    nextRoute: Route,
    nowRoute: Route
) {
    val snackbarHostState = remember { SnackbarHostState() }
    HandleLoginState(snackbarHostState, loginViewModel, navController, nextRoute, nowRoute)
    LoginForm(loginViewModel)
}

@Composable
private fun HandleLoginState(
    snackbarHostState: SnackbarHostState,
    loginViewModel: LoginViewModel,
    navController: NavController,
    nextRoute: Route,
    nowRoute: Route
) {
    val isAuthenticated by loginViewModel.isAuthenticated.observeAsState()
    LaunchedEffect(key1 = isAuthenticated) {
        when (isAuthenticated) {
            true -> {
                navController.navigate(nextRoute.screen) {
                    popUpTo(nowRoute.screen) { inclusive = true }
                }
            }

            false -> {
                snackbarHostState.showSnackbar(
                    message = "Invalid username or password.",
                    duration = SnackbarDuration.Short,
                )
                loginViewModel.resetAuthenticationState()
            }

            null -> {}
        }
    }
}

@Composable
private fun LoginForm(
    loginViewModel: LoginViewModel,
) {
    val usernameState by loginViewModel.username.observeAsState("")
    val rememberMeState by loginViewModel.rememberMe.observeAsState(false)
    var username by remember { mutableStateOf(usernameState) }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(rememberMeState) }
    val isLoginEnabled = username.isNotBlank() && password.isNotBlank()
    LaunchedEffect(usernameState, rememberMeState) {
        username = usernameState
        rememberMe = rememberMeState
        Log.d("PAM", "LoginForm: username $usernameState rememberMeState $rememberMeState")
    }

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            UsernameField(username, onUsernameChange = { username = it })
            Spacer(modifier = Modifier.height(5.dp))
            PasswordField(password, onPasswordChange = { password = it })
            RememberMeSwitch(rememberMe) { isChecked -> rememberMe = isChecked }
            Spacer(modifier = Modifier.height(16.dp))
            LoginButton(isLoginEnabled) {
                loginViewModel.login(
                    username,
                    password,
                    rememberMe
                )
            }
        }
    }

}

@Composable
private fun UsernameField(username: String, onUsernameChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = username,
        onValueChange = onUsernameChange,
        label = {
            Text(
                "Tên đăng nhập",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic
            )
        }
    )
}

@Composable
private fun PasswordField(password: String, onPasswordChange: (String) -> Unit) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = password,
        onValueChange = onPasswordChange,
        label = {
            Text(
                "Mật khẩu",
                fontSize = 13.sp,
                fontStyle = FontStyle.Italic
            )
        },
        visualTransformation = PasswordVisualTransformation()
    )
}

@Composable
private fun LoginButton(isEnabled: Boolean, onLoginClick: () -> Unit) {
    Button(
        onClick = onLoginClick,
        enabled = isEnabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) Color.Black else Color.LightGray,
            contentColor = Color.Blue
        ),
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(40.dp)
    ) {
        Text("Đăng Nhập", fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
private fun RememberMeSwitch(rememberMe: Boolean, onCheckedChange: (Boolean) -> Unit) {
    var isChecked by remember { mutableStateOf(rememberMe) }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Switch(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onCheckedChange(it)
            },
            modifier = Modifier
                .scale(0.75f)
                .padding(0.dp),
        )
        Text(
            "Lưu tài khoản", modifier = Modifier.padding(start = 5.dp),
            color = Color.Black
        )
    }
}