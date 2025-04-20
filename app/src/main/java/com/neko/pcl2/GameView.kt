package com.neko.pcl2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private var gameThread: GameThread? = null

    init {
        holder.addCallback(this)
        isFocusable = true // 接收按键/触控输入
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        gameThread = GameThread(holder, this)
        gameThread?.running = true
        gameThread?.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        gameThread?.running = false
        while (retry) {
            try {
                gameThread?.join()
                retry = false
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    fun drawCanvas(canvas: Canvas) {
        // 清屏
        canvas.drawColor(Color.BLACK)

        // 绘制内容（临时画一行字）
        val paint = Paint()
        paint.color = Color.WHITE
        paint.textSize = 64f
        paint.isAntiAlias = true
        canvas.drawText("欢迎来到 PCL2 手机版", 100f, 100f, paint)
    }
}
