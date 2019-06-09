package pa.nsolar.backend.client.api.dto;

public class GenerationMeasurement {

	private String indicator;
	private Double value;

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "GenerationMeasurement [indicator=" + indicator + ", value=" + value + "]";
	}
}
