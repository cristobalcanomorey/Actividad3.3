package aplicacion.vista;

import aplicacion.modelo.ejb.CalculosEJB;
import aplicacion.modelo.pojo.Calculo;
import aplicacion.modelo.pojo.Usuario;

public class ObtenerTabla {

	public static Tabla crearTablaHistorial(Usuario u) {
		Tabla tabla = null;
		if (u.getCalculos() != null) {
			tabla = new Tabla();
			tabla.addAFila(new Tag("Estatura"), "th");
			tabla.addAFila(new Tag("Peso"), "th");
			tabla.addAFila(new Tag("Fecha"), "th");
			tabla.addAFila(new Tag("IMC"), "th");
			tabla.addFilaAEncabezado();
			tabla.resetFila();
			tabla.prepararCuerpo();
			for (Calculo c : u.getCalculos()) {
				tabla.addAFila(new Tag(c.getEstatura().toString()), "td");
				tabla.addAFila(new Tag(c.getPeso().toString()), "td");
				tabla.addAFila(new Tag(CalculosEJB.fechaAString(c.getFecha())), "td");
				tabla.addAFila(new Tag(String.format("%.2f", c.getImc())), "td");
				tabla.addFilaACuerpo();
				tabla.resetFila();
			}
		}

		return tabla;
	}

}
