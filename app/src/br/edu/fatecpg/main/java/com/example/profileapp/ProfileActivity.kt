package com.example.profileapp

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.profileapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val repo by lazy { UserRepository(this) }
    private val viewModel: UserViewModel by viewModels { UserViewModel.Factory(repo) }
    private var currentPhotoUri: String? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            currentPhotoUri = it.toString()
            binding.ivPhoto.load(it) { placeholder(android.R.drawable.sym_def_app_icon) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // load user from repo
        viewModel.setUser(repo.getUser())
        viewModel.user.observe(this) { user ->
            binding.etName.setText(user.name)
            binding.etEmail.setText(user.email)
            currentPhotoUri = user.photoUri
            if (!currentPhotoUri.isNullOrEmpty()) {
                binding.ivPhoto.load(Uri.parse(currentPhotoUri))
            } else {
                binding.ivPhoto.setImageResource(android.R.drawable.sym_def_app_icon)
            }
        }

        binding.ivPhoto.setOnClickListener {
            // open gallery chooser (images)
            pickImage.launch("image/*")
        }

        binding.btnSave.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            if (name.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Nome e email não podem ficar vazios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            viewModel.updateUser(name, email, currentPhotoUri)
            Toast.makeText(this, "Perfil salvo", Toast.LENGTH_SHORT).show()
        }

        binding.btnLogout.setOnClickListener {
            repo.logout()
            finish()
        }
    }
}
