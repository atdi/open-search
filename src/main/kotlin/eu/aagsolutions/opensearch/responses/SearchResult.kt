package eu.aagsolutions.opensearch.responses

import eu.aagsolutions.opensearch.model.Restaurant

data class SearchResult(val restaurants: List<Restaurant>, val filters: List<SearchFacet>)