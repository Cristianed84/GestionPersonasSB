package cl.tesoreria.GestionPersonasRest.service;

import cl.tesoreria.GestionPersonasRest.vo.PersonaVO;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;

public interface PersonaService {

	ResponseVO getAll(int page, int limit, String sort, String fields, String filter) throws Exception;	

	ResponseVO getByRut(Integer rut)throws Exception;
	
	ResponseVO update(PersonaVO rq)throws Exception;
	
	ResponseVO create(PersonaVO rq)throws Exception;
}
