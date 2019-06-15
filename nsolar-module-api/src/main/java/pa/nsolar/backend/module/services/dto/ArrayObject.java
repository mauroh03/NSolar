package pa.nsolar.backend.module.services.dto;

import java.util.List;

public class ArrayObject {

	private Integer array_id;
	private String label;
	private Integer x;
	private Integer y;
	private Integer azimuth;
	private List<ModulesObject> modules;
	private DimensionObject dimensions;

	public Integer getArray_id() {
		return array_id;
	}

	public String getLabel() {
		return label;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getAzimuth() {
		return azimuth;
	}

	public List<ModulesObject> getModules() {
		return modules;
	}

	public DimensionObject getDimensions() {
		return dimensions;
	}

	public void setArray_id(Integer array_id) {
		this.array_id = array_id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setAzimuth(Integer azimuth) {
		this.azimuth = azimuth;
	}

	public void setModules(List<ModulesObject> modules) {
		this.modules = modules;
	}

	public void setDimensions(DimensionObject dimensions) {
		this.dimensions = dimensions;
	}

	@Override
	public String toString() {
		return "ArrayObject [array_id=" + array_id + ", label=" + label + ", x=" + x + ", y=" + y + ", azimuth="
				+ azimuth + ", modules=" + modules + ", dimensions=" + dimensions + "]";
	}
}
