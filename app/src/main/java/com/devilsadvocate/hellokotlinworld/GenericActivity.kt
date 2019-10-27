package com.devilsadvocate.hellokotlinworld

import android.media.MediaPlayer
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_generic.*

class GenericActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generic)

        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.generic_sound)

        mediaPlayer?.start()
        genericImageView.setImageResource(R.drawable.generic_photo)
    }
}
