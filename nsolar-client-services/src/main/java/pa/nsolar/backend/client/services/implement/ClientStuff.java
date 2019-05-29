package pa.nsolar.backend.client.services.implement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import pa.nsolar.backend.client.api.dto.ExampleResponse;
import pa.nsolar.backend.client.services.interfaces.IClientStuff;

@Service
public class ClientStuff implements IClientStuff {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientStuff.class);

	@Override
	public ExampleResponse consultClient() {
		ExampleResponse response = new ExampleResponse();
		response.setMessage("Hello world, from S-Solar backend");
		LOGGER.info(response.getMessage());
		return response;
	}
}
