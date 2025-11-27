package com.rasaloka.app.Login

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rasaloka.app.HomeActivity
import com.rasaloka.app.R

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextUserName = findViewById<EditText>(R.id.textUsername)
        val editTextPassword = findViewById<EditText>(R.id.textPassword)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)


        buttonLogin.setOnClickListener {
            val username = editTextUserName.text.toString()
            if (editTextUserName.text.toString() == "admin" &&
                editTextPassword.text.toString() == "123"
            ) {

                val intent = Intent(this, HomeActivity::class.java)
                intent.putExtra(KEY_USERNAME, username)
                startActivity(intent)

                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()

                finish() // agar tidak bisa kembali ke login
            } else {
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val KEY_USERNAME = "username"
    }
}