package pa.nsolar.backend.client.api.dto;

public class GenerationMeasurement {

	private String indicator;
	private String value;

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GenerationMeasurement [indicator=" + indicator + ", value=" + value + "]";
	}
}
