package com.example.namager.api;

import com.example.namager.model.AgifyModel;
import com.example.namager.model.SearchResult;
import com.example.namager.logic.SearchResultLogic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/age")
public class SearchResultController {

    private final SearchResultLogic searchResultLogic;

    public SearchResultController(SearchResultLogic searchResultLogic) {
        this.searchResultLogic = searchResultLogic;
    }

    @GetMapping()
    public ResponseEntity<AgifyModel> getAgeResult(
            @RequestParam(name = "name") String name
    ) {
        AgifyModel ageResult = searchResultLogic.getAgeResultByName(name);
        return ageResult.isValid() ? new ResponseEntity<>(ageResult, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/history")
    public List<SearchResult> getSearchResultHistory(
            @RequestParam(name = "sortParam", required = false, defaultValue = "") String sortParam
    ) {

        if (!sortParam.isBlank()) {
            return searchResultLogic.getSortedSearchResults(sortParam);
        } else {
            return searchResultLogic.getAllSearchResults();
        }
    }

    @GetMapping("/names")
    public Set<String> getAllSearchedName() {
        return searchResultLogic.getAllSearchedNames();
    }

}