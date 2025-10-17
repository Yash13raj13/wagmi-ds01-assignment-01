package main.java.com.wagmi.finance.processing;

import main.java.com.wagmi.finance.model.Transaction;

public class PendingQueue {
    private final Transaction[] data;
    private int head = 0;
    private int tail = 0;
    private int size = 0;

    public PendingQueue(int capacity) {
        this.data = new Transaction[capacity];
    }

    public void enqueue(Transaction tx) {
        if (tx == null) throw new NullPointerException("transaction cannot be null");
        if (isFull()) throw new UnsupportedOperationException("overflow");

        data[tail] = tx;
        tail = (tail + 1) % data.length;
        size++;
    }

    public Transaction dequeue() {
        if (isEmpty()) throw new UnsupportedOperationException("underflow");

        Transaction tx = data[head];
        data[head] = null; // optional cleanup
        head = (head + 1) % data.length;
        size--;
        return tx;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int size() {
        return size;
    }
}