import java.util.*;

class Service {
    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " (₹" + cost + ")";
    }
}

class AddOnServiceManager {
    private Map<String, List<Service>> reservationServices = new HashMap<>();

    public void addService(String reservationId, Service service) {
        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public List<Service> getServices(String reservationId) {
        return reservationServices.getOrDefault(reservationId, Collections.emptyList());
    }

    public double calculateTotalCost(String reservationId) {
        return getServices(reservationId).stream()
                .mapToDouble(Service::getCost)
                .sum();
    }
}

public class AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservation1 = "RES123";
        String reservation2 = "RES456";

        manager.addService(reservation1, new Service("Breakfast", 500));
        manager.addService(reservation1, new Service("Airport Pickup", 1200));
        manager.addService(reservation2, new Service("Spa Access", 2000));

        System.out.println("Reservation ID: " + reservation1);
        System.out.println("Selected Services: " + manager.getServices(reservation1));
        System.out.println("Additional Cost: ₹" + manager.calculateTotalCost(reservation1));

        System.out.println();

        System.out.println("Reservation ID: " + reservation2);
        System.out.println("Selected Services: " + manager.getServices(reservation2));
        System.out.println("Additional Cost: ₹" + manager.calculateTotalCost(reservation2));
    }
}