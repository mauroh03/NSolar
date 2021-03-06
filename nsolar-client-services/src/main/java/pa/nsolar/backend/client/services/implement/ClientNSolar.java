package pa.nsolar.backend.client.services.implement;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
import pa.nsolar.backend.client.api.dto.SystemObject;
import pa.nsolar.backend.client.services.interfaces.IApiCallOperation;
import pa.nsolar.backend.client.services.interfaces.IClientNSolar;

@Service
public class ClientNSolar implements IClientNSolar {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientNSolar.class);

	@Autowired
	IApiCallOperation apiCallOperation;

	@Value("${nsolar.general.filePath}")
	private String filePath;

	@Value("${nsolar.excel.filePath}")
	private String excelFilePath;

	@Override
	public EnergyLifeTimeResponse nSolarClientList() {
		EnergyLifeTimeResponse response = new EnergyLifeTimeResponse();
		response = apiCallOperation.nSolarClientList();
		List<SystemObject> clientList = response.getSystems();
		clientList.sort(Comparator.comparing(SystemObject::getSystem_name));
		response.setSystems(clientList);
		return response;
	}

	@Override
	public ClientSummaryResponse nSolarClientSummary(ClientSummaryRequest clientSummaryRequest) {
		return apiCallOperation.nSolarCLientSummary(clientSummaryRequest);
	}

	@Override
	public EnergyLifeTimeDatedResponse nSolarLifeTimeEnergy(EnergyLifeTimeDatedRequest energyLifeTimeDatedRequest) {

		DecimalFormat df = new DecimalFormat("#.00");
		EnergyLifeTimeDatedObject energyLifeTimeDatedObject = new EnergyLifeTimeDatedObject();
		ExcelObject clientExcelObject = this.getClientJSONObject(energyLifeTimeDatedRequest.getClientId());
		DateObject dateObject = this.getDates(energyLifeTimeDatedRequest.getDate(), 1);
		energyLifeTimeDatedObject.setProduction("all");
		energyLifeTimeDatedObject.setClientId(String.valueOf(energyLifeTimeDatedRequest.getClientId()));
		energyLifeTimeDatedObject.setStartDate(dateObject.getStartDate());
		energyLifeTimeDatedObject.setEndDate(dateObject.getEndDate());
		EnergyLifeTimeDatedResponse response = apiCallOperation.nSolarEnergyLifeTime(energyLifeTimeDatedObject);
		Double totalGneration = Double
				.valueOf(df.format(response.getProduction().stream().mapToInt(Integer::intValue).sum() / 1000.0));
		response.setMayorGeneration(this.getGenerationDate(response, "+"));
		response.setMinorGeneration(this.getGenerationDate(response, "-"));
		response.setTotalGeneracion(totalGneration);
		response.setGenerationComparizon(this.getComparisson(energyLifeTimeDatedRequest, response));
		response.setMetrictsTons(this.getMetricsTons(response));
		response.setHouses(this.getHouseCount(response));
		response.setTrees(this.getTreesCount(response));
		response.setClientExcelObject(clientExcelObject);
		response.setMediaGeneration(Double.valueOf(df.format(totalGneration / response.getProduction().size())));

		return response;
	}

	private ExcelObject getClientJSONObject(Integer clientId) {
		List<ExcelObject> excelClientList = new ArrayList<>();
		ExcelObject userObject = new ExcelObject();
		try {
			excelClientList = this.getExcelClientList();
			for (ExcelObject excelObject : excelClientList) {
				if (null != excelObject.getUser_id() && excelObject.getUser_id().equals(clientId)) {
					LOGGER.info("clientExcelObject: {}", excelObject);
					return excelObject;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.error("Client not found on excel file");

		return userObject;
	}

	private List<ExcelObject> getExcelClientList() {
		DecimalFormat df = new DecimalFormat("#.00");
		DecimalFormat integerF = new DecimalFormat("#");
		List<ExcelObject> excelClientList = new ArrayList<>();
		try {
			FileInputStream excelFile = new FileInputStream(new File(excelFilePath));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();
				if (count != 0) {
					ExcelObject userObject = new ExcelObject();
					while (cellIterator.hasNext()) {
						Cell currentCell = cellIterator.next();
						int index = currentCell.getColumnIndex();
						if (currentCell != null) {
							switch (index) {
							case 0:
								userObject.setCliente(currentCell.getStringCellValue());
								break;
							case 1:
								userObject.setUser_id(Integer.valueOf(integerF.format(currentCell.getNumericCellValue())));
								break;
							case 2:
								userObject.setSistema_kW(df.format(currentCell.getNumericCellValue()));
								break;
							case 3:
								userObject.setPaneles(Integer.valueOf(integerF.format(currentCell.getNumericCellValue())));
								break;
							case 4:
								userObject.setWatts_Panel(Integer.valueOf(integerF.format(currentCell.getNumericCellValue())));
								break;
							case 5:
								userObject.setFecha_Operacion_Enphase(currentCell.getStringCellValue());
								break;
							case 6:
								userObject.setFecha_Medidor_Bidireccional(currentCell.getStringCellValue());
								break;
							case 7:
								userObject.setPanel(currentCell.getStringCellValue());
								break;
							case 8:
								userObject.setModelo_Panel(currentCell.getStringCellValue());
								break;
							case 9:
								userObject.setMicroinversor(currentCell.getStringCellValue());
								break;
							default:
								LOGGER.info("INDEX OUT OF BOUNDS");
							}
						}
						excelClientList.add(userObject);
					}
				}
				count++;
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return excelClientList;
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
		DecimalFormat df = new DecimalFormat("#.00");
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
		genObject.setGeneration(Double.valueOf(df.format(value / 1000.0)));

		return genObject;
	}

	private Integer getTreesCount(EnergyLifeTimeDatedResponse response) {
		Double result = (response.getMetrictsTons() / 0.060);
		LOGGER.info("arboles: {}/0.060 = {}", response.getMetrictsTons(), Integer.valueOf(result.intValue()));
		return Integer.valueOf(result.intValue());
	}

	private Integer getHouseCount(EnergyLifeTimeDatedResponse response) {
		Double result = (response.getTotalGeneracion() / 32.23013699);
		LOGGER.info("casas: ({}/32.23)/1000 = {}", response.getTotalGeneracion(), Integer.valueOf(result.intValue()));
		return Integer.valueOf(result.intValue());
	}

	private Double getMetricsTons(EnergyLifeTimeDatedResponse response) {
		DecimalFormat df = new DecimalFormat("#.00");
		LOGGER.info("carbon offest (tons): (7.07x10-4)*{} = {}",
				response.getProduction().stream().mapToInt(Integer::intValue).sum(),
				Double.valueOf(df.format((7.07 * Math.pow(10, -4))
						* response.getProduction().stream().mapToInt(Integer::intValue).sum())));
		return Double.valueOf(df.format((7.07 * Math.pow(10, -4)) * response.getTotalGeneracion()));
	}

	private GenerationMeasurement getComparisson(EnergyLifeTimeDatedRequest request,
			EnergyLifeTimeDatedResponse actualData) {
		DecimalFormat df = new DecimalFormat("#.00");
		GenerationMeasurement response = new GenerationMeasurement();
		DateObject dateObject = this.getDates(request.getDate(), -10);
		EnergyLifeTimeDatedObject energyLifeTimeDatedObject = new EnergyLifeTimeDatedObject();
		energyLifeTimeDatedObject.setProduction("all");
		energyLifeTimeDatedObject.setClientId(String.valueOf(request.getClientId()));
		energyLifeTimeDatedObject.setStartDate(dateObject.getStartDate());
		energyLifeTimeDatedObject.setEndDate(dateObject.getEndDate());
		EnergyLifeTimeDatedResponse previousData = apiCallOperation.nSolarEnergyLifeTime(energyLifeTimeDatedObject);
		Double actualProduction = actualData.getProduction().stream().mapToInt(Integer::intValue).sum() / 1000.0;
		Double previousProduction = previousData.getProduction().stream().mapToInt(Integer::intValue).sum() / 1000.0;
		response.setIndicator((actualProduction - previousProduction) > 0 ? "Aumentó" : "Disminuyó");

		Double up = Double.valueOf(actualProduction - previousProduction);
		Double down = Double.valueOf(previousProduction);
		response.setValue(df.format((up / down) * 100).replaceAll("-", ""));

		LOGGER.info("Comparisson: {} percentage: |(({} - {}) / {})*100| = {}", response.getIndicator(),
				actualProduction, previousProduction, previousProduction, (up / down) * 100);

		return response;
	}
}
