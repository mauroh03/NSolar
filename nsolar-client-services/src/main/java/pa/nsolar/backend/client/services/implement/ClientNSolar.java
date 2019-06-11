package pa.nsolar.backend.client.services.implement;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.DateObject;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedObject;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedRequest;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;
import pa.nsolar.backend.client.api.dto.ExcelObject;
import pa.nsolar.backend.client.api.dto.GenerationMeasurement;
import pa.nsolar.backend.client.api.dto.GenerationObject;
import pa.nsolar.backend.client.services.interfaces.IApiCallOperation;
import pa.nsolar.backend.client.services.interfaces.IClientNSolar;

@Service
public class ClientNSolar implements IClientNSolar {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientNSolar.class);
	
	@Autowired
	IApiCallOperation apiCallOperation;

	
	@Value("${nsolar.general.filePath}")
	private String filePath;
	
	@Override
	public EnergyLifeTimeResponse nSolarClientList() {
		return apiCallOperation.nSolarClientList();
	}

	@Override
	public ClientSummaryResponse nSolarClientSummary(ClientSummaryRequest clientSummaryRequest) {
		return apiCallOperation.nSolarCLientSummary(clientSummaryRequest);
	}

	@Override
	public EnergyLifeTimeDatedResponse nSolarLifeTimeEnergy(EnergyLifeTimeDatedRequest energyLifeTimeDatedRequest) {
		EnergyLifeTimeDatedObject energyLifeTimeDatedObject = new EnergyLifeTimeDatedObject();
		EnergyLifeTimeDatedResponse response = new EnergyLifeTimeDatedResponse();
		ExcelObject clientExcelObject = this.getClientJSONObject(energyLifeTimeDatedRequest.getClientId());
		DateObject dateObject = this.getDates(energyLifeTimeDatedRequest.getDate(), 1);
		energyLifeTimeDatedObject.setProduction("all");
		energyLifeTimeDatedObject.setClientId(String.valueOf(energyLifeTimeDatedRequest.getClientId()));
		energyLifeTimeDatedObject.setStartDate(dateObject.getStartDate());
		energyLifeTimeDatedObject.setEndDate(dateObject.getEndDate());
		response = apiCallOperation.nSolarEnergyLifeTime(energyLifeTimeDatedObject);
		response.setMayorGeneration(this.getGenerationDate(response, "+"));
		response.setMinorGeneration(this.getGenerationDate(response, "-"));
		response.setTotalGeneracion(response.getProduction().stream().mapToInt(Integer::intValue).sum());
		response.setGenerationComparizon(this.getComparisson(energyLifeTimeDatedRequest, response));
		response.setMetrictsTons(this.getMetricsTons(response));
		response.setHouses(this.getHouseCount(response));
		response.setTrees(this.getTreesCount(response));
		response.setPanel(String.valueOf(clientExcelObject.getPaneles()));
		response.setPanelModel(clientExcelObject.getModelo_Panel());
		response.setPanelWatts(clientExcelObject.getWatts_Panel());
		
		return response;
	}
	
	

	private ExcelObject getClientJSONObject(Integer clientId) {
		ObjectMapper mapper = new ObjectMapper();
		List<ExcelObject> excelClientList = new ArrayList<>();
		ExcelObject userObject = new ExcelObject();
		try {
			excelClientList = mapper.readValue(
					new File(filePath),
					mapper.getTypeFactory().constructCollectionType(List.class, ExcelObject.class));
			
			for (ExcelObject excelObject : excelClientList) {
				if(excelObject.getUser_id().equals(clientId)) {
					LOGGER.info("clientExcelObject: {}", excelObject);
					return excelObject;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.error("Client not found on excel file: {}");
		
		return userObject;
	}

	private DateObject getDates(String commingDate, Integer moreLess) {
		DateObject dateObject = new DateObject();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Long givenDate = Long.valueOf(commingDate);
		Date date = new Date(givenDate * 1000);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, moreLess);
		int res = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String dateFormated = format.format(cal.getTime()) + "-";
		String startDate = dateFormated + "01";
		String endDate = dateFormated + res;

		dateObject.setStartDate(startDate);
		dateObject.setEndDate(endDate);

		return dateObject;
	}

	private GenerationObject getGenerationDate(EnergyLifeTimeDatedResponse data, String signal) {
		GenerationObject genObject = new GenerationObject();
		Integer value = data.getProduction().get(0);
		Integer day = 0;

		if (signal.equals("+")) {
			for (int i : data.getProduction()) {
				value = value > i ? value : i;
			}
			day = data.getProduction().indexOf(value) + 1;
		} else if (signal.equals("-")) {
			for (int i : data.getProduction()) {
				value = value < i ? value : i;
			}
			day = data.getProduction().indexOf(value) + 1;
		}

		genObject.setDay(day);
		genObject.setGeneration(value);

		return genObject;
	}

	private Integer getTreesCount(EnergyLifeTimeDatedResponse response) {
		Double result = (response.getMetrictsTons() / 0.060);
		LOGGER.info("Trees = {}/0.060 = {}", response.getMetrictsTons(), Integer.valueOf(result.intValue()));
		return Integer.valueOf(result.intValue());
	}

	private Integer getHouseCount(EnergyLifeTimeDatedResponse response) {
		Double result = (response.getMetrictsTons() / 5734);
		LOGGER.info("Houses = {}/5734 = {}", response.getMetrictsTons(), Integer.valueOf(result.intValue()));
		return Integer.valueOf(result.intValue());
	}

	private Double getMetricsTons(EnergyLifeTimeDatedResponse response) {
		DecimalFormat df = new DecimalFormat("#.00");
		LOGGER.info("MetrictsTons = (7.07*10^-4)*{} = {}",
				response.getProduction().stream().mapToInt(Integer::intValue).sum(),
				Double.valueOf(df.format((7.07 * Math.pow(10, -4))
						* response.getProduction().stream().mapToInt(Integer::intValue).sum())));
		return Double.valueOf(df.format(
				(7.07 * Math.pow(10, -4)) * response.getProduction().stream().mapToInt(Integer::intValue).sum()));
	}

	private GenerationMeasurement getComparisson(EnergyLifeTimeDatedRequest request,
			EnergyLifeTimeDatedResponse actualData) {
		GenerationMeasurement response = new GenerationMeasurement();
		DateObject dateObject = this.getDates(request.getDate(), -10);
		EnergyLifeTimeDatedObject energyLifeTimeDatedObject = new EnergyLifeTimeDatedObject();
		energyLifeTimeDatedObject.setProduction("all");
		energyLifeTimeDatedObject.setClientId(String.valueOf(request.getClientId()));
		energyLifeTimeDatedObject.setStartDate(dateObject.getStartDate());
		energyLifeTimeDatedObject.setEndDate(dateObject.getEndDate());
		EnergyLifeTimeDatedResponse previousData = apiCallOperation.nSolarEnergyLifeTime(energyLifeTimeDatedObject);
		Integer actualProduction = actualData.getProduction().stream().mapToInt(Integer::intValue).sum();
		Integer previousProduction = previousData.getProduction().stream().mapToInt(Integer::intValue).sum();
		response.setIndicator((actualProduction - previousProduction) > 0 ? "Aumentó" : "Disminuyó");
		response.setValue(Double.valueOf(Math.abs((actualProduction - previousProduction) / 100)));

		LOGGER.info("Comparisson: {} percentage: |{}|", response.getIndicator(),
				(actualProduction - previousProduction) / 100);

		return response;
	}
}
