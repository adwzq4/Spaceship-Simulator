public class CargoShip extends SpaceShip implements CanMove {
    private int cargo;

    //no-arg constructor calls SpaceShip's constructor plus randomly sets cargo 1-100
    public CargoShip(){
        super();
        this.cargo = (int) (Math.random() * 100);
    }

    //constructor for changing pirate ship to cargo ship, takes in data from old ship and passes it to new ship
    public CargoShip(String oldName, String date, int x, int y, int booty){
        super(oldName, date, x, y);
        this.cargo = booty;
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

    //override returns superclass's toString plus cargo
    @Override
    public String toString(){
        return (super.toString() + "\t\tCargo: " +  cargo);
    }

    //getter for cargo
    @Override
    public int getCargo(){
        return cargo;
    }

    //returns 0 since CargoShip has no booty
    @Override
    public int getBooty() {
        return 0;
    }
}