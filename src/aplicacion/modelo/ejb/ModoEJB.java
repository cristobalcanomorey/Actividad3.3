package aplicacion.modelo.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import aplicacion.modelo.pojo.Usuario;

@Stateless(mappedName = "ModoEJB")
@LocalBean
public class ModoEJB {
	public String actualizarModo(Usuario usuario, String modo) {
		String modoDelUsuario = "";
		if (usuario != null) {
			if (usuario.getModoNocturno()) {
				modoDelUsuario = "nocturno";
			} else {
				modoDelUsuario = "diurno";
			}
			if (modo != null & !modo.equals(modoDelUsuario)) {
				usuario = UsuariosEJB.cambiarModo(modo, usuario);
				if (usuario.getModoNocturno()) {
					modo = "nocturno";
				} else {
					modo = "diurno";
				}
			}
		}
		return modo;
	}

	public String obtenerRuta(String modo, String pagina) {
		if (modo != null && modo.equals("nocturno")) {
			return "/nocturno/" + pagina + ".jsp";
		} else if (modo == null || modo.equals("diurno")) {
			return "/" + pagina + ".jsp";
		}
		return "";
	}
}
