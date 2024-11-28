package ry.tech.speedban

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
    }





}