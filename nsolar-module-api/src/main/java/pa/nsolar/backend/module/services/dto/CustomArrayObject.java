package pa.nsolar.backend.module.services.dto;

import java.util.List;

public class CustomArrayObject {

	private String label;
	private Integer xMax;
	private Integer yMax;
	private Integer azimuth;
	private List<CustomModuleObject> modules;

	public String getLabel() {
		return label;
	}

	public Integer getxMax() {
		return xMax;
	}

	public Integer getyMax() {
		return yMax;
	}

	public Integer getAzimuth() {
		return azimuth;
	}

	public List<CustomModuleObject> getModules() {
		return modules;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setxMax(Integer xMax) {
		this.xMax = xMax;
	}

	public void setyMax(Integer yMax) {
		this.yMax = yMax;
	}

	public void setAzimuth(Integer azimuth) {
		this.azimuth = azimuth;
	}

	public void setModules(List<CustomModuleObject> modules) {
		this.modules = modules;
	}

	@Override
	public String toString() {
		return "CustomArrayObject [label=" + label + ", xMax=" + xMax + ", yMax=" + yMax + ", azimuth=" + azimuth
				+ ", modules=" + modules + "]";
	}
}
