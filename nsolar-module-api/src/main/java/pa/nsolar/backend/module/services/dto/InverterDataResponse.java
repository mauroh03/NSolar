package pa.nsolar.backend.module.services.dto;

public class InverterDataResponse {

	private Double maxGeneration;
	private Double minGeneration;

	public Double getMaxGeneration() {
		return maxGeneration;
	}

	public Double getMinGeneration() {
		return minGeneration;
	}

	public void setMaxGeneration(Double maxGeneration) {
		this.maxGeneration = maxGeneration;
	}

	public void setMinGeneration(Double minGeneration) {
		this.minGeneration = minGeneration;
	}

	@Override
	public String toString() {
		return "InverterDataResponse [maxGeneration=" + maxGeneration + ", minGeneration=" + minGeneration + "]";
	}
}
