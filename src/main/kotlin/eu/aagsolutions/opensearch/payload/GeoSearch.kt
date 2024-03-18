package eu.aagsolutions.opensearch.payload

import org.opensearch.common.geo.GeoPoint

data class GeoSearch(val geoPoint: GeoPoint, val distance: Double)