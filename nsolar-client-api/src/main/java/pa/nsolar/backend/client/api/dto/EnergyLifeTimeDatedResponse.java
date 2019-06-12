package pa.nsolar.backend.client.api.dto;

import java.util.List;

public class EnergyLifeTimeDatedResponse {

	private Integer system_id;
	private String start_date;
	private List<Integer> production;
	private MetaObject meta;
	private List<Integer> meter_production;
	private List<Integer> micro_production;
	private String endDate;
	private Double totalGeneracion;
	private GenerationObject minorGeneration;
	private GenerationObject mayorGeneration;
	private GenerationMeasurement generationComparizon;
	private Double metrictsTons;
	private Integer houses;
	private Integer trees;
	private ExcelObject clientExcelObject;

	public Integer getSystem_id() {
		return system_id;
	}

	public void setSystem_id(Integer system_id) {
		this.system_id = system_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public List<Integer> getProduction() {
		return production;
	}

	public void setProduction(List<Integer> production) {
		this.production = production;
	}

	public MetaObject getMeta() {
		return meta;
	}

	public void setMeta(MetaObject meta) {
		this.meta = meta;
	}

	public List<Integer> getMeter_production() {
		return meter_production;
	}

	public void setMeter_production(List<Integer> meter_production) {
		this.meter_production = meter_production;
	}

	public List<Integer> getMicro_production() {
		return micro_production;
	}

	public void setMicro_production(List<Integer> micro_production) {
		this.micro_production = micro_production;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public GenerationObject getMinorGeneration() {
		return minorGeneration;
	}

	public void setMinorGeneration(GenerationObject minorGeneration) {
		this.minorGeneration = minorGeneration;
	}

	public GenerationObject getMayorGeneration() {
		return mayorGeneration;
	}

	public void setMayorGeneration(GenerationObject mayorGeneration) {
		this.mayorGeneration = mayorGeneration;
	}

	public GenerationMeasurement getGenerationComparizon() {
		return generationComparizon;
	}

	public void setGenerationComparizon(GenerationMeasurement generationComparizon) {
		this.generationComparizon = generationComparizon;
	}

	public Double getMetrictsTons() {
		return metrictsTons;
	}

	public void setMetrictsTons(Double metrictsTons) {
		this.metrictsTons = metrictsTons;
	}

	public Integer getHouses() {
		return houses;
	}

	public void setHouses(Integer houses) {
		this.houses = houses;
	}

	public Integer getTrees() {
		return trees;
	}

	public void setTrees(Integer trees) {
		this.trees = trees;
	}

	public ExcelObject getClientExcelObject() {
		return clientExcelObject;
	}

	public Double getTotalGeneracion() {
		return totalGeneracion;
	}

	public void setTotalGeneracion(Double totalGeneracion) {
		this.totalGeneracion = totalGeneracion;
	}

	public void setClientExcelObject(ExcelObject clientExcelObject) {
		this.clientExcelObject = clientExcelObject;
	}

	@Override
	public String toString() {
		return "EnergyLifeTimeDatedResponse [system_id=" + system_id + ", start_date=" + start_date + ", production="
				+ production + ", meta=" + meta + ", meter_production=" + meter_production + ", micro_production="
				+ micro_production + ", endDate=" + endDate + ", totalGeneracion=" + totalGeneracion
				+ ", minorGeneration=" + minorGeneration + ", mayorGeneration=" + mayorGeneration
				+ ", generationComparizon=" + generationComparizon + ", metrictsTons=" + metrictsTons + ", houses="
				+ houses + ", trees=" + trees + ", clientExcelObject=" + clientExcelObject + "]";
	}
}
