package pa.nsolar.backend.module.services.dto;

public class BackgroundObjeect {

	private String url;
	private Integer opacity;
	private Integer rotation;
	private Integer scale;
	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;

	public String getUrl() {
		return url;
	}

	public Integer getOpacity() {
		return opacity;
	}

	public Integer getRotation() {
		return rotation;
	}

	public Integer getScale() {
		return scale;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setOpacity(Integer opacity) {
		this.opacity = opacity;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "BackgroundObjeect [url=" + url + ", opacity=" + opacity + ", rotation=" + rotation + ", scale=" + scale
				+ ", x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]";
	}
}
