package cl.tesoreria.GestionPersonasRest.dao;

import cl.tesoreria.GestionPersonasRest.vo.PersonaVO;
import cl.tesoreria.GestionPersonasRest.vo.InputListVO;
import cl.tesoreria.GestionPersonasRest.vo.PersonaListVO;

public interface PersonaDAO {

	PersonaListVO getAll(InputListVO input) throws Exception;	
	
	PersonaVO getByRut(Integer rut) throws Exception;
	
	boolean update(PersonaVO persona) throws Exception;
	
	boolean create(PersonaVO persona) throws Exception;
}
