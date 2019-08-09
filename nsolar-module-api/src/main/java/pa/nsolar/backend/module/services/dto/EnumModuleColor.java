package pa.nsolar.backend.module.services.dto;

public enum EnumModuleColor {

	C1(100, 76, "rgb(0,170,228,0.4)"),
	C2(75, 51, "rgb(25,179,186,0.4)"),
	C3(50, 50, "rgb(31,110,146,0.4)"),
	C4(49, 26, "rgb(31,82,107, 0.4)"),
	C5(1, 25, "rgb(26,55,71,0.4)"),
	C6(0, 0, "rgb(0,0,0,0.4)");
	
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
