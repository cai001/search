import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UrlThread implements Runnable{
    public Thread thread;
    private String name;
    private String url;
    private int id;
    private int num;
    private UrlList urlobj;
    private StatList statobj;
    private ArrayList<String> alstr;
    UrlThread(String name, int num, UrlList urlobj, StatList statobj, ArrayList<String> alstr){
//        super(name);
        this.name = name;
        this.url = urlobj.get(0).url;
        this.id = urlobj.get(0).id;
        urlobj.remove(0);
        this.num = num;
        this.statobj = statobj;
        this.alstr = alstr;
        thread = new Thread(this);
//        thread.start();
    }
//    private String urldb = "jdbc:mysql://localhost:3306/urlobj?useSSL=false";
//    private String login = "mysql";
//    private String password = "mysql";
//    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//    private String date = sdf.format(new java.util.Date());
    public void run(){

        try{
            URL hp = new URL(url);
            HttpURLConnection hpCon = (HttpURLConnection) hp.openConnection();
            statobj.add(new Statobj(id, hpCon.getResponseCode()));
//            System.out.println("id = " + id + ", stat = " + hpCon.getResponseCode());
//          Connection con = DriverManager.getConnection(urldb, login, password);
//          Statement st = con.createStatement();
//          st.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + hpCon.getResponseCode() + " WHERE id = " + id);
//          st.close();
        }catch (UnknownHostException uhe){
            statobj.add(new Statobj(id, 600));
//            System.out.println("600");
        }catch (ConnectException ce){
            statobj.add(new Statobj(id, 601));
//            System.out.println("601");
        }catch(Exception e){
            System.out.println(e);
        }finally{

        }

    }
}
