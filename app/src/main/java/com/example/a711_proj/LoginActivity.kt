package com.example.a711_proj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            // Initialize Firebase Auth
            auth = Firebase.auth

            val btnLogin: Button = findViewById(R.id.btnLogin)
            val texRegister: TextView = findViewById(R.id.textRegister)

            texRegister.setOnClickListener {
                val intent = Intent(this, RegisterActivity::class.java)
                startActivity(intent)
            }


            btnLogin.setOnClickListener {
                performLogin()
            }

        }

        private fun performLogin() {

            val email: EditText = findViewById(R.id.loginEmail)
            val password: EditText = findViewById(R.id.loginPassword)

            if (email.text.isEmpty() || password.text.isEmpty()) {
                Toast.makeText(this, "Email and password must not be empty", Toast.LENGTH_SHORT).show()

                return
            }

            val inputEmail = email.text.toString().lowercase()
            val inputPassword = password.text.toString().lowercase()

            auth.signInWithEmailAndPassword(inputEmail, inputPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success,

                        Toast.makeText(baseContext, "Authentication Success.",
                            Toast.LENGTH_SHORT).show()

                        //Time to next activity (ms)
                        Thread.sleep(700)

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                        //Time to next activity (ms)
                        Thread.sleep(1000)

                        Toast.makeText(baseContext, "Check your Email and Password.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
        }

    }