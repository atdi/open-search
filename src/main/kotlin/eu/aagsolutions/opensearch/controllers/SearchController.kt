package eu.aagsolutions.opensearch.controllers

import eu.aagsolutions.opensearch.payload.GeoSearch
import eu.aagsolutions.opensearch.responses.SearchResult
import eu.aagsolutions.opensearch.services.RestaurantService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/search")
@RestController
class SearchController(private val restaurantService: RestaurantService) {

    @PostMapping
    fun searchByLocation(@RequestBody geoSearch: GeoSearch): ResponseEntity<SearchResult> {
        return ResponseEntity.ok(restaurantService.findRadiusSearch(geoSearch.geoPoint, geoSearch.distance))
    }
}