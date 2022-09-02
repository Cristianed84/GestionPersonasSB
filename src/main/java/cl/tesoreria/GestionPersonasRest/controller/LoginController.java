package cl.tesoreria.GestionPersonasRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.tesoreria.GestionPersonasRest.service.LoginService;
import cl.tesoreria.GestionPersonasRest.vo.RequestLoginVO;
import cl.tesoreria.GestionPersonasRest.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestMethod;


@SuppressWarnings("rawtypes")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping("/security/")
public class LoginController {

	@Autowired
	private LoginService ls;	
	
	
	@PostMapping(value="auth")
	public ResponseEntity auth(@RequestBody RequestLoginVO rq){
		ResponseVO resp=new ResponseVO();
		try {		
			resp=ls.auth(rq);			
			return new ResponseEntity<>(resp,HttpStatus.OK);			
		}catch(Exception ex) {
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}
	}
	
	
	@PostMapping(value="login")
	public ResponseEntity login(@RequestBody RequestLoginVO rq){
		ResponseVO resp=new ResponseVO();
		try {
			resp=ls.login(rq);			
			return new ResponseEntity<>(resp,HttpStatus.OK);
		}catch(Exception ex) {
			resp.setStatus(false);
			resp.setMsg(ex.getMessage());
			resp.setCode(200);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}
	}
}
