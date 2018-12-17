import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Prepare {
    public int prepare (String date) throws Exception{
        String urldb = "jdbc:mysql://localhost:3306/urlobj?useSSL=false";
        String login = "mysql";
        String password = "mysql";
        Connection con = DriverManager.getConnection(urldb, login, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM urlobj.urllistg WHERE date < '" + date + "' or date is null");
        rs.next();
        int count = rs.getInt(1);
        ResultSet rs2 = st.executeQuery("SELECT MAX(id) FROM urlobj.urllistg WHERE date < '" + date + "' or date is null");
        rs2.next();
        int maxid = rs.getInt(1);
        st.close();

        return maxid;
    }
}
