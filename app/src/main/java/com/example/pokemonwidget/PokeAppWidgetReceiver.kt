package com.example.pokemonwidget

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver

class PokeAppWidgetReceiver(override val glanceAppWidget: GlanceAppWidget = PokeAppWidget()) :
    GlanceAppWidgetReceiver() {
}