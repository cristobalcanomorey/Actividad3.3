package aplicacion.modelo.ejb;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;

import aplicacion.modelo.LogSingleton;
import aplicacion.modelo.pojo.AltaBaja;
import aplicacion.modelo.pojo.Mail;
import aplicacion.vista.MensajeAltasBajas;

@Stateless
public class TimerEJB {

	@EJB
	UsuariosEJB usuariosEJB;

	@EJB
	MailEJB mailEJB;

	@EJB
	AltasBajasEJB altasBajasEJB;

	private final String CORREO_JEFE = "tofol.c.m@gmail.com";

	public TimerEJB() {
	}

	@SuppressWarnings("unused")
	@Schedule(second = "0", minute = "*/5", hour = "*", dayOfWeek = "*", dayOfMonth = "*", month = "*", year = "*", info = "TimerEJB")
	private void scheduledTimeout(final Timer t) {
		LogSingleton log = LogSingleton.getInstance();
		usuariosEJB.limpiar();
		try {
			ArrayList<AltaBaja> altsBajs = altasBajasEJB.getAltasBajas();
			MensajeAltasBajas mensajeAltasBajas = new MensajeAltasBajas(altsBajs);
			enviarCorreo(mensajeAltasBajas);
			altasBajasEJB.vaciar();
		} catch (SQLException e) {
			log.getLoggerTimerSingleton().error("Se ha producido un error en TimerSingleton: ", e);
		}
	}

	private boolean enviarCorreo(MensajeAltasBajas mensajeAltasBajas) {
		Mail correo = mailEJB.getMail("smtp.gmail.com", 587, "imcpractica@gmail.com", "contrasenyaimc");
		String tabla = "";
		if (mensajeAltasBajas.getTabla() != null) {
			tabla = mensajeAltasBajas.getTabla().toString();
		}
		return mailEJB.sendMail(CORREO_JEFE, mensajeAltasBajas.getMensaje(), tabla, correo);
	}

}