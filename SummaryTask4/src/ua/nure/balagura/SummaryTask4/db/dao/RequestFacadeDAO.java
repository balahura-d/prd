package ua.nure.balagura.SummaryTask4.db.dao;

import java.sql.SQLException;
import java.util.List;

import ua.nure.balagura.SummaryTask4.db.entity.Auto;
import ua.nure.balagura.SummaryTask4.db.entity.Request;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.db.facade.RequestFacade;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface RequestFacadeDAO {
	List<RequestFacade> getAll() throws DBException;
	boolean applyRequest(Request req, User processed, Auto auto) throws DBException,SQLException;
	void addAvailableAuto(RequestFacade requestFacade) throws DBException;
}
