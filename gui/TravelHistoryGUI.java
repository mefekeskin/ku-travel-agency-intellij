package gui;

import Users.Customer;
import core.App;
import services.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * GUI for displaying a customer's travel history.
 */
public class TravelHistoryGUI extends JFrame {
    private JTable historyTable;
    private JPanel historyPanel;
    private JScrollPane historyScrollPane;
    public ArrayList<Reservation> travelHistory;
    private static final DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("H:mm");
    private static final DateTimeFormatter formatterDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
    private static final Font font = new Font("Arial", Font.PLAIN, 14);

    /**
     * Constructs a TravelHistoryGUI instance for the given customer.
     * @param customer The customer whose travel history is to be displayed.
     * @throws FileNotFoundException if the customer's travel history file is not found.
     */
    public TravelHistoryGUI(Customer customer) throws FileNotFoundException {
        setTitle("Travel History #" + customer.getID());
        travelHistory = customer.getTravelHistory();
        setSize(1400, 800);

        DefaultTableModel dtm = new DefaultTableModel();
        dtm.addColumn("Reservation ID");
        dtm.addColumn("From City");
        dtm.addColumn("To City");
        dtm.addColumn("Hotel Name");
        dtm.addColumn("Airline");
        dtm.addColumn("Flight Class");
        dtm.addColumn("Taxi Type");
        dtm.addColumn("Taxi Departure Time");
        dtm.addColumn("Start Date");
        dtm.addColumn("End Date");
        dtm.addColumn("Price($)");
        dtm.addColumn("Status");

        historyTable = new JTable(dtm);
        historyTable.setRowHeight(30);

        historyPanel = new JPanel(new BorderLayout());
        historyScrollPane = new JScrollPane(historyTable);
        historyPanel.add(historyScrollPane, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");
        backButton.setFont(font);
        buttonPanel.add(backButton);
        backButton.addActionListener(e -> {
            dispose();
            if (App.isAdmin) {
                new AdminGUI().setVisible(true);
            } else {
                new CustomerUI().setVisible(true);
            }
        });

        mainPanel.add(historyPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.add(mainPanel);

        for (Reservation reservation : travelHistory) {
            String[] row = new String[12];
            row[0] = String.valueOf(reservation.getId());
            row[1] = reservation.getRelatedPackage().getFlight().getDepartureCity();
            row[2] = reservation.getRelatedPackage().getFlight().getArrivalCity();
            row[3] = reservation.getRelatedPackage().getHotel().getName();
            row[4] = reservation.getRelatedPackage().getFlight().getAirline();
            row[5] = reservation.getRelatedPackage().getFlight().getTicketClass();
            row[6] = reservation.getRelatedPackage().getTaxi().getTaxiType();
            row[7] = reservation.getRelatedPackage().getTaxiTime().format(formatterDateTime).split(" ")[1];
            row[8] = reservation.getRelatedPackage().getDateStart().format(formatterDate);
            row[9] = reservation.getRelatedPackage().getDateEnd().format(formatterDate);
            row[10] = String.valueOf(reservation.getRelatedPackage().getDiscountedPrice());
            row[11] = reservation.isStatus() ? "Confirmed" : "Cancelled";
            dtm.addRow(row);
        }
    }
}
