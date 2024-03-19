package eu.aagsolutions.opensearch.model

import org.opensearch.common.geo.GeoPoint
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.UUID

@Document(indexName = "restaurants")
data class Restaurant(@Id
                      val id: UUID,
                      @Field(type = FieldType.Text, name = "name")
                      val name: String,
                      @Field(type = FieldType.Text, name = "city")
                      val city: String,
                      @Field(type = FieldType.Text, name = "country")
                      val country: String,
                      @Field(type = FieldType.Text, name = "address")
                      val address: String,
                      @Field(type = FieldType.Keyword, name = "specific")
                      val specific: String,
                      @Field(name = "location", type = FieldType.Auto)
                      val location: GeoPoint
)
