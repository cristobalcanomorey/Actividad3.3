package aplicacion.modelo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import aplicacion.modelo.dao.mappers.AltasBajasMapper;
import aplicacion.modelo.ejb.CalculosEJB;
import aplicacion.modelo.pojo.AltaBaja;
import aplicacion.modelo.pojo.Usuario;

/***
 * Gestiona los movimientos de la tabla altas_bajas en la BBDD
 * 
 * @author tofol
 *
 */
public class AltasBajasDAO {

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
	}

	/***
	 * Selecciona todos los datos.
	 * 
	 * @return Altas, bajas y validaciones de los usuarios.
	 */
	public static ArrayList<AltaBaja> getAltasBajas() {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AltasBajasMapper altasBajasMapper = sqlSession.getMapper(AltasBajasMapper.class);
			ArrayList<AltaBaja> ab = altasBajasMapper.getAltasBajas();

			if (ab.size() > 0) {
				return ab;
			} else {
				return null;
			}
		} finally {
			sqlSession.close();
		}
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
	}

}
