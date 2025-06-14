/*****************************************************    
 * Algorithm Design Block
 
    Algorithm Title: Locker doors algorithm

    Logical Steps:
        Step 1: Declare a variable to hold comparison count
        Step 2: Declare a variable to hold open lockers count
        Step 3: Declare a variable to hold closed lockers count initially equal to list size
        Step 4: Add 'n' locker objects to array list (amount 'n' specified by user)
        Step 5: Set up a loop going up to 'n' on the list (i=1 to n)
        Step 6: Set up a nested loop from i-1 to n-1 where its intervals are incremented by +i
        Step 7: Change state of locker object at every ith position within nested loop. Increment comparison count. Increment/decrement open or closed lockers counts
        Step 8: Print number of lockers
        Step 9: Print open locker count
        Step 10: Print closed locker count
        Step 11: print comparison count

    Pseudocode Synatx:

    Algorithm: Parse through n lockers i=n times and close the ith locker(s) on each iteration
    Input: non-empty List (Lockers) of locker objects
    Output: Lockers size, closed lockers, open lockers, comparison count

    Begin
        comparisons <- 0;
        closed = n;
        open = o;

        for i <- 1 to Lockers.size
            for j <- (i-1) to Lockers.size-1 do
                if (Lockers[j] state <- 'closed')
                    closed--
                    open++
                    Lockers[j] change state
                else 
                    closed++
                    open--
                    Lockers[j] change state

                comparison++
                j+i
            End loop
        End loop
        print Lockers.size;
        print open;
        print closed;
        print comparisons;
    End;


*****************************************************/

import java.util.ArrayList;
public class LockerDoors{
    public static void main(String[] args){
        java.util.ArrayList<Locker> lockers = new java.util.ArrayList<>();
        java.util.Scanner read = new java.util.Scanner(System.in);

        boolean menuClose = false;
        while(!menuClose){
            try {
                System.out.print("""
                                 
                                    -----------------MAIN MENU--------------
                                    1. Read number of lockers (integer value)
                                    2. Run algorithm and display output
                                    3. Exit program

                                    Enter option number:\s""");
                int input = Integer.parseInt(read.nextLine());

                // in case user inputs an option not on menu
                if(input < 1 || input > 3){
                    System.out.println("That is not a valid menu option. Please choose between options 1-3.");
                    continue; 
                }

                switch(input){
                    case 1:
                        // clearing list for new locker count
                        lockers.clear();
                        // asking for locker count
                        System.out.print("\nHow many lockers would you like?: ");
                        int lockerCount = Integer.parseInt(read.nextLine());
                        System.out.printf("Adding %d lockers...%n%n", lockerCount);

                        // populating array list
                        for(int i = 0; i < lockerCount; i++){
                            Locker newLocker = new Locker();
                            lockers.add(newLocker);
                        }
                        break;

                    case 2:
                        // algorithm implementation
                        lockerAlgorithm(lockers);
                        break;
                    case 3:
                        System.out.print("Exiting...");
                        read.close();
                        menuClose = true;
                        break;
                }
            } catch(NumberFormatException e){
                // in case user inputs an option that A) isn't on the menu, or B) isn't an integer
                System.out.println("That is not a valid menu option. Please choose between options 1-3.");
            }
        }
    }

    public static void lockerAlgorithm(ArrayList<Locker> lockers){
        //variable declarations
        int comparisons = 0;
        int openLockers = 0;
        int closedLockers = lockers.size();

        // running through lockers 1 to n
        for(int i = 1; i<lockers.size(); i++){
            // interacting with every 'ith' locker
            for(int j = (i-1); j < lockers.size()-1; j+=i){
                if(lockers.get(j).state == "closed"){ // comparison
                    lockers.get(j).setState("open");
                    closedLockers--;
                    openLockers++;
                } else{
                    lockers.get(j).setState("closed");
                    closedLockers++;
                    openLockers--;
                }
                comparisons++;
            }
        }
        // print output in uniform format
        System.out.printf(
            "%n%-20s %5d%n%-20s %5d%n%-20s %5d%n%-20s %5d%n%n",
            "Number of lockers:", lockers.size(),
                    "Open lockers:", openLockers,
                    "Closed lockers:", closedLockers,
                    "Comparisons:", comparisons);
        for (int i = 1; i < lockers.size(); i++) {
            System.out.printf("Locker %d: %s%n", i, lockers.get(i - 1).getState());
        }
    }
}

class Locker{
    String state = " ";

    Locker(){
        state="closed";
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
