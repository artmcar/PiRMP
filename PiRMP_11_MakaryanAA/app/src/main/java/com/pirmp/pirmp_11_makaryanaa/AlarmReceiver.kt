package com.pirmp.pirmp_11_makaryanaa

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.pirmp.pirmp_11_makaryanaa.fragments.MediaPlayerFragment

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val builder = NotificationCompat.Builder(context, MediaPlayerFragment.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Wouldn't you like to listen to some nice music?")
            .setContentText("Radio Yerevan is waiting for you")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, builder.build())
    }
}