package aplicacion.vista;

import javax.ejb.EJB;

import aplicacion.modelo.ejb.UsuariosEJB;
import aplicacion.modelo.pojo.Usuario;

public class Navegacion {

	private NavBar navBar;
	@EJB
	private UsuariosEJB usuariosEJB;

	public Navegacion(Usuario usuario) {
		if (usuario == null) {
			navBar = new NavBar(null, null);
			navBar.addRegistro();
			navBar.addLogin();
		} else {
			navBar = new NavBar(usuario.getNombre(), UsuariosEJB.getRutaFotoCompleta(usuario));
			navBar.addLogout();
			navBar.addHistorial();
			navBar.addDarseDeBaja();
		}
	}

	@Override
	public String toString() {
		return navBar.toString();
	}
}
