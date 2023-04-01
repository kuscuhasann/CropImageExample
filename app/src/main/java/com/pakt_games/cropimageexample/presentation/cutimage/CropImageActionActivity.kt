package com.pakt_games.cropimageexample.presentation.cutimage

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.app.ActivityCompat
import com.canhub.cropper.CropImageActivity
import com.canhub.cropper.databinding.CropImageActivityBinding
import com.pakt_games.cropimageexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class CropImageActionActivity : CropImageActivity() {

    companion object {
        fun start(activity: Activity) {
            ActivityCompat.startActivity(
                activity,
                Intent(activity, CropImageActionActivity::class.java),
                null,
            )
        }
    }

    private lateinit var binding: CropImageActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = CropImageActivityBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        
    }
}