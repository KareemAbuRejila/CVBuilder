package edu.miu.cs473de.cv_builder.ui.launchpad

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import edu.miu.cs473de.cv_builder.R
import edu.miu.cs473de.cv_builder.common.CommonConstrains.USER_REPO
import edu.miu.cs473de.cv_builder.models.User
import edu.miu.cs473de.cv_builder.ui.HomeActivity
import kotlinx.coroutines.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//        intent.getStringExtra("email")?.let { Log.i("email",it) }
//        intent.getStringExtra("pass")?.let { Log.i("pass",it) }
//        runBlocking {
//            withContext(Dispatchers.IO){
//                login("e","2")
//            }
//        }
        CoroutineScope(Dispatchers.IO).launch {
//            login("e","2")

        }
    }




    fun signIn(view: View) {
        val email = findViewById<EditText>(R.id.edt_email).text.toString().lowercase()
        val pass = findViewById<EditText>(R.id.edt_pass).text.toString()
        runBlocking {
            withContext(Dispatchers.IO) {
                login(email, pass)
            }
        }

    }

    suspend fun login(email: String, pass: String) {
        val user = validateUser(email, pass)
        if (user!=null)goToHomeActivity(userId = user.id)
        else {
            runOnUiThread {
                Toast.makeText(this@LoginActivity, "We don't have this user", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private suspend fun validateUser(email: String, pass: String): User? =
        USER_REPO?.verify(email, pass)

    private fun goToHomeActivity(userId: Long) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("userId", userId)
        startActivity(intent)
        finish()
    }

    fun goToRegisterActivity(view: View) {
        startForResult.launch(Intent(this, RegisterActivity::class.java))
    }

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val email=intent?.getStringExtra("email")
            val pass=intent?.getStringExtra("pass")
            if (email != null && pass != null)
                runBlocking {
                    withContext(Dispatchers.IO) {
                        login(email, pass)
                    }
                }
        }
    }
}