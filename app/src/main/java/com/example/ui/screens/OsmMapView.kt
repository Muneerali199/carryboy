package com.example.ui.screens

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline

@Composable
fun OsmMapView(
    modifier: Modifier = Modifier,
    targetLocation: GeoPoint?,
    workerLocation: GeoPoint?
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            Configuration.getInstance().load(context, context.getSharedPreferences("osm", Context.MODE_PRIVATE))
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(15.0)
                
                // Set default center
                val startPoint = targetLocation ?: GeoPoint(28.6139, 77.2090) // New Delhi
                controller.setCenter(startPoint)
            }
        },
        update = { mapView ->
            mapView.overlays.clear()
            
            if (targetLocation != null) {
                val targetMarker = Marker(mapView)
                targetMarker.position = targetLocation
                targetMarker.title = "Pickup Location"
                mapView.overlays.add(targetMarker)
                mapView.controller.animateTo(targetLocation)
            }
            
            if (workerLocation != null) {
                val workerMarker = Marker(mapView)
                workerMarker.position = workerLocation
                workerMarker.title = "Worker Location"
                mapView.overlays.add(workerMarker)
            }
            
            if (targetLocation != null && workerLocation != null) {
                val line = Polyline(mapView)
                line.addPoint(targetLocation)
                line.addPoint(workerLocation)
                mapView.overlays.add(line)
                
                // Zoom to fit both
                val centerLat = (targetLocation.latitude + workerLocation.latitude) / 2
                val centerLon = (targetLocation.longitude + workerLocation.longitude) / 2
                mapView.controller.setCenter(GeoPoint(centerLat, centerLon))
                mapView.controller.setZoom(14.0)
            }
            
            mapView.invalidate()
        }
    )
}
