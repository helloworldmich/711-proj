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

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth


        val btnRegister: Button = findViewById(R.id.btnRegister)
        val textLogin: TextView = findViewById(R.id.textLogin)

        textLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnRegister.setOnClickListener {
            performSignUp()
        }

    }

    private fun performSignUp() {

        val email: EditText = findViewById(R.id.registerEmail)
        val password: EditText = findViewById(R.id.registerPassword)

        if (email.text.isEmpty()) {
            Toast.makeText(this, "Email should not be Empty", Toast.LENGTH_SHORT).show()
            return
        } else if (password.text.isEmpty()) {
            Toast.makeText(this, "Password should not be Empty", Toast.LENGTH_SHORT).show()
            return
        }


        val inputEmail = email.text.toString().lowercase()
        val inputPassword = password.text.toString().lowercase()

        auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success

                    Toast.makeText(
                        baseContext, "Authentication Success.",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error occurred ${it.localizedMessage}", Toast.LENGTH_SHORT)
                    .show()
            }

    }
}

