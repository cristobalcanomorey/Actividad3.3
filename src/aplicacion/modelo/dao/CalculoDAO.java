package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;

import aplicacion.modelo.JDBCSingleton;
import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.dao.mappers.CalculosMapper;
import aplicacion.modelo.ejb.CalculosEJB;
import aplicacion.modelo.pojo.Calculo;
import aplicacion.modelo.pojo.Usuario;

/***
 * Gestiona los movimientos de la tabla calculo en la BBDD
 * 
 * @author tofol
 *
 */
public class CalculoDAO {

	private static final Logger LOG = LogSingleton.getInstance().getLoggerCalculoDAO();
	private static final JDBCSingleton CON = JDBCSingleton.getInstance();

	/***
	 * Obtiene los cálculos de un usuario.
	 * 
	 * @param usuario Usuario al que se le busca su historial
	 * @return Historial del usuario
	 */
	public static ArrayList<Calculo> getHistorial(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		ArrayList<Calculo> hist = null;
		try {
			CalculosMapper calculosMapper = sqlSession.getMapper(CalculosMapper.class);
			hist = calculosMapper.getHistorial(usuario);
			return hist;
		} finally {
			sqlSession.close();
		}
	}

	/***
	 * Añade un calculo.
	 * 
	 * @param usuario Usuario que ha hecho el cálculo
	 * @param calculo Calculo realizado.
	 */
	public static void insertCalculo(Usuario usuario, Calculo calculo) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CalculosMapper calculosMapper = sqlSession.getMapper(CalculosMapper.class);
			calculosMapper.insertCalculo(usuario, calculo, CalculosEJB.fechaAString(calculo.getFecha()));
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
//		String query = "INSERT INTO calculo (estatura,peso,fecha,idUsuario) values ('" + calculo.getEstatura() + "','"
//				+ calculo.getPeso() + "','" + CalculosEJB.fechaAString(calculo.getFecha()) + "','" + usuario.getId()
//				+ "')";
//		try {
//			CON.setConnection("java:/comp/env", "jdbc/ActividadIMC");
//			if (CON.getConnection() != null) {
//				CON.setStatement();
//				CON.getStatement().executeUpdate(query);
//			}
//		} catch (ClassNotFoundException | SQLException | NamingException e) {
//			LOG.error("ERROR CALCULO DAO: ", e);
//		} finally {
//			if (CON.getStatement() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR CALCULO DAO: ", e);
//				}
//			}
//			if (CON.getConnection() != null) {
//				try {
//					CON.getConnection().close();
//				} catch (SQLException e) {
//					LOG.error("ERROR CALCULO DAO: ", e);
//				}
//			}
//		}

	}

}
