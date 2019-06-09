package pa.nsolar.backend.client.services.interfaces;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedRequest;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;

public interface IClientNSolar {

	EnergyLifeTimeResponse nSolarClientList();
	ClientSummaryResponse nSolarClientSummary(ClientSummaryRequest clientSummaryRequest);
	EnergyLifeTimeDatedResponse nSolarLifeTimeEnergy(EnergyLifeTimeDatedRequest energyLifeTimeDatedRequest);
}
