package cl.tesoreria.GestionPersonasRest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import cl.tesoreria.GestionPersonasRest.dao.LoginDAO;
import cl.tesoreria.GestionPersonasRest.security.TokenManager;
import cl.tesoreria.GestionPersonasRest.vo.RequestLoginVO;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;
import cl.tesoreria.GestionPersonasRest.vo.TokenVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	private TokenManager tm=new TokenManager();	
	
	@Autowired
	private LoginDAO ld;
	
	

	@Override
	public ResponseVO login(RequestLoginVO req) throws Exception {
		
		ResponseVO resp=new ResponseVO();	
		
		int ret=ld.login(req);
		switch (ret) {
		case 0:
			resp.setMsg("usuario/contraseña erronea");			
			break;
		case 1:
			resp.setMsg("usuario no vigente");
			break;
		case 2:
			resp.setMsg("ok");
			break;		
		}
		resp.setStatus(true);
		resp.setCode(100);
		return resp;
	}
	
	@Override
	public ResponseVO auth(RequestLoginVO req) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		ResponseVO resp=new ResponseVO();		
		int ret=ld.auth(req);		
		if(ret==1) {		
			resp.setStatus(true);
			TokenVO token=new TokenVO(tm.getJWTToken(req.getUsername()));
			String json = mapper.writeValueAsString(token);
			JsonNode jsonNode = mapper.readTree(json);
			ObjectNode objectNode = jsonNode.deepCopy();
			resp.setData(objectNode);
			resp.setMsg("ok");
		}		
		else {
			resp.setMsg("no autorizado");
		}
		resp.setCode(100);
		return resp;
	}

	
}
