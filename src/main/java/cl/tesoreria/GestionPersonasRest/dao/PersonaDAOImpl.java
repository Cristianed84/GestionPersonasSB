package cl.tesoreria.GestionPersonasRest.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.tesoreria.GestionPersonasRest.vo.InputListVO;
import cl.tesoreria.GestionPersonasRest.vo.PersonaListVO;
import cl.tesoreria.GestionPersonasRest.vo.PersonaVO;

@Repository
@SuppressWarnings("unchecked")
public class PersonaDAOImpl implements PersonaDAO {

	@Autowired
	private EntityManager em;
	
	
	@Override
	public PersonaListVO getAll(InputListVO input) throws Exception {	
		
		PersonaListVO resp = new PersonaListVO();
		
		try {
			StoredProcedureQuery sp=null;
			if(input.getPage()==0 && input.getLimit()==0) {
				sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.PERSONAS_ALL");
			}
			else {
				sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.PERSONAS")
				.registerStoredProcedureParameter("pPAGE", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pLIMIT", Integer.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pSORT", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("pDIR", String.class, ParameterMode.IN)				
				.registerStoredProcedureParameter("pFILTER", String.class, ParameterMode.IN);
				sp.setParameter("pPAGE", input.getPage());
				sp.setParameter("pLIMIT", input.getLimit());
				sp.setParameter("pSORT", input.getSort());
				sp.setParameter("pDIR", input.getDirection());				
				sp.setParameter("pFILTER", input.getFilter());
			}
			sp.registerStoredProcedureParameter("oTOTAL", Long.class, ParameterMode.OUT);
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);

			
			
			List<Object[]> lst = sp.getResultList();
			List<PersonaVO> data = lstObjectToLstPersona(lst);
			resp.setData(data);
			resp.setTotal((Long) sp.getOutputParameterValue("oTOTAL"));
			resp.setPage(input.getPage());
			resp.setLimit(input.getLimit());

		} catch (Exception ex) {
			throw new Exception(ex);
		}
		return resp;
	}

	@Override
	public PersonaVO getByRut(Integer rut) throws Exception {
		PersonaVO resp = new PersonaVO();
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.PERSONAS_RUT");
			sp.registerStoredProcedureParameter("pRUT", Integer.class, ParameterMode.IN);			
			sp.registerStoredProcedureParameter("IO_CURSOR", Class.class, ParameterMode.REF_CURSOR);

			sp.setParameter("pRUT", rut);
			
			List<Object[]> lst = sp.getResultList();

			resp = lstObjectToLstPersona(lst).get(0);

		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return resp;
	}

	@Override
	public boolean update(PersonaVO persona) throws Exception {
		
		boolean resp = false;
		Date fechaJ=null;
		java.sql.Date fechaNac = new java.sql.Date(0);
		java.sql.Date fechaDef = new java.sql.Date(0);
		java.sql.Date fecha = new java.sql.Date(0);
		
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.PERSONAS_UPDATE")
			.registerStoredProcedureParameter("pRUT", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pPATERNO", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pMATERNO", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pNOMBRES", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pFECHA_NAC", java.sql.Date.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pFECHA_DEF", java.sql.Date.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pEMAIL", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_ESTADO_PERSONA", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_TIPO_CONTRIBUYENTE", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_TIPO_PERSONA", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_SEXO", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_ESTADO_VALIDADO", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_FUENTE_DATO", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pFECHA_MODIFICACION", java.sql.Date.class, ParameterMode.IN);
			
			sp.setParameter("pRUT", persona.getRut());
			sp.setParameter("pPATERNO", persona.getApellidoPaterno());
			sp.setParameter("pMATERNO", persona.getApellidoMaterno());
			sp.setParameter("pNOMBRES", persona.getNombres());
			
			if(persona.getFechaNacimiento()!=null) {
				fechaJ=new SimpleDateFormat("yyyy-MM-dd").parse(persona.getFechaNacimiento());
				fechaNac.setTime(fechaJ.getTime());
				sp.setParameter("pFECHA_NAC", fechaNac);
			}
			else {
				sp.setParameter("pFECHA_NAC", null);
			}
			
			if(persona.getFechaDefuncion()!=null) {
				fechaJ=new SimpleDateFormat("yyyy-MM-dd").parse(persona.getFechaDefuncion());
				fechaDef.setTime(fechaJ.getTime());
				sp.setParameter("pFECHA_DEF", fechaDef);
			}
			else {
				sp.setParameter("pFECHA_DEF", null);
			}
			
			sp.setParameter("pEMAIL", persona.getEmail());
			sp.setParameter("pID_ESTADO_PERSONA", persona.getIdEstadoPersona());
			sp.setParameter("pID_TIPO_CONTRIBUYENTE", persona.getIdTipoContribuyente());
			sp.setParameter("pID_TIPO_PERSONA", persona.getIdTipoPersona());
			sp.setParameter("pID_SEXO", persona.getIdSexo());
			sp.setParameter("pID_ESTADO_VALIDADO", persona.getIdEstadoValidado());
			sp.setParameter("pID_FUENTE_DATO", persona.getIdFuenteDato());
			
			if(persona.getFechaModificacion()!=null) {
				fecha.setTime(persona.getFechaModificacion().getTime());
				sp.setParameter("pFECHA_MODIFICACION", fecha);
			}
			else {
				sp.setParameter("pFECHA_MODIFICACION", null);
			}			
			
			sp.execute();
			resp=true;

		} catch (Exception ex) {
			throw new Exception(ex);
		}
		
		return resp;
	}

