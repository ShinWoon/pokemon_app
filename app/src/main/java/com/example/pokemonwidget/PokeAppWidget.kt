package com.example.pokemonwidget

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.layout.Alignment
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize

class PokeAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            GlanceTheme {
                PokeWidgetContent()
            }
        }
    }

    @Composable
    private fun PokeWidgetContent(
        modifier: GlanceModifier = GlanceModifier
    ) {
        Row(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column {

            }
            Column {

            }
        }
    }


    @Preview
    @Composable
    fun PokeWidgetContentPreview() {
        GlanceTheme {
            PokeWidgetContent()
        }
    }
}