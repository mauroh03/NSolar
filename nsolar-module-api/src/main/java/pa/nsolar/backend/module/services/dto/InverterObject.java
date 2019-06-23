package pa.nsolar.backend.module.services.dto;

public class InverterObject {

	private Integer inverter_id;
	private String serial_num;

	public Integer getInverter_id() {
		return inverter_id;
	}

	public String getSerial_num() {
		return serial_num;
	}

	public void setInverter_id(Integer inverter_id) {
		this.inverter_id = inverter_id;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	@Override
	public String toString() {
		return "InverterObject [inverter_id=" + inverter_id + ", serial_num=" + serial_num + "]";
	}
}
