package pa.nsolar.backend.client.services.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;
import pa.nsolar.backend.client.services.interfaces.IApiCallOperation;
import pa.nsolar.backend.client.services.interfaces.IClientNSolar;

@Service
public class ClientNSolar implements IClientNSolar {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientNSolar.class);

	@Autowired
	IApiCallOperation apiCallOperation;
	
	@Override
	public EnergyLifeTimeResponse nSolarClientList() {
		return apiCallOperation.nSolarClientList();
	}

	@Override
	public ClientSummaryResponse nSolarClientSummary(ClientSummaryRequest clientSummaryRequest) {
		return apiCallOperation.nSolarCLientSummary(clientSummaryRequest);
	}
}
