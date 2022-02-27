import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */

public class App {
    private static JSONArray legislator;

    public static void main(String[] args) throws ParseException {

        try {
            // URLConnection urlCon;
            String apiKey = "803abe5541a16de0d79f2c3503f200cd";
            URL url = new URL(
                    "http://www.opensecrets.org/api/?method=getLegislators&id=NJ&output=json&apikey=" + apiKey);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setRequestMethod("GET");
            urlCon.setRequestProperty("Accept", "application/json");
            urlCon.setRequestProperty("User-Agent", "Mozilla/5.0");// add headers to prevent 403 error

            if(urlCon.getResponseCode() != 200){
                throw new RuntimeException("");
                
            }

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            
            String line;
            StringBuilder sb = new StringBuilder();

            while ((line = inputStream.readLine()) != null)
                sb.append(line);
            JSONObject jsonObject = new JSONObject(sb.toString());

            JSONObject result = jsonObject.getJSONObject("response");
            legislator = result.getJSONArray("legislator");
            
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/javatest", "root", "");
            String sql = "INSERT INTO legislator " +
                     "(cid,firstlast,lastname,party,gender,firstelectoff,phone,votesmart_id,feccandid,birthdate)"+
                     "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            
            int i=0;
            while(i < legislator.length()){
           
                JSONObject attributes = legislator.getJSONObject(i);
                JSONObject obj = attributes.getJSONObject("@attributes");
                               
                stmt.setString(1, obj.get("cid").toString());
                stmt.setString(2, obj.get("firstlast").toString());
                stmt.setString(3, obj.get("lastname").toString());
                stmt.setString(4, obj.get("party").toString());
                stmt.setString(5, obj.get("gender").toString());
                stmt.setString(6, obj.get("first_elected").toString());
                stmt.setString(7, obj.get("phone").toString());
                stmt.setString(8, obj.get("votesmart_id").toString());
                stmt.setString(9, obj.get("feccandid").toString());
                stmt.setString(10, obj.get("birthdate").toString());
                
                stmt.addBatch();
                i++;
                if (i % 999 == 0 || i == legislator.length()) {
                    stmt.executeBatch();
                    i++; // Execute every 999 items.
                }
                
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
