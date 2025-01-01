package services;

import products.Flight;
import products.Hotel;
import products.Product;
import products.Taxi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Package {
    private int id;
    private int totalCost;
    private int discountedPrice;
    private String type; //offered or custom
    private Hotel hotel;
    private Flight flight;
    private Taxi taxi;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private LocalDate hotelStart;
    private LocalDateTime taxiTime;
    private long daysInHotel;

    public Package(Package pck) {
        this.hotel = pck.getHotel();
        this.flight = pck.getFlight();
        this.taxiTime = pck.getTaxiTime();
        this.taxi = pck.getTaxi();
        this.dateEnd = pck.getDateEnd();
        this.totalCost = pck.getTotalCost();
        this.discountedPrice = pck.getDiscountedPrice();
        this.daysInHotel = pck.getDaysInHotel();
        this.dateStart = pck.getDateStart();
        this.hotelStart = pck.getHotelStart();
        this.type = pck.getType();
        this.id = pck.getId();

    }

    public Package(String type, int hotelID, int flightID, int taxiID,
                   LocalDate dateStart, LocalDate dateEnd, LocalDateTime taxiTime, int id) {

        this.hotel = Hotel.retrieveHotel(hotelID);
        this.flight = Flight.retrieveFlight(flightID);
        this.taxi = Taxi.retrieveTaxi(taxiID);
        this.dateStart = dateStart;
        this.type = type;
        this.taxiTime = taxiTime;
        this.id = id;


        this.hotelStart = dateStart;


        this.dateEnd = dateEnd;

        // Calculate how many days the user will stay in the hotel
        long daysInHotel = ChronoUnit.DAYS.between(hotelStart, dateEnd);
        this.daysInHotel = daysInHotel;
        if (daysInHotel <= 0) {
            throw new IllegalArgumentException("End date must be after start date for hotel stay.");
        }

        // Multiply the number of days by the hotel's price per night
        this.totalCost = (int) (hotel.getPricePerNight() * daysInHotel + flight.getPrice() + (taxi.getBaseFare() + (taxi.getPerKmRate() * hotel.getDistanceToAirport())));
        setDiscountedPrice(totalCost);

    }


    @Override
    public String toString() {
        return this.hotel.getName() + "," + this.flight.getAirline() + "," + this.taxi.getTaxiType();
    }


    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int getId() {
        return id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public Flight getFlight() {
        return flight;
    }

    public Taxi getTaxi() {
        return taxi;
    }

    public LocalDate getHotelStart() {
        return hotelStart;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public long getDaysInHotel() {
        return daysInHotel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTaxiTime() {
        return taxiTime;
    }

    public void setTaxiTime(LocalDateTime taxiTime) {
        this.taxiTime = taxiTime;
    }

    public void setDateStart(LocalDate startDate) {
        this.dateStart = startDate;
    }

    public void setDateEnd(LocalDate endDate) {
        this.dateEnd = endDate;
    }

    public int getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(int discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

}
