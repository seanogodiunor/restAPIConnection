package http_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;


public class deleteAPI {
	
	
//	public static void main(String[] args) throws IOException {
//		
//		deleteAPI api = new deleteAPI();
//		
//		int employeeID = 17;
//		String deleteUrl = "http://localhost:8080/api/employees/"+ employeeID;
//		
//        String userName = "john";
//        String userPassword = "fun123";
//		
//		System.out.println(
//				
//				api.delete(deleteUrl, userName, userPassword)
//				);
//		//401	sever error
//	}
	
	
	// DELETE METHOD
    public int delete(String apiUrl, String username, String password) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        connection.setRequestProperty("Accept", "application/json");

        // Encode credentials and set Authorization header
        String encodedCredentials = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);

        // Get the response code
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Read the response using try-with-resources
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                return responseCode;
            }
        } else {
        	return responseCode;
           // throw new IOException();
        }
    }
    
    
    

}
