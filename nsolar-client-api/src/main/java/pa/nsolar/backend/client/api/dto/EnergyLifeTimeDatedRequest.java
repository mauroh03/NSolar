package pa.nsolar.backend.client.api.dto;

public class EnergyLifeTimeDatedRequest {

	private Integer clientId;
	private String date;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "EnergyLifeTimeDatedRequest [clientId=" + clientId + ", date=" + date + "]";
	}
}
