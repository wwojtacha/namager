package com.example.namager.logic;

import com.example.namager.model.SearchResult;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
@Component
public class ComparatorFactory {

    Map<String, Comparator<SearchResult>> comparatorsMap = new HashMap<>();

    {
        Comparator<SearchResult> nameComparator = Comparator.comparing(SearchResult::getName);
        Comparator<SearchResult> ageComparator = Comparator.comparing(SearchResult::getAge);

        comparatorsMap.put("name", nameComparator);
        comparatorsMap.put("age", ageComparator);
    }

    public Comparator<SearchResult> getComparator(String sortParam) {
        return comparatorsMap.get(sortParam);
    }

}
