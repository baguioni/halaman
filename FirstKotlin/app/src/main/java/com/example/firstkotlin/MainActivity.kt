package com.example.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@MainActivity, dashboard::class.java)
            startActivity(intent)
            finish()
        }
        login()
    }

    private fun login() {
        loginButton.setOnClickListener {
            if (TextUtils.isEmpty(emailLogin.text.toString())) {
                emailLogin.error = "Please enter registered email."
                return@setOnClickListener
            } else if (TextUtils.isEmpty(passwordLogin.text.toString())) {
                passwordLogin.error = "Please enter password."
                return@setOnClickListener
            }
            auth.signInWithEmailAndPassword(emailLogin.text.toString(), passwordLogin.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this@MainActivity, dashboard::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Login failed, please try again! ",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

        }

        registerText.setOnClickListener {
            val intent = Intent(this@MainActivity, registration::class.java)
            startActivity(intent)
            finish()
        }
    }
}