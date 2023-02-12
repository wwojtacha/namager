package com.example.namager.logic;

import com.example.namager.aspect.History;
import com.example.namager.model.AgifyModel;
import com.example.namager.model.SearchResult;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

@Service
public class SearchResultLogic {
    private final ConcurrentMap<String, List<SearchResult>> searchResults = new ConcurrentHashMap<>();
    private final ComparatorFactory comparatorFactory;
    private final AgifyDataProvider agifyDataProvider;
    public SearchResultLogic(ComparatorFactory comparatorFactory, AgifyDataProvider agifyDataProvider) {
        this.comparatorFactory = comparatorFactory;
        this.agifyDataProvider = agifyDataProvider;
    }

    public ConcurrentMap<String, List<SearchResult>> getSearchResults() {
        return searchResults;
    }

    public Set<String> getAllSearchedNames () {
        return searchResults.keySet();
    }

    public List<SearchResult> getSortedSearchResults(String sortParam) {
        List<SearchResult> allSearchResults = getAllSearchResults();
        Optional.ofNullable(comparatorFactory.getComparator(sortParam)).ifPresent(allSearchResults::sort);

        return allSearchResults;
    }

    public List<SearchResult> getAllSearchResults () {
        return searchResults.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
    }

    @History
    public AgifyModel getAgeResultByName(String name) {
        return agifyDataProvider.getAgeResultByName(name);
    }

    public void archiveSearchResult(AgifyModel ageResponse) {
        if (ageResponse.isValid()) {
            String name = ageResponse.getName();
            int age = ageResponse.getAge();
            SearchResult searchResult = SearchResult.create(name, age);

            List<SearchResult> searchResultsByName = searchResults.get(name);

            if (searchResultsByName != null) {
                searchResultsByName.add(searchResult);
            } else {
                searchResults.put(name, new ArrayList<>(Arrays.asList(searchResult)));
            }
        }
    }

}
