import java.util.HashMap;

/**
 * Book My Stay App
 * Use Case 4: Room Search & Availability Check
 *
 * Allows guests to search available rooms without modifying inventory state.
 *
 * @author Student
 * @version 4.0
 */

// Abstract Room class
abstract class Room {
    protected String roomType;
    protected double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Price per Night: ₹" + price);
    }
}

// Single Room
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 2000);
    }
}

// Double Room
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 3500);
    }
}

// Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 6000);
    }
}

// Centralized Inventory
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0); // unavailable example
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
}

// Main Class
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Version: 4.0 ");
        System.out.println("=================================");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        System.out.println("\nAvailable Rooms:\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println("---------------------------");
            }
        }

        System.out.println("Search completed. Inventory unchanged.");
    }
}