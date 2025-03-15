import java.util.*;
class User {
    String username;
    String password;
    
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
class Ticket {
    int pnr;
    String name;
    int trainNumber;
    String trainName;
    String classType;
    String date;
    String from;
    String to;

    Ticket(int pnr, String name, int trainNumber, String trainName, String classType, String date, String from, String to) {
        this.pnr = pnr;
        this.name = name;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.date = date;
        this.from = from;
        this.to = to;
    }
}

public class OnlineReservationSystem {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();
    static Map<Integer, Ticket> tickets = new HashMap<>();
    static int pnrCounter = 1000;

    public static void main(String[] args) {
        users.put("admin", new User("admin", "admin123"));
        
        System.out.println("Welcome to Online Reservation System");
        if (login()) {
            int choice;
            do {
                System.out.println("1. Reserve Ticket\n2. Cancel Ticket\n3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        reserveTicket();
                        break;
                    case 2:
                        cancelTicket();
                        break;
                    case 3:
                        System.out.println("Thank you for using Online Reservation System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 3);
        }
    }

    static boolean login() {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("Login Successful!");
            return true;
        } else {
            System.out.println("Invalid Credentials. Exiting...");
            return false;
        }
    }

    static void reserveTicket() {
        System.out.print("Enter Passenger Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Train Number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Class Type: ");
        String classType = scanner.nextLine();
        System.out.print("Enter Date of Journey (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter From Station: ");
        String from = scanner.nextLine();
        System.out.print("Enter To Station: ");
        String to = scanner.nextLine();

        int pnr = pnrCounter++;
        Ticket ticket = new Ticket(pnr, name, trainNumber, trainName, classType, date, from, to);
        tickets.put(pnr, ticket);
        System.out.println("Ticket Reserved Successfully! Your PNR is: " + pnr);
    }

    static void cancelTicket() {
        System.out.print("Enter PNR Number: ");
        int pnr = scanner.nextInt();
        scanner.nextLine();
        
        if (tickets.containsKey(pnr)) {
            Ticket ticket = tickets.get(pnr);
            System.out.println("Ticket Details:");
            System.out.println("PNR: " + ticket.pnr);
            System.out.println("Name: " + ticket.name);
            System.out.println("Train: " + ticket.trainName + " (" + ticket.trainNumber + ")");
            System.out.println("Class: " + ticket.classType);
            System.out.println("Journey Date: " + ticket.date);
            System.out.println("From: " + ticket.from + " To: " + ticket.to);
            
            System.out.print("Do you want to confirm cancellation? (yes/no): ");
            String confirm = scanner.nextLine();
            
            if (confirm.equalsIgnoreCase("yes")) {
                tickets.remove(pnr);
                System.out.println("Ticket Cancelled Successfully!");
            } else {
                System.out.println("Cancellation Aborted.");
            }
        } else {
            System.out.println("PNR Not Found!");
        }
    }
}