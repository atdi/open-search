package eu.aagsolutions.opensearch.repositories

import eu.aagsolutions.opensearch.model.Restaurant
import eu.aagsolutions.opensearch.responses.SearchFacet
import eu.aagsolutions.opensearch.responses.SearchResult
import org.opensearch.action.search.SearchRequest
import org.opensearch.action.search.SearchResponse
import org.opensearch.client.RequestOptions
import org.opensearch.client.RestHighLevelClient
import org.opensearch.common.geo.GeoPoint
import org.opensearch.index.query.QueryBuilders
import org.opensearch.search.aggregations.AggregationBuilders
import org.opensearch.search.aggregations.bucket.terms.ParsedStringTerms
import org.opensearch.search.builder.SearchSourceBuilder
import java.util.*

class GeoRepositoryImpl(private val openSearchClient: RestHighLevelClient) : GeoRepository {
    override fun searchWithin(geoPoint: GeoPoint?, distance: Double?, unit: String?): SearchResult {
        val searchRequest = SearchRequest("restaurants")
        val searchSourceBuilder = SearchSourceBuilder()
            .query(
                QueryBuilders.geoDistanceQuery("location")
                    .point(geoPoint)
                    .distance("${distance}${unit}")
            ).aggregation(AggregationBuilders.terms("specific").field("specific"))


        searchRequest.source(searchSourceBuilder)

        val searchResponse: SearchResponse = openSearchClient.search(searchRequest, RequestOptions.DEFAULT)
        val hits = searchResponse.hits
        var filters: List<SearchFacet> = ArrayList()
        val aggregation = searchResponse.aggregations.map { agg -> agg }[0]
        if (aggregation is ParsedStringTerms) {
            filters = aggregation.buckets.map { bucket -> SearchFacet(bucket.keyAsString, bucket.docCount) }
        }
        val restaurants = hits.map { h ->
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
        return SearchResult(restaurants, filters)
    }
}