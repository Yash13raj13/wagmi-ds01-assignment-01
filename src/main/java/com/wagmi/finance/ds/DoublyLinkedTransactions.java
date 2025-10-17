package main.java.com.wagmi.finance.ds;

/*
 TODO[Student]: Doubly linked list of transactions
 - Implement tail-insert `add`, `deleteById`, `findById`, `reverse`, `toArray`.
 - Maintain `head`, `tail`, and `size` correctly across all operations.
 - After each edit, run `DoublyLinkedTransactionsTest` (or `./scripts/test-run.sh`).
*/

import main.java.com.wagmi.finance.model.Transaction;

public class DoublyLinkedTransactions {

    private static class Node {
        Transaction value;
        Node prev;
        Node next;

        Node(Transaction value) {
            this.value = value;
        }
    }

    private Node head;
    private Node tail;
    private int size =0 ;

    public void add(Transaction tx) {
        // stub: implement insertion at tail
        if (tx==null) throw new NullPointerException("transaction cannot be null");
        Node temp = new Node(tx);
       if (head==null){
           head =  temp;
           tail = head;
           temp.next= null;
           size++;
           return;
       }
       Node traverse =head;
       while(traverse.next!=null) {
           traverse=traverse.next;
       }
        traverse.next = temp;
        temp.prev = traverse;
        tail=temp;
        size++;
//        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean deleteById(String id) {
        // stub: implement search and delete
        if (id==null) return false;
        if (id == null || head ==null) return false;

        Node find = head;
        while( find!= null && !find.value.getId().equals(id)){
            find = find.next;
        }
        if (find == null) return false;
        // deleting the head
        if (find == head){  //first node
            head = find.next; //movind head pointer to next node
            if (head!=null){  // if next element exist, the head now at newelemnt
                head.prev=null; // then unlink the prev of head

            }
            else{ // if only one node present
                tail=null;
            }
        }

        // deleting as tail
        else if (find==tail){
            tail=find.prev;  // moved to one step back
            find.next=null; // current elemnt next set to null
        }
        else { // as middle
             find.prev.next=find.next;
             find.next.prev=find.prev;
        }
        size--;
        return true;
//        throw new UnsupportedOperationException("Not implemented");
    }

    public Transaction findById(String id) {
        // stub: linear search
     if (id == null  ) return null;
        if (head == null  ) return null;
        Node search = head;
       while(search!=null){
        if(search.value.getId().compareTo(id)==0){
             return search.value;
        }
        search=search.next;
       }
       return null;

//        throw new UnsupportedOperationException("Not implemented");
    }

    public void reverse() {
        // stub: reverse pointers
        Node go = head;
        Node tempp=null;
        while(go!=null){
            tempp = go.prev;
            go.prev=go.next;
            go.next=tempp;

            go=go.prev; // next swap hogya h prev se
        }
        if (tempp!= null) {
            tail= head;
            head= tempp.prev;
        }

//        throw new UnsupportedOperationException("Not implemented");
    }

    public int size() {
        return size;
    }

    public Transaction[] toArray() {
        // stub: iterate from head
        Node curr = head;
        int count =0;
        while(curr!=null){
            count++;
            curr=curr.next;
        }
        Transaction[] newArr =new Transaction[count];
        int i =0;
        Node curr2 = head;
        while(curr2!=null){
            newArr[i++]=curr2.value;
            curr2= curr2.next;
        }
        return newArr;
//        throw new UnsupportedOperationException("Not implemented");
    }
}