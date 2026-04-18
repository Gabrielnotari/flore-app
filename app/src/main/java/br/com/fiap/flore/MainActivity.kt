package br.com.fiap.flore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.flore.R.drawable
import br.com.fiap.flore.ui.theme.FloreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FloreTheme {

            }
        }
    }
}

@Composable
fun InitialScreen(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
    ){
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .background(color = Color.Green)
                .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally

        ){
            Image(
                painter = painterResource(R.drawable.logo_flore),
                contentDescription = "Logo Flore",
                modifier = Modifier
                    .size(190.dp)
            )
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(200.dp)
                    .background(color = Color.Gray)
            ) {
                Text(
                    text = "Bem-vindo a Florê",
                    fontSize = 16.sp,
                    color = Color(0xFF860000),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Comece a Anunciar",
                    fontSize = 64.sp,
                    lineHeight = 65.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFCB8080)
                )
                Row{
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = Color(0xFF73E113)
                            ),
                        border = BorderStroke(
                            width = 1.dp,
                            color = Color(0xFFFFC107)
                        ),
                        modifier = Modifier
                            .size(width = 128.dp, height = 48.dp)
                    ) {
                        Text(
                            text = "Entrar",
                            color = Color(0xFFFFFFFF),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults
                            .buttonColors(
                                containerColor = Color(0xFFFFC107)
                            ),
                        border = BorderStroke(
                            width = 1.dp, color = Color(0xFF52EA97)
                        ),
                        modifier = Modifier
                            .size(width = 128.dp, height = 48.dp)
                    ) {
                        Text(
                            text = "Registrar",
                            color = Color(0xFFFFFFFF),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun InitialScreenPreview() {
    FloreTheme {
        InitialScreen()
    }
}

