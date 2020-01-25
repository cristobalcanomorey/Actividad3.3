package aplicacion.controlador;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
 * Servlet para registrar usuarios.
 * 
 * @author tofol
 *
 */
@WebServlet("/Registro")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5)
public class Registro extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FALTAN_DATOS = "1";
	private static final String USUARIO_EXISTE = "2";
	private static final String ERROR_CORREO = "3";
	private static final String PAGINA = "PaginaRegistro";

	@EJB
	SesionesEJB sesionesEJB;

	@EJB
	UsuariosEJB usuariosEJB;

	@EJB
	ModoEJB modoEJB;

	/***
	 * Si el usuario está logeado lo redirige a la página principal, si no, muestra
	 * la página de registro.
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
				log.getLoggerRegistro().error("Se ha producido un error en GET Registro: ", e);
			}
		} else {
			modo = modoEJB.actualizarModo(usuario, modo);

			// Obtengo un dispatcher hacia el jsp
			RequestDispatcher rs = getServletContext().getRequestDispatcher(modoEJB.obtenerRuta(modo, PAGINA));

			// Hago un forward al jsp con el objeto ya dentro de la petición
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerLogin().error("Se ha producido un error en GET de Registro: ", e);
			}
		}
	}

	/***
	 * Si el usuario está logeado lo redirige a la página principal, si no, guarda
	 * el usuario y su código de autenticación en la BBDD
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		LogSingleton log = LogSingleton.getInstance();

		Usuario logeado = null;
		if (session != null) {
			logeado = sesionesEJB.usuarioLogeado(session);
		}
		String modo = request.getParameter("modo");
		modo = modoEJB.actualizarModo(logeado, modo);

		// Obtengo un dispatcher hacia el jsp
		RequestDispatcher rs = getServletContext().getRequestDispatcher(modoEJB.obtenerRuta(modo, PAGINA));
		if (logeado != null) {
			try {
				if (!response.isCommitted())
					response.sendRedirect("Principal?modo=" + modo);
			} catch (IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en POST Registro: ", e);
			}
		}
		String uploadPath = getServletContext().getRealPath(UsuariosEJB.getUploadDirectory());
		String nombre = request.getParameter("nombre");
		String correo = request.getParameter("correo");
		String paswd = request.getParameter("paswd");
		String fPerfil = null;
		boolean correoEnviado = false;
		Usuario nuevo = null;

		if (nombre != null & correo != null & paswd != null) {
			if (!usuariosEJB.existeUsuario(correo)) {
				try {
					// Establece el correo sin @ y sin puntos como el nombre de su foto de perfil
					String rutaPerfil = correo.replace("@", "_").replace(".", "_");
					fPerfil = usuariosEJB.crearFotoDePerfil(uploadPath, request.getParts(), rutaPerfil);
					nuevo = new Usuario(correo, nombre, paswd, fPerfil, false, new Date(), false);
					correoEnviado = usuariosEJB.registrarUsuario(nuevo);
				} catch (IOException | ServletException e) {
					log.getLoggerRegistro().error("Se ha producido un error en POST Registro: ", e);
				}
			} else {
				try {
					if (!response.isCommitted())
						response.sendRedirect("Registro?modo=" + modo + "&error=" + USUARIO_EXISTE);
				} catch (IOException e) {
					log.getLoggerRegistro().error("Se ha producido un error en POST Registro: ", e);
				}
			}
		} else {
			try {
				if (!response.isCommitted())
					response.sendRedirect("Registro?modo=" + modo + "&error=" + FALTAN_DATOS);
			} catch (IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en POST Registro: ", e);
			}
		}

		if (correoEnviado) {
			// Añado el objeto a la petición
			request.setAttribute("enviado", "true");

			// Hago un forward al jsp con el objeto ya dentro de la petición
			try {
				rs.forward(request, response);
			} catch (ServletException | IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en POST Registro: ", e);
			}
		} else {
			try {
				if (!response.isCommitted())
					response.sendRedirect("Registro?modo=" + modo + "&error=" + ERROR_CORREO);
			} catch (IOException e) {
				log.getLoggerRegistro().error("Se ha producido un error en POST Registro: ", e);
			}
		}
	}

}
