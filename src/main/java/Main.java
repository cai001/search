import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String args[]) throws Exception{
        System.out.println(new java.util.Date());
//        int blockQueueSize = 10;
        int x = 0, y = 0, z = 0;
        String urldb = "jdbc:mysql://localhost:3306/urllist?useSSL=false";
        String login = "mysql";
        String password = "mysql";
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection(urldb, login, password);
        UrlList urlobj = new UrlList();
        StatList statobj = new StatList();
        ArrayList<String> alstr = new ArrayList<String>();
        ArrayList<Future> futures = new ArrayList<Future>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new java.util.Date());
        String date1 = date;
        if (args.length != 0){
            date1 = args[0];
        }

//        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(blockQueueSize);
//        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
//        ExecutorService service =  new ThreadPoolExecutor(numOfThread, numOfThread,
//                0L, TimeUnit.MILLISECONDS, blockingQueue, rejectedExecutionHandler);

        Statement st = con.createStatement();
        for(int i = 0; i < 100; i++) {
//            new SelThread("seltype" + i, x, date, urlobj, con);
            ResultSet rs = st.executeQuery("SELECT * FROM urllistg WHERE date < '" + date1 + "' or date is null");
            while (rs.next()){
                urlobj.add(new Urlobj(rs.getInt("id"), rs.getString("url")));
            }
        }
        st.close();
        con.close();
//        Thread.sleep(10000);
        System.out.println(urlobj.size());
//        for (int i = 0; i < urlobj.size(); i++){
//            System.out.println("id = " + urlobj.get(i).id + ", url = " + urlobj.get(i).url);
//        }
//        urlobj.add(new Urlobj(10,"http://google.com"));
//        urlobj.add(new Urlobj(11,"http://rumbler.com"));
//        urlobj.add(new Urlobj(12,"http://yandex.ru"));
//        urlobj.add(new Urlobj(13,"http://mail.ru"));

        ExecutorService service = Executors.newCachedThreadPool();
//        service.submit(new SelThread("seltype" + 1, 0, date, urlobj, con));

        while(!urlobj.isEmpty()){
            futures.add(service.submit(new UrlThread("urltype", y, urlobj, statobj, alstr)));
//            urlobj.remove(0);
        }
//        System.out.println(futures.size());

//        for(int i = 0; i < 10; i++) {
//            if (!urlobj.isEmpty()) {
//                new UrlThread("urltype" + i, i, urlobj, statobj);
//            }
//        }
//        Thread.sleep(6000);
//        boolean fin = false;
//        while (!fin) {
////            System.out.println(fin);
//            fin = true;
//            for (int i = 0; i < futures.size(); i++) {
//                fin = fin && futures.get(i).isDone();
//            }
//            Thread.sleep(1000);
//        }
        while (!futures.isEmpty()){
            if (futures.get(0).isDone()){
                futures.remove(0);
            }else{
                Thread.sleep(100);
            }
        }
//        System.out.println(futures.size());
        service.shutdown();
//        System.out.println(futures.size());
        System.out.println(urlobj.size());
        System.out.println(statobj.size());
//        for (int i = 0; i < statobj.size(); i++){
//            System.out.println("id = " + statobj.get(i).id + ", stat = " + statobj.get(i).stat);
//        }
        Connection coni = DriverManager.getConnection(urldb, login, password);
        while (!statobj.isEmpty()){
            int stat = statobj.get(0).stat;
            int id = statobj.get(0).id;
            statobj.remove(0);

            Statement sti = coni.createStatement();
            sti.executeUpdate("UPDATE urllistg SET date = '" + date + "', status = " + stat + " WHERE id = " + id);
            sti.close();

        }
        coni.close();
//        while (!statobj.isEmpty()){
//            service.submit(new UpdThread("updtype", z, date, statobj));
//        }

//        Thread.sleep(1000);
        System.out.println(urlobj.size());
        System.out.println(statobj.size());
//        for (int i = 0; i < c; i++) {
//            System.out.println(statobj.get(i).id + ", " + statobj.get(i).stat);
//        }


//        Prepare prep = new Prepare();
//        int maxid = prep.prepare("20181215");
//        StatCont sc = new StatCont(maxid);
//        UrlCont uc = new UrlCont(maxid);
//        new UrlThread("1",1,"http:google.com",1,sc);
//        System.out.println("");
        System.out.println(new java.util.Date());
    }
}
