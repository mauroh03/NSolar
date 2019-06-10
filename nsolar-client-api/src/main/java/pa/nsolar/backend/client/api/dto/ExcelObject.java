package pa.nsolar.backend.client.api.dto;

public class ExcelObject {

	private String cliente;
	private Integer user_id;
	private String sistema_kW;
	private Integer paneles;
	private Integer watts_Panel;
	private String fecha_Operacion_Enphase;
	private String fecha_Medidor_Bidireccional;
	private String panel;
	private String modelo_Panel;
	private String microinversor;

	public String getCliente() {
		return cliente;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public String getSistema_kW() {
		return sistema_kW;
	}

	public Integer getPaneles() {
		return paneles;
	}

	public Integer getWatts_Panel() {
		return watts_Panel;
	}

	public String getFecha_Operacion_Enphase() {
		return fecha_Operacion_Enphase;
	}

	public String getFecha_Medidor_Bidireccional() {
		return fecha_Medidor_Bidireccional;
	}

	public String getPanel() {
		return panel;
	}

	public String getModelo_Panel() {
		return modelo_Panel;
	}

	public String getMicroinversor() {
		return microinversor;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public void setSistema_kW(String sistema_kW) {
		this.sistema_kW = sistema_kW;
	}

	public void setPaneles(Integer paneles) {
		this.paneles = paneles;
	}

	public void setWatts_Panel(Integer watts_Panel) {
		this.watts_Panel = watts_Panel;
	}

	public void setFecha_Operacion_Enphase(String fecha_Operacion_Enphase) {
		this.fecha_Operacion_Enphase = fecha_Operacion_Enphase;
	}

	public void setFecha_Medidor_Bidireccional(String fecha_Medidor_Bidireccional) {
		this.fecha_Medidor_Bidireccional = fecha_Medidor_Bidireccional;
	}

	public void setPanel(String panel) {
		this.panel = panel;
	}

	public void setModelo_Panel(String modelo_Panel) {
		this.modelo_Panel = modelo_Panel;
	}

	public void setMicroinversor(String microinversor) {
		this.microinversor = microinversor;
	}

	@Override
	public String toString() {
		return "ExcelObject [cliente=" + cliente + ", user_id=" + user_id + ", sistema_kW=" + sistema_kW + ", paneles="
				+ paneles + ", watts_Panel=" + watts_Panel + ", fecha_Operacion_Enphase=" + fecha_Operacion_Enphase
				+ ", fecha_Medidor_Bidireccional=" + fecha_Medidor_Bidireccional + ", panel=" + panel
				+ ", modelo_Panel=" + modelo_Panel + ", microinversor=" + microinversor + "]";
	}
}
