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


    public String checkNiu(String niu) {
        try {
            statement.executeUpdate("INSERT INTO users values('" + niu + "','')");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "1";
        }
        return "0";
    }

    public String getQA() {
        String qa = "";
        try {
            rS = statement.executeQuery("SELECT * FROM qa ");
            int i = 4;
            while (rS.next()) {
                if (i % 4 == 0) {
                    qa += rS.getString("question") + ",";
                }
                qa += rS.getString("answer") + ",";
                i++;
            }
            System.out.println(qa);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "1";
        }
        return qa;
    }

    public String answer(String answers[]) {
        String a = "";
        for (int i = 2; i < answers.length; i++)
            a += answers[i] + ",";

        try {
            statement.executeUpdate("Update users set answer='" + a + "'where id_users='" + answers[1] + "'");

            int j = 1;
            for (int i = 2; i < answers.length; i++) {
                switch (answers[i]) {
                    case "A":
                        statement.executeUpdate("Update results set A=A+1 where id_result='" + j + "'");
                        break;
                    case "B":
                        statement.executeUpdate("Update results set B=B+1 where id_result='" + j + "'");
                        break;
                    case "C":
                        statement.executeUpdate("Update results set C=C+1 where id_result='" + j + "'");
                        break;
                    case "D":
                        statement.executeUpdate("Update results set D=D+1 where id_result='" + j + "'");
                        break;
                    default:
                        break;
                }
                j++;
            }

            rS = statement.executeQuery("Select * from results order by id_result");
            a = "";
            while (rS.next()) {
                a += rS.getString("id_result") + ",";
                a += rS.getString("A") + ",";
                a += rS.getString("B") + ",";
                a += rS.getString("C") + ",";
                a += rS.getString("D") + ",";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return a;
    }

    public String checkNumber() {
        try {
            rS = statement.executeQuery("SELECT count(id_users) as number from users");
            rS.next();
            return rS.getString("number");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "1";
        }
    }

    public String checkUserAnswers(String splited) {
        try {
            rS = statement.executeQuery("Select * from users where id_users='" + splited + "'");
            rS.next();
            return rS.getString("answer");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "1";
        }
    }
}