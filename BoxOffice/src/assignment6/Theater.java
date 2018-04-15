// insert header here
package assignment6;

import java.util.ArrayList;
import java.util.List;

public class Theater {
	private int numRows;
	private int seatsPerRow;
	private String show;
	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
	ArrayList<Seat> seats = new ArrayList<Seat>(); 
	/*
	 * Represents a seat in the theater
	 * A1, A2, A3, ... B1, B2, B3 ...
	 */
	static class Seat {
		private int rowNum;
		private int seatNum;
		private boolean reserved = false;

		public Seat(int rowNum, int seatNum) {
			this.rowNum = rowNum;
			this.seatNum = seatNum;
		}

		public int getSeatNum() {
			return seatNum;
		}

		public int getRowNum() {
			return rowNum;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return the full Seat location ex: A1
		}
	}

    /*
	 * Represents a ticket purchased by a client
	 */
	static class Ticket {
		private String show;
		private String boxOfficeId;
		private Seat seat;
		private int client;

		public Ticket(String show, String boxOfficeId, Seat seat, int client) {
			this.show = show;
			this.boxOfficeId = boxOfficeId;
			this.seat = seat;
			this.client = client;
		}

		public Seat getSeat() {
			return seat;
		}

		public String getShow() {
			return show;
		}

		public String getBoxOfficeId() {
			return boxOfficeId;
		}

		public int getClient() {
			return client;
		}

		@Override
		public String toString() {
			// TODO: Implement this method to return a string that resembles a ticket
		}
	}

	public Theater(int numRows, int seatsPerRow, String show) {
		this.numRows = numRows;
		this.seatsPerRow = seatsPerRow;
		this.show = show;
		//this.
		// TODO: Implement this constructor
		for(int i = 0; i <= numRows; i++) {
			for(int j = 0; j <= seatsPerRow; j++) {
				Seat seat = new Seat(i, j);
				seats.add(seat);
			}
		}
	}

	/**
	 * Calculates the best seat not yet reserved
	 *
 	 * @return the best seat or null if theater is full
     */
	public Seat bestAvailableSeat() {
		for(Seat s: seats) {
			if(s.reserved == false) {
				s.reserved = true;
				return s;
			}
		}
		return null;
		//TODO: Implement this method
	}

	/**
	 * Prints a ticket for the client after they reserve a seat
     * Also prints the ticket to the console
	 *
     * @param seat a particular seat in the theater
     * @return a ticket or null if a box office failed to reserve the seat
     */
	public Ticket printTicket(String boxOfficeId, Seat seat, int client) {
		if(seats.isEmpty()) {
			return null;
		}
		Ticket ticket = new Ticket(show, boxOfficeId, seat, client);
		System.out.println(ticket.toString());
		tickets.add(ticket);
		return ticket;
		//TODO: Implement this method
	}

	/**
	 * Lists all tickets sold for this theater in order of purchase
	 *
     * @return list of tickets sold
     */
	public List<Ticket> getTransactionLog() {
		return tickets;
		//TODO: Implement this method
	}
}
