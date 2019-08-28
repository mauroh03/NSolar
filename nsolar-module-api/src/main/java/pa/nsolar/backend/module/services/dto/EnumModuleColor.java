package pa.nsolar.backend.module.services.dto;

public enum EnumModuleColor {

	C1(100, 86, "rgb(58,168,243)"),
	C2(85, 71, "rgb(127,160,198)"),
	C3(70, 56, "rgb(22,139,228)"),
	C4(55, 31, "rgb(19,101,162)"),
	C5(30, 16, "rgb(20,84,128)"),
	C6(15, 1, "rgb(17,45,62)"),
	C7(0, 0, "rgb(0,0,0)");
	
	private final Integer maxPercentage;
	private final Integer minPercentage;
	private final String color;
	
	private EnumModuleColor(Integer maxPercentage, Integer minPercentage, String color) {
		this.maxPercentage = maxPercentage;
		this.minPercentage = minPercentage;
		this.color = color;
	}

	public Integer getMaxPercentage() {
		return maxPercentage;
	}

	public Integer getMinPercentage() {
		return minPercentage;
	}

	public String getColor() {
		return color;
	}
}
