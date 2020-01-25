package aplicacion.modelo.dao.mappers;

import java.util.ArrayList;
import java.util.Map;

import aplicacion.modelo.pojo.AltaBaja;

public interface AltasBajasMapper {

	public void registrarValidacion(Map<String, Object> altaBaja);

	public ArrayList<AltaBaja> getAltasBajas();

	public void vaciar();

}
