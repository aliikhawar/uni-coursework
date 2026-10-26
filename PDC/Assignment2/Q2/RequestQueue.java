package PDC.Assignment2.Q2;

public class RequestQueue {
    private char[] data;
    private int front = 0;
    private int rear = 0;
    private int size = 0;

    public RequestQueue(int capacity) {
        data = new char[capacity];
    }

    public void enqueue(char c) {
        if (size == data.length) {
            throw new IllegalStateException("Queue is full");
        }

        data[rear] = c;
        rear = (rear + 1) % data.length;
        size++;
    }

    public char dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        char value = data[front];
        front = (front + 1) % data.length;
        size--;

        return value;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}