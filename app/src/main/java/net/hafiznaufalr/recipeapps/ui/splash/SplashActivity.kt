package net.hafiznaufalr.recipeapps.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import net.hafiznaufalr.recipeapps.R
import net.hafiznaufalr.recipeapps.ui.base.BaseActivity
import net.hafiznaufalr.recipeapps.ui.main.MainActivity

class SplashActivity : BaseActivity() {
    lateinit var bottomtotop: Animation
    lateinit var smtobig: Animation

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun onActivityReady(savedInstanceState: Bundle?) {
        bottomtotop = AnimationUtils.loadAnimation(this, R.anim.bottomtotop)
        smtobig = AnimationUtils.loadAnimation(this, R.anim.smalltobig)

        iv_food.startAnimation(smtobig)
        tv_title.startAnimation(bottomtotop)
        val thread = object : Thread() {
            override fun run() {
                try {
                    sleep(3000)
                } catch (ex: InterruptedException) {
                    ex.printStackTrace()
                } finally {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
        thread.start()
    }



}
