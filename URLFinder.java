import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * The URLFinder class takes a web address and creates a URL object. The
 * stream from the URL is read in and compared to a Regular Expression that
 * determines what pieces of the page are links.
 */
public class URLFinder {

    private URL url;
    private Scanner sc;
    private ArrayList<Link> links;
    private final static String linkExpression = "href=[\\\"\\'](http:\\/\\/|\\"
            + ".\\/|\\/)?\\w+(\\.\\w+)*(\\/\\w+(\\.\\w+)?)" +
            "*(\\/|\\?\\w*=\\w*(&\\w*=\\w*)*)?[\\\"\\']";
    private Pattern pattern;

    public URLFinder(String address) throws IncorrectProtocolError{
        try{
            this.url = new URL(address);
        }
        catch (MalformedURLException e){
            System.err.println("MalformedURLException " + e.getMessage());
        }
        try{
            sc = new Scanner(url.openStream());
            //using this delimiter, scanner reads in the entire stream,
            // allowing the entire page to be
            //parsed instead of reading line by line
            sc.useDelimiter("\\Z");
        }
        catch (IOException e){
            System.err.println("IOException: " + e.getMessage());
        }
        //compiles the Regular Expression for use with the Matcher class
        pattern = Pattern.compile(linkExpression);
        links = new ArrayList<Link>();
    }

    //reads the HTML and finds all of the text that matches
    // linkExpression, and then returns all of the matches in an ArrayList
    public ArrayList<Link> getLinks(){
        String input = "";
        if(sc.hasNext()){
            input =  sc.next();
        }
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            String address = matcher.group();
            address = address.substring(6).replace("\"", "");
            Link link = new Link(address);
            links.add(link);
        }
        return links;
    }
}