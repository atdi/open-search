package eu.aagsolutions.opensearch.services

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.repositories.RestaurantRepository
import org.opensearch.common.geo.GeoPoint
import org.opensearch.search.SearchHit
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RestaurantService(val restaurantRepository: RestaurantRepository) {
    fun findRadiusSearch(point: GeoPoint, distance: Double): List<Restaurant> {
        return restaurantRepository.searchWithin(point, distance, "km")
    }

    fun indexDocument(restaurant: Restaurant): Restaurant {
        return restaurantRepository.save(restaurant)
    }
}