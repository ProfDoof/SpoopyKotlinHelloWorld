package com.devilsadvocate.hellokotlinworld

import java.io.Serializable


class Maze : Serializable {

    var verticalLines: Array<BooleanArray>? = null
        set(lines) {
            field = lines
            mazeHeight = this.verticalLines!!.size
        }
    var horizontalLines: Array<BooleanArray>? = null
        set(lines) {
            field = lines
            mazeWidth = this.horizontalLines!![0].size
        }
    var mazeWidth: Int = 0
        private set
    var mazeHeight: Int = 0
        private set         //stores the width and height of the maze
    var currentX: Int = 0
        private set
    var currentY: Int = 0
        private set   //stores the current location of the ball
    var finalX: Int = 0
        private set
    var finalY: Int = 0
        private set       //stores the finishing of the maze
    var isGameComplete: Boolean = false
        private set

    fun move(direction: Int): Boolean {
        var moved = false
        if (direction == UP) {
            if (currentY != 0 && !this.horizontalLines!![currentY - 1][currentX]) {
                currentY--
                moved = true
            }
        }
        if (direction == DOWN) {
            if (currentY != mazeHeight - 1 && !this.horizontalLines!![currentY][currentX]) {
                currentY++
                moved = true
            }
        }
        if (direction == RIGHT) {
            if (currentX != mazeWidth - 1 && !this.verticalLines!![currentY][currentX]) {
                currentX++
                moved = true
            }
        }
        if (direction == LEFT) {
            if (currentX != 0 && !this.verticalLines!![currentY][currentX - 1]) {
                currentX--
                moved = true
            }
        }
        if (moved) {
            if (currentX == finalX && currentY == finalY) {
                isGameComplete = true
            }
        }
        return moved
    }

    fun setStartPosition(x: Int, y: Int) {
        currentX = x
        currentY = y
    }

    fun setFinalPosition(x: Int, y: Int) {
        finalX = x
        finalY = y
    }

    companion object {

        private const val serialVersionUID = 1L

        val UP = 0
        val DOWN = 1
        val RIGHT = 2
        val LEFT = 3
    }
}

