package eu.aagsolutions.opensearch.services

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.payload.RestaurantSearchRequest
import eu.aagsolutions.opensearch.repositories.RestaurantRepository
import eu.aagsolutions.opensearch.responses.SearchResult
import org.springframework.stereotype.Service

@Service
class RestaurantService(val restaurantRepository: RestaurantRepository) {
    fun findRadiusSearch(restaurantSearchRequest: RestaurantSearchRequest): SearchResult {
        return restaurantRepository.searchWithin(restaurantSearchRequest, "km")
    }

    fun indexDocument(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }
}