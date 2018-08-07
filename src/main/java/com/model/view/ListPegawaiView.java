package com.model.view;

import java.util.List;

public class ListPegawaiView {
    private String search;
    private List<PegawaiFormView> pegawaiFormViewList;
    private Integer items;
    private Integer itemsOnPage;
    private String query;
    private String currentPage;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public List<PegawaiFormView> getPegawaiFormViewList() {
        return pegawaiFormViewList;
    }

    public void setPegawaiFormViewList(List<PegawaiFormView> pegawaiFormViewList) {
        this.pegawaiFormViewList = pegawaiFormViewList;
    }

    public Integer getItems() {
        return items;
    }

    public void setItems(Integer items) {
        this.items = items;
    }

    public Integer getItemsOnPage() {
        return itemsOnPage;
    }

    public void setItemsOnPage(Integer itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }
}
