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

        val users = mapOf(
            "amanda" to "123",
            "pawestri" to "123",
            "rafid" to "123"
        )

        // mapping foto
        val userPhotoMap = mapOf(
            "amanda" to R.drawable.user_amanda,
            "pawestri" to R.drawable.user_pawestri,
            "rafid" to R.drawable.user_rafid
        )

        buttonLogin.setOnClickListener {
            val usernameInput = editTextUserName.text.toString().trim()
            val usernameKey = usernameInput.lowercase()
            val password = editTextPassword.text.toString().trim()

            if (users[usernameInput] == password) {
                //simpan user ke SharedPreferences
                val prefs = getSharedPreferences("USER_PREF", MODE_PRIVATE)
                prefs.edit()
                    .putString("USERNAME_DISPLAY", usernameInput) //original case
                    .putString("USERNAME", usernameKey) //lowercase
                    .putInt("USER_PHOTO", userPhotoMap[usernameKey] ?: R.drawable.default_user)
                    .apply()

                val intent = Intent(this, HomeActivity::class.java)
                //intent.putExtra(KEY_USERNAME, username)

                startActivity(intent)
                finish()
                //finish() // agar tidak bisa kembali ke login
            } else {
                Toast.makeText(this, "Username atau Password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val KEY_USERNAME = "username"
    }
}