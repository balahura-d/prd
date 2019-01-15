package ua.nure.balagura.SummaryTask4.db.facade;

import java.util.ArrayList;
import java.util.List;

import ua.nure.balagura.SummaryTask4.db.entity.Auto;

public class RequestFacade {
	private int id;
	private int driverId;
	private String driverName;
	private int flightId;
	private String flightName;
	private int seats;
	private List<Auto> availableAuto = new ArrayList<Auto>();
	private String statusName;
	private String processedByDisp;

	/**
	 * @return the availableAuto
	 */
	public List<Auto> getAvailableAuto() {
		return availableAuto;
	}

	/**
	 * @param availableAuto the availableAuto to set
	 */
	public void setAvailableAuto(List<Auto> availableAuto) {
		this.availableAuto = availableAuto;
	}

	@Override
	public String toString() {
		return "RequestFacade [id=" + id + ", driverId=" + driverId + ", driverName=" + driverName + ", flightId="
				+ flightId + ", flightName=" + flightName + ", seats=" + seats + ", availableAuto=" + availableAuto
				+ ", statusName=" + statusName + ", processedByDisp=" + processedByDisp + "]";
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the driverId
	 */
	public int getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @param driverName
	 *            the driverName to set
	 */
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	/**
	 * @return the flightId
	 */
	public int getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId the flightId to set
	 */
	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	/**
	 * @return the flightName
	 */
	public String getFlightName() {
		return flightName;
	}

	/**
	 * @param flightName
	 *            the flightName to set
	 */
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}

	/**
	 * @return the seats
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * @param seats
	 *            the seats to set
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * @param statusName
	 *            the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	/**
	 * @return the processedByDisp
	 */
	public String getProcessedByDisp() {
		return processedByDisp;
	}

	/**
	 * @param processedByDisp
	 *            the processedByDisp to set
	 */
	public void setProcessedByDisp(String processedByDisp) {
		this.processedByDisp = processedByDisp;
	}

}