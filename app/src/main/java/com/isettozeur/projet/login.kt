package com.isettozeur.projet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.isettozeur.projet.databinding.ActivityLoginBinding

class login : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.loginbutton.setOnClickListener(){
            val loginEmail=binding.loginEmail.text.toString()
            val loginPassword=binding.loginPassword.text.toString()

            if (loginEmail.isNotEmpty()&&loginPassword.isNotEmpty()) {
                loginUser(loginEmail,loginPassword)

            }else {
                Toast.makeText(this@login,"All fields are mandatory",Toast.LENGTH_SHORT).show()
            }

        }
        binding.signupRedirect.setOnClickListener {
            Toast.makeText(this@login,"signup successful",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@login,signup::class.java))
            finish()

        }
        binding.ForgetPassword.setOnClickListener {
            Toast.makeText(this@login,"ForgetPassword",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@login,ForgetPassword::class.java))
            finish()

        }


    }


    private fun loginUser(email : String, password: String){
        databaseReference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (datasnapshot.exists()) {

                    for (userSnapshot in datasnapshot.children) {
                        val userData = userSnapshot.getValue(UserData::class.java)
                        if (userData != null && userData.password == password) {
                            Toast.makeText(this@login, "login Successful", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@login, Acceuil::class.java))
                            finish()
                            return
                        }

                    }
                }
                Toast.makeText(this@login, "login Failed", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@login,"Database error: ${databaseError.message}",Toast.LENGTH_SHORT).show()
            }
        })
    }
}





