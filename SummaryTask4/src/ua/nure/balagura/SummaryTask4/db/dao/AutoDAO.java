package ua.nure.balagura.SummaryTask4.db.dao;

import java.sql.Connection;

import ua.nure.balagura.SummaryTask4.db.entity.Auto;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface AutoDAO extends DAO<Auto> {

	boolean update(Auto auto, Connection conn) throws DBException;
}
