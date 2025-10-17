package main.java.com.wagmi.finance.alg;

/*
 TODO[Student]: Sorting algorithms
 - Implement Quick Sort by amount (ascending).
 - Implement Merge Sort by date (ascending), stable.
 - Implement Insertion Sort by category using provided comparator.
 - See `SortersTest` for exact expectations and edge cases.
*/

import java.util.Comparator;

import main.java.com.wagmi.finance.model.Transaction;

public final class Sorters {
    private Sorters() {
    }

    public static void quickSortByAmount(Transaction[] arr) {
        // stub for quicksort
        if (arr==null || arr.length==0) return;
     quickSort(arr, 0 , arr.length-1);
//        throw new UnsupportedOperationException("Not implemented");
    }

    public static void quickSort(Transaction[] arr, int l , int h ) {
        if(l<h){
          int  j= partition( arr, l ,h );
            quickSort(arr, l, j-1);
            quickSort(arr, j+1, h);
        }
    }
    public static int partition(Transaction[] arr , int l, int h){
        double pivot = arr[l].getAmount();
        int i = l-1; // do while loop use kiya h so pahle increment ho hoga matlab do execute so arr[l or 0 ] wala element skip ho jayega
        int j = h+1; // same here ,j-- se arr[h-1], Last element arr[h] kabhi check hi nahi hota! Directly skip ho jaata hai
        while(i<j){
            do {i++;}while(i<=h && arr[i].getAmount() <=pivot);
            do {j--;}while(j>=l && arr[j].getAmount() >pivot);

            if (i<j){
                swap(arr, i,j);
            }
        }
        // when i cross the j
        swap (arr ,l,j);
        return j;
    }

    public static void swap(Transaction[] arr , int i , int j ) {
        Transaction temp ;
        temp= arr[i];
        arr[i]= arr[j];
        arr[j] = temp;
    }

/*-------- merge sort -----------*/
    public static void mergeSortByDate(Transaction[] arr) {
        // stub for mergesort
        if (arr==null || arr.length==0) return;
        mergesort(arr, 0, arr.length-1);
//        throw new UnsupportedOperationException("Not implemented");
    }

    public static void mergesort(Transaction[] arr, int l , int h){
            if (l<h){
                int mid = (l+h)/2;
                mergesort(arr, l ,mid);
                mergesort(arr, mid+1,h);
                merge(arr,l,mid,h);
            }
    }
     public static void merge(Transaction[] arr, int l , int mid , int h){
        int i , j,k;
        Transaction[] B = new Transaction[h+1];
         i = l;
         j = mid+1;
         k = l;

        while(i<=mid && j<=h){
            if (arr[i].getDate().compareTo(arr[j].getDate()) <= 0) B[k++]=arr[i++];
            else B[k++]=arr[j++];
        }
        for (; i<=mid; i++){
            B[k++]=arr[i];
        }
        for (;  j<=h; j++){
            B[k++]=arr[j];
        }
        for (int m =l ; m<=h; m++){
            arr[m]=B[m];
        }

     }
    /*-------- insertion sort -----------*/

    public static void insertionSortByCategory(Transaction[] arr, Comparator<String> categoryComparator) {
        // insertion sort by category
        for (int i =0; i<arr.length; i++){
            Transaction temp = arr[i];  // ye pure transaction obj ko store kra rha h temp and int me hum string nhi store kra sakte
            int j = i-1;
            while(j>=0 && categoryComparator.compare(arr[j].getCategory(),temp.getCategory())>0){
                arr[j+1]= arr[j];
                j--;
            }
            arr[j+1]= temp;
//        throw new UnsupportedOperationException("Not implemented");
        }
    }
}