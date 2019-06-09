package pa.nsolar.backend.client.api.dto;

public class EnergyLifeTimeDatedObject {

	private String startDate;
	private String endDate;
	private String production;
	private String clientId;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "EnergyLifeTimeDatedObject [startDate=" + startDate + ", endDate=" + endDate + ", production="
				+ production + ", clientId=" + clientId + "]";
	}
}
