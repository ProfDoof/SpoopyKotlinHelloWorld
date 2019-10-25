package com.devilsadvocate.hellokotlinworld

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat.startActivity
import kotlin.math.absoluteValue

class GameView(context: Context, private val maze: Maze) : View(context) {
    //mazeWidth and mazeHeight of the whole maze and mazeWidth of lines which
    //make the walls
    private var mazeWidth: Int = 0
    private var mazeHeight: Int = 0
    private var lineWidth: Int = 0
    private var totalDisplayHeight: Int = 0
    private var totalDisplayWidth: Int = 0

    //size of the maze i.e. number of cells in it
    private val mazeSizeX: Int = maze.mazeWidth
    private val mazeSizeY: Int = maze.mazeHeight

    //mazeWidth and mazeHeight of cells in the maze
    private var cellWidth: Float = 0.toFloat()
    private var cellHeight: Float = 0.toFloat()

    //the following store result of cellWidth+lineWidth
    //and cellHeight+lineWidth respectively
    private var totalCellWidth: Float = 0.toFloat()
    private var totalCellHeight: Float = 0.toFloat()

    //the finishing point of the maze
    private val mazeFinishX: Int = maze.finalX
    private val mazeFinishY: Int = maze.finalY

    // maze instructions
    private var mazeInstructionLayoutPortrait: StaticLayout
    private var mazeInstructionLayoutLandscape: StaticLayout

    private val context: Activity = context as Activity
    private val line: Paint = Paint()
    private val red: Paint  = Paint()
    private val redTextPaint: TextPaint = TextPaint()
    private val background: Paint = Paint()

    private val detector: GestureDetector

    init {
        line.color = resources.getColor(R.color.line)
        red.color = resources.getColor(R.color.position)
        redTextPaint.set(red)
        redTextPaint.textSize = 72.toFloat()
        mazeInstructionLayoutPortrait = StaticLayout(
            resources.getString(R.string.maze_instructions),
            redTextPaint,
            16,
            Layout.Alignment.ALIGN_CENTER,
            1.0f,
            0.0f,
            false)
        mazeInstructionLayoutLandscape = mazeInstructionLayoutPortrait
        background.color = resources.getColor(R.color.game_bg)
//        isFocusable = true
//        this.isFocusableInTouchMode = true
        detector = GestureDetector(getContext(), MazeGestureDetector(maze, this::onMazeMove))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        mazeWidth = if (w < h) w-32 else h-32
        mazeHeight = mazeWidth         //for now square mazes
        totalDisplayHeight = h
        totalDisplayWidth = w
        lineWidth = 1          //for now 1 pixel wide walls
        cellWidth = (mazeWidth - mazeSizeX.toFloat() * lineWidth) / mazeSizeX
        totalCellWidth = cellWidth + lineWidth
        cellHeight = (mazeHeight - mazeSizeY.toFloat() * lineWidth) / mazeSizeY
        totalCellHeight = cellHeight + lineWidth
        red.textSize = cellHeight * 0.75f

        mazeInstructionLayoutPortrait = StaticLayout(
            resources.getString(R.string.maze_instructions),
            redTextPaint,
            mazeWidth-32,
            Layout.Alignment.ALIGN_CENTER,
            1.0f,
            0.0f,
            false)

        mazeInstructionLayoutLandscape = StaticLayout(
            resources.getString(R.string.maze_instructions),
            redTextPaint,
            totalDisplayWidth-mazeWidth,
            Layout.Alignment.ALIGN_CENTER,
            1.0f,
            0.0f,
            false)

        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas) {
        //fill in the background
        canvas.drawRect(0f, 0f, totalDisplayWidth.toFloat(), totalDisplayHeight.toFloat(), background)
        canvas.translate(16.toFloat(),16.toFloat())
        val hLines = maze.horizontalLines
        val vLines = maze.verticalLines
        //iterate over the boolean arrays to draw walls
        for (i in 0 until mazeSizeX) {
            for (j in 0 until mazeSizeY) {
                val x = j * totalCellWidth
                val y = i * totalCellHeight
                if (j < mazeSizeX - 1 && vLines!![i][j]) {
                    //we'll draw a vertical line
                    canvas.drawLine(x + cellWidth, //start X
                        y, //start Y
                        x + cellWidth, //stop X
                        y + cellHeight, //stop Y
                        line)
                }
                if (i < mazeSizeY - 1 && hLines!![i][j]) {
                    //we'll draw a horizontal line
                    canvas.drawLine(x, //startX
                        y + cellHeight, //startY
                        x + cellWidth, //stopX
                        y + cellHeight, //stopY
                        line)
                }
            }
        }

        val currentX = maze.currentX
        val currentY = maze.currentY
        //draw the ball
        canvas.drawCircle(currentX * totalCellWidth + cellWidth / 2, //x of center
            currentY * totalCellHeight + cellWidth / 2, //y of center
            cellWidth * 0.45f, //radius
            red)
        //draw the finishing point indicator
        canvas.drawText("F",
            mazeFinishX * totalCellWidth + cellWidth * 0.25f,
            mazeFinishY * totalCellHeight + cellHeight * 0.75f,
            red)

        //draw a separator
        canvas.drawLine(0.toFloat(), 0.toFloat(), mazeWidth.toFloat(), 0.toFloat(), line)
        canvas.drawLine(0.toFloat(), mazeHeight.toFloat(), mazeWidth.toFloat(), mazeHeight.toFloat(), line)
        canvas.drawLine(mazeWidth.toFloat(), 0.toFloat(), mazeWidth.toFloat(), mazeHeight.toFloat(), line)
        canvas.drawLine(0.toFloat(), 0.toFloat(), 0.toFloat(), mazeHeight.toFloat(), line)
        canvas.translate(-(16.toFloat()), -(16.toFloat()))
        // print instructionstes

        canvas.translate(0.toFloat(), (mazeHeight.toFloat()+totalDisplayHeight.toFloat())/2)
        mazeInstructionLayoutPortrait.draw(canvas)
        canvas.translate(0.toFloat(), -((mazeHeight.toFloat()+totalDisplayHeight.toFloat())/2))
        canvas.translate(mazeWidth.toFloat()+32, mazeHeight.toFloat()/2)
        mazeInstructionLayoutPortrait.draw(canvas)
    }

