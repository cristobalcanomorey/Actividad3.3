package aplicacion.modelo.dao.mappers;

import java.sql.ResultSet;
import java.util.Map;

public interface AltasBajasMapper {

	public void registrarValidacion(Map<String, Object> altaBaja);

	public ResultSet getAltasBajas();

	public void vaciar();

}
