public class UrlCont {
    private int maxid;
//    private int[] id;
    private int[] stat;
    UrlCont(int maxid){
        this.maxid = maxid;
//        this.id = new int[maxid];
        this.stat = new int[maxid];
    }
    public void setStat (int id, int stat) {
//        this.id[id] = id;
        this.stat[id] = stat;
    }
    public int getStat (int id) {
        return this.stat[id];
    }
}
