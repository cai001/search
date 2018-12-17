import java.util.ArrayList;

public class UrlList {
    ArrayList<Urlobj> al;

    UrlList(){
        this.al = new ArrayList<Urlobj>();
    }
    synchronized public Urlobj get(int i){
         return al.get(i);
    }
    synchronized public boolean add(Urlobj e){
        return al.add(e);
    }
    synchronized public Urlobj remove(int i){
        return al.remove(i);
    }
    public int size(){
        return al.size();
    }
    public boolean isEmpty(){
        return al.isEmpty();
    }
}
