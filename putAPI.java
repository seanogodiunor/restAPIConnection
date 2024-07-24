package http_api;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class putAPI {
	
	
//	public static void main(String[] args) throws IOException {
//		
//		putAPI api = new putAPI();
//		
//		String putUrl = "http://localhost:8080/api/employees";
//		String userName = "john";
//        String userPassword = "fun123";
//        String employeeID = "11";
//        String firstname = "rrrrrrrrrrrrrrrrrrr";
//        String lastname = "okocha";
//        String email = "okocha.mail";
//        String street = "okacha street";
//    	String housNR = "23";
//    	String plz = "87564";
//	
//		System.out.println(
//				
//				api.put(putUrl, userName, userPassword, employeeID, firstname, lastname, email, street, housNR, plz)
//				
//				);
//		// 401 SEVER ERROR
//	}

	
	 // UPDATE DATA FROM API
    public int put(String apiUrl, String username, String password,
    		String id, String firstname, String lastname, String email,
    								String street, String houseNR, String plz) {
    	
    																												
       String jsonData =  String.format
    		   ("{\"id\":\"%s\",\"firstName\":\"%s\",\"lastName\":\"%s\",\"email\":\"%s\",\"street\":\"%s\",\"houseNR\":\"%s\",\"plz\":\"%s\"}",									
    		   id, firstname, lastname, email, street, houseNR, plz);

        System.out.println(jsonData);

        // Encode username and password for Basic Authentication
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);

        HttpURLConnection conn = null;
        try {
        	
            URL url = new URL(apiUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Authorization", authHeader);
            conn.setDoOutput(true);

            // Write the JSON data to the output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonData.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get the response code to ensure the request was successful
            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            	
            	return responseCode; //successfully
            } else {
            	
            	return responseCode;	//Unsuccessfully "PUT request failed with response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
		return 0;
    }
    

}
