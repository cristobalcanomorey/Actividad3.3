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
			modoDelUsuario = obtenerModo(usuario);
			if (modo != null) {
				if (!modo.equals(modoDelUsuario)) {
					usuario = UsuariosEJB.cambiarModo(modo, usuario);
					modo = obtenerModo(usuario);
				}
			} else {
				modo = "diurno";
			}
		}
		return modo;
	}

	public String obtenerModo(Usuario usuario) {
		if (usuario.getModoNocturno()) {
			return "nocturno";
		} else {
			return "diurno";
		}
	}

	public String obtenerRuta(String modo, String pagina) {
		if (modo != null && modo.equals("nocturno")) {
			return "/nocturno/" + pagina + ".jsp";
		} else if (modo == null || modo.equals("diurno") || modo.equals("null")) {
			return "/" + pagina + ".jsp";
		}
		return "";
	}
}
