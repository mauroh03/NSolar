package pa.nsolar.backend.module.services.dto;

public class CustomModuleObject {

	private Integer inverterId;
	private Integer x;
	private Integer y;
	private Double generatedPower;

	public Integer getInverterId() {
		return inverterId;
	}

	public void setInverterId(Integer inverterId) {
		this.inverterId = inverterId;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public Double getGeneratedPower() {
		return generatedPower;
	}

	public void setGeneratedPower(Double generatedPower) {
		this.generatedPower = generatedPower;
	}

	@Override
	public String toString() {
		return "CustomModuleObject [inverterId=" + inverterId + ", x=" + x + ", y=" + y + ", generatedPower="
				+ generatedPower + "]";
	}
}
