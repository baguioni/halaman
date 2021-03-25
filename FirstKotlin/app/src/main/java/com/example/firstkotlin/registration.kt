package com.example.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registration.*

class registration : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    var  databaseReference : DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        register()
    }

    private fun register(){
        registerButton.setOnClickListener {
                if(TextUtils.isEmpty(fullNameRegister.text.toString())) {
                    fullNameRegister.setError("Please enter full name")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(phoneNumberRegister.text.toString())) {
                    phoneNumberRegister.setError("Please enter a valid phone number")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(passwordRegister.text.toString())) {
                    passwordRegister.setError("Please enter password")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(regionRegister.text.toString())) {
                    passwordRegister.setError("Please enter Region")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(cityRegister.text.toString())) {
                    passwordRegister.setError("Please enter City")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(address1Register.text.toString())) {
                    passwordRegister.setError("Please enter address")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(address2Register.text.toString())) {
                    passwordRegister.setError("Please enter address")
                    return@setOnClickListener
                } else if(TextUtils.isEmpty(emailRegister.text.toString())) {
                    passwordRegister.setError("Please enter email")
                    return@setOnClickListener
                }

            auth.createUserWithEmailAndPassword(emailRegister.text.toString(), passwordRegister.text.toString())
                .addOnCompleteListener{
                    if(it.isSuccessful){
                        val currentUser = auth.currentUser
                        val currentUSerDb = databaseReference?.child((currentUser?.uid!!))
                        currentUSerDb?.child("Fullname")?.setValue(fullNameRegister.text.toString())
                        Toast.makeText(this@registration, "Registration Success! ", Toast.LENGTH_LONG).show()
                        finish()

                    } else {
                        Toast.makeText(this@registration, "Registration failed, please try again! ", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}