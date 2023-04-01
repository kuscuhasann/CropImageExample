package com.pakt_games.cropimageexample.presentation.gotocropimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.pakt_games.cropimageexample.R
import com.pakt_games.cropimageexample.databinding.ActivityGoToCropImageActivitiyBinding
import com.pakt_games.cropimageexample.presentation.cutimage.CropImageActionActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoToCropImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoToCropImageActivitiyBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoToCropImageActivitiyBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.sampleCustomActivity.setOnClickListener { CropImageActionActivity.start(this) }

        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val fragment = supportFragmentManager.findFragmentById(binding.container.id)
                    isEnabled = fragment != null

                    if (fragment != null) {
                        supportFragmentManager.beginTransaction().remove(fragment).commit()
                    } else {
                        onBackPressedDispatcher.onBackPressed()
                    }

                    isEnabled = true
                }
            },
        )

    }
}