package pa.nsolar.backend.client.api.dto;

public class GenerationObject {

	private Integer day;
	private Integer generation;

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getGeneration() {
		return generation;
	}

	public void setGeneration(Integer generation) {
		this.generation = generation;
	}

	@Override
	public String toString() {
		return "GenerationObject [day=" + day + ", generation=" + generation + "]";
	}
}
