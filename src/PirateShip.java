public class PirateShip extends SpaceShip implements CanMove {
    private int booty;

    //no-arg constructor calls SpaceShip's constructor plus randomly sets booty 1-100
    public PirateShip(){
        super();
        this.booty = (int) (Math.random() * 100);
    }

    //constructor for changing cargo ship to pirate ship, takes in data from old ship and passes it to new ship
    public PirateShip(String oldName, String date, int x, int y, int cargo){
        super(oldName, date, x, y);
        this.booty = cargo;
    }

    //changes x and y values, keeping them between 1 and 10 (inclusive)
    @Override
    public void moveShip(int x, int y){
        if (xVal + x < 1) {
            xVal = 1;
        }
        else if (xVal + x > 10){
            xVal = 10;
        }
        else {
            xVal += x;
        }
        if (yVal + y < 1) {
            yVal = 1;
        }
        else if (yVal + y > 10){
            yVal = 10;
        }
        else {
            yVal += y;
        }
    }

    //override returns superclass's toString plus booty
    @Override
    public String toString(){
        return (super.toString() + "\t\tBooty: " +  booty);
    }

    //returns 0 since pirate ship has no cargo
    @Override
    public int getCargo() {
        return 0;
    }

    //getter for booty
    @Override
    public int getBooty(){
        return booty;
    }
}