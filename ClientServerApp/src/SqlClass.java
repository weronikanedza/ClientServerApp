import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlClass {

    Statement statement;
    ResultSet rS;

    public SqlClass(Statement statement) {
        this.statement = statement;
    }


    public String checkNiu(String niu){
        try {
            statement.executeUpdate("INSERT INTO users values('"+niu+"','')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
    }
}