package cl.tesoreria.GestionPersonasRest.dao;

import cl.tesoreria.GestionPersonasRest.vo.RequestLoginVO;

public interface LoginDAO {

	int login(RequestLoginVO rq)throws Exception;
	
	int auth(RequestLoginVO rq) throws Exception;
	
}
