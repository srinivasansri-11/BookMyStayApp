/**
 * Use Case 2: Basic Room Types & Static Availability
 *
 * This class initializes predefined room types and displays
 * their availability in the hotel booking system.
 *
 * @author Student
 * @version 2.0
 */
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Version: 2.0 ");
        System.out.println("=====================================");

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 6;
        int suiteRoomAvailable = 3;

        // Display room details
        System.out.println("\n--- Room Details ---");

        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleRoomAvailable);

        System.out.println();

        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailable);

        System.out.println();

        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailable);

        System.out.println("\nThank you for using Book My Stay App!");
    }
}