import java.util.Scanner;

abstract class SpaceShip {
    //attributes
    private String name;
    private String buildDate;
    public int xVal;
    public int yVal;

    //no-arg constructor calls setName(), setBuildDate(), and randomizes x and y values
    public SpaceShip(){
        this.name = setName();
        this.buildDate = setBuildDate();
        this.xVal = (int) (Math.random() * 9) + 1;
        this.yVal = (int) (Math.random() * 9) + 1;
    }

    //constructor for changing ship type, passes data of old ship to new ship
    public SpaceShip(String oldName, String date, int x, int y){
        this.name = oldName;
        this.buildDate = date;
        this.xVal = x;
        this.yVal = y;
    }

    //setter for name, prompts user for input
    public String setName(){
        Scanner in = new Scanner(System.in);
        System.out.println("What is the name of this Ship?");
        return in.nextLine();
    }

    //getter for name
    public String getName(){
        return name;
    }

    //setter for buildDate, prompts user for input
    public String setBuildDate(){
        Scanner in = new Scanner(System.in);
        System.out.println("When was this ship built?");
        return in.nextLine();
    }

    //getter for buildDate
    public String getBuildDate(){
        return buildDate;
    }

    //setter for xVal to move ship horizontally
    public void setXVal(int x){
        xVal += x;
    }

    //getter for xVal
    public int getXVal(){
        return xVal;
    }

    //setter for yVal to move ship vertically
    public void setYVal(int y){
        yVal += y;
    }

    //getter for yVal
    public int getYVal(){
        return yVal;
    }

    //toString for SpaceShip, returns name and buildDate
    public String toString(){
        return (" SS " + name + "\t\t Date of Construction: " + buildDate);
    }

    //abstract getters
    public abstract int getCargo();
    public abstract int getBooty();
}