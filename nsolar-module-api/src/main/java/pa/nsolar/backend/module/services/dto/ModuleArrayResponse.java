package pa.nsolar.backend.module.services.dto;

import java.util.List;

public class ModuleArrayResponse {

	private List<CustomArrayObject> systemArrays;
	private BackgroundObjeect background;
	private Integer rotation;
	private String generalView;

	public List<CustomArrayObject> getSystemArrays() {
		return systemArrays;
	}

	public void setSystemArrays(List<CustomArrayObject> systemArrays) {
		this.systemArrays = systemArrays;
	}

	public BackgroundObjeect getBackground() {
		return background;
	}

	public void setBackground(BackgroundObjeect background) {
		this.background = background;
	}

	public Integer getRotation() {
		return rotation;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public String getGeneralView() {
		return generalView;
	}

	public void setGeneralView(String generalView) {
		this.generalView = generalView;
	}

	@Override
	public String toString() {
		return "ModuleArrayResponse [systemArrays=" + systemArrays + ", background=" + background + ", rotation="
				+ rotation + ", generalView=" + generalView + "]";
	}
}
