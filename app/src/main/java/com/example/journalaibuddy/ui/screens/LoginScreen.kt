package com.example.journalaibuddy.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isLogin by remember { mutableStateOf(true) }
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = "Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (!isLogin) {
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(onClick = {
            if (isLogin) {
                signIn(auth, username, password, onLoginSuccess)
            } else {
                signUp(auth, username, password, confirmPassword, onLoginSuccess)
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text(if (isLogin) "Sign In" else "Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { isLogin = !isLogin }, modifier = Modifier.fillMaxWidth()) {
            Text(if (isLogin) "Switch to Sign Up" else "Switch to Sign In")
        }
    }
}

private fun signIn(auth: FirebaseAuth, email: String, password: String, onLoginSuccess: () -> Unit) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onLoginSuccess()
            } else {
                Log.e("LoginScreen", "SignIn failed: ${task.exception?.message}")
                // Handle errors
            }
        }
}

private fun signUp(auth: FirebaseAuth, email: String, password: String, confirmPassword: String, onLoginSuccess: () -> Unit) {
    if (password != confirmPassword) {
        Log.e("LoginScreen", "Passwords do not match")
        // Handle password mismatch
        return
    }

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onLoginSuccess()
            } else {
                Log.e("LoginScreen", "SignUp failed: ${task.exception?.message}")
                // Handle errors
            }
        }
}
