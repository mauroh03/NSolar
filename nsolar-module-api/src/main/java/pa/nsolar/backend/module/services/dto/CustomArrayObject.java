package pa.nsolar.backend.module.services.dto;

import java.util.List;

public class CustomArrayObject {

	private String label;
	private Integer x;
	private Integer y;
	private Integer azimuth;
	private List<CustomModuleObject> modules;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Integer getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(Integer azimuth) {
		this.azimuth = azimuth;
	}

	public List<CustomModuleObject> getModules() {
		return modules;
	}

	public void setModules(List<CustomModuleObject> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "CustomArrayObject [label=" + label + ", x=" + x + ", y=" + y + ", azimuth=" + azimuth + ", modules="
				+ modules + "]";
	}
}
