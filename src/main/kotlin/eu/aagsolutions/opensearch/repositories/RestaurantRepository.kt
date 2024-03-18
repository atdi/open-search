package eu.aagsolutions.opensearch.repositories

import eu.aagsolutions.opensearch.model.Restaurant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RestaurantRepository : CrudRepository<Restaurant, UUID>, GeoRepository {

}