package cl.tesoreria.GestionPersonasRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cl.tesoreria.GestionPersonasRest.dao.PersonaDAO;
import cl.tesoreria.GestionPersonasRest.vo.InputListVO;
import cl.tesoreria.GestionPersonasRest.vo.PersonaListVO;
import cl.tesoreria.GestionPersonasRest.vo.PersonaVO;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;

@Service
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaDAO pd;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public ResponseVO getAll(int page, int limit, String sort, String fields, String filter) throws Exception {
		
		String[] field=null;
		
		ResponseVO resp=new ResponseVO();
		
		InputListVO input=new InputListVO();
		
		input.setSort(null);
		input.setDirection(null);		
		
		if(!sort.equals("")) {
			String[] array=sort.split(",");
			String campo="";
			switch(array[0]) {
				case "idPersona":
					campo="ID_PERSONA";
					break;				
				case "rut":
					campo="RUT";
					break;
				case "dv":
					campo="DV";
					break;
				case "apellidoPaterno":
					campo="APELLIDO_PATERNO";
					break;
				case "apellidoMaterno":
					campo="APELLIDO_MATERNO";
					break;
				case "nombres":
					campo="NOMBRES";
					break;
				case "fechaNacimiento":
					campo="FECHA_NACIMIENTO";
					break;
				case "fechaDefuncion":
					campo="FECHA_DEFUNCION";
					break;
				case "email":
					campo="EMAIL";
					break;
				case "idEstadoPersona":
					campo="ID_ESTADO_PERSONA";
					break;
				case "idTipoContribuyente":
					campo="ID_TIPO_CONTRIBUYENTE";
					break;
				case "idTipoPersona":
					campo="ID_TIPO_PERSONA";
					break;
				case "idSexo":
					campo="ID_SEXO";
					break;
				case "idEstadoValidado":
					campo="ID_ESTADO_VALIDADO";
					break;
				case "idFuenteDato":
					campo="ID_FUENTE_DATO";
					break;
				case "fechaCreacion":
					campo="FECHA_CREACION";
					break;
				case "fechaModificacion":
					campo="FECHA_MODIFICACION";
					break;				
			}			
			if(!campo.equals("")) {
				
				input.setSort(campo);
				if(array.length>1) {
					String order=array[1].toUpperCase();
					if(order.equals("ASC") || order.equals("DESC")) {
						input.setDirection(order);							
					}								
				}				
			}			
		}	
		
		
		if(!fields.equals("")) {			
			field=fields.split(",");
		}		
		
		input.setPage(page);
		input.setLimit(limit);			
		input.setFilter("");		
		PersonaListVO ret=pd.getAll(input);
		resp.setCode(100);
		resp.setData(selectedByField(ret,field));
		resp.setStatus(true);
		return resp;
	}

	@Override
	public ResponseVO getByRut(Integer rut) throws Exception {
		
		ResponseVO resp=new ResponseVO();
		
		PersonaVO ret=pd.getByRut(rut);
		resp.setStatus(true);
		if(ret!=null) {
			
			  String json = mapper.writeValueAsString(ret); 
			  JsonNode jsonNode = mapper.readTree(json); 
			  ObjectNode objectNode = jsonNode.deepCopy();
			 
			
			resp.setData(objectNode);
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		
		return resp;
	}

	@Override
	public ResponseVO update(PersonaVO rq) throws Exception {
		
		ResponseVO resp=new ResponseVO();
		boolean ret=pd.update(rq);
		resp.setStatus(ret);
		resp.setData(null);		
		if(ret) {
			resp.setCode(100);
			resp.setMsg("registro actualizado");
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se pudo actualizar");
		}		
		return resp;
	}

	@Override
	public ResponseVO create(PersonaVO rq) throws Exception {
		
		ResponseVO resp=new ResponseVO();	
		
		boolean ret=pd.create(rq);
		resp.setStatus(ret);
		resp.setData(null);
		if(ret) {
			resp.setCode(100);			
			resp.setMsg("registro creado");
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se pudo crear");
		}
		return resp;
	}

	private ObjectNode selectedByField(PersonaListVO input, String[] field) throws JsonProcessingException {
		
		ObjectNode resp = mapper.createObjectNode();
		
		if(field!=null) {			
			resp.put("total", input.getTotal());
			resp.put("page",input.getPage());
			resp.put("limit",input.getLimit());
			ArrayNode arrayNode = mapper.createArrayNode();
			for(PersonaVO persona : input.getData()) {
				
				ObjectNode data= mapper.createObjectNode();				
				
				for(String f : field) {
					switch(f){ 
					case "idPersona":
						data.put(f, persona.getIdPersona());						
						break; 
					case "rut":
						data.put(f, persona.getRut());						
						break; 
					case "dv": 
						data.set(f,mapper.convertValue(persona.getDv(),JsonNode.class));
						break; 
					case "apellidoPaterno":
						data.put(f,persona.getApellidoPaterno());
						break; 
					case "apellidoMaterno":
						data.put(f,persona.getApellidoMaterno());
						break; 
					case "nombres": 
						data.put(f,persona.getNombres());
						break;
					case "fechaNacimiento":
						data.set(f,mapper.convertValue(persona.getFechaNacimiento().toString(),JsonNode.class));
						break; 
					case "fechaDefuncion":
						if(persona.getFechaDefuncion()!=null) {
							data.set(f,mapper.convertValue(persona.getFechaDefuncion().toString(),JsonNode.class));	
						}
						else {
							data.set(f,null);
						}
						
						break; 
					case "email":
						data.put(f,persona.getEmail());
						break; 
					case "idEstadoPersona": 
						data.put(f,persona.getIdEstadoPersona());
						break; 
					case "idTipoContribuyente": 
						data.put(f,persona.getIdTipoContribuyente());
						break;
					case "idTipoPersona": 
						data.put(f,persona.getIdTipoPersona());
						break; 
					case "idSexo":
						data.put(f,persona.getIdSexo());
						break; 
					case "idEstadoValidado":
						data.put(f,persona.getIdEstadoValidado());
						break; 
					case "idFuenteDato":
						data.put(f,persona.getIdFuenteDato());
						break; 
					case "fechaCreacion":
						data.set(f,mapper.convertValue(persona.getFechaCreacion(),JsonNode.class));
						break; 
					case "fechaModificacion":
						if(persona.getFechaModificacion()!=null) {
							data.set(f,mapper.convertValue(persona.getFechaModificacion().toString(),JsonNode.class));	
						}
						else {
							data.set(f,null);
						}						
						break; 
					}
				}
				arrayNode.add(data);
			}
			resp.set("data",mapper.convertValue(arrayNode,ArrayNode.class));
		}
		else {
			String json = mapper.writeValueAsString(input);
			JsonNode jsonNode = mapper.readTree(json);
			resp = jsonNode.deepCopy();
		}		
		
		return resp;
	}	
}
