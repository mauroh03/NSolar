package pa.nsolar.backend.module.services.dto;

public class ModulesObject {

	private Integer module_id;
	private Integer rotation;
	private Integer x;
	private Integer y;
	private InverterObject inverter;

	public Integer getModule_id() {
		return module_id;
	}

	public Integer getRotation() {
		return rotation;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public InverterObject getInverter() {
		return inverter;
	}

	public void setModule_id(Integer module_id) {
		this.module_id = module_id;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setInverter(InverterObject inverter) {
		this.inverter = inverter;
	}

	@Override
	public String toString() {
		return "ModulesObject [module_id=" + module_id + ", rotation=" + rotation + ", x=" + x + ", y=" + y
				+ ", inverter=" + inverter + "]";
	}
}
