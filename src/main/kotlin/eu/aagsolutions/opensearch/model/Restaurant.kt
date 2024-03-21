package eu.aagsolutions.opensearch.model

import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.opensearch.common.geo.GeoPoint
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.util.*

@Schema(description = "Restaurant indexing model")
@Document(indexName = "restaurants")
data class Restaurant(
    @Schema(type = "string", format = "uuid", example = "ff59014a-a94f-4f33-ae75-2f07fe24e206", requiredMode = REQUIRED)
    @NotBlank
    @Id
    val id: UUID,
    @Schema(type = "string", example = "Chop suy", requiredMode = REQUIRED)
    @NotBlank
    @Field(type = FieldType.Text, name = "name")
    val name: String,
    @Schema(type = "string", example = "London", requiredMode = REQUIRED)
    @NotBlank
    @Field(type = FieldType.Text, name = "city")
    val city: String,
    @Schema(type = "string", example = "England", requiredMode = REQUIRED)
    @NotBlank
    @Field(type = FieldType.Text, name = "country")
    val country: String,
    @Schema(type = "string", example = "Bond Street, 2", requiredMode = REQUIRED)
    @NotBlank
    @Field(type = FieldType.Text, name = "address")
    val address: String,
    @Schema(allowableValues=["italian", "indian", "french", "chinese", "japanese", "vietnamese"],
        type = "string", example = "Chop suy", requiredMode = REQUIRED)
    @NotBlank
    @Field(type = FieldType.Keyword, name = "specific")
    val specific: String,
    @Schema(type = "object", ref = "GeoPoint", requiredMode = REQUIRED)
    @NotNull
    @Field(name = "location", type = FieldType.Auto)
    val location: GeoPoint
)
