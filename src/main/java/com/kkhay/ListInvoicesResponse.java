package com.kkhay;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Paginated response containing a list of gateway invoices.
 */
public class ListInvoicesResponse {
    @SerializedName("ok")
    private boolean ok;

    @SerializedName("items")
    private List<Invoice> items = new ArrayList<>();

    @SerializedName(value = "totalCount", alternate = {"total", "total_count"})
    private int totalCount;

    @SerializedName(value = "totalPages", alternate = {"total_pages"})
    private int totalPages;

    @SerializedName(value = "currentPage", alternate = {"current_page", "page"})
    private int currentPage;

    @SerializedName("limit")
    private int limit;

    public ListInvoicesResponse() {}

    public boolean isOk() { return ok; }
    public void setOk(boolean ok) { this.ok = ok; }

    public List<Invoice> getItems() { return items; }
    public void setItems(List<Invoice> items) { this.items = items; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public int getTotalPages() { return totalPages; }
    public void setTotalPages(int totalPages) { this.totalPages = totalPages; }

    public int getCurrentPage() { return currentPage; }
    public void setCurrentPage(int currentPage) { this.currentPage = currentPage; }

    public int getLimit() { return limit; }
    public void setLimit(int limit) { this.limit = limit; }

    public boolean isHasMore() {
        return totalPages > 0 && currentPage < totalPages;
    }
}
