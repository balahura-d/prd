package ua.nure.balagura.SummaryTask4.db.dao;

import java.util.Date;
import java.util.List;

import ua.nure.balagura.SummaryTask4.db.entity.Condition;
import ua.nure.balagura.SummaryTask4.db.entity.Flight;
import ua.nure.balagura.SummaryTask4.db.facade.FlightFacade;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface FlightFacadeDAO {

	List<FlightFacade> getAll() throws DBException;

	boolean submitFlight(Flight flight, Condition condition) throws DBException;

	boolean cancelFlight(Flight flight) throws DBException;

	int[] getStat(Date from, Date to) throws DBException;
}
