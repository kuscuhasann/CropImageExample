package com.pakt_games.cropimageexample.presentation.gotocropimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pakt_games.cropimageexample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoToCropImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_crop_image_activitiy)
    }
}