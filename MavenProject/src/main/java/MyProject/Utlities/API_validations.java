package MyProject.Utlities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class API_validations {
	HttpClient httpClient = HttpClientBuilder.create().build();
	HttpGet request;
	static RestTemplate restTemplate;
	static HttpHeaders headers;
	static ResponseEntity<String> response;
	static HttpEntity<String> entity;
	static JsonNode root;
	
	public List<String> readValuesFromJSON(String API_URL) throws Exception{
		List<String> li = new ArrayList<String>();
		try {
			restTemplate = new RestTemplate();
			String vzid="srini1106";
			String jsonRequest=vzid+" and mandatory values";
			headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			entity = new HttpEntity<String>(jsonRequest,headers);
			response = restTemplate.exchange(API_URL, HttpMethod.POST, entity, String.class);
			ObjectMapper mapper = new ObjectMapper();
			root = mapper.readTree(response.getBody());
			System.out.println(root);
			li = root.findValuesAsText("JSON_NODE");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return li;
	}
}
