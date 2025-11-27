package com.example.profileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.profileapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val repo by lazy { UserRepository(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // If user already logged, go to profile
        if (repo.isLogged()) {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val name = binding.etName.text.toString().trim()
            if (email.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Preencha nome e email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val user = User(name = name, email = email, photoUri = null)
            repo.saveUser(user)
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
