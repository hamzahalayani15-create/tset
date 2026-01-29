package Assignment1;
import java.util.Random;

/**
 *
 * Data Structures Assignment 1
 * Contains implementations for Array operations, Singly Linked List,
 * Doubly Linked List, and Circular Linked List.
 */
public class DataStructuresAssignment {

    // ==========================================
    // 1. Array Operations
    // ==========================================

    /**
     * Write a program to clone an array
     */
    public static int[] cloneArray(int[] original) {
        if (original == null) return null;
        int[] clone = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            clone[i] = original[i];
        }
        return clone;
    }

    /**
     * Write a program in Java to remove a random element from an array
     */
    public static int[] removeRandomElement(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        Random rand = new Random();
        int randomIndex = rand.nextInt(arr.length);
        return removeElementAtIndex(arr, randomIndex);
    }

    /**
     * Write a program in Java to remove a specific element from an array
     */
    public static int[] removeSpecificElement(int[] arr, int element) {
        if (arr == null) return null;
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                index = i;
                break;
            }
        }
        if (index == -1) return arr; // Element not found
        return removeElementAtIndex(arr, index);
    }

    // Helper for array removal
    private static int[] removeElementAtIndex(int[] arr, int index) {
        int[] result = new int[arr.length - 1];
        for (int i = 0, k = 0; i < arr.length; i++) {
            if (i == index) continue;
            result[k++] = arr[i];
        }
        return result;
    }

    /**
     * Write a Java program to reverse an array
     */
    public static void reverseArray(int[] arr) {
        if (arr == null) return;
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    // ==========================================
    // 2. Singly Linked List Operations
    // ==========================================

    static class Node {
        int data;
        Node next;
        Node(int d) { data = d; next = null; }
    }

    /**
     * Write a function to concatenate two linked lists
     */
    public static Node concatenate(Node head1, Node head2) {
        if (head1 == null) return head2;
        Node temp = head1;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = head2;
        return head1;
    }

    /**
     * Write a function to rotate a linked list to the right by k places
     */
    public static Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        Node curr = head;
        int len = 1;
        while (curr.next != null) {
            curr = curr.next;
            len++;
        }

        curr.next = head; // Make it circular
        k = k % len;
        int stepsToNewHead = len - k;

        while (stepsToNewHead-- > 0) {
            curr = curr.next;
        }

        head = curr.next;
        curr.next = null;
        return head;
    }

    /**
     * Write a function to search for element in singly linked list and return its position
     */
    public static int searchSingly(Node head, int key) {
        Node temp = head;
        int pos = 0;
        while (temp != null) {
            if (temp.data == key) return pos;
            temp = temp.next;
            pos++;
        }
        return -1;
    }

    /**
     * Write a function to find the index of a given data value in a linked list.
     * If not found, return -1
     */
    public static int findIndex(Node head, int value) {
        return searchSingly(head, value); // Same logic as search
    }

    /**
     * Write a function to remove at specific position from singly linked list
     */
    public static Node removeAtPosition(Node head, int pos) {
        if (head == null) return null;
        if (pos == 0) return head.next;

        Node temp = head;
        for (int i = 0; temp != null && i < pos - 1; i++) {
            temp = temp.next;
        }

        if (temp == null || temp.next == null) return head;

        temp.next = temp.next.next;
        return head;
    }

    // ==========================================
    // 3. Doubly Linked List Operations
    // ==========================================

    static class DNode {
        int data;
        DNode next, prev;
        DNode(int d) { data = d; next = prev = null; }
    }

    /**
     * Write a function to remove duplicates elements from doubly Linked List
     */
    public static void removeDuplicatesDoubly(DNode head) {
        if (head == null) return;
        DNode current = head;
        while (current != null) {
            DNode runner = current.next;
            while (runner != null) {
                if (runner.data == current.data) {
                    DNode nextNode = runner.next;
                    DNode prevNode = runner.prev;
                    if (nextNode != null) nextNode.prev = prevNode;
                    if (prevNode != null) prevNode.next = nextNode;
                    runner = nextNode;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    /**
     * Write a function to traverse a doubly linked list in reverse and print all elements
     */
    public static void printReverseDoubly(DNode head) {
        if (head == null) return;
        DNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        System.out.print("Reverse Traversal: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.prev;
        }
        System.out.println();
    }

    /**
     * Write a function to search for an element in a doubly linked list
     */
    public static boolean searchDoubly(DNode head, int key) {
        DNode temp = head;
        while (temp != null) {
            if (temp.data == key) return true;
            temp = temp.next;
        }
        return false;
    }

    // ==========================================
    // 4. Circular Linked List Operations
    // ==========================================

    /**
     * Write a function to insert a node at a specific position in a circular linked list
     */
    public static Node insertCircular(Node head, int data, int pos) {
        Node newNode = new Node(data);
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        if (pos == 0) {
            Node temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = newNode;
            newNode.next = head;
            return newNode;
        }
        Node curr = head;
        for (int i = 0; i < pos - 1 && curr.next != head; i++) {
            curr = curr.next;
        }
        newNode.next = curr.next;
        curr.next = newNode;
        return head;
    }

    /**
     * Write a function to delete a node from a specific position in a circular linked list
     */
    public static Node deleteCircular(Node head, int pos) {
        if (head == null) return null;
        if (pos == 0) {
            if (head.next == head) return null;
            Node temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = head.next;
            return head.next;
        }
        Node curr = head;
        for (int i = 0; i < pos - 1 && curr.next != head; i++) {
            curr = curr.next;
        }
        if (curr.next == head) return head;
        curr.next = curr.next.next;
        return head;
    }

    /**
     * Write a function to search for an element in a circular linked list
     */
    public static boolean searchCircular(Node head, int key) {
        if (head == null) return false;
        Node temp = head;
        do {
            if (temp.data == key) return true;
            temp = temp.next;
        } while (temp != head);
        return false;
    }

    /**
     * Write a function to split a circular linked list into two halves
     */
    public static Node[] splitCircular(Node head) {
        if (head == null || head.next == head) return new Node[]{head, null};

        Node slow = head, fast = head;
        while (fast.next != head && fast.next.next != head) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast.next.next == head) fast = fast.next;

        Node head1 = head;
        Node head2 = slow.next;
        fast.next = slow.next;
        slow.next = head;

        return new Node[]{head1, head2};
    }

    // Main method for basic testing (Optional)
    public static void main(String[] args) {
        System.out.println("Data Structures Assignment Logic Initialized.");
        // Test array clone
        int[] a = {1, 2, 3};
        int[] b = cloneArray(a);
        System.out.println("Original: " + a[0] + ", Clone: " + b[0]);
    }
}
