package cl.tesoreria.GestionPersonasRest.vo;

import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO {
	
	private boolean status;
	
	private String msg;
	
	private int code;
	
	private ObjectNode data;
}
