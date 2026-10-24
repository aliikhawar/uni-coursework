package PDC.Assignment2.Q2;

public class TransactionStack {
    private char[] data;
    private int top = -1;

    public TransactionStack(int capacity) {
        data = new char[capacity];
    }

    public void push(char c) {
        if (top == data.length - 1) {
            throw new IllegalStateException("Stack is full");
        }

        data[++top] = c;
    }

    public char pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return data[top--];
    }

    public char peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }

        return data[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}