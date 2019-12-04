package espedsgtvinnsad;

import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientTester {

	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
	    try {
	      //
	      //HttpGet httpGetRequest = new HttpGet("https://test.ekspressfortolling.toll.no/api/exf/manifest-auth-api/user/login");	
	      HttpGet httpGetRequest = new HttpGet("http://gturnquist-quoters.cfapps.io/api/random");
	    	
	      //HttpGet httpGetRequest = new HttpGet("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys");
	      HttpResponse httpResponse = httpClient.execute(httpGetRequest);

	      System.out.println("----------------------------------------");
	      System.out.println(httpResponse.getStatusLine());
	      System.out.println("----------------------------------------");

	      HttpEntity entity = httpResponse.getEntity();

	      byte[] buffer = new byte[1024];
	      if (entity != null) {
	        InputStream inputStream = entity.getContent();
	        try {
	          int bytesRead = 0;
	          BufferedInputStream bis = new BufferedInputStream(inputStream);
	          while ((bytesRead = bis.read(buffer)) != -1) {
	            String chunk = new String(buffer, 0, bytesRead);
	            System.out.println(chunk);
	          }
	        } catch (Exception e) {
	          e.printStackTrace();
	        } finally {
	          try { inputStream.close(); } catch (Exception ignore) {}
	        }
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      httpClient.getConnectionManager().shutdown();
	    }
	  }

	}
