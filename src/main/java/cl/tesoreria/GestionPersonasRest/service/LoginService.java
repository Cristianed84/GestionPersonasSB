package cl.tesoreria.GestionPersonasRest.service;

import cl.tesoreria.GestionPersonasRest.vo.RequestLoginVO;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;

public interface LoginService {
	
	ResponseVO login(RequestLoginVO req) throws Exception;
	
	ResponseVO auth(RequestLoginVO req) throws Exception;

}
