package cl.tesoreria.GestionPersonasRest.dao;

import java.util.List;

import cl.tesoreria.GestionPersonasRest.vo.TiposVO;

public interface TiposDAO {

	List<TiposVO> findSexos() throws Exception;
	
	List<TiposVO> findEstadosPersonas() throws Exception;
	
	List<TiposVO> findEstadosValidados() throws Exception;
	
	List<TiposVO> findFuentesDatos() throws Exception;
	
	List<TiposVO> findTiposContribuyentes() throws Exception;
	
	List<TiposVO> findTiposPersonas() throws Exception;
}
