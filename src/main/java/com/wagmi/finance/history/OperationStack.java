package main.java.com.wagmi.finance.history;

/*
 TODO[Student]: Array-based stack
 - Enforce underflow/overflow behaviors per tests.
 - Note dynamic growth is allowed only when initial capacity >= 10.
 - Validate null pushes (should throw).
 - Run `OperationStackTest` after changes.
*/

public class OperationStack {
    private String[] data;
    private final int initialCapacity;
    private int top = -1;

    public OperationStack(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative.");
        }
  this.initialCapacity = capacity;
        this.data = new String[capacity];
    }

    public void push(String operation) {
        if (operation == null) {
            throw new IllegalArgumentException("Cannot push a null operation.");
        }

        if (top == data.length - 1) { // Stack is full
            if (initialCapacity >= 10) {
                int newCapacity = data.length == 0 ? 10 : data.length * 2;
                String[] newData = new String[newCapacity];
                System.arraycopy(data, 0, newData, 0, data.length);
                this.data = newData;
            } else {
                throw new UnsupportedOperationException("Stack overflow.");
            }
        }
        data[++top] = operation;
    }

    public String pop() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Cannot pop from an empty stack.");
        }
        String operation = data[top];
        data[top--] = null; // Clear the reference to help garbage collector
        return operation;
    }

    public String peek() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("Cannot peek into an empty stack.");
        }
        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }
}