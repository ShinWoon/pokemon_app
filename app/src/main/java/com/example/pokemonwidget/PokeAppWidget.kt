package com.example.pokemonwidget

import android.content.Context
import androidx.compose.material3.Text
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent

class PokeAppWidget: GlanceAppWidget() {
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Text(text = "Hello World")
        }
    }
}