package pa.nsolar.backend.module.services.dto;

public class InverterDataRequest {

	private String inverterData;

	public String getInverterData() {
		return inverterData;
	}

	public void setInverterData(String inverterData) {
		this.inverterData = inverterData;
	}

	@Override
	public String toString() {
		return "InverterDataRequest [inverterData=" + inverterData + "]";
	}
}
