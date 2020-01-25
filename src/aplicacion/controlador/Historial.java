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
import aplicacion.modelo.ejb.CalculosEJB;
import aplicacion.modelo.ejb.ModoEJB;
import aplicacion.modelo.ejb.SesionesEJB;
import aplicacion.modelo.pojo.Usuario;

/***
 * Servlet para mostrar el historial del usuario.
 * 
 * @author tofol
 *
 */
@WebServlet("/Historial")
public class Historial extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGINA = "PaginaHistorial";
	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	CalculosEJB calculosEJB;
	@EJB
	ModoEJB modoEJB;

	/***
	 * Si el usuario está logeado le añade los calculos de BBDD y muestra la página
	 * de historial, si no lo redirige a la página principal.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();
		Usuario usuario = sesionesEJB.usuarioLogeado(session);
		String modo = request.getParameter("modo");
		if (usuario != null) {
			// Le añade el historial de cálculos al usuario para mostrarlos.
			usuario.setCalculos(calculosEJB.getHistorial(usuario));
		} else {
			try {
				response.sendRedirect("Principal?modo=" + modo);
			} catch (IOException e) {
				log.getLoggerHistorial().error("Se ha producido un error en GET Historial: ", e);
			}
		}
		modo = modoEJB.actualizarModo(usuario, modo);

		// Obtengo un dispatcher hacia el jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(modoEJB.obtenerRuta(modo, PAGINA));

		// Añado el objeto a la petición
		request.setAttribute("usuario", usuario);

		// Hago un forward al jsp con el objeto ya dentro de la petición
		try {
			rs.forward(request, response);
		} catch (ServletException | IOException e) {
			log.getLoggerHistorial().error("Se ha producido un error en GET Historial: ", e);
		}
	}
}
