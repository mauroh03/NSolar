package pa.nsolar.backend.module.services.dto;

public enum EnumModuleColor {

	C1(100, 94, "rgb(224,242,246)"),
	C2(93, 87, "rgb(194,229,237)"),
	C3(86, 80, "rgb(164,216,229)"),
	C4(79, 73, "rgb(134,204,220)"),
	C5(72, 66, "rgb(103,191,211)"),
	C6(65, 59, "rgb(73,178,203)"),
	C7(58, 52, "rgb(43,165,194)"),
	C8(51, 45, "rgb(13,153,186)"),
	C9(44, 38, "rgb(9,114,139)"),
	C10(37, 31, "rgb(8,95,116)"),
	C11(30, 24, "rgb(26,55,71)"),
	C12(23, 17, "rgb(6,76,93)"),
	C13(16, 10, "rgb(4,57,69)"),
	C14(9, 11, "rgb(3,38,46)"),
	C15(0, 0, "rgb(0,0,0)");
	
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
