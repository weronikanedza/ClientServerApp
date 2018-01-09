import com.sun.org.apache.bcel.internal.generic.Select;

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

    public String getQA(){
        String qa="";
        try {
            rS=statement.executeQuery("SELECT * FROM qa ");
            int i=4;
            while(rS.next()){
                if(i%4==0){
                    qa+= rS.getString("question")+",";
                }
                qa+= rS.getString("answer")+",";
                i++;
            }
            System.out.println(qa);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "1";
        }
        return qa;
    }
}
