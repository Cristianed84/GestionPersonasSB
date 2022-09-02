package cl.tesoreria.GestionPersonasRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.tesoreria.GestionPersonasRest.service.TiposService;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
@SuppressWarnings("rawtypes")
@RequestMapping("/types/")
public class TypesController {

	@Autowired
	private TiposService ts;
	
	@GetMapping(value="sexes")
	public ResponseEntity findSexos(){	
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ts.findSexos();		
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	@GetMapping(value="statesPeople")
	public ResponseEntity findEstadosPersonas(){	
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ts.findEstadosPersonas();		
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	@GetMapping(value="statesValidated")
	public ResponseEntity findEstadosValidados(){	
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ts.findEstadosValidados();			
					
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	@GetMapping(value="dataSources")
	public ResponseEntity findFuentesDatos(){	
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ts.findFuentesDatos();
					
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	@GetMapping(value="taxpayingTypes")
	public ResponseEntity findTiposContribuyentes(){	
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ts.findTiposContribuyentes();
					
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	@GetMapping(value="typesPeople")
	public ResponseEntity findTiposPersonas(){	
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ts.findTiposPersonas();
					
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
}
