package eu.aagsolutions.opensearch.controllers

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.payload.GeoSearch
import eu.aagsolutions.opensearch.services.RestaurantService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/index")
@RestController
class IndexController(private val restaurantService: RestaurantService) {

    @PostMapping
    fun index(@RequestBody restaurant: Restaurant): ResponseEntity<Restaurant> {
        return ResponseEntity.ok(restaurantService.indexDocument(restaurant))
    }
}