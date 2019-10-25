package com.devilsadvocate.hellokotlinworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val textView = findViewById<TextView>(R.id.sentMessage).apply {
            text = message
        }

        val startMazeButton = findViewById<Button>(R.id.startMazeButton).apply {
            setOnClickListener(View.OnClickListener {
                val intent = Intent(this@DisplayMessageActivity, Game::class.java)
                val maze = MazeCreator.getMaze(1)
                intent.putExtra("maze", maze)
                startActivity(intent)
            })
        }
    }
}
