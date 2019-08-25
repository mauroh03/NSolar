package pa.nsolar.backend.module.services.dto;

public class ModuleArrayRequest {

	private String moduleData;
	private String moduleProduction;
	private String clientId;

	public String getModuleData() {
		return moduleData;
	}

	public void setModuleData(String moduleData) {
		this.moduleData = moduleData;
	}

	public String getModuleProduction() {
		return moduleProduction;
	}

	public void setModuleProduction(String moduleProduction) {
		this.moduleProduction = moduleProduction;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "ModuleArrayRequest [moduleData=" + moduleData + ", moduleProduction=" + moduleProduction + ", clientId="
				+ clientId + "]";
	}
}
