package com.example.namager;

import com.example.namager.logic.ComparatorFactory;
import com.example.namager.logic.SearchResultLogic;
import com.example.namager.model.AgifyModel;
import com.example.namager.model.SearchResult;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SearchResultTestConfiguration.class })
public class NamagerLogicTests {
    @Autowired
    private SearchResultLogic searchResultLogic;
    @Autowired
    private ComparatorFactory comparatorFactory;

    @Before
    public void init() {

        SearchResult searchResultMagda1 = SearchResult.create("magda", 12);
        SearchResult searchResultMagda2 = SearchResult.create("magda", 12);
        SearchResult searchResultMagda3 = SearchResult.create("magda", 12);
        List<SearchResult> magdaSearchResults = new ArrayList<>(Arrays.asList(searchResultMagda1, searchResultMagda2, searchResultMagda3));

        SearchResult searchResultFred = SearchResult.create("fred", 23);
        SearchResult searchResultStefan = SearchResult.create("stefan", 32);
        SearchResult searchResultZbigniew = SearchResult.create("zbigniew", 10);

        ConcurrentMap<String, List<SearchResult>> searchResults = searchResultLogic.getSearchResults();

        searchResults.put("magda", magdaSearchResults);
        searchResults.put("fred", List.of(searchResultFred));
        searchResults.put("stefan", List.of(searchResultStefan));
        searchResults.put("zbigniew", List.of(searchResultZbigniew));
    }

    @Test
    public void shouldSortByName() {
        List<SearchResult> sortedByName = shouldReturnNotEmptyList("name");

        String expectedFirstName = "fred";
        String firstNameMessage = "Expected different name at the beginning of list.";

        Assertions.assertEquals(expectedFirstName, sortedByName.get(0).getName(), firstNameMessage);
    }

    @Test
    public void shouldSortByAge() {
        List<SearchResult> sortedByAge = shouldReturnNotEmptyList("age");

        int expectedFirstAge = 10;
        String firstAgeMessage = "Expected different age at the beginning of list";

        Assertions.assertEquals(expectedFirstAge, sortedByAge.get(0).getAge(), firstAgeMessage);
    }

    @Test
    public void shouldReturnUniqueNames() {
        Set<String> allSearchedNames = searchResultLogic.getAllSearchedNames();

        int expectedNamesAmount = 4;
        int actualNamesAmount = allSearchedNames.size();
        String message = "Expected different amount of unique names";

        Assertions.assertEquals(expectedNamesAmount, actualNamesAmount, message);
    }

    @Test
    public void shouldReturnNullComparator() {
        Comparator<SearchResult> comparator = comparatorFactory.getComparator("invalid_sort_param");
        String message = "Should not find proper comparator.";
        Assertions.assertNull(comparator, message);
    }

    @Test
    public void shouldReturnValidSearchResult() {
        AgifyModel ageResult = searchResultLogic.getAgeResultByName("magda");
        String message = "Expected to get a valid response from Agify API.";

        Assertions.assertTrue(ageResult.isValid(), message);
    }

    private List<SearchResult> shouldReturnNotEmptyList(String sortParam) {
        List<SearchResult> sortedByParam = searchResultLogic.getSortedSearchResults(sortParam);
        String notEmptyMessage = "Sorted list must not be empty";
        Assertions.assertFalse(sortedByParam.isEmpty(), notEmptyMessage);

        return sortedByParam;
    }
}
