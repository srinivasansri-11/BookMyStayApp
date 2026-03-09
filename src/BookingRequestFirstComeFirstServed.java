import java.util.LinkedList;
import java.util.Queue;

/**
 * Book My Stay App
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates how booking requests are collected using
 * a Queue to preserve arrival order (FIFO).
 *
 * No room allocation or inventory update occurs here.
 *
 * @author Student
 * @version 5.0
 */

// Reservation class representing a guest booking request
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// Booking Request Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added to queue.");
    }

    // Display queued requests
    public void displayRequests() {

        System.out.println("\nCurrent Booking Requests (FIFO Order):");

        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" Welcome to Book My Stay App ");
        System.out.println(" Version: 5.0 ");
        System.out.println("====================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulating guest booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue
        bookingQueue.displayRequests();

        System.out.println("\nRequests stored in arrival order.");
        System.out.println("Allocation will be handled in the next use case.");
    }
}