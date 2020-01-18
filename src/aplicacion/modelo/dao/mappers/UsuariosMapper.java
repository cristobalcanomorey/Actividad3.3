package aplicacion.modelo.dao.mappers;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Usuario;

public interface UsuariosMapper {

	public Usuario existeUsuario(@Param("correo") String correo, @Param("paswd") String paswd);

}
