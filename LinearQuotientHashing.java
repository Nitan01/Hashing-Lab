package hashing;

public class LinearQuotientHashing {
    public static void main(String[] args) {
        // Array of keys to be inserted
        int[] keys = {27, 53, 13, 10, 138, 109, 49, 174, 26, 24};

        // Hash table size
        int N = 13;
        // Prime number for offset when q % N == 0
        int prime = 19;

        // Hash table initialized with null (indicating empty slots)
        Integer[] hashTable = new Integer[N];

        // Insert each key into the hash table using linear quotient hashing
        for (int key : keys) {
            insert(hashTable, key, N, prime);
        }

        // Print the final hash table
        printHashTable(hashTable);
    }

    public static void insert(Integer[] hashTable, int key, int N, int prime) {
        // Initial placement (ip)
        int ip = key % N;

        // Check for collisions
        if (hashTable[ip] == null) {
            // No collision, insert the key
            hashTable[ip] = key;
            System.out.println("Inserted " + key + " at index " + ip);
        } else {
            // Collision occurred, calculate offset using quotient
            int q = key / N;
            int offset = (q % N != 0) ? q : prime;

            // Linear probing using the offset until an empty slot is found
            int collisions = 0;
            while (hashTable[ip] != null) {
                collisions++;
                System.out.println("Collision " + collisions + " for key " + key + " at index " + ip);
                ip = (ip + offset) % N;
            }

            // Insert the key after resolving the collision
            hashTable[ip] = key;
            System.out.println("Inserted " + key + " at index " + ip + " after " + collisions + " collisions");
        }
    }

    public static void printHashTable(Integer[] hashTable) {
        System.out.println("\nFinal Hash Table:");
        for (int i = 0; i < hashTable.length; i++) {
            System.out.println("Index " + i + ": " + (hashTable[i] == null ? "null" : hashTable[i]));
        }
    }
}