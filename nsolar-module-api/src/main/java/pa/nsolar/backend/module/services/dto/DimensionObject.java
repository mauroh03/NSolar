package pa.nsolar.backend.module.services.dto;

public class DimensionObject {

	private Integer x_min;
	private Integer x_max;
	private Integer y_min;
	private Integer y_max;

	public Integer getX_min() {
		return x_min;
	}

	public Integer getX_max() {
		return x_max;
	}

	public Integer getY_min() {
		return y_min;
	}

	public Integer getY_max() {
		return y_max;
	}

	public void setX_min(Integer x_min) {
		this.x_min = x_min;
	}

	public void setX_max(Integer x_max) {
		this.x_max = x_max;
	}

	public void setY_min(Integer y_min) {
		this.y_min = y_min;
	}

	public void setY_max(Integer y_max) {
		this.y_max = y_max;
	}

	@Override
	public String toString() {
		return "DimensionObject [x_min=" + x_min + ", x_max=" + x_max + ", y_min=" + y_min + ", y_max=" + y_max + "]";
	}
}
