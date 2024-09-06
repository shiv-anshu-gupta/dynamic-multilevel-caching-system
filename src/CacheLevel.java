class CacheLevel {
    private String[] keys;
    private String[] values;
    private int size;
    private int count; // To track how many items are currently in the cache

    // Constructor to initialize the cache size
    public CacheLevel(int size) {
        this.size = size;
        this.keys = new String[size];
        this.values = new String[size];
        this.count = 0;
    }

    // Get data from the cache
    public String get(String key) {
        for (int i = 0; i < count; i++) {
            if (keys[i].equals(key)) {
                // Move the found item to the front (most recently used)
                String value = values[i];
                moveToFront(i);
                return value;
            }
        }
        return null; // Cache miss
    }

    // Put data into the cache
    public void put(String key, String value) {
        // Check if the key is already in the cache
        for (int i = 0; i < count; i++) {
            if (keys[i].equals(key)) {
                // Update the value and move it to the front
                values[i] = value;
                moveToFront(i);
                return;
            }
        }

        // If cache is full, remove the least recently used (last element)
        if (count == size) {
            removeLast();
        }

        // Insert the new key-value pair at the front
        insertAtFront(key, value);
    }

    // Move an item to the front (most recently used)
    private void moveToFront(int index) {
        String key = keys[index];
        String value = values[index];
        // Shift all elements to the right of the index
        for (int i = index; i > 0; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[0] = key;
        values[0] = value;
    }

    // Remove the last (least recently used) item from the cache
    private void removeLast() {
        count--; // Just reduce the count to remove the last item
    }

    // Insert a new key-value pair at the front
    private void insertAtFront(String key, String value) {
        // Shift all elements to the right
        for (int i = count; i > 0; i--) {
            keys[i] = keys[i - 1];
            values[i] = values[i - 1];
        }
        keys[0] = key;
        values[0] = value;
        count++; // Increment the count as we've added a new item
    }

    // Display current cache contents
    public void displayCache() {
        for (int i = 0; i < count; i++) {
            System.out.print(keys[i] + ": " + values[i] + " ");
        }
        System.out.println();
    }
}