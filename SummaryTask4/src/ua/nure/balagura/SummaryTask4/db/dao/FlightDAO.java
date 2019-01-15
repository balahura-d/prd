package ua.nure.balagura.SummaryTask4.db.dao;

import java.sql.Connection;

import ua.nure.balagura.SummaryTask4.db.entity.Flight;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface FlightDAO extends DAO<Flight> {

	boolean update(Flight flight, Connection conn) throws DBException;
}