package aplicacion.vista;

public class NavBar {
	private Tag logo;
	private Tag usuario = null;
	private Tag fotoPerfil = null;
	private Tag logout = null;
	private Tag darseDeBaja = null;
	private Tag login = null;
	private Tag registro = null;
	private Tag historial = null;
	private Tag modo = null;

	/***
	 * Establece la cabecera de la página, que es el logo, la barra de navegación y
	 * los datos del usuario
	 * 
	 * @param nombreUsuario
	 * @param rutaFotoPerfil
	 */
	public NavBar(String nombreUsuario, String rutaFotoPerfil) {
		if (nombreUsuario != null) {
			this.usuario = new Tag(nombreUsuario);
		}
		if (rutaFotoPerfil != null) {
			this.fotoPerfil = new Tag("img", null, false, false);
			this.fotoPerfil.prepararAtributos();
			this.fotoPerfil.addAtributo("src", rutaFotoPerfil);
			this.fotoPerfil.addAtributo("alt", "fotoDePerfil");
		}
		var lgo = new Tag("img", null, false, false);
		lgo.prepararAtributos();
		lgo.addAtributo("src", "imgs/logo.png");
		this.logo = Tag.incrustarEn(lgo, "a");
		this.logo.prepararAtributos();
		this.logo.addAtributo("href", "Principal");
	}

	public void addLogout(String modo) {
		this.logout = new Tag("a", "Logout", true, true);
		this.logout.prepararAtributos();
		this.logout.addAtributo("href", "Logout?modo=" + modo);
	}

	public void addLogin(String modo) {
		this.login = new Tag("a", "Login", true, true);
		this.login.prepararAtributos();
		this.login.addAtributo("href", "Login?modo=" + modo);
	}

	public void addRegistro(String modo) {
		this.registro = new Tag("a", "Registro", true, true);
		this.registro.prepararAtributos();
		this.registro.addAtributo("href", "Registro?modo=" + modo);
	}

	public void addDarseDeBaja(String modo) {
		this.darseDeBaja = new Tag("a", "Darse de baja", true, true);
		this.darseDeBaja.prepararAtributos();
		this.darseDeBaja.addAtributo("href", "Baja?modo=" + modo);
	}

	public void addHistorial(String modo) {
		this.historial = new Tag("a", "Historial", true, true);
		this.historial.prepararAtributos();
		this.historial.addAtributo("href", "Historial?modo=" + modo);
	}

	/***
	 * Añade la cabecera a la página
	 * 
	 * @param pagina
	 * @return Página Html con la cabecera
	 */
	@Override
	public String toString() {
		Tag navBar = new Tag("ul", null, true, true);
		navBar.prepararAtributos();
		navBar.addAtributo("id", "navegacion");
		navBar.prepararHijos();
		navBar.addChild(Tag.incrustarEn(this.logo, "li"));
		if (this.usuario != null) {
			navBar.addChild(Tag.incrustarEn(this.usuario, "li"));
			if (this.fotoPerfil != null) {
				navBar.addChild(Tag.incrustarEn(this.fotoPerfil, "li"));
			}
			if (this.logout != null) {
				navBar.addChild(Tag.incrustarEn(this.logout, "li"));
			}
			if (this.historial != null) {
				navBar.addChild(Tag.incrustarEn(this.historial, "li"));
			}
			if (this.darseDeBaja != null) {
				navBar.addChild(Tag.incrustarEn(this.darseDeBaja, "li"));
			}
		} else {
			if (this.login != null) {
				navBar.addChild(Tag.incrustarEn(this.login, "li"));
			}
			if (this.registro != null) {
				navBar.addChild(Tag.incrustarEn(this.registro, "li"));
			}
		}
		return navBar.toString();
	}
}
