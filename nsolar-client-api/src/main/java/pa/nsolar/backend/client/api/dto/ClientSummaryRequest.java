package pa.nsolar.backend.client.api.dto;

public class ClientSummaryRequest {

	private Integer clientId;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@Override
	public String toString() {
		return "ClientSummaryRequest [clientId=" + clientId + "]";
	}
}
