package ua.nure.balagura.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.balagura.SummaryTask4.db.facade.AutoFacade;
import ua.nure.balagura.SummaryTask4.exception.DBException;

public interface AutoFacadeDAO {
	List<AutoFacade> gettAll() throws DBException;
}
