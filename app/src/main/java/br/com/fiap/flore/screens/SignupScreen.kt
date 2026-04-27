package br.com.fiap.flore.screens

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.flore.R
import br.com.fiap.flore.model.User
import br.com.fiap.flore.navigation.Destination
import br.com.fiap.flore.repository.SharedPrefrencesUserRepository
import br.com.fiap.flore.ui.theme.FloreTheme

@Composable
fun SignupScreen(navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colorScheme.background
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(alignment = Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TitleComponent()
            Spacer(modifier = Modifier.height(48.dp))
            UserImage()
            SignupUserForm(navController)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SignupScreenPreview() {
    FloreTheme() {
        SignupScreen(rememberNavController())
    }
}

@Composable
fun TitleComponent(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.registrar_se),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.height(48.dp),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.crie_uma_nova_conta),
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleComponentPreview() {
    FloreTheme{
        TitleComponent()
    }
}

@Composable
fun UserImage(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .size(120.dp)
    ){
        Image(
            painter = painterResource(R.drawable.profile),
            contentDescription = stringResource(R.string.profile_image),
            modifier = Modifier
                .size(100.dp)
                .align(alignment = Alignment.Center)
        )
        Icon(
            imageVector = Icons.Default.AddAPhoto,
            contentDescription = stringResource(R.string.camera_icon),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileImagePreview() {
    FloreTheme() {
        UserImage()
    }
}

@Composable
fun SignupUserForm( navController: NavController) {

    var name by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    //variaveis de estado p ver se os dados estao corretos
    var isNameError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }
    var isPasswordError by remember { mutableStateOf(false) }

    //variavel de estado p controlar a exibição da msg de erro
    var showDialogError by remember {mutableStateOf(false)}
    var showDialogSuccess by remember {mutableStateOf(false)}

    fun validate(): Boolean{
        isNameError = name.length < 3
        isEmailError = email.length < 3 || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
        isPasswordError = password.length < 3
        return !isNameError && !isEmailError && !isPasswordError
    }


    val userRepository = SharedPrefrencesUserRepository(LocalContext.current)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.digite_o_seu_nome))
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = stringResource(R.string.person_icon),
                    tint = MaterialTheme.colorScheme.tertiary

                )
            },
            isError = isNameError,
            trailingIcon = {
                if (isNameError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isNameError){
                    Text(
                        text = "Nome deve conter no mínimo 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )
        //Caixa de texto para email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.digite_seu_e_mail))
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Mail,
                    contentDescription = stringResource(R.string.email_icon),
                    tint = MaterialTheme.colorScheme.tertiary

                )
            },
            isError = isEmailError,
            trailingIcon = {
                if (isEmailError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isEmailError){
                    Text(
                        text = "E-mail deve conter no mínimo 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }
        )
        //Caixa de texto para senha
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier
                .fillMaxWidth(),
            label = {
                Text(text = stringResource(R.string.digite_sua_senha))
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults
                .colors(
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.primary
                ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = stringResource(R.string.lock_icon),
                    tint = MaterialTheme.colorScheme.tertiary
                )
            },
            isError = isPasswordError,
            trailingIcon = {
                if (isPasswordError){
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            supportingText = {
                if (isPasswordError){
                    Text(
                        text = "Senha deve conter no mínimo 3 caracteres",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End
                    )
                }
            }

        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = {
                if (validate()){
                    userRepository.saveUser(
                        User(
                            name = name,
                            email = email,
                            password = password
                        )
                    )
                    showDialogSuccess = true
                }else{
                    showDialogError = true
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = stringResource(R.string.crie_uma_nova_conta)
            )
        }
    }
    //caixa de dialogo de sucesso
    if (showDialogSuccess){
        AlertDialog(
            onDismissRequest = { showDialogError = false},
            title = {
                Text(text = "Sucesso")
            },
            text = {
                Text(text = "Conta criada com sucesso")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogSuccess = false
                        navController.navigate(Destination.LoginScreen.route)}
                ) {Text(text = "OK") }
            }
        )
    }

    //caixa de dialogo de erro
    if (showDialogError){
        AlertDialog(
            onDismissRequest = {showDialogError = false},
            title = {
                Text(text = "Erro")
            },
            text = {
                Text(text = "Por favor, preencha todos os campos corretamente")
                   },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialogError = false
                    })
                {
            Text(text = "OK")
        }
    }
        )
}
}

@Preview(showBackground = true)
@Composable
private fun SignupUserFormPreview() {
    FloreTheme() {
        SignupUserForm(rememberNavController())
    }
}