package eu.aagsolutions.opensearch.repositories

import eu.aagsolutions.opensearch.model.Restaurant
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RestaurantRepository : CrudRepository<Restaurant, UUID> {

}