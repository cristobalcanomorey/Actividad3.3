package aplicacion.modelo.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.dao.mappers.AltasBajasMapper;
import aplicacion.modelo.ejb.CalculosEJB;
import aplicacion.modelo.pojo.Usuario;

/***
 * Gestiona los movimientos de la tabla altas_bajas en la BBDD
 * 
 * @author tofol
 *
 */
public class AltasBajasDAO {

	private static final Logger LOG = LogSingleton.getInstance().getLoggerAltasBajasDAO();

	/***
	 * Registra la validación de un usuario.
	 * 
	 * @param usuario Usuario que se ha validado
	 */
	public static void registrarValidacion(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AltasBajasMapper altasBajasMapper = sqlSession.getMapper(AltasBajasMapper.class);
			Map<String, Object> altaBaja = new HashMap<String, Object>();
			altaBaja.put("correo", usuario.getCorreo());
			altaBaja.put("nombre", usuario.getNombre());
			altaBaja.put("tipoAccion", "V");
			altaBaja.put("fecha", CalculosEJB.fechaAString(new Date()));
			altasBajasMapper.registrarValidacion(altaBaja);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
//		String insert = "INSERT INTO altas_bajas (correo,nombre,tipoAccion,fecha) VALUES ('" + usuario.getCorreo()
//				+ "','" + usuario.getNombre() + "','V','" + CalculosEJB.fechaAString(new Date()) + "')";
//
//		try {
//			CON.setConnection("java:/comp/env", "jdbc/ActividadIMC");
//			if (CON.getConnection() != null) {
//				CON.setStatement();
//				CON.getStatement().executeUpdate(insert);
//			}
//		} catch (ClassNotFoundException | SQLException | NamingException e) {
//			LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//		} finally {
//			if (CON.getStatement() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//				}
//			}
//			if (CON.getConnection() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//				}
//			}
//		}
	}

	/***
	 * Selecciona todos los datos.
	 * 
	 * @return Altas, bajas y validaciones de los usuarios.
	 */
	public static ResultSet getAltasBajas() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AltasBajasMapper altasBajasMapper = sqlSession.getMapper(AltasBajasMapper.class);
			ResultSet ab = altasBajasMapper.getAltasBajas();

			if (ab.getRow() > 0) {
				ab.beforeFirst();
				return ab;
			}
		} catch (SQLException e) {
			LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
		} finally {
			sqlSession.close();
		}
		return null;
//		String query = "SELECT * FROM altas_bajas";
//		ResultSet rs = null;
//		try {
//			CON.setConnection("java:/comp/env", "jdbc/ActividadIMC");
//			if (CON.getConnection() != null) {
//				CON.setStatement();
//				rs = CON.getStatement().executeQuery(query);
//				rs.last();
//				if (rs.getRow() > 0) {
//					rs.beforeFirst();
//					return rs;
//				}
//				rs.close();
//			}
//		} catch (ClassNotFoundException | SQLException | NamingException e) {
//			LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//		} finally {
//			if (CON.getStatement() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//				}
//			}
//			if (CON.getConnection() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//				}
//			}
//		}
//		return rs;
	}

	/***
	 * Elimina todos los datos de la tabla.
	 */
	public static void vaciar() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AltasBajasMapper altasBajasMapper = sqlSession.getMapper(AltasBajasMapper.class);
			altasBajasMapper.vaciar();
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
//		String truncar = "TRUNCATE TABLE altas_bajas";
//		try {
//			CON.setConnection("java:/comp/env", "jdbc/ActividadIMC");
//			if (CON.getConnection() != null) {
//				CON.setStatement();
//				CON.getStatement().executeUpdate(truncar);
//			}
//		} catch (ClassNotFoundException | SQLException | NamingException e) {
//			LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//		} finally {
//			if (CON.getStatement() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//				}
//			}
//			if (CON.getConnection() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR ALTAS_BAJAS DAO: ", e);
//				}
//			}
//		}
	}

}
