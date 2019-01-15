package ua.nure.balagura.SummaryTask4.db.entity;

public class Request extends Entity {
	private int driverId;
	private int flightId;
	private int seats;
	private int statusId;
	private int processedBy;

	/**
	 * @return the driverId
	 */
	public int getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId
	 *            the driverId to set
	 */
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the flightId
	 */
	public int getFlightId() {
		return flightId;
	}

	/**
	 * @param flightId
	 *            the flightId to set
	 */
	public void setFlightId(int flightId) {
		this.flightId = flightId;
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
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return the processedBy
	 */
	public int getProcessedBy() {
		return processedBy;
	}

	/**
	 * @param processedBy
	 *            the processedBy to set
	 */
	public void setProcessedBy(int processedBy) {
		this.processedBy = processedBy;
	}

	@Override
	public String toString() {
		return "Request [driverId=" + driverId + ", flightId=" + flightId + ", seats=" + seats + ", statusId="
				+ statusId + ", processedBy=" + processedBy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + driverId;
		result = prime * result + flightId;
		result = prime * result + processedBy;
		result = prime * result + seats;
		result = prime * result + statusId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Request other = (Request) obj;
		if (driverId != other.driverId)
			return false;
		if (flightId != other.flightId)
			return false;
		if (processedBy != other.processedBy)
			return false;
		if (seats != other.seats)
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}

}
