package aplicacion.vista;

import javax.ejb.EJB;

import aplicacion.modelo.ejb.UsuariosEJB;
import aplicacion.modelo.pojo.Usuario;

public class Navegacion {

	private NavBar navBar;
	@EJB
	private UsuariosEJB usuariosEJB;

	public Navegacion(Usuario usuario, String modo) {
		if (usuario == null) {
			navBar = new NavBar(null, null);
			navBar.addRegistro(modo);
			navBar.addLogin(modo);
		} else {
			navBar = new NavBar(usuario.getNombre(), UsuariosEJB.getRutaFotoCompleta(usuario));
			navBar.addLogout(modo);
			navBar.addHistorial(modo);
			navBar.addDarseDeBaja(modo);
		}
	}

	@Override
	public String toString() {
		return navBar.toString();
	}
}
