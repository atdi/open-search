package eu.aagsolutions.opensearch.components

import eu.aagsolutions.opensearch.model.Restaurant
import io.github.springwolf.core.asyncapi.annotations.AsyncMessage
import io.github.springwolf.core.asyncapi.annotations.AsyncOperation
import io.github.springwolf.core.asyncapi.annotations.AsyncPublisher
import io.github.springwolf.plugins.kafka.asyncapi.annotations.KafkaAsyncOperationBinding
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class IndexPublisher(private val kafkaTemplate: KafkaTemplate<String, Restaurant>) {

    @AsyncPublisher(
        operation = AsyncOperation(
            channelName = "INDEX_TOPIC",
            description = "Topic for receiving indexing messages",
            payloadType = Restaurant::class
        )
    )
    @KafkaAsyncOperationBinding
    fun publish(restaurant: Restaurant) {
        kafkaTemplate.send("INDEX_TOPIC", restaurant)
    }
}