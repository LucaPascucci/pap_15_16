package lab03;

/**
 * Created by Luca on 02/04/16.
 */
public class Trader {

    private final String name;
    private final String city;

    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }

    public String getName(){
        return this.name;
    }

    public String getCity(){
        return this.city;
    }
}
