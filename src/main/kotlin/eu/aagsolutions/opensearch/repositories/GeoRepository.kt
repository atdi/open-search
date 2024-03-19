package eu.aagsolutions.opensearch.repositories

import eu.aagsolutions.opensearch.payload.RestaurantSearchRequest
import eu.aagsolutions.opensearch.responses.SearchResult


interface GeoRepository {
    fun searchWithin(restaurantSearch: RestaurantSearchRequest, unit: String?): SearchResult
}