package ua.nure.balagura.SummaryTask4.db.entity;

public enum Status {
	OPEN, PROGRESS, CLOSED, CANCELED;

	public static Status getStatus(Flight flight) {
		int statusId = flight.getStatusId();
		return Status.values()[statusId];
	}

	public String getName() {
		return name().toLowerCase();
	}
}
