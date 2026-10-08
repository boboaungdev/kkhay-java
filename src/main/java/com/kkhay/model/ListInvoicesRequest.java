package com.kkhay.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Filter parameters for querying invoices.
 */
public class ListInvoicesRequest {
    private String status;
    private Integer limit;
    private String startingAfter;
    private String endingBefore;

    public ListInvoicesRequest() {}

    public static Builder builder() {
        return new Builder();
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getLimit() { return limit; }
    public void setLimit(Integer limit) { this.limit = limit; }

    public String getStartingAfter() { return startingAfter; }
    public void setStartingAfter(String startingAfter) { this.startingAfter = startingAfter; }

    public String getEndingBefore() { return endingBefore; }
    public void setEndingBefore(String endingBefore) { this.endingBefore = endingBefore; }

    public Map<String, String> toQueryParams() {
        Map<String, String> params = new HashMap<>();
        if (status != null && !status.isEmpty()) params.put("status", status);
        if (limit != null) params.put("limit", String.valueOf(limit));
        if (startingAfter != null && !startingAfter.isEmpty()) params.put("starting_after", startingAfter);
        if (endingBefore != null && !endingBefore.isEmpty()) params.put("ending_before", endingBefore);
        return params;
    }

    public static class Builder {
        private final ListInvoicesRequest request = new ListInvoicesRequest();

        public Builder status(String status) {
            request.setStatus(status);
            return this;
        }

        public Builder limit(int limit) {
            request.setLimit(limit);
            return this;
        }

        public Builder startingAfter(String startingAfter) {
            request.setStartingAfter(startingAfter);
            return this;
        }

        public Builder endingBefore(String endingBefore) {
            request.setEndingBefore(endingBefore);
            return this;
        }

        public ListInvoicesRequest build() {
            return request;
        }
    }
}

