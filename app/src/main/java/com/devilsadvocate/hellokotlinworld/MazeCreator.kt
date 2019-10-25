package com.devilsadvocate.hellokotlinworld

object MazeCreator {

    fun getMaze(mazeNo: Int): Maze? {
        var maze: Maze? = null
        if (mazeNo == 1) {
            maze = Maze()
            val vLines = arrayOf(booleanArrayOf(true, false, false, false, true, false, false), booleanArrayOf(true, false, false, true, false, true, true), booleanArrayOf(false, true, false, false, true, false, false), booleanArrayOf(false, true, true, false, false, false, true), booleanArrayOf(true, false, false, false, true, true, false), booleanArrayOf(false, true, false, false, true, false, false), booleanArrayOf(false, true, true, true, true, true, false), booleanArrayOf(false, false, false, true, false, false, false))
            val hLines = arrayOf(booleanArrayOf(false, false, true, true, false, false, true, false), booleanArrayOf(false, false, true, true, false, true, false, false), booleanArrayOf(true, true, false, true, true, false, true, true), booleanArrayOf(false, false, true, false, true, true, false, false), booleanArrayOf(false, true, true, true, true, false, true, true), booleanArrayOf(true, false, false, true, false, false, true, false), booleanArrayOf(false, true, false, false, false, true, false, true))
            maze.verticalLines = vLines
            maze.horizontalLines = hLines
            maze.setStartPosition(0, 0)
            maze.setFinalPosition(7, 7)
        }
        if (mazeNo == 2) {
            maze = Maze()
            val vLines = arrayOf(booleanArrayOf(false, false, false, true, false, false, false), booleanArrayOf(false, false, true, false, true, false, false), booleanArrayOf(false, false, true, true, false, false, false), booleanArrayOf(false, false, true, true, true, false, false), booleanArrayOf(false, false, true, false, true, false, false), booleanArrayOf(true, false, false, true, false, true, false), booleanArrayOf(true, false, true, true, false, false, false), booleanArrayOf(false, false, true, false, false, false, true))
            val hLines = arrayOf(booleanArrayOf(false, true, true, true, false, true, true, true), booleanArrayOf(true, true, false, false, true, true, true, false), booleanArrayOf(false, true, true, false, false, false, true, true), booleanArrayOf(true, true, false, false, false, true, true, false), booleanArrayOf(false, true, true, true, true, false, true, false), booleanArrayOf(false, false, true, false, false, true, true, true), booleanArrayOf(false, true, false, false, true, true, false, false))
            maze.verticalLines = vLines
            maze.horizontalLines = hLines
            maze.setStartPosition(0, 7)
            maze.setFinalPosition(7, 0)
        }
        if (mazeNo == 3) {
            maze = Maze()
            val vLines = arrayOf(booleanArrayOf(false, false, true, false, false, false, true, false, false, false, false, false), booleanArrayOf(false, true, false, false, false, true, false, false, false, false, true, true), booleanArrayOf(true, false, false, false, false, true, false, false, false, false, true, true), booleanArrayOf(true, true, false, false, false, true, true, true, false, false, true, true), booleanArrayOf(true, true, true, false, false, true, true, false, true, false, true, true), booleanArrayOf(false, true, true, true, false, true, false, false, false, true, false, false), booleanArrayOf(false, false, false, true, false, true, false, true, false, false, false, false), booleanArrayOf(false, false, true, false, true, false, true, true, false, true, false, false), booleanArrayOf(true, true, true, true, false, true, true, false, false, true, false, false), booleanArrayOf(false, false, false, true, false, false, true, true, false, true, true, false), booleanArrayOf(false, false, true, false, true, false, true, false, false, false, false, false), booleanArrayOf(true, true, true, true, true, true, true, false, false, true, false, false), booleanArrayOf(false, false, true, false, false, true, false, false, false, false, true, false))
            val hLines = arrayOf(booleanArrayOf(true, false, false, true, true, false, false, false, true, true, true, true, false), booleanArrayOf(false, true, true, true, true, true, true, true, true, true, false, false, false), booleanArrayOf(false, false, true, true, true, false, false, true, true, true, true, false, false), booleanArrayOf(false, false, false, true, true, true, false, false, false, true, false, false, false), booleanArrayOf(false, false, false, false, true, false, false, true, true, true, false, false, false), booleanArrayOf(true, true, false, false, false, true, true, true, true, false, true, true, true), booleanArrayOf(false, true, true, true, true, true, false, false, false, true, true, true, false), booleanArrayOf(true, false, false, false, true, false, true, false, true, false, false, true, true), booleanArrayOf(false, true, false, false, false, true, false, true, true, true, true, true, false), booleanArrayOf(true, true, false, true, false, true, true, false, false, true, false, true, false), booleanArrayOf(false, true, true, false, true, false, false, true, true, false, true, true, true), booleanArrayOf(false, true, false, false, true, false, false, true, true, true, false, false, true))
            maze.verticalLines = vLines
            maze.horizontalLines = hLines
            maze.setStartPosition(0, 0)
            maze.setFinalPosition(12, 12)
        }
        return maze
    }
}