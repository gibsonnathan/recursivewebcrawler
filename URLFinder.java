import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 *
 */
public class URLFinder {

    private URL url;
    private Scanner sc;
    private ArrayList<Link> links;
    private final static String linkExpression = "\\s*(?i)href\\s*=\\s*(\\\"" +
            "([^\"]*\\\")|'[^']*'|([^'\">\\s]+))";
    private Pattern pattern;

    public URLFinder(String address) throws IncorrectProtocolError{
        try{
            this.url = new URL(address);
        }
        catch (MalformedURLException e){
            System.err.println(e.getMessage());
        }

        try{
            sc = new Scanner(url.openStream());
            //using this delimiter, scanner reads in the entire stream,
            // allowing the entire page to be
            //parsed instead of reading line by line
            sc.useDelimiter("\\Z");
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
        //compiles the Regular Expression for use with the Matcher class
        pattern = Pattern.compile(linkExpression);
        links = new ArrayList<Link>();
    }

    //reads the entire stream from Scanner, finds anything that matches
    //linkExpression, trims the surrounding characters and then adds it to
    //the links list, finally it returns the list
    public ArrayList<Link> getLinks(){
        String input = "";
        if(sc.hasNext()){
            input = sc.next();
        }else{

        }

        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            String address = matcher.group();
            address = address.substring(6).replace("\"", "");
            Link link = new Link(address);
            if(containsLink(links, link.getUrl())){
                for(Link i : links){
                    if(i.getUrl().equals(link.getUrl())){
                        i.increaseCount();
                        break;
                    }
                }
            }
            else{
                links.add(link);
            }
        }
        return links;
    }

    //helper methods that checks to see if a List of links contains a link,
    //if so it returns true, else false
    private boolean containsLink(ArrayList<Link> links, String url){
        boolean contains = false;
        for(Link i : links){
            if(i.getUrl().equals(url)){
                contains = true;
                break;
            }
            else
                contains = false;
        }
        return contains;
    }
}
