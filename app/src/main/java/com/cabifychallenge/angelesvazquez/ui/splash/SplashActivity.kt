package com.cabifychallenge.angelesvazquez.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cabifychallenge.angelesvazquez.ui.MainActivity

/**
 * Author: ÁngelesVP
 *
 * This it's the first activity, it's a color background while it's loading the application.
 */
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}