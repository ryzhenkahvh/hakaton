package ry.tech.speedban

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import ry.tech.speedban.databinding.ActivityDetectionBinding


class DetectionActivity : AppCompatActivity() {

    lateinit var bindingClass: ActivityDetectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        bindingClass = ActivityDetectionBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        val theme = intent.getStringExtra("theme") ?: getString(R.string.theme_default)
        bindingClass.tvDetections.text = theme

        Log.d("DetectionActivity", "Theme received: $theme")

        if (savedInstanceState == null) {
            val fragment = Kotlin_Fragment.newInstance(theme)
            supportFragmentManager.beginTransaction()
                .replace(R.id.detectionFrame, fragment)
                .commit()
        }
    }

    override fun getSupportFragmentManager(): FragmentManager {
        return super.getSupportFragmentManager()
    }
}
