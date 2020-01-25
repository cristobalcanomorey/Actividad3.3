package aplicacion.modelo.dao;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.ValidacionesMapper;
import aplicacion.modelo.pojo.Usuario;

/***
 * Gestiona los movimientos de la tabla validacion en la BBDD
 * 
 * @author tofol
 *
 */
public class ValidacionDAO {

	/***
	 * Añade el código de validación de un usuario.
	 * 
	 * @param usuario Usuario al que hace referencia.
	 * @param codigo  Codigo del usuario.
	 */
	public static void insertValidacion(Usuario usuario, String codigo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ValidacionesMapper validacionesMapper = sqlSession.getMapper(ValidacionesMapper.class);
			validacionesMapper.insertValidacion(codigo, usuario.getId().toString());
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Selecciona el id del usuario al que pertenece el código.
	 * 
	 * @param codigo Código de validacion.
	 * @return Id del usuario.
	 */
	public static String selectIdUsuario(String codigo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			ValidacionesMapper validacionesMapper = sqlSession.getMapper(ValidacionesMapper.class);
			return validacionesMapper.selectIdUsuario(codigo);
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Elimina un código.
	 * 
	 * @param codigo Código a borrar.
	 */
	public static void borrar(String codigo) {
		if (codigo != null) {
			SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
			try {
				ValidacionesMapper validacionesMapper = sqlSession.getMapper(ValidacionesMapper.class);
				validacionesMapper.borrar(codigo);
				sqlSession.commit();
			} finally {
				sqlSession.close();
			}
		}
	}
}
