package brandy.newcld.pokemon.ui.util

import android.media.AudioAttributes
import android.media.MediaPlayer
import brandy.newcld.pokemon.presentation.util.SoundPlayer

class AppSoundPlayer : SoundPlayer {
    private var player: MediaPlayer? = null

    override fun play(url: String) {
        player?.reset() ?: run { player = MediaPlayer() }
        player?.apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
            setOnPreparedListener { it.start() }
            setOnErrorListener { _, _, _ ->
                reset()
                true
            }
            setDataSource(url)
            prepareAsync()
        }
    }

    override fun stop() {
        player?.reset()
    }

    override fun release() {
        player?.release()
        player = null
    }
}
