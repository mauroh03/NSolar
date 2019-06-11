package pa.nsolar.backend.module.services.interfaces;

import pa.nsolar.backend.module.services.dto.InverterDataRequest;
import pa.nsolar.backend.module.services.dto.InverterDataResponse;

public interface IModuleInfoNSolar {

	InverterDataResponse getInverterData(InverterDataRequest request);
}
