package com.example.journalaibuddy.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Username") }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Button(onClick = { handleLogin(username, password, onLoginSuccess) }) {
            Text("Sign In")
        }
        Button(onClick = { handleSignUp(username, password, onLoginSuccess) }) {
            Text("Sign Up")
        }
    }
}

private fun handleLogin(username: String, password: String, onLoginSuccess: () -> Unit) {
    FirebaseAuth.getInstance().signInWithEmailAndPassword(username, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onLoginSuccess()
            } else {
                // Handle errors
            }
        }
}

private fun handleSignUp(username: String, password: String, onLoginSuccess: () -> Unit) {
    FirebaseAuth.getInstance().createUserWithEmailAndPassword(username, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onLoginSuccess()
            } else {
                // Handle errors
            }
        }
}
