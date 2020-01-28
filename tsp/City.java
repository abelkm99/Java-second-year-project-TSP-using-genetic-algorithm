package tsp;
import java.util.*;
public class City{
    private int x;
    private int y;
    public String Name = generateRandomString(3);
    public City(int x,int y){
        this.x = x;
        this.y = y;

    }
    public double distanceFrom(City city){
        double deltaxsq = Math.pow((city.getX() - this.getX()), 2);
        double deltaysq = Math.pow((city.getY() - this.getY()), 2);
        double distance = Math.sqrt(Math.abs(deltaxsq + deltaysq));
        return distance;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public  String generateRandomString(int length) {
        String out="";
        while(length>0){
            Random r = new Random();
            int low = 69;
            int high = 67+26;
            int result = r.nextInt(high-low) + low;
            out+=(char) result;
            length--;
        }
        return out;
    }
}