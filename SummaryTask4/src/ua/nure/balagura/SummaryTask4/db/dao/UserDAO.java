package ua.nure.balagura.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.balagura.SummaryTask4.db.entity.Role;
import ua.nure.balagura.SummaryTask4.db.entity.User;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface UserDAO extends DAO<User> {

	User selectUserByLogin(String name) throws DBException;

	List<User> selectUsersByRole(int role) throws DBException;

	List<User> selectUsersByRole(Role role) throws DBException;

}
