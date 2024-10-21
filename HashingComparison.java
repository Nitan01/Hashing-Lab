package hashing;

import java.util.LinkedList;

public class HashingComparison {

    public static void main(String[] args) {
        // Array of keys to be inserted
        int[] keys = {27, 53, 13, 10, 138, 109, 49, 174, 26, 24};
        int[] keysToRetrieve = {53, 138, 109, 49, 174, 26};

        // Hash table size for both methods
        int linearN = 13; // For linear-quotient hashing
        int bucketN = 10; // For bucket hashing

        // Prime number for offset (4k+3) in linear-quotient hashing
        int prime = 19;

        // Create and fill the linear-quotient hash table
        Integer[] linearTable = new Integer[linearN];
        for (int key : keys) {
            insertLinearQuotient(linearTable, key, linearN, prime);
        }

        // Create and fill the bucket hash table
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] bucketTable = new LinkedList[bucketN];
        for (int key : keys) {
            insertBucket(bucketTable, key, bucketN);
        }

        // Print the tables
        System.out.println("\nLinear-Quotient Hash Table:");
        printHashTable(linearTable);

        System.out.println("\nBucket Hash Table:");
        printBucketHashTable(bucketTable);

        // Count comparisons for retrieving keys in both tables
        System.out.println("\nNumber of Comparisons to Retrieve Keys:");
        System.out.println("Key | Linear-Quotient Comparisons | Bucket Comparisons");
        System.out.println("----------------------------------------------");
        for (int key : keysToRetrieve) {
            int linearComparisons = getComparisonsLinearQuotient(linearTable, key, linearN, prime);
            int bucketComparisons = getComparisonsBucket(bucketTable, key, bucketN);
            System.out.println(key + "    | " + linearComparisons + "                           | " + bucketComparisons);
        }
    }

    // Method to insert a key in the Linear-Quotient hash table
    public static void insertLinearQuotient(Integer[] hashTable, int key, int N, int prime) {
        int ip = key % N;  // initial placement
        if (hashTable[ip] == null) {
            hashTable[ip] = key;
        } else {
            int q = key / N;
            int offset = (q % N != 0) ? q : prime;
            while (hashTable[ip] != null) {
                ip = (ip + offset) % N;
            }
            hashTable[ip] = key;
        }
    }

    // Method to count comparisons in Linear-Quotient hash table
    public static int getComparisonsLinearQuotient(Integer[] hashTable, int key, int N, int prime) {
        int comparisons = 1;
        int ip = key % N;
        if (hashTable[ip] != null && hashTable[ip].equals(key)) {
            return comparisons;
        } else {
            int q = key / N;
            int offset = (q % N != 0) ? q : prime;
            while (hashTable[ip] != null) {
                comparisons++;
                ip = (ip + offset) % N;
                if (hashTable[ip] != null && hashTable[ip].equals(key)) {
                    return comparisons;
                }
            }
        }
        return comparisons; // Shouldn't reach here unless the key isn't in the table
    }

    // Method to insert a key in the Bucket hash table
    public static void insertBucket(LinkedList<Integer>[] hashTable, int key, int N) {
        int ip = key % N;
        if (hashTable[ip] == null) {
            hashTable[ip] = new LinkedList<>();
        }
        hashTable[ip].add(key);
    }

    // Method to count comparisons in Bucket hash table
    public static int getComparisonsBucket(LinkedList<Integer>[] hashTable, int key, int N) {
        int ip = key % N;
        if (hashTable[ip] != null) {
            int comparisons = 1;
            for (Integer element : hashTable[ip]) {
                if (element.equals(key)) {
                    return comparisons;
                }
                comparisons++;
            }
        }
        return 0; // Shouldn't reach here unless the key isn't in the table
    }

    // Print method for Linear-Quotient hash table
    public static void printHashTable(Integer[] hashTable) {
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println("Index " + i + ": " + (hashTable[i] == null ? "null" : hashTable[i]));
        }
    }

    // Print method for Bucket hash table
    public static void printBucketHashTable(LinkedList<Integer>[] hashTable) {
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
