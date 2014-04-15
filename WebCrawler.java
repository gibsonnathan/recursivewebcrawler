import java.util.ArrayList;
/**
 *
 *
 *
 */
public class WebCrawler {

    public static void main(String[]args) throws IncorrectProtocolError,
            NegativeDepthException{

        String address = "";
        int depth = 0;

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
        crawl(depth, address);
    }

    //recursively crawls the links in the address until the depth is equal to
    // zero
    public static void crawl(int depth, String address) throws
            IncorrectProtocolError{



        URLFinder finder = new URLFinder(address);
        ArrayList<Link> links = new ArrayList<Link>(finder.getLinks());

        if(depth == 0){
            for(Link i : links){
                System.out.println(i);
            }
        }
        else{
            for(Link i : links){
                if(i.getUrl().contains("http")){
                    crawl(depth - 1, i.getUrl());
                }
            }
        }
    }
}