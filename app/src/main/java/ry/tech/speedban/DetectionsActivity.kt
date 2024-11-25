package ry.tech.speedban

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ry.tech.speedban.databinding.ActivityDetectionsBinding

class DetectionsActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityDetectionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        bindingClass = ActivityDetectionsBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(bindingClass.root)


        val theme = intent.getStringExtra("theme") ?: getString(R.string.theme_default)
        bindingClass.tvDetections.text = theme

    }
}