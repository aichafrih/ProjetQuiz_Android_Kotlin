package com.isettozeur.projet

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.isettozeur.projet.databinding.ActivitySignupBinding

class signup : AppCompatActivity() {
private lateinit var binding: ActivitySignupBinding
private lateinit var firebaseDatabase: FirebaseDatabase
private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")

        binding.signupButton.setOnClickListener{
            val signupUserName=binding.signupUserName.text.toString()
            val signupEmail=binding.signupEmail.text.toString()
            val signupPassword=binding.signupPassword.text.toString()

            if (signupUserName.isNotEmpty()&&signupEmail.isNotEmpty()&&signupPassword.isNotEmpty()) {
                signupUser(signupUserName,signupEmail,signupPassword)

        }else {
                Toast.makeText(this@signup,"All fields are mandatory",Toast.LENGTH_SHORT).show()
            }

        }
    binding.loginRedirect.setOnClickListener {
    Toast.makeText(this@signup,"signup successful",Toast.LENGTH_SHORT).show()
    startActivity(Intent(this@signup,login::class.java))
    finish()

}

}

    private fun signupUser(username: String, email : String, password: String){
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(datasnapshot: DataSnapshot) {
                if (!datasnapshot.exists()){
                    val id =databaseReference.push().key
                    val userData = UserData(id,username,email,password)
                    databaseReference.child(id!!).setValue(userData)
                    Toast.makeText(this@signup,"signup successfuly",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@signup,Acceuil::class.java))
                    finish()
                }
                else{
                    Toast.makeText(this@signup,"user already exists",Toast.LENGTH_SHORT).show()
                }

            }

            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@signup,"Database erreur: ${databaseError.message}",Toast.LENGTH_SHORT).show()
            }

        } )
    }
}
