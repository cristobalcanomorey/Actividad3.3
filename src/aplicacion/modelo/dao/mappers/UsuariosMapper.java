package aplicacion.modelo.dao.mappers;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import aplicacion.modelo.pojo.Usuario;

public interface UsuariosMapper {

	public Usuario loginUsuario(@Param("correo") String correo, @Param("paswd") String paswd);

	public Usuario existeUsuario(@Param("correo") String correo);

	public void insertUsuario(Map<String, Object> usuario);

	public void validarPorId(@Param("idUsuario") String idUsuario);

	public void delete(Usuario usuario);

	public Usuario selectPorId(@Param("idUsuario") String idUsuario);

	public void limpiar();
}
