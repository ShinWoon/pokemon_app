package brandy.newcld.pokemon.ui.util

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import brandy.newcld.pokemon.presentation.viewmodel.SoundPlayer

class AppSoundPlayer(context: Context): SoundPlayer {
    private val player = ExoPlayer.Builder(context).build()
    override fun play(url: String) {
        player.setMediaItem(MediaItem.fromUri(url))
        player.prepare()
        player.play()
    }

    override fun stop() {
        player.stop()
    }

    fun release() {
        player.release()
    }

}