package eu.aagsolutions.opensearch.repositories

import eu.aagsolutions.opensearch.model.Restaurant
import org.opensearch.action.search.SearchRequest
import org.opensearch.action.search.SearchResponse
import org.opensearch.client.RequestOptions
import org.opensearch.client.RestHighLevelClient
import org.opensearch.common.geo.GeoPoint
import org.opensearch.index.query.QueryBuilders
import org.opensearch.search.builder.SearchSourceBuilder
import java.util.*

class GeoRepositoryImpl(private val openSearchClient: RestHighLevelClient) : GeoRepository {
    override fun searchWithin(geoPoint: GeoPoint?, distance: Double?, unit: String?): List<Restaurant> {
        val searchRequest = SearchRequest("restaurants")

        val searchSourceBuilder = SearchSourceBuilder()
            .query(
                QueryBuilders.geoDistanceQuery("location")
                    .point(geoPoint)
                    .distance("${distance}${unit}")
            )
            .size(10)


        searchRequest.source(searchSourceBuilder)

        val searchResponse: SearchResponse = openSearchClient.search(searchRequest, RequestOptions.DEFAULT)
        val hits = searchResponse.hits
        return hits.map { h ->
            Restaurant(
                UUID.fromString(h.id),
                h.sourceAsMap["name"].toString(),
                h.sourceAsMap["city"].toString(),
                h.sourceAsMap["country"].toString(),
                h.sourceAsMap["address"].toString(),
                h.sourceAsMap["specific"].toString(),
                GeoPoint(
                    (h.sourceAsMap["location"] as Map<String, Double>)["lat"]!!,
                    (h.sourceAsMap["location"] as Map<String, Double>)["lon"]!!
                )
            )
        }
    }
}