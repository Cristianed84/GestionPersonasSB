package cl.tesoreria.GestionPersonasRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.tesoreria.GestionPersonasRest.service.PersonaService;
import cl.tesoreria.GestionPersonasRest.vo.PersonaVO;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@SuppressWarnings("rawtypes")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.POST,RequestMethod.GET,RequestMethod.PUT})
@RequestMapping("/GestionPersonasRest/persons/")
public class PersonController {

	@Autowired
	private PersonaService ps;
	
	@GetMapping(value="people")
	public ResponseEntity findAll(@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="0") int limit,
			@RequestParam(value="sort", defaultValue="") String sort,@RequestParam(value="fields", defaultValue="") String fields){		
		
		ResponseVO resp=new ResponseVO();
		try {
			
			resp=ps.getAll(page, limit, sort, fields, "");			
		    return new ResponseEntity<>(resp,HttpStatus.OK);
		    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	
	@GetMapping(value="people/{rut}")
	public ResponseEntity findByRut(@PathVariable Integer rut) {
		
		ResponseVO resp=new ResponseVO();
		
		try {
			resp=ps.getByRut(rut);			
			return new ResponseEntity<>(resp,HttpStatus.OK);					    
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}   
	}
	
	@PutMapping("people")
	public ResponseEntity update(@RequestBody PersonaVO rq){
		ResponseVO resp=new ResponseVO();
		try {
			resp=ps.update(rq);
			if(resp.isStatus()) {
				return new ResponseEntity<>(resp,HttpStatus.OK);		
			}
			else {
				return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
			}	
		}catch(Exception ex) {
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}
		 
	}
	
	
	@PostMapping("people")
	public ResponseEntity create(@RequestBody PersonaVO rq){
		ResponseVO resp=new ResponseVO();
		try {
			resp=ps.create(rq);
			if(resp.isStatus()) {
				return new ResponseEntity<>(resp,HttpStatus.CREATED);	
			}
			else {
				return new ResponseEntity<>(resp,HttpStatus.CONFLICT);
			}	
		}catch(Exception ex) {			
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}
	}
}
