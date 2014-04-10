import java.util.ArrayList;
import java.util.Collections;

/**
 *
 *
 *
 */
public class WebCrawler {

    public static void main(String[]args) throws IncorrectProtocolError{

        String address = "";

        //command line arguments
        if(args.length == 0){
            System.err.println("Usage: java WebCrawler -url " +
                    "http://example.com/");
            System.exit(0);
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-url")) {
                address = args[++i];
            } else {
                System.err.print("Unknown command line argument ");
                System.err.print(args[i]);
                System.err.println(".");
                System.exit(0);
            }
        }

        //checks to make sure the supplied address is the correct protocol
        if(!address.substring(0,7).equals("http://") && !address
                .substring(0,8).equals("https://")){
            throw new IncorrectProtocolError("Protocol Must be HTTP/HTTPS");
        }

        URLFinder finder = new URLFinder(address);
        ArrayList<Link> links = new ArrayList<Link>(finder.getLinks());

        //sorts the arrayList based on number of links
        Collections.sort(links);

        //prints out all of the links along with their frequency
        for(Link i : links){
            System.out.println("[" + i.getCount() + "]" + i.getUrl());
        }
    }
}
