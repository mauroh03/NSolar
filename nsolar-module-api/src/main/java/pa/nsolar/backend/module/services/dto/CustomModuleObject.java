package pa.nsolar.backend.module.services.dto;

public class CustomModuleObject {

	private Integer inverterId;
	private Integer x;
	private Integer y;
	private Double generatedPower;
	private String moduleColor;
	private Integer panelRotation;

	public Integer getInverterId() {
		return inverterId;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Double getGeneratedPower() {
		return generatedPower;
	}

	public String getModuleColor() {
		return moduleColor;
	}

	public Integer getPanelRotation() {
		return panelRotation;
	}

	public void setInverterId(Integer inverterId) {
		this.inverterId = inverterId;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setGeneratedPower(Double generatedPower) {
		this.generatedPower = generatedPower;
	}

	public void setModuleColor(String moduleColor) {
		this.moduleColor = moduleColor;
	}

	public void setPanelRotation(Integer panelRotation) {
		this.panelRotation = panelRotation;
	}

	@Override
	public String toString() {
		return "CustomModuleObject [inverterId=" + inverterId + ", x=" + x + ", y=" + y + ", generatedPower="
				+ generatedPower + ", moduleColor=" + moduleColor + ", panelRotation=" + panelRotation + "]";
	}
}
