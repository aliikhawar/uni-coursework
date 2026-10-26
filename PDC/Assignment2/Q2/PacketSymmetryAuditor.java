package PDC.Assignment2.Q2;

import java.util.Scanner;

public class PacketSymmetryAuditor {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter packet payload: ");
        String packet = input.nextLine();

        TransactionStack stack = new TransactionStack(packet.length());
        RequestQueue queue = new RequestQueue(packet.length());

        for (int i = 0; i < packet.length(); i++) {
            char c = packet.charAt(i);
            stack.push(c);
            queue.enqueue(c);
        }

        boolean symmetric = true;

        while (!stack.isEmpty() && !queue.isEmpty()) {
            if (stack.pop() != queue.dequeue()) {
                symmetric = false;
            }
        }

        if (symmetric) {
            System.out.println("Verified: packet is a palindrome.");
        } else {
            System.out.println("Verified: packet is not a palindrome.");
        }

        input.close();
    }
}