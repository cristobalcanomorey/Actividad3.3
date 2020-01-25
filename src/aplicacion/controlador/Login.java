package aplicacion.controlador;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.ejb.ModoEJB;
import aplicacion.modelo.ejb.SesionesEJB;
import aplicacion.modelo.ejb.UsuariosEJB;
import aplicacion.modelo.pojo.Usuario;

/***
 * Servlet para iniciar sesión.
 * 
 * @author tofol
 *
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGINA = "PaginaLogin";
	@EJB
	UsuariosEJB usuariosEJB;

	@EJB
	SesionesEJB sesionesEJB;
	@EJB
	ModoEJB modoEJB;

	/***
	 * Si el usuario está logeado lo redirige a la página principal, si no le
	 * muestra la página de login
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		String modo = request.getParameter("modo");
		Usuario usuario = sesionesEJB.usuarioLogeado(session);

		if (usuario != null) {
			try {
				response.sendRedirect("Principal?modo=" + modo);
			} catch (IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en GET de Login: ", e);
			}
		} else {
			modo = modoEJB.actualizarModo(usuario, modo);

			// Obtengo un dispatcher hacia el jsp
			RequestDispatcher rs = getServletContext().getRequestDispatcher(modoEJB.obtenerRuta(modo, PAGINA));

			// Hago un forward al jsp con el objeto ya dentro de la petición
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en GET de Login: ", e);
			}
		}
	}

	/***
	 * Inicia la sesión del usuario, lo redirige a Login para mostrar error o lo
	 * redirige a la página principal
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		LogSingleton log = LogSingleton.getInstance();

		String correo = request.getParameter("correo");
		String paswd = request.getParameter("paswd");
		String modo = request.getParameter("modo");
		Usuario usuario = usuariosEJB.loginUsuario(correo, paswd);
		if (usuario == null) {
			try {
				response.sendRedirect("Login?modo=" + modo + "&error=1");
			} catch (IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en POST Login: ", e);
			}
		} else {
			if (usuario.isValidado()) {
				HttpSession session = request.getSession(true);
				sesionesEJB.loginUsuario(session, usuario);

				try {
					response.sendRedirect("Principal?modo=" + modoEJB.obtenerModo(usuario));
				} catch (IOException e) {
					log.getLoggerLogin().error("Se ha producido un error en POST Login: ", e);
				}
			} else {
				try {
					response.sendRedirect("Login?modo=" + modo + "&error=2");
				} catch (IOException e) {
					log.getLoggerLogin().error("Se ha producido un error en POST Login: ", e);
				}
			}
		}
	}

}
