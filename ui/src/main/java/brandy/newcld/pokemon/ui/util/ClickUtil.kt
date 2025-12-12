package brandy.newcld.pokemon.ui.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.IndicationNodeFactory
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.node.DelegatableNode
import androidx.compose.ui.node.DrawModifierNode
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private class ScaleNode(private val interactionSource: InteractionSource) :
    Modifier.Node(), DrawModifierNode {
    val animatedScalePercent = Animatable(1f)

    private suspend fun animateToPressed() {
        animatedScalePercent.animateTo(0.9f, spring())
    }

    private suspend fun animateToResting() {
        animatedScalePercent.animateTo(1f, spring())
    }

    override fun onAttach() {
        coroutineScope.launch {
            interactionSource.interactions.collectLatest { interaction ->
                when(interaction) {
                    is PressInteraction.Press -> animateToPressed()
                    is PressInteraction.Release -> animateToResting()
                    is PressInteraction.Cancel -> animateToResting()
                }
            }
        }
    }

    override fun onDetach() {
        coroutineScope.launch {
            animatedScalePercent.stop()
        }
    }
    override fun ContentDrawScope.draw() {
        scale(
            scale = animatedScalePercent.value,
            pivot = center
        ) {
            this@draw.drawContent()
        }
    }
}

object ScaleIndication: IndicationNodeFactory {
    override fun create(interactionSource: InteractionSource): DelegatableNode = ScaleNode(interactionSource = interactionSource)

    override fun equals(other: Any?): Boolean = other == this

    override fun hashCode(): Int = System.identityHashCode(this)
}