import java.util.ArrayList;
import java.util.Collections;

/**
 * WebCrawler takes a URL argument and potentially a depth argument from the
 * command line. WebCrawler then recursively crawls the URL to the desired
 * depth and prints out all of the links on the pages along with their
 * frequency.
 */
public class WebCrawler {

    public static void main(String[]args) throws IncorrectProtocolError,
            NegativeDepthException{

        String address = "";
        int depth = 0;
        ArrayList<Link> links = new ArrayList<Link>();

        //command line arguments
        if(args.length == 0){
            System.err.println("Usage: java WebCrawler -url " +
                    "http://example.com/ [-depth 5]");
            System.exit(0);
        }
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-url")) {
                address = args[++i];
            }
            else if(args[i].equals("-depth")){
                if(Integer.parseInt(args[++i]) >= 0){
                    depth = Integer.parseInt(args[i]);
                }
                else{
                    throw new NegativeDepthException("Depth must be a " +
                            "positive integer");
                }
            }
            else {
                System.err.print("Unknown command line argument ");
                System.err.print(args[i]);
                System.err.println(".");
                System.exit(0);
            }
        }

        //makes sure that the user actually entered an address
        if(address.equals("")){
            System.err.println("A URL must be entered");
            System.exit(0);
        }

        //checks to make sure the supplied address is the correct protocol
        if(!address.substring(0,7).equals("http://") && !address
                .substring(0,8).equals("https://")){
            throw new IncorrectProtocolError("Protocol Must be HTTP/HTTPS");
        }

        //crawls the link to the desired depth, adding the results to the
        // links array
        crawl(depth, address, links);

        //increases the count of any link that appears multiple times
        for(Link i : links){
            for(Link j : links){
                if(i.getUrl().equals(j.getUrl())){
                    i.increaseCount();
                }
            }
        }

        //an arrayList to contain the unique links
        ArrayList<Link> duplicatesRemoved = new ArrayList<Link>();

        for(Link i : links){
            if(!duplicatesRemoved.contains(i)){
                duplicatesRemoved.add(i);
            }
        }

        //sort the ArrayList so that when it is printed it will list the most
        // frequent links first
        Collections.sort(duplicatesRemoved);

        //print the links along with their frequency
        for(Link i : duplicatesRemoved){
            System.out.println("[" + i.getCount() + "]" + i.getUrl());
        }

    }

    //finds all the links on the given address, if the depth is greater
    // than zero, crawl then "crawls" the subsequent links and decrements the
    // depth until depth is equal to zero, then adds all of the found links
    // to allLinks
    public static void crawl(int depth, String address,
                             ArrayList<Link> allLinks) throws
            IncorrectProtocolError{

        URLFinder finder = new URLFinder(address);
        ArrayList<Link> links = new ArrayList<Link>(finder.getLinks());

        if(depth == 0){
            for(Link i : links)
                allLinks.add(i);
        }
        else{
            for(Link i : links){
                //only crawl if the site is HTTP or HTTPS
                if(i.getUrl().contains("http://") || i.getUrl().contains
                        ("https://")){

                    crawl(depth - 1, i.getUrl(), allLinks);
                }
            }
        }
    }
}