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
                System.out.println("Wybrano baze");
            }catch(SQLException e){
                System.out.println("Utworzenie bazy");
                createDB();
        }

    }

    public void createDB(){
        update("CREATE DATABASE baza CHARACTER SET utf8 COLLATE utf8_polish_ci;");
        update("use baza");
        update("CREATE TABLE QA(id_qa int PRIMARY KEY ,question varchar(100),answer varchar(100))");
        update("CREATE TABLE USERS(id_users int PRIMARY KEY ,answer varchar(100))");
        update("CREATE TABLE RESULTS(id_result int PRIMARY KEY ,A int ,B int,C int,D int)");

        for (int i=1;i<11;i++)
            update("INSERT INTO results VALUES ('"+i+"','0','0','0','0')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('1', '1.Jak długo pracujesz w naszej firmie', 'Mniej niż rok')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('2', '1.Jak długo pracujesz w naszej firmie', '1-5')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('3', '1.Jak długo pracujesz w naszej firmie', '5-10')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('4', '1.Jak długo pracujesz w naszej firmie', 'Ponad 10 lat')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('5', '2.Jakie zajmujesz stanowisko ?', 'Programista')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('6', '2.Jakie zajmujesz stanowisko ?', 'Tester oprogramowania')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('7', '2.Jakie zajmujesz stanowisko ?', 'Analityk')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('8', '2.Jakie zajmujesz stanowisko ?', 'Żadne z powyższych')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('9',  '3.W jakim języku programujesz na codzień?', 'Java')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('10', '3.W jakim języku programujesz na codzień?', 'C+')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('11', '3.W jakim języku programujesz na codzień?', 'C#')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('12', '3.W jakim języku programujesz na codzień?', 'Żaden z powyższych')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('13', '4.Ile zarabiasz miesięcznie?', '2000-3000 zł')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('14', '4.Ile zarabiasz miesięcznie?', '3000-4000 zł')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('15', '4.Ile zarabiasz miesięcznie?', '4000-5000 zł')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('16', '4.Ile zarabiasz miesięcznie?', 'Ponad 5000 zł')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('17', '5.Czy odpowiada ci twoja pensja?', 'Tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('18', '5.Czy odpowiada ci twoja pensja?', 'Nie')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('19', '5.Czy odpowiada ci twoja pensja?', 'Raczej tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('20', '5.Czy odpowiada ci twoja pensja?', 'Raczej nie')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('21', '6.Moje wynagrodzenie w stosunku do innych firm jest:', 'Wyższe')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('22', '6.Moje wynagrodzenie w stosunku do innych firm jest:', 'Niższe')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('23', '6.Moje wynagrodzenie w stosunku do innych firm jest:', 'Raczej niższe')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('24', '6.Moje wynagrodzenie w stosunku do innych firm jest:', 'Raczej wyższe')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('25', '7.Wiek:', 'Mniej niż 20 lat')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('26', '7.Wiek:', '20-30 lat')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('27', '7.Wiek:', '30-40 lat')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('28', '7.Wiek:', 'Więcej niż 40 lat')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('29', '8.Mogę liczyć na pomoc współpracowników:', 'Tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('30', '8.Mogę liczyć na pomoc współpracowników:', 'Nie')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('31', '8.Mogę liczyć na pomoc współpracowników:', 'Raczej tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('32', '8.Mogę liczyć na pomoc współpracowników:', 'Raczej nie')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('33', '9.Jestem doceniany:', 'Tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('34', '9.Jestem doceniany:', 'Nie')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('35', '9.Jestem doceniany:', 'Raczej tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('36', '9.Jestem doceniany:', 'Raczej nie')");

        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('37', '10.Poleciłbym znajomemu zartudnienie w tej firmie:', 'Tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('38', '10.Poleciłbym znajomemu zartudnienie w tej firmie:', 'Nie')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('39', '10.Poleciłbym znajomemu zartudnienie w tej firmie:', 'Raczej tak')");
        update("INSERT INTO `qa` (`id_qa`, `question`, `answer`) VALUES ('40', '10.Poleciłbym znajomemu zartudnienie w tej firmie:', 'Raczej nie')");

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
