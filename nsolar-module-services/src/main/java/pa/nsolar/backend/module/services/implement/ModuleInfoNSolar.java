package pa.nsolar.backend.module.services.implement;

import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import pa.nsolar.backend.module.services.dto.InverterDataObject;
import pa.nsolar.backend.module.services.dto.InverterDataRequest;
import pa.nsolar.backend.module.services.dto.InverterDataResponse;
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
		Entry<String,Integer> entry = new AbstractMap.SimpleEntry<>("", 0);
		LOGGER.info("totalProduction: {}",  df.format(inverterProduction.values().stream().mapToInt(i->i).sum() / 1000.0));
		if(maxMin.equals("+")) {
			entry = Collections.max(inverterProduction.entrySet(), (Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1.getValue()
			        .compareTo(e2.getValue()));
			LOGGER.info("MaxValue: {}", entry);
		} else if(maxMin.equals("-")) {
			entry = Collections.min(inverterProduction.entrySet(), (Entry<String, Integer> e1, Entry<String, Integer> e2) -> e1.getValue()
			        .compareTo(e2.getValue()));
			LOGGER.info("MinValue: {}", entry);
		}
		
		return Double.valueOf(df.format(entry.getValue() / 1000.0));
	}
}
