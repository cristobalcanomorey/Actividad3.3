package aplicacion.modelo.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

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

	/***
	 * Obtiene los cálculos de un usuario.
	 * 
	 * @param usuario Usuario al que se le busca su historial
	 * @return Historial del usuario
	 */
	public static ArrayList<Calculo> getHistorial(Usuario usuario) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CalculosMapper calculosMapper = sqlSession.getMapper(CalculosMapper.class);
			return calculosMapper.getHistorial(usuario);
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

	}

}
