package com.junino.tourguide.ui.login

import android.content.Intent


import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

import android.widget.*


import com.google.firebase.ktx.Firebase
import com.junino.tourguide.R
import com.junino.tourguide.enums.TagEnum
import com.junino.tourguide.models.User
import com.junino.tourguide.utils.isLoggedIn
import com.junino.tourguide.utils.saveUser
import com.junino.tourguide.utils.user
import java.util.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var edtEmail : EditText
    private lateinit var edtUsername : EditText
    private lateinit var edtPassword : EditText
    private lateinit var edtKonfirPass : EditText
    private lateinit var btnRegister : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)
        supportActionBar?.hide()

        edtEmail = findViewById(R.id.edt_email)
        edtUsername = findViewById(R.id.edt_name)
        edtPassword = findViewById(R.id.edt_password)
        edtKonfirPass = findViewById(R.id.edt_conf_pass)
        btnRegister = findViewById(R.id.btn_register)

        val signIn = findViewById<TextView>(R.id.tv_signin)
        signIn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnRegister.setOnClickListener {
            registerUser()
        }

    }

    private fun registerUser() {
        val email = edtEmail.text.toString()
        val username = edtUsername.text.toString().lowercase(Locale.getDefault()).filter { !it.isWhitespace() }
        val password = edtPassword.text.toString()
        val konfirmasiPass = edtKonfirPass.text.toString()

        when {
            username.isEmpty() -> edtUsername.error = "Username tidak boleh kosong"
            password.isEmpty() -> edtPassword.error = "Password tidak boleh kosong"
            password.length < 6 -> edtPassword.error = "Password minimal terdiri dari 6 karakter"
            konfirmasiPass != password -> edtKonfirPass.error = "Password tidak sama"
            else -> {
                user = User(
                    username = username,
                    password = password,
                    email = email,
                )
                saveUser(user!!).continueWith {
                    isLoggedIn = true
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
        }
    }


    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

///**
// * Extension function to simplify setting an afterTextChanged action to EditText components.
// */
//fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
//    this.addTextChangedListener(object : TextWatcher {
//        override fun afterTextChanged(editable: Editable?) {
//            afterTextChanged.invoke(editable.toString())
//        }
//
//        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
//
//        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
//    })
//}
