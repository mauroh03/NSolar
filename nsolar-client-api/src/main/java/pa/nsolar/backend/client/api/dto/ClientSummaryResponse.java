package pa.nsolar.backend.client.api.dto;

public class ClientSummaryResponse {

	private Integer system_id;
	private Integer modules;
	private Integer size_w;
	private Integer current_power;
	private Integer energy_today;
	private Integer energy_lifetime;
	private String summary_date;
	private String source;
	private String status;
	private Long operational_at;
	private Long last_report_at;
	private Long last_interval_end_at;

	public Integer getSystem_id() {
		return system_id;
	}

	public Integer getModules() {
		return modules;
	}

	public Integer getSize_w() {
		return size_w;
	}

	public Integer getCurrent_power() {
		return current_power;
	}

	public Integer getEnergy_today() {
		return energy_today;
	}

	public Integer getEnergy_lifetime() {
		return energy_lifetime;
	}

	public String getSummary_date() {
		return summary_date;
	}

	public String getSource() {
		return source;
	}

	public String getStatus() {
		return status;
	}

	public Long getOperational_at() {
		return operational_at;
	}

	public Long getLast_report_at() {
		return last_report_at;
	}

	public Long getLast_interval_end_at() {
		return last_interval_end_at;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}

	public void setModules(Integer modules) {
		this.modules = modules;
	}

	public void setSize_w(Integer size_w) {
		this.size_w = size_w;
	}

	public void setCurrent_power(Integer current_power) {
		this.current_power = current_power;
	}

	public void setEnergy_today(Integer energy_today) {
		this.energy_today = energy_today;
	}

	public void setEnergy_lifetime(Integer energy_lifetime) {
		this.energy_lifetime = energy_lifetime;
	}

	public void setSummary_date(String summary_date) {
		this.summary_date = summary_date;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setOperational_at(Long operational_at) {
		this.operational_at = operational_at;
	}

	public void setLast_report_at(Long last_report_at) {
		this.last_report_at = last_report_at;
	}

	public void setLast_interval_end_at(Long last_interval_end_at) {
		this.last_interval_end_at = last_interval_end_at;
	}

	@Override
	public String toString() {
		return "ClientSummaryResponse [system_id=" + system_id + ", modules=" + modules + ", size_w=" + size_w
				+ ", current_power=" + current_power + ", energy_today=" + energy_today + ", energy_lifetime="
				+ energy_lifetime + ", summary_date=" + summary_date + ", source=" + source + ", status=" + status
				+ ", operational_at=" + operational_at + ", last_report_at=" + last_report_at
				+ ", last_interval_end_at=" + last_interval_end_at + "]";
	}
}
