import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private final boolean[] seats; 
    private final Lock lock;        

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; 
        lock = new ReentrantLock();
    }

    public boolean bookSeat(int seatNumber, String user) {
        lock.lock();  
        try {
            if (seatNumber < 0 || seatNumber >= seats.length) {
                System.out.println(user + " attempted to book an invalid seat.");
                return false;
            }

            if (!seats[seatNumber]) {
                seats[seatNumber] = true;
                System.out.println(user + " successfully booked seat " + seatNumber);
                return true;
            } else {
                System.out.println(user + " tried to book seat " + seatNumber + " but it was already taken.");
                return false;
            }
        } finally {
            lock.unlock(); 
        }
    }
}

class UserThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String userName;

    public UserThread(TicketBookingSystem system, int seatNumber, String userName, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.userName = userName;
        this.setPriority(priority);  
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, userName);
    }
}

public class TicketBookingApp {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5); 

        UserThread user1 = new UserThread(system, 2, "VIP_User_1", Thread.MAX_PRIORITY);
        UserThread user2 = new UserThread(system, 2, "Regular_User_2", Thread.NORM_PRIORITY);
        UserThread user3 = new UserThread(system, 3, "Regular_User_3", Thread.NORM_PRIORITY);
        UserThread user4 = new UserThread(system, 1, "VIP_User_4", Thread.MAX_PRIORITY);
        UserThread user5 = new UserThread(system, 1, "Regular_User_5", Thread.NORM_PRIORITY);

        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
    }
}
