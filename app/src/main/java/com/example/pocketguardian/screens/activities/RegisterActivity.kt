package com.example.pocketguardian.screens.activities
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.TextUtils
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import com.google.android.gms.tasks.OnCompleteListener
//import com.google.android.material.snackbar.Snackbar
//import com.google.firebase.auth.AuthResult
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.auth.FirebaseUser
//import com.mae.apevent.Firestore.FirestoreClass
//import com.mae.apevent.R
//import com.mae.apevent.models.User
//
//class RegisterActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_register)
//
//        findViewById<Button>(R.id.btnRegister).setOnClickListener {
//            attemptRegistration()
//        }
//    }
//
//    private fun areDetailsValid(): Boolean {
//        return when{
//            TextUtils.isEmpty(findViewById<EditText>(R.id.etRegoName).text.toString()) -> {
//                Snackbar.make(findViewById(android.R.id.content), R.string.empty_name, Snackbar.LENGTH_SHORT).show()
//                false
//            }
//
//            TextUtils.isEmpty(findViewById<EditText>(R.id.etRegoEmail).text.toString()) -> {
//                Snackbar.make(findViewById(android.R.id.content), R.string.empty_email, Snackbar.LENGTH_SHORT).show()
//                false
//            }
//
//            TextUtils.isEmpty(findViewById<EditText>(R.id.etRegoPassword).text.toString().trim { it <= ' ' }) -> {
//                Snackbar.make(findViewById(android.R.id.content), R.string.empty_password, Snackbar.LENGTH_SHORT).show()
//                false
//            }
//
//            findViewById<EditText>(R.id.etRegoPassword).text.toString().trim { it <= ' ' } != findViewById<EditText>(R.id.etRegoConfirmPassword).text.toString().trim { it <= ' ' } ->{
//                Snackbar.make(findViewById(android.R.id.content), R.string.passwords_dont_match, Snackbar.LENGTH_SHORT).show()
//                false
//            }
//
//            else -> {
//                true
//            }
//        }
//    }
//
//    private fun attemptRegistration() {
//        if (areDetailsValid()){
//
//            val name = findViewById<EditText>(R.id.etRegoName).text.toString().trim { it <= ' ' }
//            val email = findViewById<EditText>(R.id.etRegoEmail).text.toString().trim { it <= ' ' }
//            val password = findViewById<EditText>(R.id.etRegoPassword).text.toString().trim { it <= ' ' }
//
//            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(
//                    OnCompleteListener<AuthResult> { task ->
//                        if (task.isSuccessful){
//                            val firebaseUser: FirebaseUser = task.result!!.user!!
//                            val user = User(
//                                firebaseUser.uid,
//                                name,
//                                email
//                            )
//                            FirestoreClass().registerUser(this, user)
//                            finish()
//                            val intent = Intent(this, LoginActivity::class.java)
//                            startActivity(intent)
//                        }
//                        else{
//                            Snackbar.make(
//                                findViewById(android.R.id.content),
//                                task.exception?.message.toString(),
//                                Snackbar.LENGTH_SHORT
//                            ).show()
//                        }
//                    }
//                )
//        }
//    }
//
//
//}