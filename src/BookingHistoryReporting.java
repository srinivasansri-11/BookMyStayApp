import java.util.*;

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

class BookingHistory {
    private List<Reservation> confirmedBookings = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    public List<Reservation> getAllReservations() {
        return confirmedBookings;
    }
}

class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void generateReport() {
        List<Reservation> reservations = history.getAllReservations();
        System.out.println("Booking History Report");
        System.out.println("----------------------");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
        double totalRevenue = reservations.stream()
                .mapToDouble(Reservation::getCost)
                .sum();
        System.out.println("----------------------");
        System.out.println("Total Bookings: " + reservations.size());
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES001", "Arun Kumar", "Deluxe", 3500);
        Reservation r2 = new Reservation("RES002", "Priya Sharma", "Suite", 7500);
        Reservation r3 = new Reservation("RES003", "Rahul Mehta", "Standard", 2500);

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService(history);
        reportService.generateReport();
    }
}