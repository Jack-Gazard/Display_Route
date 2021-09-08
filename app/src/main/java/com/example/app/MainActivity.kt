package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.esri.arcgisruntime.ArcGISRuntimeEnvironment
import com.esri.arcgisruntime.geometry.Point
import com.esri.arcgisruntime.geometry.PointCollection
import com.esri.arcgisruntime.geometry.Polygon
import com.esri.arcgisruntime.geometry.Polyline
import com.esri.arcgisruntime.geometry.SpatialReferences
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.BasemapStyle
import com.esri.arcgisruntime.mapping.BasemapStyle.ARCGIS_TOPOGRAPHIC
import com.esri.arcgisruntime.mapping.Viewpoint
import com.esri.arcgisruntime.mapping.view.Graphic
import com.esri.arcgisruntime.mapping.view.GraphicsOverlay
import com.esri.arcgisruntime.mapping.view.MapView
import com.esri.arcgisruntime.symbology.SimpleFillSymbol
import com.esri.arcgisruntime.symbology.SimpleLineSymbol
import com.esri.arcgisruntime.symbology.SimpleMarkerSymbol
import com.esri.arcgisruntime.data.ServiceFeatureTable
import com.esri.arcgisruntime.layers.FeatureLayer
import com.esri.arcgisruntime.mapping.view.LocationDisplay
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.Toast
import androidx.core.content.ContextCompat





import com.example.app.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private val locationDisplay: LocationDisplay by lazy { mapView.locationDisplay }

private val activityMainBinding by lazy {
    ActivityMainBinding.inflate(layoutInflater)
}

