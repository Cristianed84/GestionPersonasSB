package cl.tesoreria.GestionPersonasRest.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TiposVO {
	private int id;
	private String descripcion;
	private String permiso;
}
