package pa.nsolar.backend.module.services.dto;

public class ModuleArrayResponse {

	private Boolean itsOk;

	public Boolean getItsOk() {
		return itsOk;
	}

	public void setItsOk(Boolean itsOk) {
		this.itsOk = itsOk;
	}

	@Override
	public String toString() {
		return "ModuleArrayResponse [itsOk=" + itsOk + "]";
	}
}
