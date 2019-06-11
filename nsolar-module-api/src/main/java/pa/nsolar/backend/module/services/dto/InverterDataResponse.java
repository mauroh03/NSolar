package pa.nsolar.backend.module.services.dto;

public class InverterDataResponse {

	private Integer maxGeneration;
	private Integer minGeneration;

	public Integer getMaxGeneration() {
		return maxGeneration;
	}

	public Integer getMinGeneration() {
		return minGeneration;
	}

	public void setMaxGeneration(Integer maxGeneration) {
		this.maxGeneration = maxGeneration;
	}

	public void setMinGeneration(Integer minGeneration) {
		this.minGeneration = minGeneration;
	}

	@Override
	public String toString() {
		return "InverterDataResponse [maxGeneration=" + maxGeneration + ", minGeneration=" + minGeneration + "]";
	}
}
