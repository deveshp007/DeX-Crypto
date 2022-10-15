package alpha.dex.dexcrypto.ui.activity

import alpha.dex.dexcrypto.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScr : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val front_logo: ImageView = findViewById(R.id.splash_logo)

        val img_anim = AnimationUtils.loadAnimation(this, R.anim.slide_anim);

        front_logo.startAnimation(img_anim)

        // Splash Screen code to stay for 3000 milli sec ( 3 sec )
        Handler().postDelayed(Runnable {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

    }
}