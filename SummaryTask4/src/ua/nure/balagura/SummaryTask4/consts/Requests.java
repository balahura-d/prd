package ua.nure.balagura.SummaryTask4.consts;

import ua.nure.balagura.SummaryTask4.db.entity.Status;

public class Requests { // НАЗВАНИЕ КОЛОНОК ДОЛЖНІ СООТВЕТСТВОВАТЬ БАЗЕ
	/*
	 * user
	 */
	public static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
	public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
	public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=?";
	public static final String SQL_INSERT_USER = "INSERT INTO users VALUES (default, ?, ?, ?, ?)";
	public static final String SQL_UPDATE_USER = "UPDATE users " + "SET login=?, password=?, full_name=?, role_id=? "
			+ "WHERE (id=?)";
	public static final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
	public static final String SQL_SELECT_USERS_BY_ROLE_ID = "SELECT * FROM users WHERE role_id=?";

	/*
	 * auto
	 */
	public static final String SQL_SELECT_AUTO_BY_ID = "SELECT * FROM autos WHERE id=?";
	public static final String SQL_SELECT_ALL_AUTOS = "SELECT * FROM autos";
	public static final String SQL_DELETE_AUTO_BY_ID = "DELETE FROM autos WHERE id=?";
	public static final String SQL_INSERT_AUTO = "INSERT INTO autos VALUES (default, ?, ?, ?)";
	public static final String SQL_UPDATE_AUTO = "UPDATE autos " + "SET name=?, seats=?, condition_id=? "
			+ "WHERE (id=?)";

	/*
	 * flight
	 */
	public static final String SQL_SELECT_FLIGHT_BY_ID = "SELECT * FROM flights WHERE id=?";
	public static final String SQL_SELECT_ALL_FLIGHTS = "SELECT * FROM flights";
	public static final String SQL_DELETE_FLIGHT_BY_ID = "DELETE FROM flights WHERE id=?";
	public static final String SQL_INSERT_FLIGHT = "INSERT INTO flights VALUES (default, ?, ?, null, null, ?)";
	public static final String SQL_UPDATE_FLIGHT = "UPDATE flights "
			+ "SET name=?, create_date=?, auto_id=?, driver_id=?, status_id=? " + "WHERE (id=?)";

	/*
	 * request
	 */
	public static final String SQL_SELECT_REQUEST_BY_ID = "SELECT * FROM requests WHERE id=?";
	public static final String SQL_SELECT_ALL_REQUESTS = "SELECT * FROM requests";
	public static final String SQL_DELETE_REQUEST_BY_ID = "DELETE FROM requests WHERE id=?";
	public static final String SQL_INSERT_REQUEST = "INSERT INTO requests VALUES (default, ?, ?, ?, ?, ?)";
	public static final String SQL_UPDATE_REQUEST = "UPDATE requests "
			+ "SET driver_id=?, flight_id=?, seats=?, status_id=?, processed_by=? " + "WHERE (id=?)";

	/*
	 * FACADES
	 */
	public static final String SQL_SELECT_ALL_FLIGHT_FACADES = "select f.id, f.name, f.create_date, f.status_id, s.name as status, u.full_name as driver, f.driver_id, a.name as auto "
			+ "from flights as f " + "left join users as u on f.driver_id = u.id "
			+ "left join statuses as s on f.status_id = s.id " + "left join autos as a on f.auto_id = a.id;";
	public static final String SQL_SELECT_ALL_AUTO_FACADES = "select a.id, a.name, a.seats, a.condition_id, c.name as 'condition' "
			+ "from autos as a " + "join conditions as c " + "where a.condition_id = c.id;";
	public static final String SQL_SELECT_ALL_REQUEST_FACADES = "select r.id, r.driver_id, r.flight_id, u.full_name as driver, f.name as flight, r.seats, s.name as status, u2.full_name as processed "
			+ "from requests as r " + "join users as u on r.driver_id = u.id "
			+ "join users as u2 on r.processed_by = u2.id " 
			+ "join flights as f on r.flight_id = f.id "
			+ "join statuses as s on r.status_id = s.id;";
	public static final String SQL_UPDATE_REQUEST_FLIGHT_AUTO = "UPDATE requests AS r, flights AS f, autos AS a "
			+ "SET r.status_id=?, r.processed_by=?, a.condition_id=?, f.auto_id=?, f.driver_id=?, f.status_id=? "
			+ "WHERE r.flight_id=f.id AND a.id=? AND r.id=?";
	public static final String SQL_SELECT_AUTO_FOR_REQUEST = "SELECT * FROM autos "
			+ "WHERE (condition_id BETWEEN 1 AND 3) AND (seats >= ?)";
	public static final String SQL_UPDATE_FLIGHT_AUTO_AFTER_SUBMIT = "UPDATE flights as f, autos AS a "
			+ "SET a.condition_id=?, f.auto_id=null, f.status_id=" + Status.CLOSED.ordinal() + " "
			+ "WHERE f.id=? AND a.id=f.auto_id;";
	public static final String SQL_UPDATE_FLIGHT_AUTO_AFTER_CANCEL = "UPDATE flights as f, autos AS a "
			+ "SET f.status_id=" + Status.CANCELED.ordinal() + ", f.auto_id=null, a.condition_id=? "
			+ "WHERE f.id=? AND a.id=f.auto_id;";
	public static final String SQL_SELECT_STAT_BETWEEN_DATE = "select count(*) FROM flights WHERE (create_date between ? AND ?) AND status_id=?;";

}
