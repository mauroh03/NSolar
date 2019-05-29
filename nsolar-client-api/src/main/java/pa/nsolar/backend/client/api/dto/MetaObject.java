package pa.nsolar.backend.client.api.dto;

public class MetaObject {

	private String status;
	private Long last_report_at;
	private Long last_energy_at;
	private Long operational_at;

	public String getStatus() {
		return status;
	}

	public Long getLast_report_at() {
		return last_report_at;
	}

	public Long getLast_energy_at() {
		return last_energy_at;
	}

	public Long getOperational_at() {
		return operational_at;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setLast_report_at(Long last_report_at) {
		this.last_report_at = last_report_at;
	}

	public void setLast_energy_at(Long last_energy_at) {
		this.last_energy_at = last_energy_at;
	}

	public void setOperational_at(Long operational_at) {
		this.operational_at = operational_at;
	}

	@Override
	public String toString() {
		return "MetaObject [status=" + status + ", last_report_at=" + last_report_at + ", last_energy_at="
				+ last_energy_at + ", operational_at=" + operational_at + "]";
	}
}
