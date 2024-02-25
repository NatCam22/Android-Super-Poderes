package com.example.androidsp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidsp.R

@Composable
fun LoginScreen(){
    Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.size(200.dp))
        //Primer componente>  Imagen
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Login image",
            modifier = Modifier
                .clip(shape = CircleShape)
                .border(shape = CircleShape, width = 2.dp, color = Color.Black)
                .background(color = Color.Green))
        //Segundo componente> Titulo
        Text(text = "Android Superpoderes", style = MaterialTheme.typography.headlineMedium)
        //Tercer componente> TextField> Reusable

        TextFieldReusable("", Icons.Default.Email, null, "Email")
        TextFieldReusable("", Icons.Default.Face, Icons.Filled.Lock, "Password")

        //Cuarto componente Boton
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Iniciar Sesión")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldReusable(value: String, leadingIcon: ImageVector, trailingIcon: ImageVector?, label: String){
    TextField(
        value = value,
        onValueChange ={},
        label = {
            Text(label)
        },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = "Custom Icon")
        },
        trailingIcon = {
            trailingIcon?.let { Icon(imageVector = trailingIcon, contentDescription = "Custom Icon") }
        },)
}

@Preview(showSystemUi = true)
@Composable
private fun LoginScreen_Preview(){
    LoginScreen()
}