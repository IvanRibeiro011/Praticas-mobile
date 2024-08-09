package com.weatherapp


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.ui.theme.Purple40

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPage();
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LoginPage(modifier: Modifier = Modifier) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    val activity = LocalContext.current as? Activity

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = CenterHorizontally,
        ) {
            Text(
                text = "Bem-vindo/a!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedTextField(
                value = email,
                label = { Text(text = "Digite seu e-mail") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { email = it }
            )
            OutlinedTextField(
                value = password,
                label = { Text(text = "Digite sua senha") },
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { password = it },
                visualTransformation = PasswordVisualTransformation()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = modifier) {
                Button(
                    onClick = {
                        Toast.makeText(activity, "Login OK!", Toast.LENGTH_LONG).show()
                    },
                    enabled = email.isNotEmpty() && password.isNotEmpty()
                ) {
                    Text("Login")
                }
                Spacer(modifier = Modifier.size(24.dp))
                Button(
                    onClick = { email = ""; password = "" }
                ) {
                    Text("Limpar")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            val annotatedString = buildAnnotatedString { append("Não tem uma conta? ")
                pushStringAnnotation(tag = "register", annotation = "register")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("Registre-se")
                }
                pop() }
            Text(
                text = annotatedString,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        activity?.let {
                            val intent = Intent(it, RegisterActivity::class.java)
                            it.startActivity(intent)
                        }
                    }

            )
        }
    }
}