    override fun onKeyDown(keyCode: Int, evt: KeyEvent): Boolean {
        var moved = false
        when (keyCode) {
            KeyEvent.KEYCODE_DPAD_UP -> moved = maze.move(Maze.UP)
            KeyEvent.KEYCODE_DPAD_DOWN -> moved = maze.move(Maze.DOWN)
            KeyEvent.KEYCODE_DPAD_RIGHT -> moved = maze.move(Maze.RIGHT)
            KeyEvent.KEYCODE_DPAD_LEFT -> moved = maze.move(Maze.LEFT)
            else -> return super.onKeyDown(keyCode, evt)
        }
        if (moved) {
            //the ball was moved so we'll redraw the view
            invalidate()
            if (maze.isGameComplete) {
                val builder = AlertDialog.Builder(context)
                builder.setTitle(context.getText(R.string.finished_title))
                val inflater = context.layoutInflater
                val view = inflater.inflate(R.layout.finish, null)
                builder.setView(view)
                val closeButton = view.findViewById<Button>(R.id.closeGame)
                closeButton.setOnClickListener { clicked: View ->
                    if (clicked.id == R.id.closeGame) {
                        context.finish()
                    }
                }
                val finishDialog = builder.create()
                finishDialog.show()
            }
        }
        return true
    }

    fun onMazeMove() {
        invalidate()
        if (maze.isGameComplete) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(context.getText(R.string.finished_title))
            val inflater = context.layoutInflater
            val view = inflater.inflate(R.layout.finish, null)
            builder.setView(view)
            val closeButton = view.findViewById<Button>(R.id.closeGame)
            closeButton.setOnClickListener { clicked: View ->
                if (clicked.id == R.id.closeGame) {
                    context.finish()
                    val intent = Intent(
                        getContext(),
                        GenericActivity::class.java
                    )
                    startActivity(getContext(), intent, null)
                }
            }
            val finishDialog = builder.create()
            finishDialog.show()
        }
    }

    private class MazeGestureDetector(private val maze: Maze, private val mazeMoveFun: () -> Unit): GestureDetector.SimpleOnGestureListener() {
        override fun onDown(event: MotionEvent): Boolean {
            return true
        }

        private val SWIPE_THRESHOLD = 100

        override fun onFling(downEvent: MotionEvent, moveEvent: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {
            var result: Boolean = false
            val diffY: Float = moveEvent.getY() - downEvent.getY()
            val diffX: Float = moveEvent.getX() - downEvent.getX()

            // which was greater? Movement across Y or X
            if (diffX.absoluteValue > diffY.absoluteValue) {
                // right or left swipe
                if (diffX.absoluteValue > SWIPE_THRESHOLD && velocityX.absoluteValue > 100) {
                    if (diffX > 0) {
                        maze.move(Maze.RIGHT)
                    } else {
                        maze.move(Maze.LEFT)
                    }
                    result = true
                }
            } else {
                // up or down swipe
                if (diffY.absoluteValue > SWIPE_THRESHOLD && velocityY.absoluteValue > 100) {
                    if (diffY > 0) {
                        maze.move(Maze.DOWN)
                    } else {
                        maze.move(Maze.UP)
                    }
                    result = true
                }
            }

            if (result) {
                //the ball was moved so we'll redraw the view
                mazeMoveFun()
            }

            return result
        }

        override fun onLongPress(event: MotionEvent) {
        }

        override fun onScroll(e1: MotionEvent, e2: MotionEvent,
                              distanceX: Float, distanceY: Float): Boolean {
            return true
        }

        override fun onSingleTapUp(event: MotionEvent): Boolean {
            return true
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            return true
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (detector.onTouchEvent(event))
            return true
        return super.onTouchEvent(event)
    }
}
