package ua.nure.balagura.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.balagura.SummaryTask4.db.entity.Entity;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface DAO<T extends Entity> {

	T selectById(int id) throws DBException;

	List<T> getAll() throws DBException;

	boolean deleteById(int id) throws DBException;

	boolean create(T entity) throws DBException;

	boolean update(T entity) throws DBException;

}
