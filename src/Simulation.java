import java.util.ArrayList;
import java.util.Scanner;

public class Simulation {

    //displays positions of spaceships on 10 x 10 grid
    public static void displayWorld(ArrayList<SpaceShip> spaceShipArrayList){
        for(int i = 1; i < 11; i++){
            for(int j = 1; j < 11; j++){
                //checks all ships in array list for x and y values that both match current grid square
                int numShips = 0;
                for(int k = 0; k < spaceShipArrayList.size(); k++){
                    if(spaceShipArrayList.get(k).getXVal() == j && spaceShipArrayList.get(k).getYVal() == i){
                        //prints C and ship number for cargo ship, P and ship number for pirate ships
                        numShips++;
                        if (numShips == 1 && spaceShipArrayList.get(k) instanceof CargoShip) {
                            System.out.printf("| C%d", k+1);
                        }
                        else if( numShips == 1 && spaceShipArrayList.get(k) instanceof PirateShip){
                            System.out.printf("| P%d", k+1);
                        }
                        //if more than one ship occupies grid square, prints +
                        else{
                            System.out.printf("+");
                            break;
                        }
                    }
                }
                //prints ** for unoccupied grid squares
                if (numShips == 0){
                    System.out.printf("| ** ");
                }
                else if (numShips == 1){
                    System.out.printf(" ");
                }
            }
            //prints | at right end of this row and move to next row
            System.out.println("|\n");
        }
    }

    //calls toString for each ship in array list
    public static void displayShipInfo(ArrayList<SpaceShip> spaceShipArrayList){
        for(int i = 0; i < spaceShipArrayList.size(); i++){
            System.out.println("Ship " + (i+1) + ": " + spaceShipArrayList.get(i).toString());
        }
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        //asks user how many SpaceShips, validates that input is 1-9
        System.out.println("How many SpaceShips would you like to work with? (1-9)");
        int numSpaceShips = in.nextInt();
        while (numSpaceShips < 1 || numSpaceShips > 9){
            System.out.println("Please enter a number between 1 and 9:");
            numSpaceShips = in.nextInt();
        }

        //creates array list of SpaceShips of specified size
        ArrayList<SpaceShip> spaceShipArrayList = new ArrayList<SpaceShip>();

        //asks user for ship type (pirate or cargo) of each ship in array
        for(int i = 0; i < numSpaceShips; i++){
            System.out.println("What type of ship is this? \t(1) Cargo Ship. \t(2) Pirate Ship");
            int shipType = in.nextInt();
            while (shipType < 1 || shipType > 2){
                System.out.println("Please enter 1 or 2:");
                numSpaceShips = in.nextInt();
            }
            if (shipType == 1){
                spaceShipArrayList.add(new CargoShip());
            }
            else{
                spaceShipArrayList.add(new PirateShip());
            }
        }

        int shipChoice = 0;
        while(shipChoice != -1){
            //displays world and ship info at beginning of each loop
            displayWorld(spaceShipArrayList);
            displayShipInfo(spaceShipArrayList);

            //asks which ship to act on, validates choice
            System.out.println("Would you like to change the status of a ship? If yes, enter " +
                    "the number of the ship. If no, enter -1:");
            shipChoice = in.nextInt();
            while(shipChoice < -1 || shipChoice == 0 || shipChoice > spaceShipArrayList.size()){
                System.out.println("Enter a valid choice:");
                shipChoice = in.nextInt();
            }

            //ends simulation if choice was -1
            if(shipChoice == -1){
                break;
            }

            //asks whether to change ship type or move ship
            System.out.println("Would you like to:\n(1)  Change the type of this ship\n(2)  Move this ship");
            int option = in.nextInt();
            while(option < 1 || option > 2){
                System.out.println("Enter 1 or 2:");
                option = in.nextInt();
            }

            //if chose to change ship type, stores ship data in local variables
            if (option == 1){
                String name = spaceShipArrayList.get(shipChoice-1).getName();
                String date = spaceShipArrayList.get(shipChoice-1).getBuildDate();
                int x = spaceShipArrayList.get(shipChoice-1).getXVal();
                int y = spaceShipArrayList.get(shipChoice-1).getYVal();

                //for cargo ships, removes old ship then constructs pirate ship with old ship's data
                if (spaceShipArrayList.get(shipChoice-1) instanceof CargoShip){
                    int cargo = spaceShipArrayList.get(shipChoice-1).getCargo();
                    spaceShipArrayList.remove(shipChoice-1);
                    spaceShipArrayList.add(shipChoice-1, new PirateShip(name, date, x, y, cargo));
                }

                //for cargo ships, removes old ship then constructs pirate ship with old ship's data
                else{
                    int booty = spaceShipArrayList.get(shipChoice-1).getBooty();
                    spaceShipArrayList.remove(shipChoice-1);
                    spaceShipArrayList.add(shipChoice-1, new CargoShip(name, date, x, y, booty));
                }
            }

            //if chose to move ship, ask how far to move ship (x and y)
            if (option == 2){
                System.out.println("How far do you want to move the ship?\nEnter an x-value (negative to move left," +
                        "positive to move right):");
                int x = in.nextInt();
                System.out.println("Now enter a y-value (negative to move down, positive to move up):");
                int y = in.nextInt();

                //call individual moveShip functions for cargo and pirate ships
                if (spaceShipArrayList.get(shipChoice-1) instanceof CargoShip){
                    //y input is inverted so that negative input moves ship down, not up
                    ((CargoShip)spaceShipArrayList.get(shipChoice-1)).moveShip(x, -y);
                }
                else{
                    ((PirateShip)spaceShipArrayList.get(shipChoice-1)).moveShip(x, -y);
                }
            }
        }
    }
}