private val mapView: MapView by lazy {
    activityMainBinding.mapView
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityMainBinding.root)
        setupMap()
    }




    private fun setupMap() {
        ArcGISRuntimeEnvironment.setApiKey("AAPK24c7359f636640faa3c9c52f19b2f091DJx5LAph95oDjdUvs8xyPiIgTTsR_k-NEmb-HxCV2WMkoETv1Aw3b3uI5M9mu9Hl")

        val map = ArcGISMap(BasemapStyle.ARCGIS_TOPOGRAPHIC_BASE)

        mapView.map = map
        mapView.setViewpoint(Viewpoint(40.7128, -74.0060, 360000.0))
        //Adds Line of route
        val serviceFeatureTable =
            ServiceFeatureTable("https://services6.arcgis.com/OO2s4OoyCZkYJ6oE/arcgis/rest/services/TestRoute1/FeatureServer/0")
        val featureLayer = FeatureLayer(serviceFeatureTable)
        map.operationalLayers.add(featureLayer)
        //Adds points of locations
        val serviceFeatureTable2 =
            ServiceFeatureTable("https://services6.arcgis.com/OO2s4OoyCZkYJ6oE/arcgis/rest/services/TestDest1/FeatureServer/0")
        val featureLayer2 = FeatureLayer(serviceFeatureTable2)
        map.operationalLayers.add(featureLayer2)

    }

    private fun addPoint() {
        val graphicsOverlay = GraphicsOverlay()
        mapView.graphicsOverlays.add(graphicsOverlay)
        //Creating point LATITUDE AND LONGITUDE ARE FLIPPED FOR X-Y COORDINATES
        val point = Point(-74.0060, 40.7128, SpatialReferences.getWgs84())
        //Assigning color and style with outline
        val simpleMarkerSymbol = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, -0xa8cd, 10f)
        val blueOutlineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c01, 2f)
        simpleMarkerSymbol.outline = blueOutlineSymbol
        // Val created with specified properties
        val pointGraphic = Graphic(point, simpleMarkerSymbol)

        //Adds point to map
        graphicsOverlay.graphics.add(pointGraphic)
    }
    private fun addPolyline() {
        val graphicsOverlay = GraphicsOverlay()
        mapView.graphicsOverlays.add(graphicsOverlay)
        val polylinePoints = PointCollection(SpatialReferences.getWgs84()).apply {
            // Point(latitude, longitude)
            add(Point(-118.8215, 34.0139))
            add(Point(-118.8148, 34.0080))
            add(Point(-118.8088, 34.0016))
        }

        // Takes points into polyline constructor
        val polyline = Polyline(polylinePoints)

        // create a blue line symbol for the polyline
        val polylineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c01, 3f)
        //Creates polyline value
        val polylineGraphic = Graphic(polyline, polylineSymbol)
        graphicsOverlay.graphics.add(polylineGraphic)

    }
    private fun addPolygon() {
        val graphicsOverlay = GraphicsOverlay()
        mapView.graphicsOverlays.add(graphicsOverlay)
        val simpleMarkerSymbol = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, -0xa8cd, 10f)
        val blueOutlineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c01, 2f)
        val polygonPoints = PointCollection(SpatialReferences.getWgs84()).apply {
            // Point(latitude, longitude)
            add(Point(-118.8189, 34.0137))
            add(Point(-118.8067, 34.0215))
            add(Point(-118.7914, 34.0163))
            add(Point(-118.7959, 34.0085))
            add(Point(-118.8085, 34.0035))
        }
        // create a polygon geometry from the point collection
        val polygon = Polygon(polygonPoints)

        // create an orange fill symbol with 20% transparency and the blue simple line symbol
        val polygonFillSymbol =
            SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, -0x7f00a8cd, blueOutlineSymbol)
        // create a polygon graphic from the polygon geometry and symbol
        val polygonGraphic = Graphic(polygon, polygonFillSymbol)
        // add the polygon graphic to the graphics overlay
        graphicsOverlay.graphics.add(polygonGraphic)


    }



    private fun addGraphics() {
        val graphicsOverlay = GraphicsOverlay()
        mapView.graphicsOverlays.add(graphicsOverlay)
        //Creating point
        val point = Point(-118.8065, 34.0005, SpatialReferences.getWgs84())
        //Assigning color and style with outline
        val simpleMarkerSymbol = SimpleMarkerSymbol(SimpleMarkerSymbol.Style.CIRCLE, -0xa8cd, 10f)
        val blueOutlineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c01, 2f)
        simpleMarkerSymbol.outline = blueOutlineSymbol
        // Val created with specified properties
        val pointGraphic = Graphic(point, simpleMarkerSymbol)

        //Adds point to map
        graphicsOverlay.graphics.add(pointGraphic)

        val polylinePoints = PointCollection(SpatialReferences.getWgs84()).apply {
            // Point(latitude, longitude)
            add(Point(-118.8215, 34.0139))
            add(Point(-118.8148, 34.0080))
            add(Point(-118.8088, 34.0016))
        }

        // Takes points into polyline constructor
        val polyline = Polyline(polylinePoints)

        // create a blue line symbol for the polyline
        val polylineSymbol = SimpleLineSymbol(SimpleLineSymbol.Style.SOLID, -0xff9c01, 3f)
        //Creates polyline value
        val polylineGraphic = Graphic(polyline, polylineSymbol)

        // adds value within function called in onCreate
        graphicsOverlay.graphics.add(polylineGraphic)

        val polygonPoints = PointCollection(SpatialReferences.getWgs84()).apply {
            // Point(latitude, longitude)
            add(Point(-118.8189, 34.0137))
            add(Point(-118.8067, 34.0215))
            add(Point(-118.7914, 34.0163))
            add(Point(-118.7959, 34.0085))
            add(Point(-118.8085, 34.0035))
        }
        // create a polygon geometry from the point collection
        val polygon = Polygon(polygonPoints)

        // create an orange fill symbol with 20% transparency and the blue simple line symbol
        val polygonFillSymbol =
            SimpleFillSymbol(SimpleFillSymbol.Style.SOLID, -0x7f00a8cd, blueOutlineSymbol)
        // create a polygon graphic from the polygon geometry and symbol
        val polygonGraphic = Graphic(polygon, polygonFillSymbol)
        // add the polygon graphic to the graphics overlay
        graphicsOverlay.graphics.add(polygonGraphic)

    }
    override fun onPause() {
        mapView.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onDestroy() {
        mapView.dispose()
        super.onDestroy()
    }
}