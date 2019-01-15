package ua.nure.balagura.SummaryTask4.db.dao;

import java.sql.Connection;

import ua.nure.balagura.SummaryTask4.db.entity.Request;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface RequestDAO extends DAO<Request>{

	boolean update(Request req, Connection conn) throws DBException;
}	