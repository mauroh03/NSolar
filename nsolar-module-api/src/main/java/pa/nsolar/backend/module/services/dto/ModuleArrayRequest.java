package pa.nsolar.backend.module.services.dto;

public class ModuleArrayRequest {

	private String moduleData;

	public String getModuleData() {
		return moduleData;
	}

	public void setModuleData(String moduleData) {
		this.moduleData = moduleData;
	}

	@Override
	public String toString() {
		return "ModuleArrayRequest [moduleData=" + moduleData + "]";
	}
}
