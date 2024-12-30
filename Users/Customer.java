package Users;

import services.Reservation;
import services.ReservationsManagers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Customer implements User{
    private final String username;
    private final String password;
    private final Integer id;
    private ArrayList<Reservation> travelHistory;

    public Customer(String username, String password, Integer id){
        travelHistory = new ArrayList<Reservation>();
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public void loadTravelHistory() throws FileNotFoundException {
        //from reservations.txt
        travelHistory = new ArrayList<Reservation>();
        File file = new File("services/reservations.txt");
        Scanner scn = new Scanner(file);
        ReservationsManagers.loadReservations();
        while(scn.hasNextLine()){
            String line = scn.nextLine();
            String[] lineArray = line.split(",");
            if(Objects.equals(lineArray[5], String.valueOf(this.getID()))){
                travelHistory.add(ReservationsManagers.getReservation(Integer.parseInt(lineArray[0])));
            }
        }

    }

public ArrayList<Reservation> getTravelHistory() throws FileNotFoundException {
        loadTravelHistory();
        return travelHistory;
}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getID() {
        return id;
    }
}
