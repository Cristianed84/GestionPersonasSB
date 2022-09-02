package cl.tesoreria.GestionPersonasRest.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import cl.tesoreria.GestionPersonasRest.dao.TiposDAO;
import cl.tesoreria.GestionPersonasRest.service.TiposService;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;
import cl.tesoreria.GestionPersonasRest.vo.TiposVO;

@Service
public class TiposServiceImpl implements TiposService {

	@Autowired
	private TiposDAO td;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public ResponseVO findSexos() throws Exception {
		
		ResponseVO resp=new ResponseVO();
		List<TiposVO> ret=td.findSexos();
		resp.setStatus(true);
		if(ret!=null) {
			resp.setData(toObjectNode(ret));
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		return resp;
	}

	@Override
	public ResponseVO findEstadosPersonas() throws Exception {
		
		ResponseVO resp=new ResponseVO();
		List<TiposVO> ret=td.findEstadosPersonas();
		resp.setStatus(true);
		if(ret!=null) {
			resp.setData(toObjectNode(ret));
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		return resp;
	}

	@Override
	public ResponseVO findEstadosValidados() throws Exception {
		
		ResponseVO resp=new ResponseVO();
		List<TiposVO> ret=td.findEstadosValidados();
		resp.setStatus(true);
		if(ret!=null) {
			resp.setData(toObjectNode(ret));
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		return resp;
	}

	@Override
	public ResponseVO findFuentesDatos() throws Exception {
		
		ResponseVO resp=new ResponseVO();
		List<TiposVO> ret=td.findFuentesDatos();
		resp.setStatus(true);
		if(ret!=null) {
			resp.setData(toObjectNode(ret));
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		return resp;
	}

	@Override
	public ResponseVO findTiposContribuyentes() throws Exception {
		
		ResponseVO resp=new ResponseVO();
		List<TiposVO> ret=td.findTiposContribuyentes();
		resp.setStatus(true);
		if(ret!=null) {
			resp.setData(toObjectNode(ret));
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		return resp;
	}

	@Override
	public ResponseVO findTiposPersonas() throws Exception {
		
		ResponseVO resp=new ResponseVO();
		List<TiposVO> ret=td.findTiposPersonas();
		resp.setStatus(true);
		if(ret!=null) {
			resp.setData(toObjectNode(ret));
			resp.setCode(100);	
		}
		else {
			resp.setCode(300);
			resp.setMsg("no se encontró información");
		}
		return resp;
	}
	
	private ObjectNode toObjectNode(List<TiposVO> input) throws JsonProcessingException {
		
		ObjectNode resp = mapper.createObjectNode();
		
		ArrayNode arrayNode = mapper.createArrayNode();
		
		for(TiposVO t : input) {
			ObjectNode data= mapper.createObjectNode();
			data.put("id",t.getId());
			data.put("descripcion",t.getDescripcion());
			data.put("permiso",t.getPermiso());
			arrayNode.add(data);
		}		
		
		resp.set("data",mapper.convertValue(arrayNode,ArrayNode.class));
		
		return resp;
	}

}
