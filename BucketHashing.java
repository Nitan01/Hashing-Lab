package hashing;

import java.util.LinkedList;

public class BucketHashing {
    public static void main(String[] args) {
        // Array of keys to be inserted
        int[] keys = {27, 53, 13, 10, 138, 109, 49, 174, 26, 24};

        // Hash table size
        int N = 10;

        // Hash table as an array of LinkedLists (buckets)
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] hashTable = new LinkedList[N];

        // Insert each key into the hash table using bucket hashing
        for (int key : keys) {
            insert(hashTable, key, N);
        }

        // Print the final hash table
        printHashTable(hashTable);
    }

    public static void insert(LinkedList<Integer>[] hashTable, int key, int N) {
        // Initial placement (ip) using modulo operator
        int ip = key % N;

        // If no LinkedList (bucket) exists at the calculated index, create a new one
        if (hashTable[ip] == null) {
            hashTable[ip] = new LinkedList<>();
        }

        // Insert the key into the appropriate bucket (LinkedList)
        hashTable[ip].add(key);
        System.out.println("Inserted " + key + " in bucket " + ip);
    }

    public static void printHashTable(LinkedList<Integer>[] hashTable) {
        System.out.println("\nFinal Bucket Hash Table:");
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print("Bucket " + i + ": ");
            if (hashTable[i] == null) {
                System.out.println("null");
            } else {
                System.out.println(hashTable[i]);
            }
        }
    }
}