	@Override
	public boolean create(PersonaVO persona) throws Exception {
		
		boolean resp = false;
		Date fechaJ=null;
		java.sql.Date fechaNac = new java.sql.Date(0);
		java.sql.Date fechaDef = new java.sql.Date(0);
		java.sql.Date fecha = new java.sql.Date(0);
		
		try {
			StoredProcedureQuery sp = em.createStoredProcedureQuery("PKG_API_PRUEBA.PERSONAS_CREATE")
			.registerStoredProcedureParameter("pRUT", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pDV", char.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pPATERNO", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pMATERNO", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pNOMBRES", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pFECHA_NAC", java.sql.Date.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pFECHA_DEF", java.sql.Date.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pEMAIL", String.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_ESTADO_PERSONA", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_TIPO_CONTRIBUYENTE", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_TIPO_PERSONA", char.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_SEXO", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_ESTADO_VALIDADO", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pID_FUENTE_DATO", Integer.class, ParameterMode.IN)
			.registerStoredProcedureParameter("pFECHA_CREACION", java.sql.Date.class, ParameterMode.IN);
			
			sp.setParameter("pRUT", persona.getRut());
			sp.setParameter("pDV", persona.getDv());
			sp.setParameter("pPATERNO", persona.getApellidoPaterno());
			sp.setParameter("pMATERNO", persona.getApellidoMaterno());
			sp.setParameter("pNOMBRES", persona.getNombres());
			
			if(persona.getFechaNacimiento()!=null) {
				fechaJ=new SimpleDateFormat("yyyy-MM-dd").parse(persona.getFechaNacimiento());
				fechaNac.setTime(fechaJ.getTime());
				sp.setParameter("pFECHA_NAC", fechaNac);
			}
			else {
				sp.setParameter("pFECHA_NAC", null);
			}
			
			if(persona.getFechaDefuncion()!=null) {
				
				fechaJ=new SimpleDateFormat("yyyy-MM-dd").parse(persona.getFechaDefuncion());
				fechaDef.setTime(fechaJ.getTime());
				sp.setParameter("pFECHA_DEF", fechaDef);
			}
			else {
				sp.setParameter("pFECHA_DEF", null);
			}
			
			sp.setParameter("pEMAIL", persona.getEmail());
			sp.setParameter("pID_ESTADO_PERSONA", persona.getIdEstadoPersona());
			sp.setParameter("pID_TIPO_CONTRIBUYENTE", persona.getIdTipoContribuyente());
			sp.setParameter("pID_TIPO_PERSONA", persona.getIdTipoPersona());
			sp.setParameter("pID_SEXO", persona.getIdSexo());
			sp.setParameter("pID_ESTADO_VALIDADO", persona.getIdEstadoValidado());
			sp.setParameter("pID_FUENTE_DATO", persona.getIdFuenteDato());
			
			if(persona.getFechaCreacion()!=null) {
				fecha.setTime(persona.getFechaCreacion().getTime());
				sp.setParameter("pFECHA_CREACION", fecha);
			}
			else {
				sp.setParameter("pFECHA_CREACION", null);
			}			
			
			sp.execute();
			resp=true;
			
			
		} catch (Exception ex) {
			throw new Exception(ex);
		}

		return resp;
	}
	
	
	public String convierteFecha(Date fecha) {
		return fecha.toString().split(" ")[0];        
    }
	
	
	private List<PersonaVO> lstObjectToLstPersona(List<Object[]> lst) {		
		
		BigDecimal bg;				
		List<PersonaVO> resp = new ArrayList<PersonaVO>();

		for (Object[] obj : lst) {
			PersonaVO item = new PersonaVO();
			bg=(BigDecimal)obj[0];
			item.setIdPersona(bg.longValue());
			bg=(BigDecimal)obj[1];
			item.setRut(bg.intValue());
			item.setDv((String) obj[2]);
			item.setApellidoPaterno((String) obj[3]);
			item.setApellidoMaterno((String) obj[4]);
			item.setNombres((String) obj[5]);
			
			if (obj[6] != null) {								
				item.setFechaNacimiento(convierteFecha((Date)obj[6]));
				
			} else {
				item.setFechaNacimiento(null);
			}
			
			if (obj[7] != null) {				
				item.setFechaDefuncion(convierteFecha((Date)obj[7]));
				
			} else {
				item.setFechaDefuncion(null);
			}
			
			item.setEmail((String) obj[8]);
			bg=(BigDecimal)obj[9];
			item.setIdEstadoPersona(bg.intValue());
			bg=(BigDecimal)obj[10];
			item.setIdTipoContribuyente(bg.intValue());
			bg=(BigDecimal)obj[11];
			item.setIdTipoPersona(bg.intValue());
			bg=(BigDecimal)obj[12];
			item.setIdSexo(bg.intValue());
			bg=(BigDecimal)obj[13];
			item.setIdEstadoValidado(bg.intValue());
			bg=(BigDecimal)obj[14];
			item.setIdFuenteDato(bg.intValue());
			if (obj[15] != null) {
				item.setFechaCreacion((Date)obj[15]);
				
			} else {
				item.setFechaCreacion(null);
			}
			
			if (obj[16] != null) {
				item.setFechaModificacion((Date)obj[16]);
				
			} else {
				item.setFechaModificacion(null);
			}
			
			resp.add(item);
		}

		return resp;
	}

}
