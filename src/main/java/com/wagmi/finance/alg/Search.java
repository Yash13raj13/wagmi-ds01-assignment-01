package main.java.com.wagmi.finance.alg;

/*
 TODO[Student]: Binary search by Transaction ID
 - Array must be sorted by `Transaction.getId()` ascending.
 - Handle null array (throw NPE) and null id (return -1) per tests.
 - See `SearchTest` for target indices and edge cases.
*/

import main.java.com.wagmi.finance.model.Transaction;

public final class Search {
    private Search() {
    }

    public static int binarySearchById(Transaction[] sortedById, String id) {
        // stub for binary search
        throw new UnsupportedOperationException("Not implemented");
    }
}
public static int binarySearchById(Transaction[] sortedById, String id) {
    if (sortedById == null) throw new NullPointerException();
    if (id == null) return -1;

    int low = 0, high = sortedById.length - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;
        String midId = sortedById[mid].getId();

        int cmp = midId.compareTo(id);
        if (cmp == 0) return mid;
        else if (cmp < 0) low = mid + 1;
        else high = mid - 1;
    }

    return -1;
}
