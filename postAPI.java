package http_api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.json.JSONObject;

import ui.admin_frame;

public class postAPI {

	static admin_frame adminFrame  = new admin_frame();
	
//	public static void main(String[] args) throws IOException {
//		
//		postAPI api = new postAPI();
//		
//		String putUrl = "http://localhost:8080/api/employees";
//		String userName = "john";
//        String userPassword = "fun123";
//        String firstname = "gh";
//        String lastname = "okocha";
//        String email = "okocha.mail";
//        String street = "okacha street";
//    	String housNR = "23";
//    	String plz = "87564";
//	
//		System.out.println(
//				
//				api.post(putUrl, userName, userPassword, firstname, lastname, email, street, housNR, plz)
//				
//				);
//		// 401 SEVER ERROR
//	}

	
	  public int post(String apiUrl, String username, String password, 
				String firstname, String lastname, String email,
				String street, String houseNR, String plz) {

		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("firstName", firstname);
		jsonObject.put("lastName", lastname);
		jsonObject.put("email", email);
		
		jsonObject.put("street", street);
		jsonObject.put("houseNR", houseNR);
		jsonObject.put("plz", plz);
		
		String jsonString = jsonObject.toString();  
		System.out.println("POST metho: " + jsonString);
		
		
		if (!(firstname=="") && !(lastname=="") && !(email=="") && !(street=="") && !(houseNR=="") && !(plz=="")) {
		
			if (!firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty()	&& !street.isEmpty()
																&& !houseNR.isEmpty() && !plz.isEmpty()) {
				int responseCode = 0;
				
				 // Encode username and password for Basic Authentication
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
				String authHeader = "Basic " + new String(encodedAuth);
			
				HttpURLConnection conn = null;
				try {
				  
			      URL url = new URL(apiUrl);
			      conn = (HttpURLConnection) url.openConnection();
			      conn.setRequestMethod("POST");
			      conn.setRequestProperty("Content-Type", "application/json");
			      conn.setRequestProperty("Authorization", authHeader);
			      conn.setDoOutput(true);
			      // Write the JSON data to the output stream
			      try (OutputStream os = conn.getOutputStream()) {
			    	  
			          byte[] input = jsonString.getBytes(StandardCharsets.UTF_8);
			          os.write(input, 0, input.length);
			      }
			      
			      responseCode = conn.getResponseCode(); // This line actually sends the request
			      return responseCode;		// susessf
			      
			  } catch (Exception e) {
			      e.printStackTrace();
			      
			  } finally {
			      if (conn != null) {
			          conn.disconnect();
			      }
			  }
			  return responseCode;
			}
		}
			return 0;
		}
}
