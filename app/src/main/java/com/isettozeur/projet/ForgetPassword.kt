package com.isettozeur.projet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.isettozeur.projet.databinding.ActivityForgetPasswordBinding
import com.isettozeur.projet.databinding.ActivitySignupBinding

class ForgetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.FlechRetour.setOnClickListener {
            Toast.makeText(this@ForgetPassword,"login successful", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@ForgetPassword,login::class.java))
    }
}}