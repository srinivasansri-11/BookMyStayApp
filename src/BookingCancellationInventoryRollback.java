import java.util.*;

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private double cost;
    private boolean cancelled;

    public Reservation(String reservationId, String guestName, String roomType, String roomId, double cost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.cost = cost;
        this.cancelled = false;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void markCancelled() {
        this.cancelled = true;
    }

    @Override
    public String toString() {
        return "ReservationID: " + reservationId + ", Guest: " + guestName +
                ", RoomType: " + roomType + ", RoomID: " + roomId +
                ", Cost: ₹" + cost + ", Cancelled: " + cancelled;
    }
}

class BookingHistory {
    private List<Reservation> confirmedBookings = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    public Reservation findReservation(String reservationId) {
        for (Reservation r : confirmedBookings) {
            if (r.getReservationId().equals(reservationId)) {
                return r;
            }
        }
        return null;
    }

    public List<Reservation> getAllReservations() {
        return confirmedBookings;
    }
}

class CancellationService {
    private BookingHistory history;
    private Map<String, Integer> inventory;
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(BookingHistory history, Map<String, Integer> inventory) {
        this.history = history;
        this.inventory = inventory;
    }

    public void cancelReservation(String reservationId) {
        Reservation reservation = history.findReservation(reservationId);
        if (reservation == null) {
            System.out.println("Cancellation Failed: Reservation not found.");
            return;
        }
        if (reservation.isCancelled()) {
            System.out.println("Cancellation Failed: Reservation already cancelled.");
            return;
        }
        rollbackStack.push(reservation.getRoomId());
        inventory.put(reservation.getRoomType(), inventory.get(reservation.getRoomType()) + 1);
        reservation.markCancelled();
        System.out.println("Cancellation Successful: " + reservationId + " rolled back. RoomID released: " + rollbackStack.peek());
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Standard", 1);
        inventory.put("Deluxe", 0);
        inventory.put("Suite", 0);

        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES201", "Kiran Rao", "Standard", "STD101", 3000);
        Reservation r2 = new Reservation("RES202", "Sneha Patel", "Deluxe", "DLX201", 5000);

        history.addReservation(r1);
        history.addReservation(r2);

        CancellationService cancellationService = new CancellationService(history, inventory);

        cancellationService.cancelReservation("RES201");
        cancellationService.cancelReservation("RES201");
        cancellationService.cancelReservation("RES999");

        System.out.println("\nFinal Booking History:");
        for (Reservation r : history.getAllReservations()) {
            System.out.println(r);
        }

        System.out.println("\nFinal Inventory State:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}