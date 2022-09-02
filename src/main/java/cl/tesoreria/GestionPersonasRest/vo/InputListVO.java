package cl.tesoreria.GestionPersonasRest.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputListVO {
	
	private int page;
	private int limit;
	private String sort;
	private String direction;	
	private String filter;
	
}
