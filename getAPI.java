package http_api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

import ui.admin_frame;

public class getAPI {
	
	public static int userId;
	static admin_frame adminFrame  = new admin_frame();
	
	
	
	 // LOAD API (GET AOI )
    public int loadDataFromApi(String apiUrl, String username, String password) {
        try {
            String jsonData = fetchJsonFromApi(apiUrl, username, password);
            populateTable(jsonData);
            return 1;
        } catch (IOException | org.json.JSONException e) {
            return 2;
        }

    }
	
	
	// GET METHOD
    public String fetchJsonFromApi(String apiUrl, String username, String password) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
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

                return content.toString();
            }
        } else {
        	
            throw new IOException();
            //("GET request failed. Response Code: " + responseCode);
        }
    }
    
    
    // DISPLAY DATA IN THE TABLE (JSON)
    public void populateTable(String jsonData) {
        JSONArray jsonArray = new JSONArray(jsonData);

        // Iterate over the JSON array and add rows to the table model
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            userId = jsonObject.getInt("id");
           // System.err.println("GET ID: " + userId);

            String firstName = jsonObject.getString("firstName");
            String lastName = jsonObject.getString("lastName");
            String email = jsonObject.getString("email");
            
            String street = jsonObject.getString("street");
            String houseNR = jsonObject.getString("houseNR");
            String plz = jsonObject.getString("plz");

            adminFrame.tableModel.addRow(new Object[]{userId, firstName, lastName, email, street, houseNR, plz});
            
        }
    }
	
	
}
