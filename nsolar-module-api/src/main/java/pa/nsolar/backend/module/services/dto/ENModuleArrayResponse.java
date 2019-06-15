package pa.nsolar.backend.module.services.dto;

import java.util.List;

public class ENModuleArrayResponse {

	private Integer system_id;
	private Integer rotation;
	private DimensionObject dimensions;
	private List<ArrayObject> arrays;
	private BackgroundObjeect background;
	private String haiku;

	public Integer getSystem_id() {
		return system_id;
	}

	public Integer getRotation() {
		return rotation;
	}

	public DimensionObject getDimensions() {
		return dimensions;
	}

	public List<ArrayObject> getArrays() {
		return arrays;
	}

	public BackgroundObjeect getBackground() {
		return background;
	}

	public String getHaiku() {
		return haiku;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public void setDimensions(DimensionObject dimensions) {
		this.dimensions = dimensions;
	}

	public void setArrays(List<ArrayObject> arrays) {
		this.arrays = arrays;
	}

	public void setBackground(BackgroundObjeect background) {
		this.background = background;
	}

	public void setHaiku(String haiku) {
		this.haiku = haiku;
	}

	@Override
	public String toString() {
		return "ENModuleArrayResponse [system_id=" + system_id + ", rotation=" + rotation + ", dimensions=" + dimensions
				+ ", arrays=" + arrays + ", background=" + background + ", haiku=" + haiku + "]";
	}
}
