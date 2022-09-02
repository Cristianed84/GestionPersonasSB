package cl.tesoreria.GestionPersonasRest.dao;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.tesoreria.GestionPersonasRest.vo.RequestLoginVO;



@Repository
public class LoginDAOImpl implements LoginDAO {
	
	@Autowired
	private EntityManager em;

	@Override
	public int login(RequestLoginVO rq) throws Exception {
		
		int resp=0;		 
		
		try {
			
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.LOGIN");
			sp.registerStoredProcedureParameter("pUSER", String.class, ParameterMode.IN);			
			sp.registerStoredProcedureParameter("pPASS", String.class, ParameterMode.IN);
			sp.registerStoredProcedureParameter("oSALIDA", Integer.class, ParameterMode.OUT);

			sp.setParameter("pUSER", rq.getUsername());
			sp.setParameter("pPASS", rq.getPassword());
			resp=(Integer)sp.getOutputParameterValue("oSALIDA");

		} catch (Exception ex) {
			throw new Exception(ex);
		}		
		
		return resp;
	}

	@Override
	public int auth(RequestLoginVO rq) throws Exception {
		
		int resp=0;	
		
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.API_LOGIN");
			sp.registerStoredProcedureParameter("pUSER", String.class, ParameterMode.IN);			
			sp.registerStoredProcedureParameter("pPASS", String.class, ParameterMode.IN);
			sp.registerStoredProcedureParameter("oSALIDA", Integer.class, ParameterMode.OUT);

			sp.setParameter("pUSER", rq.getUsername());
			sp.setParameter("pPASS", rq.getPassword());			
			resp=(Integer)sp.getOutputParameterValue("oSALIDA");
			

		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return resp;
	}

}
