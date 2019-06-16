package pa.nsolar.backend.module.services.dto;

import java.util.List;

public class ModuleArrayResponse {

	private List<CustomArrayObject> systemArrays;
	private BackgroundObjeect background;

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

	@Override
	public String toString() {
		return "ModuleArrayResponse [systemArrays=" + systemArrays + ", background=" + background + "]";
	}
}
