import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SelThread extends Thread{
    private String name;
    private String date;
    private int id;
    private int num;
    ArrayList<Urlobj> urlobj;
    Connection con;
    private StatCont sc;
    SelThread(String name, int num, String date, ArrayList<Urlobj> urlobj, Connection con){
        super(name);
        this.name = name;
        this.date = date;
        this.num = num;
        this.urlobj = urlobj;
        this.con = con;
        start();
    }
//    private String urldb = "jdbc:mysql://localhost:3306/urlobj?useSSL=false";
//    private String login = "mysql";
//    private String password = "mysql";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    private String date = sdf.format(new java.util.Date());
    public void run(){
        try{
//            URL hp = new URL(url);
//            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
//            sc.setStat(this.id, hpCon.getResponseCode());
//            Connection con = DriverManager.getConnection(urldb, login, password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM urllistg WHERE date < '" + date + "' or date is null");
            while (rs.next()){
                urlobj.add(new Urlobj(rs.getInt("id"), rs.getString("url")));
            }
            st.close();
        }catch(Exception e){
            System.out.println(e);
        }finally{
            num++;
        }
    }
}
