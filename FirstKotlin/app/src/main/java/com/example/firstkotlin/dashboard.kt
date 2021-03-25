package com.example.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_login.*

class dashboard : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var toggle: ActionBarDrawerToggle
    var databaseReference: DatabaseReference? = null
    var database: FirebaseDatabase? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val actionBar = supportActionBar
        actionBar!!.title = "Dashboard"

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nv.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.profile -> Toast.makeText(applicationContext, "Clicked Profile", Toast.LENGTH_SHORT).show()
                R.id.settings -> Toast.makeText(applicationContext, "Clicked Settings", Toast.LENGTH_SHORT).show()
                R.id.logoutButton -> logout()
            }
            true
        }

        satView1.setOnClickListener {
            val intent = Intent(this@dashboard, SatView::class.java)
            startActivity(intent)
        }

        satView.setOnClickListener {
            val intent = Intent(this@dashboard, SatView::class.java)
            startActivity(intent)
        }

    }

        private fun logout() {
            auth.signOut()
            startActivity(Intent(this@dashboard, MainActivity::class.java))
            finish()
        }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadProfile() {

        val user = auth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

    }


}