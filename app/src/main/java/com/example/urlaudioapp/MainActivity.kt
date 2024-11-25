package com.example.urlaudioapp

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.urlaudioapp.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.btUrlAudio.setOnClickListener {

            try {

                mediaPlayer = MediaPlayer()

                mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)

                mediaPlayer?.setDataSource("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3") // Replace with your audio URL
                mediaPlayer?.prepare() // Prepares the MediaPlayer
                mediaPlayer?.start() // Starts the playback
            } catch (e: IOException) {
                e.printStackTrace() // Handle errors (e.g., invalid URL)
            }

            mediaPlayer?.setOnCompletionListener {
                // Release MediaPlayer when audio is done
                it.release()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer?.release() // Always release when done
        mediaPlayer = null
    }

}