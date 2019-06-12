package pa.nsolar.backend.client.api.dto;

public class GenerationObject {

	private Integer day;
	private Double generation;

	public Integer getDay() {
		return day;
	}

	public Double getGeneration() {
		return generation;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setGeneration(Double generation) {
		this.generation = generation;
	}

	@Override
	public String toString() {
		return "GenerationObject [day=" + day + ", generation=" + generation + "]";
	}
}
