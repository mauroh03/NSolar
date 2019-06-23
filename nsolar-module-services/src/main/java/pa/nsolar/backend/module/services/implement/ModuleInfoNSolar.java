package pa.nsolar.backend.module.services.implement;

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import pa.nsolar.backend.module.services.dto.ArrayObject;
import pa.nsolar.backend.module.services.dto.CustomArrayObject;
import pa.nsolar.backend.module.services.dto.CustomModuleObject;
import pa.nsolar.backend.module.services.dto.ENModuleArrayResponse;
import pa.nsolar.backend.module.services.dto.InverterDataObject;
import pa.nsolar.backend.module.services.dto.InverterDataRequest;
import pa.nsolar.backend.module.services.dto.InverterDataResponse;
import pa.nsolar.backend.module.services.dto.ModuleArrayRequest;
import pa.nsolar.backend.module.services.dto.ModuleArrayResponse;
import pa.nsolar.backend.module.services.dto.ModulesObject;
import pa.nsolar.backend.module.services.interfaces.IModuleInfoNSolar;

@Service
public class ModuleInfoNSolar implements IModuleInfoNSolar {

	private static final Logger LOGGER = LoggerFactory.getLogger(ModuleInfoNSolar.class);

	@Override
	public InverterDataResponse getInverterData(InverterDataRequest request) {
		InverterDataResponse resposne = new InverterDataResponse();
		try {
			ObjectMapper mapper = new ObjectMapper();
			InverterDataObject inverterObject = mapper.readValue(request.getInverterData(), InverterDataObject.class);
			resposne.setMaxGeneration(this.getValueFromInverterdata(inverterObject.getProduction(), "+"));
			resposne.setMinGeneration(this.getValueFromInverterdata(inverterObject.getProduction(), "-"));
		} catch (Exception e) {
			LOGGER.error("[NSOLAR - ERROR] - getInverterData ERROR: {}", e.getMessage());
		}

		return resposne;
	}

	private Double getValueFromInverterdata(Map<String, Integer> inverterProduction, String maxMin) {
		DecimalFormat df = new DecimalFormat("#.00");
		Entry<String, Integer> entry = new AbstractMap.SimpleEntry<>("", 0);
		LOGGER.info("totalProduction: {}",
				df.format(inverterProduction.values().stream().mapToInt(i -> i).sum() / 1000.0));
		if (maxMin.equals("+")) {
			entry = Collections.max(inverterProduction.entrySet(),
					(Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1.getValue().compareTo(e2.getValue()));
			LOGGER.info("MaxValue: {}", entry);
		} else if (maxMin.equals("-")) {
			entry = Collections.min(inverterProduction.entrySet(),
					(Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1.getValue().compareTo(e2.getValue()));
			LOGGER.info("MinValue: {}", entry);
		}

		return Double.valueOf(df.format(entry.getValue() / 1000.0));
	}

	@Override
	public ModuleArrayResponse getModuleData(ModuleArrayRequest request) {
		ModuleArrayResponse moduleArrayResponse = new ModuleArrayResponse();
		ObjectMapper mapper = new ObjectMapper();
		// ObjectMapper productionMapper = new ObjectMapper();
		ENModuleArrayResponse moduleArrayObject = new ENModuleArrayResponse();
		InverterDataObject productionObject = new InverterDataObject();
		try {
			moduleArrayObject = mapper.readValue(request.getModuleData(), ENModuleArrayResponse.class);
			productionObject = mapper.readValue(request.getModuleProduction(), InverterDataObject.class);

			moduleArrayResponse.setBackground(moduleArrayObject.getBackground());
			moduleArrayResponse.setSystemArrays(this.buildCustomObject(moduleArrayObject, productionObject));
			moduleArrayResponse.setRotation(moduleArrayObject.getRotation());
		} catch (Exception e) {
			LOGGER.error("Error: {}", e.getMessage());
			e.printStackTrace();
		}

		return moduleArrayResponse;
	}

	public List<CustomArrayObject> buildCustomObject(ENModuleArrayResponse modulesData,
			InverterDataObject modulesProduction) throws Exception {

		List<CustomArrayObject> customArrays = new ArrayList<>();
		for (ArrayObject arrayObject : modulesData.getArrays()) {
			CustomArrayObject customArrayObject = new CustomArrayObject();
			customArrayObject.setAzimuth(arrayObject.getAzimuth());
			customArrayObject.setLabel(arrayObject.getLabel());
			customArrayObject.setX(arrayObject.getX());
			customArrayObject.setY(arrayObject.getY());
			List<CustomModuleObject> modules = new ArrayList<>();
			for (ModulesObject module : arrayObject.getModules()) {
				CustomModuleObject customModuleObject = new CustomModuleObject();
				customModuleObject.setInverterId(module.getInverter().getInverter_id());
				customModuleObject.setX(module.getX());
				customModuleObject.setY(module.getY());
				customModuleObject.setGeneratedPower(
						this.getModuleProduction(modulesProduction, module.getInverter().getInverter_id()));
				modules.add(customModuleObject);
			}
			customArrayObject.setModules(modules);
			customArrays.add(customArrayObject);
		}

		return customArrays;
	}

	public Double getModuleProduction(InverterDataObject modulesProduction, Integer inverterId) {
		DecimalFormat df = new DecimalFormat("#0.00");
		Double production = 0.0;
		try {
			production = Double
					.valueOf(df.format(modulesProduction.getProduction().get(String.valueOf(inverterId)) / 1000.00));
		} catch (Exception e) {
			LOGGER.error("Fallo buscando el InverterId {}", inverterId);
		}

		return production;
	}
}
