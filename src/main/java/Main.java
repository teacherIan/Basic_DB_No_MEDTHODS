import java.sql.*;

public class Main {

    public static void main(String[] args) {

        //CRUD -- CREATE READ UPDATE DELETE

        String dbPath = "src/main/resources/";
        String dbName = "newDB.db";




        try {

            String firstName = "Doug";
            String lastName = "malloy";
            int age = 20;
            Connection connection = DriverManager.getConnection("jdbc:sqlite:"+dbPath+dbName);
            Statement statement = connection.createStatement();

            //statement.execute("DROP TABLE IF EXISTS contacts");
            statement.execute("CREATE TABLE IF NOT EXISTS contacts(firstName TEXT,lastName TEXT,age INTEGER)");

            statement.execute("INSERT INTO contacts (firstName, lastName, age) VALUES ('Ian','Malloy',70)");
            statement.execute("INSERT INTO contacts (firstName, lastName, age) VALUES ('" + firstName + "','" + lastName + "',70)");

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO contacts (firstName, lastName, age) VALUES (?,?,?)");
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,"Malloy");
            preparedStatement.setInt(3,age);
            
            preparedStatement.execute();



            ResultSet resultSet =  statement.executeQuery("SELECT * FROM contacts");

            while (resultSet.next()) {
                System.out.print(resultSet.getString(1));
                System.out.print(resultSet.getString("lastName"));
                System.out.println( resultSet.getInt(3));

                System.out.println("------------------");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
