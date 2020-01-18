package aplicacion.modelo.dao.mappers;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Calculo;
import aplicacion.modelo.pojo.Usuario;

public interface CalculosMapper {

	public ArrayList<Calculo> getHistorial(Usuario usuario);

	public void insertCalculo(@Param("usuario") Usuario usuario, @Param("calculo") Calculo calculo,
			@Param("fecha") String fecha);
}
