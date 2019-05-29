package pa.nsolar.backend.client.api.dto;

import java.util.List;

public class EnergyLifeTimeResponse {

	private List<SystemObject> systems;

	public List<SystemObject> getSystems() {
		return systems;
	}

	public void setSystems(List<SystemObject> systems) {
		this.systems = systems;
	}

	@Override
	public String toString() {
		return "EnergyLifeTimeResponse [systems=" + systems + "]";
	}
}
