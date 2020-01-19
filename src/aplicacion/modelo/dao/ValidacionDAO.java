package aplicacion.modelo.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import aplicacion.modelo.JDBCSingleton;
import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.dao.mappers.ValidacionesMapper;
import aplicacion.modelo.pojo.Usuario;

/***
 * Gestiona los movimientos de la tabla validacion en la BBDD
 * 
 * @author tofol
 *
 */
public class ValidacionDAO {

	private static final Logger LOG = LogSingleton.getInstance().getLoggerValidacionDAO();
	private static final JDBCSingleton CON = JDBCSingleton.getInstance();

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
//		String idUsuario = null;
//		if (codigo != null) {
//			String query = "SELECT * FROM validacion WHERE codigo='" + codigo + "'";
//			try {
//				CON.setConnection("java:/comp/env", "jdbc/ActividadIMC");
//				if (CON.getConnection() != null) {
//					CON.setStatement();
//					ResultSet rs = CON.getStatement().executeQuery(query);
//					rs.last();
//					if (rs.getRow() > 0) {
//						rs.first();
//						idUsuario = rs.getString("idUsuario");
//					}
//					rs.close();
//				}
//			} catch (ClassNotFoundException | SQLException | NamingException e) {
//				LOG.error("ERROR VALIDACION DAO: ", e);
//			} finally {
//				if (CON.getStatement() != null) {
//					try {
//						CON.getConnection().close();
//					} catch (SQLException e) {
//						LOG.error("ERROR VALIDACION DAO: ", e);
//					}
//				}
//				if (CON.getConnection() != null) {
//					try {
//						CON.getConnection().close();
//					} catch (SQLException e) {
//						LOG.error("ERROR VALIDACION DAO: ", e);
//					}
//				}
//			}
//		}
//		return idUsuario;
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
//			String query = "DELETE FROM validacion WHERE codigo='" + codigo + "'";
//			try {
//				CON.setConnection("java:/comp/env", "jdbc/ActividadIMC");
//				if (CON.getConnection() != null) {
//					CON.setStatement();
//					CON.getStatement().executeUpdate(query);
//				}
//			} catch (ClassNotFoundException | SQLException | NamingException e) {
//				LOG.error("ERROR VALIDACION DAO: ", e);
//			} finally {
//				if (CON.getStatement() != null) {
//					try {
//						CON.getConnection().close();
//					} catch (SQLException e) {
//						LOG.error("ERROR VALIDACION DAO: ", e);
//					}
//				}
//				if (CON.getConnection() != null) {
//					try {
//						CON.getConnection().close();
//					} catch (SQLException e) {
//						LOG.error("ERROR VALIDACION DAO: ", e);
//					}
//				}
//			}
		}
	}
}
