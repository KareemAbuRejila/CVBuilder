package edu.miu.cs473de.cv_builder.ui.launchpad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.miu.cs473de.cv_builder.R
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread{
            sleep(3000)
            goToLoginActivity()
        }.start()
    }

    private fun goToLoginActivity() {
        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}