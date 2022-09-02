package cl.tesoreria.GestionPersonasRest.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonaVO {
	
	private Long idPersona;
	private int rut;
	private String dv;
	private String apellidoPaterno;	
	private String apellidoMaterno;
	private String nombres;	
	private String fechaNacimiento;
	private String fechaDefuncion;
	private String email;
	private int idEstadoPersona;
	private int idTipoContribuyente;
	private int idTipoPersona;		
	private int idSexo;
	private int idEstadoValidado;
	private int idFuenteDato;	
	private Date fechaCreacion;
	private Date fechaModificacion;	

}
