package pa.nsolar.backend.module.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pa.nsolar.backend.module.services.dto.InverterDataRequest;
import pa.nsolar.backend.module.services.dto.InverterDataResponse;
import pa.nsolar.backend.module.services.dto.ModuleArrayRequest;
import pa.nsolar.backend.module.services.dto.ModuleArrayResponse;
import pa.nsolar.backend.module.services.interfaces.IModuleInfoNSolar;

@RefreshScope
@RestController
@RequestMapping("${nsolar.base.endpoint}")
public class ModuleInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleInfoController.class);
	
	@Autowired
	IModuleInfoNSolar moduleStuff;
	
	@CrossOrigin(origins = "*")
	@PostMapping("/getInverterData")
	public ResponseEntity<InverterDataResponse> getInverterDataMethodPost(
			@RequestBody InverterDataRequest getInverterData) {
		LOGGER.info("[NSOLAR - TRACE] - ENTRY SERVICE getInverterData");
		return new ResponseEntity<>(moduleStuff.getInverterData(getInverterData), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/getModuoleData")
	public ResponseEntity<ModuleArrayResponse> getModuleDataMethodPost(
			@RequestBody ModuleArrayRequest moduleArrayRequest) {
		LOGGER.info("[NSOLAR - TRACE] - ENTRY SERVICE getModuleData");
		return new ResponseEntity<>(moduleStuff.getModuleData(moduleArrayRequest), HttpStatus.OK);
	}
}
