package pa.nsolar.backend.module.services.dto;

public enum EnumModuleColor {

	C1(100, 92, "rgb(76, 169, 238)"),
	C2(91, 79, "rgb(51, 155, 238)"),
	C3(78, 58, "rgb(7, 136, 228)"),
	C4(57, 30, "rgb(2, 127, 215)"),
	C5(29, 1, "rgb(6, 80, 138)"),
	C6(0, 0, "rgb(0,0,0)");
	
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
