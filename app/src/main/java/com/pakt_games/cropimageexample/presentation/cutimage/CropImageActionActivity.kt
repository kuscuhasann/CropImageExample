package com.pakt_games.cropimageexample.presentation.cutimage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.View
import androidx.core.app.ActivityCompat
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageActivity
import com.canhub.cropper.CropImageView
import com.canhub.cropper.databinding.CropImageActivityBinding
import com.pakt_games.cropimageexample.R
import com.pakt_games.cropimageexample.databinding.ActivityCropImageActionBinding
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

    private lateinit var binding: ActivityCropImageActionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityCropImageActionBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)

        binding.saveBtn.setOnClickListener { cropImage() }
        binding.backBtn.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
        binding.rotateText.setOnClickListener { onRotateClick() }

        binding.cropImageView.setOnCropWindowChangedListener {
            updateExpectedImageSize()
        }

        setCropImageView(binding.cropImageView)
    }

    override fun onSetImageUriComplete(
        view: CropImageView,
        uri: Uri,
        error: Exception?,
    ) {
        super.onSetImageUriComplete(view, uri, error)

        updateRotationCounter()
        updateExpectedImageSize()
    }

    private fun updateExpectedImageSize() {
        binding.expectedImageSize.text = binding.cropImageView.expectedImageSize().toString()
    }

    override fun setContentView(view: View) {
        super.setContentView(binding.root)
    }

    private fun updateRotationCounter() {
        binding.rotateText.text = getString(R.string.rotation_value, binding.cropImageView.rotatedDegrees.toString())
    }

    override fun onPickImageResult(resultUri: Uri?) {
        super.onPickImageResult(resultUri)

        if (resultUri != null) {
            binding.cropImageView.setImageUriAsync(resultUri)
        }
    }

    override fun getResultIntent(uri: Uri?, error: java.lang.Exception?, sampleSize: Int): Intent {
        val result = super.getResultIntent(uri, error, sampleSize)
        // Adding some more information.
        return result.putExtra("EXTRA_KEY", "Extra data")
    }

    override fun setResult(uri: Uri?, error: Exception?, sampleSize: Int) {
        val result = CropImage.ActivityResult(
            originalUri = binding.cropImageView.imageUri,
            uriContent = uri,
            error = error,
            cropPoints = binding.cropImageView.cropPoints,
            cropRect = binding.cropImageView.cropRect,
            rotation = binding.cropImageView.rotatedDegrees,
            wholeImageRect = binding.cropImageView.wholeImageRect,
            sampleSize = sampleSize,
        )

        binding.cropImageView.setImageUriAsync(result.uriContent)
    }

    override fun setResultCancel() {
        super.setResultCancel()
    }

    override fun updateMenuItemIconColor(menu: Menu, itemId: Int, color: Int) {
        super.updateMenuItemIconColor(menu, itemId, color)
    }

    private fun onRotateClick() {
        binding.cropImageView.rotateImage(90)
        updateRotationCounter()
    }
}