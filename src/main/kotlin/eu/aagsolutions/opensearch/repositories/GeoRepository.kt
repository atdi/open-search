package eu.aagsolutions.opensearch.repositories

import eu.aagsolutions.opensearch.responses.SearchResult
import org.opensearch.common.geo.GeoPoint


interface GeoRepository {
    fun searchWithin(geoPoint: GeoPoint?, distance: Double?, unit: String?): SearchResult
}