package com.example.namager.aspect;

import com.example.namager.model.AgifyModel;
import com.example.namager.logic.SearchResultLogic;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HistoryAspect {

    private final SearchResultLogic searchResultLogic;

    public HistoryAspect(SearchResultLogic searchResultLogic) {
        this.searchResultLogic = searchResultLogic;
    }

    @AfterReturning(value = "@annotation(History)", returning = "result")
    public void addToHistory(AgifyModel result) {
        searchResultLogic.archiveSearchResult(result);
    }

}
