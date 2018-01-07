import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Statement statement = null;
    private Connection connection = null;

    public Database()  {

        if (!checkDriver("com.mysql.jdbc.Driver")){
            System.out.println("Brak sterownika");
            System.exit(1);
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/?useUnicode=yes&characterEncoding=UTF-8","root","");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Nie udalo sie nawiazac polaczenia");
        }
        statement = createStatement(connection);

            try{
                statement.executeUpdate("USE baza");
                System.out.println("Wybranie bazy");
            }catch(SQLException e){
                System.out.println("Utworzenie bazy");
                createDB();
        }

    }

    public void createDB(){
        update("CREATE DATABASE baza CHARACTER SET utf8 COLLATE utf8_polish_ci;");
        update("use baza");
        update("CREATE TABLE QA(id int PRIMARY KEY ,question varchar(100),answer varchar(100))");
        update("INSERT INTO `qa` (`id`, `question`, `answer`) VALUES ('1', 'pytą', 'ała')");
    }

    public int update(String sql){
        try{
            statement.executeUpdate(sql);
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return 1;
        }
        return 0;
    }
    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public boolean checkDriver(String driver) {
        try {
            Class.forName(driver).newInstance();
            return true;
        } catch (Exception e) {
            System.out.println("Blad sterownika");
            return false;
        }
    }


    private  Statement createStatement(Connection connection) {
        try {
            return connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Błąd utworzenia statement" + e.getMessage() );
            System.exit(3);
        }
        return null;
    }

    public void closeConnection() {
        System.out.print("Zamkniecie połączenia");
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Nie " + e.getMessage());
            System.exit(4);
        }
        System.out.print("Zamknięto poprawnie bazę");
    }
}
