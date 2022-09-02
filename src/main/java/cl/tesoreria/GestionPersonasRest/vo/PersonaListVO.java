package cl.tesoreria.GestionPersonasRest.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaListVO {
	
	private Long total;
	private int limit;
	private int page;
	private List<PersonaVO> data;
}
