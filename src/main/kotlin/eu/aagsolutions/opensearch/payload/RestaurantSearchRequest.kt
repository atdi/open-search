package eu.aagsolutions.opensearch.payload

import org.opensearch.common.geo.GeoPoint

data class RestaurantSearchRequest(val geoPoint: GeoPoint, val distance: Double, val specific: String?)