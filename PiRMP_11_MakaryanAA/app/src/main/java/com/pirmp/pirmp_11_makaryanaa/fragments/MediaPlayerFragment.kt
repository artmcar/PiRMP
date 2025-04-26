package com.pirmp.pirmp_11_makaryanaa.fragments

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.NotificationCompat
import com.pirmp.pirmp_11_makaryanaa.AlarmReceiver
import com.pirmp.pirmp_11_makaryanaa.R
import java.io.IOException


class MediaPlayerFragment : Fragment() {
    private var mediaPlayer: MediaPlayer? = null
    private var scaleAnimator: ObjectAnimator? = null
    private var rotateAnim: ObjectAnimator? = null
    companion object {
        const val CHANNEL_ID = "example_channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createNotificationChannel()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_media_player, container, false)


        val btnPlay = view.findViewById<Button>(R.id.button_play)
        val btnBack = view.findViewById<Button>(R.id.button_back_mp)
        val btnClckOnMe = view.findViewById<Button>(R.id.button_clickonme)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val scaleText = view.findViewById<TextView>(R.id.textView)

        mediaPlayer = MediaPlayer()
        try {
            //mediaPlayer?.setDataSource("https://eu1.stream4cast.com/proxy/publicra/stream") // Hayastani Azgain Radio 1
            mediaPlayer?.setDataSource("https://eu1.stream4cast.com/proxy/arradioi/stream") //Radio Yerevan
            mediaPlayer?.prepareAsync() // Можно использовать prepareAsync() для сетевых потоков
        } catch (e: IOException) {
            e.printStackTrace()
        }


        btnPlay.setOnClickListener { v: View ->
            if (mediaPlayer?.isPlaying == false) {
                mediaPlayer?.start()
                startAnimation(imageView, scaleText)

                val builder = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle("You're listening to Radio Yerevan")
                    .setContentText("FM 101.9")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                val notificationManager = requireContext().getSystemService(NotificationManager::class.java)
                notificationManager.notify(1, builder.build())
                scheduleNotification(10000)

            } else {
                mediaPlayer?.pause()
            } }



        btnBack.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }


        var isMovedRight = false
        btnClckOnMe.setOnClickListener {
            val moveToSideAnim = ObjectAnimator.ofFloat(
                btnClckOnMe, "translationX", 0f, if (isMovedRight) -300f else 300f
            )
            moveToSideAnim.duration = 1000

            val returnToCenterAnim = ObjectAnimator.ofFloat(btnClckOnMe, "translationX", if (isMovedRight) -300f else 300f, 0f)
            returnToCenterAnim.duration = 1000

            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(moveToSideAnim, returnToCenterAnim)
            animatorSet.start()

            isMovedRight = !isMovedRight
        }


        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer?.release()
        mediaPlayer = null

        scaleAnimator?.cancel()
        rotateAnim?.cancel()
    }


    fun startAnimation(imageView: ImageView, scaleText: TextView){
        scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(
            scaleText,
            PropertyValuesHolder.ofFloat("scaleX", 1f, 2f),
            PropertyValuesHolder.ofFloat("scaleY", 1f, 2f)
        ).apply {
            duration = 1000
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
            start()
        }

        rotateAnim = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f).apply {
            setDuration(3500)
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.RESTART
            start()
        }

    }

    private fun createNotificationChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name: CharSequence = "Example Channel"
            val description = "Channel for example notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                this.description = description
            }
            val notificationManager = requireContext().getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun scheduleNotification(delay: Long){
        val notificationIntent = Intent(requireContext(), AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val futureInMillis = System.currentTimeMillis() + delay
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, futureInMillis, pendingIntent)
    }


}