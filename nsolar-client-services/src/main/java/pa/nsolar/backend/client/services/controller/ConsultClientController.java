package pa.nsolar.backend.client.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedRequest;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;
import pa.nsolar.backend.client.services.interfaces.IClientNSolar;

@RefreshScope
@RestController
@RequestMapping("${nsolar.base.endpoint}")
public class ConsultClientController {

	@Autowired
	IClientNSolar nSolarClient;

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsultClientController.class);
	
	@CrossOrigin(origins = "*")
	@GetMapping("/consultClientList")
	public ResponseEntity<EnergyLifeTimeResponse> consultClientListMethodGet() {
		LOGGER.info("[NSOLAR - TRACE] - ENTRY SERVICE consultClientList");
		return new ResponseEntity<>(nSolarClient.nSolarClientList(), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/consultEnergyLifeTime")
	public ResponseEntity<EnergyLifeTimeDatedResponse> consultEnergyLifeTimeMethodPost(
			@RequestBody EnergyLifeTimeDatedRequest energyLifeTimeDatedRequest) {
		LOGGER.info("[NSOLAR - TRACE] - ENTRY SERVICE consultEnergyLifeTime");
		return new ResponseEntity<>(nSolarClient.nSolarLifeTimeEnergy(energyLifeTimeDatedRequest), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/consultClientSummary")
	public ResponseEntity<ClientSummaryResponse> consultClientSummaryMethodPost(
			@RequestBody ClientSummaryRequest clientSummaryRequest) {
		LOGGER.info("[NSOLAR - TRACE] - ENTRY SERVICE consultClientSummary");
		return new ResponseEntity<>(nSolarClient.nSolarClientSummary(clientSummaryRequest), HttpStatus.OK);
	}
}
