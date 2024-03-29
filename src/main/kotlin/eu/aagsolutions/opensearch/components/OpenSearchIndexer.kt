package eu.aagsolutions.opensearch.components

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.services.RestaurantService
import io.github.springwolf.core.asyncapi.annotations.AsyncListener
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.kafka.support.mapping.AbstractJavaTypeMapper.DEFAULT_CLASSID_FIELD_NAME


@Component
class OpenSearchIndexer(private val restaurantService: RestaurantService) {

    @KafkaListener(topics = ["INDEX_TOPIC"])
    @AsyncListener(
        operation = AsyncOperation(
            channelName = "INDEX_TOPIC",
            description = "Incoming topic",
            headers = AsyncOperation.Headers(
                schemaName = "SpringKafkaDefaultHeadersIncomingPayloadDto",
                values = [
                    // this header is generated by Spring by default
                    AsyncOperation.Headers.Header(
                        name = DEFAULT_CLASSID_FIELD_NAME,
                        description = "Spring Type Id Header",
                        value = "eu.aagsolutions.opensearch.model.Restaurant")
                ]
            )
        )
    )
    @KafkaAsyncOperationBinding
    fun consume(restaurant: Restaurant) {
        restaurantService.indexDocument(restaurant)
    }
}