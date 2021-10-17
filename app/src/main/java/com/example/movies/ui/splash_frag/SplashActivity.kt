package com.example.movies.ui.splash_frag

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.R
import com.example.movies.ui.login_register.LoginRegisterNavigationActivity
import com.example.movies.ui.main.MainActivity
import com.example.movies.utils.PREFS_IS_REGISTERED_OR_NOT
import com.example.movies.utils.SPLASH_TIME_OUT
import nouri.`in`.goodprefslib.GoodPrefs

class SplashActivity() : AppCompatActivity() {
    private var boolean: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        val putUser1 = GoodPrefs.getInstance().getBoolean(PREFS_IS_REGISTERED_OR_NOT, boolean)
        if (GoodPrefs.getInstance().isKeyExists(PREFS_IS_REGISTERED_OR_NOT) && putUser1) {

            Handler().postDelayed({
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }, SPLASH_TIME_OUT)
        } else {
            Handler().postDelayed({
                Intent(this, LoginRegisterNavigationActivity::class.java).apply {
                    startActivity(this)
                    finish()
                }
            }, SPLASH_TIME_OUT)
        }
    }
}