package pa.nsolar.backend.module.services.interfaces;

import pa.nsolar.backend.module.services.dto.InverterDataRequest;
import pa.nsolar.backend.module.services.dto.InverterDataResponse;
import pa.nsolar.backend.module.services.dto.ModuleArrayRequest;
import pa.nsolar.backend.module.services.dto.ModuleArrayResponse;

public interface IModuleInfoNSolar {

	InverterDataResponse getInverterData(InverterDataRequest request);
	ModuleArrayResponse getModuleData(ModuleArrayRequest request);
}
