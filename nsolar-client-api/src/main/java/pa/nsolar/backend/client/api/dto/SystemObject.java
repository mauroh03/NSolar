package pa.nsolar.backend.client.api.dto;

public class SystemObject {

	private Integer system_id;
	private String system_name;
	private String system_public_name;
	private String status;
	private String timezone;
	private String country;
	private String state;
	private String city;
	private String postal_code;
	private String connection_type;
	private MetaObject meta;	

	public Integer getSystem_id() {
		return system_id;
	}

	public String getSystem_name() {
		return system_name;
	}

	public String getSystem_public_name() {
		return system_public_name;
	}

	public String getStatus() {
		return status;
	}

	public String getTimezone() {
		return timezone;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public String getConnection_type() {
		return connection_type;
	}

	public MetaObject getMeta() {
		return meta;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}

	public void setSystem_name(String system_name) {
		this.system_name = system_name;
	}

	public void setSystem_public_name(String system_public_name) {
		this.system_public_name = system_public_name;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public void setConnection_type(String connection_type) {
		this.connection_type = connection_type;
	}

	public void setMeta(MetaObject meta) {
		this.meta = meta;
	}

	@Override
	public String toString() {
		return "SystemObject [system_id=" + system_id + ", system_name=" + system_name + ", system_public_name="
				+ system_public_name + ", status=" + status + ", timezone=" + timezone + ", country=" + country
				+ ", state=" + state + ", city=" + city + ", postal_code=" + postal_code + ", connection_type="
				+ connection_type + ", meta=" + meta + "]";
	}
}
