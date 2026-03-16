import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private double cost;

    public Reservation(String reservationId, String guestName, String roomType, double cost) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.cost = cost;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "ReservationID: " + reservationId + ", Guest: " + guestName +
                ", Room: " + roomType + ", Cost: ₹" + cost;
    }
}

class BookingValidator {
    private static final Set<String> validRoomTypes = new HashSet<>(Arrays.asList("Standard", "Deluxe", "Suite"));
    private Map<String, Integer> inventory;

    public BookingValidator(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void validateReservation(String roomType) throws InvalidBookingException {
        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
        if (!inventory.containsKey(roomType) || inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No availability for room type: " + roomType);
        }
    }

    public void updateInventory(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {
        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 0);

        BookingValidator validator = new BookingValidator(inventory);

        try {
            validator.validateReservation("Deluxe");
            validator.updateInventory("Deluxe");
            Reservation r1 = new Reservation("RES101", "Anita Rao", "Deluxe", 4000);
            System.out.println("Booking Confirmed: " + r1);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        try {
            validator.validateReservation("Suite");
            validator.updateInventory("Suite");
            Reservation r2 = new Reservation("RES102", "Vikram Singh", "Suite", 8000);
            System.out.println("Booking Confirmed: " + r2);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        try {
            validator.validateReservation("Penthouse");
            validator.updateInventory("Penthouse");
            Reservation r3 = new Reservation("RES103", "Meena Iyer", "Penthouse", 12000);
            System.out.println("Booking Confirmed: " + r3);
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }
    }
}