package eu.aagsolutions.opensearch.services

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.repositories.RestaurantRepository
import eu.aagsolutions.opensearch.responses.SearchResult
import org.opensearch.common.geo.GeoPoint
import org.springframework.stereotype.Service

@Service
class RestaurantService(val restaurantRepository: RestaurantRepository) {
    fun findRadiusSearch(point: GeoPoint, distance: Double): SearchResult {
        return restaurantRepository.searchWithin(point, distance, "km")
    }

    fun indexDocument(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }
}