package com.main.common

import android.R
import android.annotation.SuppressLint
import android.app.Application
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity


class BackgroundSoundService :  Service() {

    private val TAG: String? = null
    var player: MediaPlayer? = null
    override fun onBind(arg0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        player = MediaPlayer.create(this, R.drawable.alert_dark_frame) //sai
        player!!.isLooping = true // Set looping
        player!!.setVolume(100f, 100f)
    }

    @SuppressLint("WrongConstant")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        player!!.start()
        return 1
    }

    override fun onStart(intent: Intent?, startId: Int) {
        // TO DO
    }

    fun onUnBind(arg0: Intent?): IBinder? {
        // TO DO Auto-generated method
        return null
    }

    fun onStop() {}
    fun onPause() {}
    override fun onDestroy() {
        player!!.stop()
        player!!.release()
    }

    override fun onLowMemory() {}
}