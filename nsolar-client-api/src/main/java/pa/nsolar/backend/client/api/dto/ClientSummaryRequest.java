package pa.nsolar.backend.client.api.dto;

public class ClientSummaryRequest {

	private Integer clientId;
	private String monthTimestamp;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getMonthTimestamp() {
		return monthTimestamp;
	}

	public void setMonthTimestamp(String monthTimestamp) {
		this.monthTimestamp = monthTimestamp;
	}

	@Override
	public String toString() {
		return "ClientSummaryRequest [clientId=" + clientId + ", monthTimestamp=" + monthTimestamp + "]";
	}
}
