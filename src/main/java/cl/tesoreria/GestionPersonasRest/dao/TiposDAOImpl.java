package cl.tesoreria.GestionPersonasRest.dao;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.tesoreria.GestionPersonasRest.vo.TiposVO;

@Repository
@SuppressWarnings("unchecked")
public class TiposDAOImpl implements TiposDAO {

	@Autowired
	private EntityManager em;
	
	@Override
	public List<TiposVO> findSexos() throws Exception {
		List<TiposVO> resp = new ArrayList<TiposVO>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.SEXOS");						
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);
			
			
			List<Object[]> lst = sp.getResultList();
			resp = lstObjectToLstTipos(lst);
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	

	@Override
	public List<TiposVO> findEstadosPersonas() throws Exception {
		List<TiposVO> resp = new ArrayList<TiposVO>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.ESTADOS_PERSONAS");						
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);
			
			
			List<Object[]> lst = sp.getResultList();
			resp = lstObjectToLstTipos(lst);
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	@Override
	public List<TiposVO> findEstadosValidados() throws Exception {
		List<TiposVO> resp = new ArrayList<TiposVO>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.ESTADOS_VALIDADOS");						
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);
			
			
			List<Object[]> lst = sp.getResultList();
			resp = lstObjectToLstTipos(lst);
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	@Override
	public List<TiposVO> findFuentesDatos() throws Exception {
		List<TiposVO> resp = new ArrayList<TiposVO>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.FUENTES_DATOS");						
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);
			
			
			List<Object[]> lst = sp.getResultList();
			resp = lstObjectToLstTipos(lst);
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	@Override
	public List<TiposVO> findTiposContribuyentes() throws Exception {
		List<TiposVO> resp = new ArrayList<TiposVO>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.TIPOS_CONTRIBUYENTES");						
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);
			
			
			List<Object[]> lst = sp.getResultList();
			resp = lstObjectToLstTipos(lst);
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	@Override
	public List<TiposVO> findTiposPersonas() throws Exception {
		List<TiposVO> resp = new ArrayList<TiposVO>();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.TIPOS_PERSONAS");						
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);
			
			
			List<Object[]> lst = sp.getResultList();
			resp = lstObjectToLstTipos(lst);
		}catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	private List<TiposVO> lstObjectToLstTipos(List<Object[]> lst) {
		BigDecimal bg;
		List<TiposVO> resp = new ArrayList<TiposVO>();
		
		for(Object[] obj : lst) {
			TiposVO item=new TiposVO();
			bg=(BigDecimal)obj[0];
			item.setId(bg.intValue());
			item.setDescripcion((String) obj[1]);			
			if(obj.length>2) {
				item.setPermiso((String)obj[2]);
			}
			else {
				item.setPermiso("N");
			}
			
			resp.add(item);
		}
		
		return resp;
	}
}
