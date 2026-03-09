import java.util.HashMap;
import java.util.Map;

/**
 * RoomInventory manages centralized room availability
 * using a HashMap as the single source of truth.
 *
 * @author Student
 * @version 3.0
 */
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor initializes the inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        // Register room types with availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 6);
        inventory.put("Suite Room", 3);
    }

    // Get availability for a room type
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display current inventory
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}