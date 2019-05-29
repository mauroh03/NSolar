package pa.nsolar.backend.client.api.dto;

public class ExampleResponse {

	String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ExampleResponse [message=" + message + "]";
	}
}
