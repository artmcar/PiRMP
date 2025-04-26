package com.pirmp.pirmp_11_makaryanaa.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.pirmp.pirmp_11_makaryanaa.R


class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnWebView = view.findViewById<Button>(R.id.button_web_view)
        val btnMediaPlayer = view.findViewById<Button>(R.id.button_media_player)
        val controller = findNavController()
        btnWebView.setOnClickListener{ controller.navigate(R.id.webViewFragment)}
        btnMediaPlayer.setOnClickListener { controller.navigate(R.id.mediaPlayerFragment) }
    }


}