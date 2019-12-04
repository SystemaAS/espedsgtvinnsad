package espedsgtvinnsad;

import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

public class RestTemplateTester {

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        System.out.println(response);
	}

}
