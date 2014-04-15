/**
 *
 *
 *
 */
import java.util.*;
public class Link implements Comparable<Link>{
    private String url;
    private int count = 0;

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

    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        if (!(o instanceof Link)) {
            return false;
        }

        Link link = (Link) o;

        return link.getUrl().equals(this.getUrl());
    }
}
