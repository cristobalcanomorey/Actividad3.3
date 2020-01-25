package aplicacion.modelo.ejb;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.dao.AltasBajasDAO;
import aplicacion.modelo.dao.UsuarioDAO;
import aplicacion.modelo.pojo.AltaBaja;
import aplicacion.modelo.pojo.Usuario;

@Stateless
@LocalBean
public class AltasBajasEJB {

	/***
	 * Añade un código de validación a la BBDD
	 * 
	 * @param idUsuario Usuario al que pertenece
	 */
	public static void registrarValidacion(String idUsuario) {
		Usuario usuario = UsuarioDAO.selectPorId(idUsuario);
		AltasBajasDAO.registrarValidacion(usuario);
	}

	/***
	 * Recoge una lista con todos los datos de la tabla altas_bajas
	 * 
	 * @return Lista con las altas y las bajas
	 * @throws SQLException Error de la BBDD
	 */
	public ArrayList<AltaBaja> getAltasBajas() throws SQLException {
		return AltasBajasDAO.getAltasBajas();
	}

	/***
	 * Elimina todos los datos de la tabla altas_bajas
	 */
	public void vaciar() {
		AltasBajasDAO.vaciar();
	}

}
