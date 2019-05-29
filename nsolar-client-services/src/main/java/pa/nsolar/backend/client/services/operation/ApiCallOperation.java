package pa.nsolar.backend.client.services.operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;
import pa.nsolar.backend.client.services.controller.ConsultClientController;
import pa.nsolar.backend.client.services.interfaces.IApiCallOperation;


@Service
public class ApiCallOperation implements IApiCallOperation {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultClientController.class);
	
	@Value("${nsolar.uri.energyLifetime}")
    private String energyLifetimeUri;
	
	@Value("${nsolar.uri.clientSummary}")
    private String clientSummary;
	
	@Value("${nsolar.key}")
    private String nSolarKey;
	
	@Value("${nsolar.user_id}")
    private String nSolarUserID;
	
	@Override
	public EnergyLifeTimeResponse nSolarClientList() {
		RestTemplate restTemplate = new RestTemplate();
		EnergyLifeTimeResponse response = new EnergyLifeTimeResponse();
		try {
			response = restTemplate.getForObject(energyLifetimeUri + "key="+ nSolarKey + "&user_id=" + nSolarUserID, EnergyLifeTimeResponse.class);
			LOGGER.info("[NSOLAR - TRACE] - consultClientList RESPONSE: {}", response);
		} catch (Exception e) {
			LOGGER.info("[NSOLAR - ERROR] - consultClientList ERROR: {}", e.getMessage());
		}
		
		return response;
	}

	@Override
	public ClientSummaryResponse nSolarCLientSummary(ClientSummaryRequest clientSummaryRequest) {
		LOGGER.info("[NSOLAR - TRACE] - consultClientSummary REQUEST: {}", clientSummaryRequest);
		RestTemplate restTemplate = new RestTemplate();
		ClientSummaryResponse response = new ClientSummaryResponse();
		try {
			clientSummary = clientSummary.replace("$clientId", String.valueOf(clientSummaryRequest.getClientId()));
			response = restTemplate.getForObject(clientSummary + "key="+ nSolarKey + "&user_id=" + nSolarUserID, ClientSummaryResponse.class);
			LOGGER.info("[NSOLAR - TRACE] - consultClientSummary RESPONSE: {}", response);
		} catch (Exception e) {
			LOGGER.info("[NSOLAR - ERROR] - consultClientSummary ERROR: {}", e.getMessage());
		}
		
		return response;
	}

}
