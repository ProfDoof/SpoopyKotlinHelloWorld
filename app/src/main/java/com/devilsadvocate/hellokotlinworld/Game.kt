package com.devilsadvocate.hellokotlinworld

import android.os.Bundle
import android.view.Window.FEATURE_ACTION_BAR
import androidx.appcompat.app.AppCompatActivity

class Game : AppCompatActivity() {
    public override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        requestWindowFeature(FEATURE_ACTION_BAR)
        val intent = intent
        val extras = intent.extras
        val maze = extras!!.get("maze") as Maze
        val view = GameView(this, maze)
        setContentView(view)
    }
}