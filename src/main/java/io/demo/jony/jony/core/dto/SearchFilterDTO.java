package io.demo.jony.jony.core.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO for filter the search.
 *
 * @author Virtus
 */
public class SearchFilterDTO {

    /**
     * Current page.
     */
    private Integer currentPage = 1;

    /**
     * Page size.
     */
    private Integer pageSize = 10;

    /**
     * Sort Page (ASC, DESC)
     */
    private String sort = "";

    /**
     * Column to sorting.
     */
    private String column = "";

    /**
     * Value to search in all columns.
     */
    private String search = "";

    /**
     * Filters.
     */
    private List<FilterDTO> filters = new ArrayList<>();

    /**
     * Gets the current page.
     *
     * @return Current page.
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * Sets the current page.
     *
     * @param currentPage Current page.
     */
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * Gets the page size.
     *
     * @return Page size.
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize Page size.
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get the filters.
     *
     * @return Filters.
     */
    public List<FilterDTO> getFilters() {
        return filters;
    }

    /**
     * Sets the filters.
     *
     * @param filters Filters.
     */
    public void setFilters(List<FilterDTO> filters) {
        this.filters = filters;
    }

    /**
     * Get the Sort
     *
     * @return
     */
    public String getSort() {
        return sort;
    }

    /**
     * Set Sort method.
     *
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * Get Column sort.
     *
     * @return
     */
    public String getColumn() {
        return column;
    }

    /**
     * Set Column sort.
     *
     * @param column
     */
    public void setColumn(String column) {
        this.column = column;
    }

    /**
     * Get the search value.
     *
     * @return
     */
    public String getSearch() {
        return search;
    }

    /**
     * Set the search value.
     *
     * @param search
     */
    public void setSearch(String search) {
        this.search = search;
    }
}
