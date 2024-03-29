package edu.miu.cs473de.cv_builder.ui.launchpad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import edu.miu.cs473de.cv_builder.R
import edu.miu.cs473de.cv_builder.common.CommonConstrains.USER_REPO
import edu.miu.cs473de.cv_builder.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        runBlocking {
//            withContext(Dispatchers.IO) {
//                createAccount(fName = "fName", lName = "lName", email = "e", password = "2")
//            }
//        }
    }

    fun registerAccount(view: View) {
        val fName = findViewById<EditText>(R.id.edt_fNameR).text.toString()
        val lName = findViewById<EditText>(R.id.edt_lNameR).text.toString()
        val email = findViewById<EditText>(R.id.edt_emailR).text.toString().lowercase()
        val pass = findViewById<EditText>(R.id.edt_passR).text.toString()
        runBlocking {
            withContext(Dispatchers.IO) {
                createAccount(fName = fName, lName = lName, email = email, password = pass)
            }
        }
    }

    private suspend fun createAccount(
        fName: String,
        lName: String,
        email: String,
        password: String
    ) {
        if (fName.isNotBlank() && lName.isNotBlank() && email.isNotBlank() && password.isNotBlank()) {
            val intent = Intent()
            val user = User(fName = fName, lName = lName, email = email, password = password)
            if (USER_REPO?.insert(user) != null) {
                intent.putExtra("email",user.email)
                intent.putExtra("pass",user.password)
                setResult(Activity.RESULT_OK,intent)
                finish()
            }
        }
    }
}