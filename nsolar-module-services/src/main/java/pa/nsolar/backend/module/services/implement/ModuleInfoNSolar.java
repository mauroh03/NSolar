package pa.nsolar.backend.module.services.implement;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import pa.nsolar.backend.module.services.dto.ArrayObject;
import pa.nsolar.backend.module.services.dto.CustomArrayObject;
import pa.nsolar.backend.module.services.dto.CustomModuleObject;
import pa.nsolar.backend.module.services.dto.ENModuleArrayResponse;
import pa.nsolar.backend.module.services.dto.EnumModuleColor;
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
	
	@Value("${nsolar.module.image.extension}")
	private String imageExtension;
	
	@Value("${nsolar.module.image.filePath}")
	private String imageBasePath;

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
		ENModuleArrayResponse moduleArrayObject = new ENModuleArrayResponse();
		InverterDataObject productionObject = new InverterDataObject();
		try {
			productionObject = mapper.readValue(request.getModuleProduction(), InverterDataObject.class);
			moduleArrayObject = mapper.readValue(request.getModuleData(), ENModuleArrayResponse.class);
			List<ArrayObject> positiveArrays = this.buildPositiveArrays(moduleArrayObject.getArrays());
			moduleArrayObject.setArrays(positiveArrays);
			moduleArrayResponse.setBackground(moduleArrayObject.getBackground());
			moduleArrayResponse.setSystemArrays(this.buildCustomObject(moduleArrayObject, productionObject));
			moduleArrayResponse.setRotation(moduleArrayObject.getRotation());
			moduleArrayResponse.setGeneralView(this.getImageBase64(request.getClientId()));
		} catch (Exception e) {
			LOGGER.error("Error: {}", e.getMessage());
			e.printStackTrace();
		}

		return moduleArrayResponse;
	}

	public List<CustomArrayObject> buildCustomObject(ENModuleArrayResponse modulesData,
			InverterDataObject modulesProduction) throws Exception {
		List<CustomArrayObject> customArrays = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#0.00");
		Double maxProduction = Double.valueOf(df.format(Collections.max(modulesProduction.getProduction().entrySet(), Map.Entry.comparingByValue()).getValue() / 1000));
		for (ArrayObject arrayObject : modulesData.getArrays()) {
			CustomArrayObject customArrayObject = new CustomArrayObject();
			customArrayObject.setAzimuth(arrayObject.getAzimuth());
			customArrayObject.setLabel(arrayObject.getLabel());
			List<CustomModuleObject> modules = new ArrayList<>();
			customArrayObject.setxMax(arrayObject.getModules().stream().max(Comparator.comparing(ModulesObject::getX)).orElseThrow(NoSuchElementException::new).getX());
			customArrayObject.setyMax(arrayObject.getModules().stream().max(Comparator.comparing(ModulesObject::getY)).orElseThrow(NoSuchElementException::new).getY());
			for (ModulesObject module : arrayObject.getModules()) {
				CustomModuleObject customModuleObject = new CustomModuleObject();
				customModuleObject.setInverterId(module.getInverter().getInverter_id());
				customModuleObject.setX(module.getX());
				customModuleObject.setY(module.getY());
				customModuleObject.setGeneratedPower(this.getModuleProduction(modulesProduction, module.getInverter().getInverter_id()));
				customModuleObject.setModuleColor(this.getModuleColor(customModuleObject.getGeneratedPower(), maxProduction));
				modules.add(customModuleObject);
			}
			customArrayObject.setModules(modules);
			customArrays.add(customArrayObject);
		}

		return customArrays;
	}
	
	private String getModuleColor(Double moduleProduction, Double maxProduction) {
		DecimalFormat df = new DecimalFormat("#0");
		Integer modulePercentage = Integer.valueOf(df.format((moduleProduction / maxProduction) * 100));
		
		if(modulePercentage >= EnumModuleColor.C1.getMinPercentage()) {
			return EnumModuleColor.C1.getColor();
		} else if (modulePercentage >= EnumModuleColor.C2.getMinPercentage() && modulePercentage <= EnumModuleColor.C2.getMaxPercentage()) {
			return EnumModuleColor.C2.getColor();
		} else if (modulePercentage >= EnumModuleColor.C3.getMinPercentage() && modulePercentage <= EnumModuleColor.C3.getMaxPercentage()) {
			return EnumModuleColor.C3.getColor();
		} else if (modulePercentage >= EnumModuleColor.C4.getMinPercentage() && modulePercentage <= EnumModuleColor.C4.getMaxPercentage()) {
			return EnumModuleColor.C4.getColor();
		} else if (modulePercentage >= EnumModuleColor.C5.getMinPercentage() && modulePercentage <= EnumModuleColor.C5.getMaxPercentage()) {
			return EnumModuleColor.C5.getColor();
		} else if (modulePercentage >= EnumModuleColor.C6.getMinPercentage() && modulePercentage <= EnumModuleColor.C6.getMaxPercentage()) {
			return EnumModuleColor.C6.getColor();
		} else if (modulePercentage >= EnumModuleColor.C7.getMinPercentage() && modulePercentage <= EnumModuleColor.C7.getMaxPercentage()) {
			return EnumModuleColor.C7.getColor();
		} else if (modulePercentage >= EnumModuleColor.C8.getMinPercentage() && modulePercentage <= EnumModuleColor.C8.getMaxPercentage()) {
			return EnumModuleColor.C8.getColor();
		} else if (modulePercentage >= EnumModuleColor.C9.getMinPercentage() && modulePercentage <= EnumModuleColor.C9.getMaxPercentage()) {
			return EnumModuleColor.C9.getColor();
		} else if (modulePercentage >= EnumModuleColor.C10.getMinPercentage() && modulePercentage <= EnumModuleColor.C10.getMaxPercentage()) {
			return EnumModuleColor.C10.getColor();
		} else if (modulePercentage >= EnumModuleColor.C11.getMinPercentage() && modulePercentage <= EnumModuleColor.C11.getMaxPercentage()) {
			return EnumModuleColor.C11.getColor();
		} else if (modulePercentage >= EnumModuleColor.C12.getMinPercentage() && modulePercentage <= EnumModuleColor.C12.getMaxPercentage()) {
			return EnumModuleColor.C12.getColor();
		} else if (modulePercentage >= EnumModuleColor.C13.getMinPercentage() && modulePercentage <= EnumModuleColor.C13.getMaxPercentage()) {
			return EnumModuleColor.C13.getColor();
		} else if (modulePercentage >= EnumModuleColor.C14.getMinPercentage() && modulePercentage <= EnumModuleColor.C14.getMaxPercentage()) {
			return EnumModuleColor.C14.getColor();
		} else {
			return EnumModuleColor.C15.getColor();
		}
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
	
	private List<ArrayObject> buildPositiveArrays(List<ArrayObject> arrays) {
		List<ArrayObject> positiveArrays = new ArrayList<>();
		for (ArrayObject arrayObject : arrays) {
			ArrayObject positiveArrayObject = arrayObject;
			List<ModulesObject> positivesModules = new ArrayList<>();
			Integer minX = Math.abs(arrayObject.getModules().stream().min(Comparator.comparing(ModulesObject::getX)).orElseThrow(NoSuchElementException::new).getX());
			Integer minY =  Math.abs(arrayObject.getModules().stream().min(Comparator.comparing(ModulesObject::getY)).orElseThrow(NoSuchElementException::new).getY());
			for (ModulesObject modulesObject : arrayObject.getModules()) {
				ModulesObject positiveModule = modulesObject;
				positiveModule.setX(positiveModule.getX()+minX);
				positiveModule.setY(positiveModule.getY()+minY);
				
				positivesModules.add(positiveModule);
			}
			positiveArrayObject.setModules(positivesModules);
			positiveArrays.add(positiveArrayObject);
		}
		
		return positiveArrays;
	}
	
	@SuppressWarnings("resource")
	private String getImageBase64(String clientId) {
		String encodedfile = null;
		try {
			File imageFile =  new File(imageBasePath + clientId + imageExtension);
			FileInputStream fileInputStreamReader = new FileInputStream(imageFile);
            byte[] bytes = new byte[(int)imageFile.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
		} catch (Exception e) {
			LOGGER.error("Error: {}", e.getMessage());
			e.printStackTrace();
		}
		
		return encodedfile;
	}
}
