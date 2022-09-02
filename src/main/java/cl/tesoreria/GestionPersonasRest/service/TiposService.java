package cl.tesoreria.GestionPersonasRest.service;

import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;

public interface TiposService {

	ResponseVO findSexos()throws Exception;
	
	ResponseVO findEstadosPersonas()throws Exception;
	
	ResponseVO findEstadosValidados()throws Exception;
	
	ResponseVO findFuentesDatos()throws Exception;
	
	ResponseVO findTiposContribuyentes()throws Exception;
	
	ResponseVO findTiposPersonas()throws Exception;
	
}
