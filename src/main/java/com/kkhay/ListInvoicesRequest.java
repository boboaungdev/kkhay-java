package com.kkhay;

import java.util.HashMap;
import java.util.Map;

/**
 * Filter parameters for querying and paginating gateway invoices.
 */
public class ListInvoicesRequest {
    private Integer page;
    private Integer limit;
    private String status;
    private String type;
    private String search;

    public ListInvoicesRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    public Integer getPage() { return page; }
    public void setPage(Integer page) { this.page = page; }

    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getSearch() { return search; }
    public void setSearch(String search) { this.search = search; }

    public Map<String, String> toQueryParams() {
        Map<String, String> params = new HashMap<>();
        if (page != null && page > 0) params.put("page", String.valueOf(page));
        if (limit != null && limit > 0) params.put("limit", String.valueOf(limit));
        if (status != null && !status.trim().isEmpty()) params.put("status", status.trim());
        if (type != null && !type.trim().isEmpty()) params.put("type", type.trim());
        if (search != null && !search.trim().isEmpty()) params.put("search", search.trim());
        return params;
    }

    public static class Builder {
        private final ListInvoicesRequest request = new ListInvoicesRequest();

        public Builder page(int page) {
            request.setPage(page);
            return this;
        }

        public Builder limit(int limit) {
            request.setLimit(limit);
            return this;
        }

        public Builder status(String status) {
            request.setStatus(status);
            return this;
        }

        public Builder type(String type) {
            request.setType(type);
            return this;
        }

        public Builder search(String search) {
            request.setSearch(search);
            return this;
        }

        public ListInvoicesRequest build() {
            return request;
        }
    }
}
