package aplicacion.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

public interface ValidacionesMapper {

	public void insertValidacion(@Param("codigo") String codigo, @Param("idUsuario") String idUsuario);

}
