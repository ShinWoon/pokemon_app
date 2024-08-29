package com.example.pokemonwidget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size

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
        glanceModifier: GlanceModifier = GlanceModifier,
    ) {
        Row(
            modifier = glanceModifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .background(R.color.purple_200),
            horizontalAlignment = Alignment.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PokeImgContent()
            Column {

            }
        }
    }

    @Composable
    private fun PokeImgContent(
        modifier: GlanceModifier = GlanceModifier,
    ) {
        Box(
            modifier = modifier
                .size(width = 72.dp, height = 96.dp)
                .background(R.color.teal_700)
                .cornerRadius(120.dp),
        ) {

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
