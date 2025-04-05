package com.example.pirmp_7_makaryanaa

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class MusicService : Service() {
    private val TAG = "MusicService"
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        // Инициализация медиаплеера
        mediaPlayer = MediaPlayer.create(this, R.raw.aramkhachaturyanmasqueradewaltz)
        mediaPlayer.isLooping = true // Зацикливание воспроизведения
        mediaPlayer.setVolume(100f, 100f)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
            Log.d(TAG, "Музыка начала играть")
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
            Log.d(TAG, "Музыка остановлена и ресурсы освобождены")
        }
    }

    override fun onBind(intent: Intent): IBinder? {
        return null // Для сервисов без привязки возвращаем null
    }
}