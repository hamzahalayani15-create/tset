package Assignment2;

import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;



    /**
     * Data Structures Assignment 2
     * Contains implementations for Stack and Queue operations.
     */
    public class DataStructuresAssignment2 {

        // ==========================================
        // 1. Stack Operations
        // ==========================================

        /**
         * Write a function to reverse a string using Stack
         */
        public static String reverseString(String input) {
            if (input == null) return null;
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < input.length(); i++) {
                stack.push(input.charAt(i));
            }
            StringBuilder reversed = new StringBuilder();
            while (!stack.isEmpty()) {
                reversed.append(stack.pop());
            }
            return reversed.toString();
        }

        /**
         * Write a function to sort a stack using only another Stack
         */
        public static Stack<Integer> sortStack(Stack<Integer> input) {
            Stack<Integer> tempStack = new Stack<>();
            while (!input.isEmpty()) {
                int current = input.pop();
                while (!tempStack.isEmpty() && tempStack.peek() > current) {
                    input.push(tempStack.pop());
                }
                tempStack.push(current);
            }
            return tempStack;
        }

        // ==========================================
        // 2. Queue Operations
        // ==========================================

        /**
         * Write a function to reverse the order of elements in a queue
         */
        public static void reverseQueue(Queue<Integer> queue) {
            if (queue == null || queue.isEmpty()) return;
            Stack<Integer> stack = new Stack<>();
            while (!queue.isEmpty()) {
                stack.push(queue.remove());
            }
            while (!stack.isEmpty()) {
                queue.add(stack.pop());
            }
        }

        /**
         * Implement a priority queue where the smallest element is dequeue first
         * (Basic implementation using a sorted linked list logic for simplicity)
         */
        static class SmallestFirstPriorityQueue {
            private LinkedList<Integer> list = new LinkedList<>();

            public void enqueue(int element) {
                int index = 0;
                while (index < list.size() && list.get(index) < element) {
                    index++;
                }
                list.add(index, element);
            }

            public Integer dequeue() {
                if (list.isEmpty()) return null;
                return list.removeFirst(); // Removes the smallest element
            }

            public boolean isEmpty() {
                return list.isEmpty();
            }

            @Override
            public String toString() {
                return list.toString();
            }
        }

        /**
         * Write a function to merge two sorted queues into a single sorted queue
         */
        public static Queue<Integer> mergeSortedQueues(Queue<Integer> q1, Queue<Integer> q2) {
            Queue<Integer> merged = new LinkedList<>();

            while (!q1.isEmpty() && !q2.isEmpty()) {
                if (q1.peek() <= q2.peek()) {
                    merged.add(q1.remove());
                } else {
                    merged.add(q2.remove());
                }
            }

            while (!q1.isEmpty()) {
                merged.add(q1.remove());
            }

            while (!q2.isEmpty()) {
                merged.add(q2.remove());
            }

            return merged;
        }

        // Main method for testing
        public static void main(String[] args) {
            // Test Reverse String
            System.out.println("Reverse 'Manus': " + reverseString("Manus"));

            // Test Sort Stack
            Stack<Integer> s = new Stack<>();
            s.push(34); s.push(3); s.push(31); s.push(98); s.push(92); s.push(23);
            System.out.println("Sorted Stack: " + sortStack(s));

            // Test Priority Queue
            SmallestFirstPriorityQueue pq = new SmallestFirstPriorityQueue();
            pq.enqueue(10); pq.enqueue(5); pq.enqueue(20); pq.enqueue(2);
            System.out.println("Priority Queue Dequeue: " + pq.dequeue()); // Should be 2

            // Test Merge Sorted Queues
            Queue<Integer> q1 = new LinkedList<>();
            q1.add(1); q1.add(3); q1.add(5);
            Queue<Integer> q2 = new LinkedList<>();
            q2.add(2); q2.add(4); q2.add(6);
            System.out.println("Merged Queue: " + mergeSortedQueues(q1, q2));
        }
    }








