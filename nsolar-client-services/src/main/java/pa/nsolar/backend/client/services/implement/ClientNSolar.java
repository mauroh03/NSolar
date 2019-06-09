package pa.nsolar.backend.client.services.implement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pa.nsolar.backend.client.api.dto.ClientSummaryRequest;
import pa.nsolar.backend.client.api.dto.ClientSummaryResponse;
import pa.nsolar.backend.client.api.dto.DateObject;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedObject;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedRequest;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeDatedResponse;
import pa.nsolar.backend.client.api.dto.EnergyLifeTimeResponse;
import pa.nsolar.backend.client.api.dto.GenerationMeasurement;
import pa.nsolar.backend.client.api.dto.GenerationObject;
import pa.nsolar.backend.client.services.interfaces.IApiCallOperation;
import pa.nsolar.backend.client.services.interfaces.IClientNSolar;

@Service
public class ClientNSolar implements IClientNSolar {
	
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

	@Override
	public EnergyLifeTimeDatedResponse nSolarLifeTimeEnergy(EnergyLifeTimeDatedRequest energyLifeTimeDatedRequest) {
		EnergyLifeTimeDatedObject energyLifeTimeDatedObject = new EnergyLifeTimeDatedObject();
		EnergyLifeTimeDatedResponse response = new EnergyLifeTimeDatedResponse();
		DateObject dateObject = new DateObject();
		
		dateObject = this.getDates(energyLifeTimeDatedRequest.getDate(), 1);
		energyLifeTimeDatedObject.setProduction("all");
		energyLifeTimeDatedObject.setClientId(String.valueOf(energyLifeTimeDatedRequest.getClientId()));
		energyLifeTimeDatedObject.setStartDate(dateObject.getStartDate());
		energyLifeTimeDatedObject.setEndDate(dateObject.getEndDate());
		response = apiCallOperation.nSolarEnergyLifeTime(energyLifeTimeDatedObject);
		response.setMayorGeneration(this.getGenerationDate(response, "+"));
		response.setMinorGeneration(this.getGenerationDate(response, "-"));
		response.setTotalGeneracion(response.getProduction().stream().mapToInt(Integer::intValue).sum());
		response.setGenerationComparizon(this.getComparisson(energyLifeTimeDatedRequest, response));
		/*response.setMetrictsTons(metrictsTons);
		response.setHouses(houses);
		response.setTrees(trees);*/
		
		return response;
	}
	
	private DateObject getDates(String commingDate, Integer moreLess) {
		DateObject dateObject = new DateObject();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Long givenDate = Long.valueOf(commingDate);
		Date date = new Date(givenDate*1000);
		Calendar cal = Calendar.getInstance();
		cal. setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, moreLess);
		int res = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String dateFormated = format.format(cal.getTime())+"-";
		String startDate = dateFormated + "01";
		String endDate = dateFormated + res;
		
		dateObject.setStartDate(startDate);
		dateObject.setEndDate(endDate);
		
		return dateObject;
	}
	
	private GenerationObject getGenerationDate(EnergyLifeTimeDatedResponse data, String signal) {
		GenerationObject genObject = new GenerationObject();
		Integer value = 0;
		Integer day = 0;
		
		switch (signal) {
		case "+":
			for (int i : data.getProduction()){
				value = value > i ? value : i;
	        }
			day = data.getProduction().indexOf(value);
			break;
		case "-":
			for (int i : data.getProduction()){
				value = value < i ? value : i;
	        }
			day = data.getProduction().indexOf(value);
			break;
		default:
			break;
		}
		
		genObject.setDay(day);
		genObject.setGeneration(value);
		
		return genObject;
	}
	
	private GenerationMeasurement getComparisson(EnergyLifeTimeDatedRequest request, EnergyLifeTimeDatedResponse actualData) {
		GenerationMeasurement response = new GenerationMeasurement();
		DateObject dateObject = this.getDates(request.getDate(), -1);
		EnergyLifeTimeDatedObject energyLifeTimeDatedObject = new EnergyLifeTimeDatedObject();
		energyLifeTimeDatedObject .setProduction("all");
		energyLifeTimeDatedObject.setClientId(String.valueOf(request.getClientId()));
		energyLifeTimeDatedObject.setStartDate(dateObject.getStartDate());
		energyLifeTimeDatedObject.setEndDate(dateObject.getEndDate());
		EnergyLifeTimeDatedResponse previousData = apiCallOperation.nSolarEnergyLifeTime(energyLifeTimeDatedObject);
		response.setIndicator(
				(actualData.getProduction().stream().mapToInt(Integer::intValue).sum() 
				- previousData.getProduction().stream().mapToInt(Integer::intValue).sum()) > 0 ?
				"Aumentó" : "Disminuyó");
		response.setValue(Double.valueOf(Math.abs(actualData.getProduction().stream().mapToInt(Integer::intValue).sum() 
				- previousData.getProduction().stream().mapToInt(Integer::intValue).sum())/100));
		
		return response;
	}
}
