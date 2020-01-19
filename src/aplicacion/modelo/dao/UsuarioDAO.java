package aplicacion.modelo.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.UsuariosMapper;
import aplicacion.modelo.ejb.UsuariosEJB;
import aplicacion.modelo.pojo.Usuario;

/***
 * Gestiona los movimientos de la tabla usuario en la BBDD
 * 
 * @author tofol
 *
 */
public class UsuarioDAO {

	/***
	 * Comprueba si existe un usuario y devuelve su información
	 * 
	 * @param correo El correo del usuario
	 * @param paswd  La contraseña del usuario
	 * @return Un objeto Usuario con la información del usuario si existe o null.
	 */
	public static Usuario loginUsuario(String correo, String paswd) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		Usuario usuario = null;
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			if (correo != null && paswd != null) {
				usuario = usuariosMapper.loginUsuario(correo, paswd);
			}
			return usuario;
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Comprueba si hay un usuario con ese correo.
	 * 
	 * @param correo Correo que se busca.
	 * @return True si lo ha encontrado, false si no.
	 */
	public static boolean existeUsuario(String correo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			Usuario usuario = null;
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			if (correo != null) {
				usuario = usuariosMapper.existeUsuario(correo);
			}
			return usuario != null;
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Añade un usuario a la tabla
	 * 
	 * @param nuevo El nuevo usuario
	 */
	public static void insertUsuario(Usuario nuevo) {
		int esValidado = nuevo.isValidado() ? 1 : 0;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		Map<String, Object> usuario = new HashMap<String, Object>();
		usuario.put("correo", nuevo.getCorreo());
		usuario.put("nombre", nuevo.getNombre());
		usuario.put("password", nuevo.getPassword());
		usuario.put("foto", nuevo.getFoto());
		usuario.put("esValidado", esValidado);
		usuario.put("fecha", UsuariosEJB.fechaAString(nuevo.getFechaRegistro()));
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			usuariosMapper.insertUsuario(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}

	}

	/***
	 * Valida a un usuario.
	 * 
	 * @param idUsuario Id del usuario a validar.
	 */
	public static void validarPorId(String idUsuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			usuariosMapper.validarPorId(idUsuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Borra a un usuario.
	 * 
	 * @param usuario Usuario a borrar.
	 */
	public static void delete(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			usuariosMapper.delete(usuario);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Obtiene un usuario por su id.
	 * 
	 * @param idUsuario Id del usuario.
	 * @return Usuario encontrado.
	 */
	public static Usuario selectPorId(String idUsuario) {
		Usuario usu = null;
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			usu = usuariosMapper.selectPorId(idUsuario);
			return usu;
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Quita a todos los usuarios que aún no han sido validados.
	 */
	public static void limpiar() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UsuariosMapper usuariosMapper = sqlSession.getMapper(UsuariosMapper.class);
			usuariosMapper.limpiar();
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}
}
