package eu.aagsolutions.opensearch.components

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.services.RestaurantService
import io.github.springwolf.core.asyncapi.annotations.AsyncListener
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OpenSearchIndexer(private val restaurantService: RestaurantService) {

    @KafkaListener(topics = ["INDEX_TOPIC"])
    @AsyncListener(
        operation = AsyncOperation(
            channelName = "INDEX_TOPIC",
            description = "Index restaurant data"
        )
    )
    @KafkaAsyncOperationBinding
    fun consume(restaurant: Restaurant) {
        restaurantService.indexDocument(restaurant)
    }
}