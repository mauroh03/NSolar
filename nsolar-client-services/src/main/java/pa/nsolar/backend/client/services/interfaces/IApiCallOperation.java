package pa.nsolar.backend.client.services.interfaces;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedObject;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;

public interface IApiCallOperation {

	EnergyLifeTimeResponse nSolarClientList();
	ClientSummaryResponse nSolarCLientSummary(ClientSummaryRequest clientSummaryRequest);
	EnergyLifeTimeDatedResponse nSolarEnergyLifeTime(EnergyLifeTimeDatedObject energyLifeTimeDatedObject);
}
