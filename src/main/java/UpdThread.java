import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class UpdThread implements Runnable{
    Thread thread;
    private String name;
    private int stat;
    private int id;
    private int num;
    private String date;
    private ArrayList<Statobj> statobj;
    UpdThread(String name, int num, String date, ArrayList<Statobj> statobj){
//        super(name);
        this.name = name;
        this.stat = statobj.get(0).stat;
        this.id = statobj.get(0).id;
        statobj.remove(0);
        this.num = num;
        this.date = date;
        thread = new Thread(this);
//        start();
    }
    private String urldb = "jdbc:mysql://localhost:3306/urlobj?useSSL=false";
    private String login = "mysql";
    private String password = "mysql";
    public void run(){
        try{
//            URL hp = new URL(url);
//            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
//            statobj.add(new Statobj(id, hpCon.getResponseCode()));
            Connection con = DriverManager.getConnection(urldb, login, password);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + stat + " WHERE id = " + id);
            st.close();
            con.close();
        }catch(Exception e){
            System.out.println(e);
        }finally{

        }
    }
}
