package pa.nsolar.backend.module.services.dto;

import java.util.Map;

public class InverterDataObject {

	private Map<String, Integer> production;
	private String start_date;
	private String end_date;

	public Map<String, Integer> getProduction() {
		return production;
	}

	public String getStart_date() {
		return start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setProduction(Map<String, Integer> production) {
		this.production = production;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	@Override
	public String toString() {
		return "InverterDataObject [production=" + production + ", start_date=" + start_date + ", end_date=" + end_date
				+ "]";
	}
}
