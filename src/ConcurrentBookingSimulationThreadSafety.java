import java.util.*;
import java.util.concurrent.*;

class BookingRequest {
    private final String guestName;
    private final int roomId;

    public BookingRequest(String guestName, int roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
    }

    public String getGuestName() {
        return guestName;
    }

    public int getRoomId() {
        return roomId;
    }
}

class HotelInventory {
    private final Set<Integer> availableRooms;

    public HotelInventory(int totalRooms) {
        availableRooms = new HashSet<>();
        for (int i = 1; i <= totalRooms; i++) {
            availableRooms.add(i);
        }
    }

    public synchronized boolean allocateRoom(int roomId, String guestName) {
        if (availableRooms.contains(roomId)) {
            availableRooms.remove(roomId);
            System.out.println("Room " + roomId + " allocated to " + guestName);
            return true;
        } else {
            System.out.println("Room " + roomId + " already booked. " + guestName + " request denied.");
            return false;
        }
    }
}

class BookingProcessor implements Runnable {
    private final BlockingQueue<BookingRequest> bookingQueue;
    private final HotelInventory inventory;

    public BookingProcessor(BlockingQueue<BookingRequest> bookingQueue, HotelInventory inventory) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try {
            while (true) {
                BookingRequest request = bookingQueue.take();
                inventory.allocateRoom(request.getRoomId(), request.getGuestName());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        int totalRooms = 5;
        HotelInventory inventory = new HotelInventory(totalRooms);
        BlockingQueue<BookingRequest> bookingQueue = new LinkedBlockingQueue<>();

        Thread processor1 = new Thread(new BookingProcessor(bookingQueue, inventory));
        Thread processor2 = new Thread(new BookingProcessor(bookingQueue, inventory));
        processor1.start();
        processor2.start();

        String[] guests = {"Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona"};
        Random random = new Random();

        for (String guest : guests) {
            int requestedRoom = random.nextInt(totalRooms) + 1;
            bookingQueue.put(new BookingRequest(guest, requestedRoom));
        }

        Thread.sleep(2000);
        processor1.interrupt();
        processor2.interrupt();
    }
}