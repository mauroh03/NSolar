package pa.nsolar.backend.module.services.dto;

public enum EnumModuleColor {

	C1(100, 93, "rgb(153, 215, 254)"),
	C2(92, 85, "rgb(134, 205, 251)"),
	C3(84, 77, "rgb(67, 171, 244)"),
	C4(76, 69, "rgb(0, 140, 241)"),
	C5(68, 61, "rgb(1, 115, 201)"),
	C6(60, 53, "rgb(0, 96, 195)"),
	C7(52, 45, "rgb(0, 66, 116)"),
	C8(44, 37, "rgb(0, 40, 68)"),
	C9(36, 1, "rgb(2, 23, 42)"),
	C10(0, 0, "rgb(0,0,0)");
	
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
