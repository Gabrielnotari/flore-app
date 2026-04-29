package br.com.fiap.flore.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.flore.R
import br.com.fiap.flore.navigation.Destination
import br.com.fiap.flore.repository.RoomUserRepository
import br.com.fiap.flore.repository.UserRepository
import br.com.fiap.flore.ui.theme.FloreTheme

@Composable
fun LoginScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ){
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
                .align(alignment = Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_flore),
                contentDescription = "Logo Flore",
                modifier = Modifier
                    .size(190.dp)
            )
            Spacer(modifier = Modifier.height(80.dp))
            LoginTitle()
            Spacer(modifier = Modifier.height(32.dp))
            LoginForm(navController)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    FloreTheme() {
        LoginScreen(rememberNavController())
    }
}

@Composable
fun LoginTitle(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.height(48.dp),
            text = stringResource(R.string.login),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.por_favor_fa_a_o_login_para_continuar),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
@Composable
private fun LoginTitlePreview() {
    FloreTheme() {
        LoginTitle()
    }
}

@Composable
fun LoginForm(navController: NavController) {

    var email by remember{
        mutableStateOf("")
    }

    var password by remember{
        mutableStateOf("")
    }

    var showPassword by remember {
        mutableStateOf(false)
    }

    var authenticateError by remember {
        mutableStateOf(false)
    }

    // Criar uma instância da classe SharedPreferencesUserRepository
    val userRepository: UserRepository =
        RoomUserRepository(LocalContext.current)


    Column() {
        OutlinedTextField(
            value = email,
            onValueChange = {emailValue ->
                email = emailValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            label = {
                Text(
                    text = stringResource(R.string.seu_e_mail)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "E-mail icon",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        OutlinedTextField(
            value = password,
            onValueChange = {passwordValue ->
                password = passwordValue
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            label = {
                Text(
                    text = stringResource(R.string.sua_senha)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "E-mail icon",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            trailingIcon = {
                val image = if (showPassword) {
                    Icons.Default.Visibility
                } else {
                    Icons.Default.VisibilityOff
                }
                IconButton(
                    onClick = {showPassword = !showPassword}
                ) {
                    Icon(
                        imageVector = image,
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.tertiary
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (showPassword) {
                VisualTransformation.None
            }
            else {
                PasswordVisualTransformation()
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                val authenticate = userRepository.login(email, password)
                if(authenticate){
                    navController
                        .navigate(Destination.HomeScreen.createRoute(email))
                }else{
                    authenticateError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ){
            Text(
                text = stringResource(R.string.entrar)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (authenticateError){
            Row {
                Icon(
                    imageVector = Icons.Default.Error,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Erro de autenticação!",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = stringResource(R.string.n_o_possui_uma_conta),
                color = MaterialTheme.colorScheme.primary
            )
            TextButton(
                onClick = {
                    navController.navigate(Destination.SignupScreen.route)
                }
            ) {
                Text(
                    text = stringResource(R.string.cadastre_se),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginFormScreen() {
    FloreTheme() {
        LoginForm(rememberNavController())
    }
}