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
        if (sortedById == null) throw new NullPointerException("Array is null");
        if (id == null) return -1;
        int low = 0;
        int high = sortedById.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            String midId = sortedById[mid].getId(); // sortedById ek array h transaction(class/obj) type ka
            int compare = midId.compareTo(id);
            if (compare == 0) { // compareTo function return 0 if string are equal and if it less than h to -ve value return and more than h to +ve  value return
                return mid;
            } else if (compare < 0) {
                low = mid + 1;
            } else
                high = mid - 1;
        }
        return -1;
    }
}