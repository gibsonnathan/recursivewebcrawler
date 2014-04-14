/**
 *
 *
 *
 */
public class Link implements Comparable<Link>{
    private String url;
    private int count = 1;

    public Link(String url){
        this.url = url;
    }

    public String getUrl(){
        return this.url;
    }

    public void increaseCount(){
        this.count++;
    }

    public int getCount(){
        return this.count;
    }

    public int compareTo(Link link){

        if(link.getCount() < this.getCount()){
            return -1;
        }
        else if(link.getCount() == this.getCount()){
            return 0;
        }
        else
            return 1;
    }
    public String toString(){

        return this.getUrl();
    }
}
