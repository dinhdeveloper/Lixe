package com.main.activity


import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_film_detail.*


const val YOUTUBE_VIDEO_ID = "-3P5S5R15wI" //DZDYZ9nRHfU

class FilmDetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    companion object {
        const val youtube_api_key: String = "AIzaSyD7FTYSJuVBwcV3tEYJsf1DyhOMlbdt8Qg"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_film_detail)
        youtube_view.initialize(youtube_api_key, this)

        getData()
    }

    private fun getData() {
        var intent = intent
        //var product = intent.getSerializableExtra("FILM") as Film

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_film_detail, menu)
        return true
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youTubePlayer: YouTubePlayer?,
        b: Boolean
    ) {
        if (!b) {
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
            youTubePlayer?.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
            youTubePlayer?.play()
            youTubePlayer?.seekToMillis(1)
        }

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        result: YouTubeInitializationResult?
    ) {
        if (result!!.isUserRecoverableError) {
            result!!.getErrorDialog(this, 1).show()
        } else {
            val error =
                String.format("Error initializing YouTube player", result.toString())
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        }
    }

}
