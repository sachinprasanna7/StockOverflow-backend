import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sql_java_connector {
    public static void main(String[] args){
        String url = "jdbc:mysql://localhost:3306/stocks_overflow";
        String user = "root";
        String password = "n3u3da!";

        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            Statement statement = connection.createStatement();

            // Execute a query
            String query = "SELECT * FROM stocks";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set
            while (resultSet.next()) {
                System.out.println("Column1: " + resultSet.getInt("symbol_id"));
                System.out.println("Column2: " + resultSet.getString("symbol"));
                System.out.println("Column3: " + resultSet.getString("company_name"));
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
