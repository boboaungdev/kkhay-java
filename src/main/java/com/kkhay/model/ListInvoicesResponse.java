package com.kkhay.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Paginated response containing a list of invoices.
 */
public class ListInvoicesResponse {
    @SerializedName("items")
    private List<Invoice> items = new ArrayList<>();

    @SerializedName("has_more")
    private boolean hasMore;

    @SerializedName("total")
    private Integer total;

    public ListInvoicesResponse() {}

    public List<Invoice> getItems() {
        return items;
    }

    public void setItems(List<Invoice> items) {
        this.items = items;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

