// Name: Tyler Stroud
// Class: CS 4306
// Term: Summer 2025
// Instructor: Dr. Haddad
// Assignment: 01
// IDE: IntelliJ

/*****************************************************
 * Algorithm Design Block
 * 
 * Algorithm Title: Anagram check for string variables
 * 
 * Logical Steps:
 * 
 *      Step 1: Trim white space from both string variables and declare them as new strings
 *      Step 2: Declare boolean variable to hold anagram determinant
 *      Step 3: Declare a variable to hold comparisons count
 *      Step 4: Check if both trimmed strings are equal size. Return false if not. Increment comparison. End.
 *      Step 5: Declare stack to hold trimmed string2 characters
 *      Step 6: Parse characters from second string into a stack
 *      Step 7: Set up loop to check for character comparisons
 *      Step 8: Pop stack and compare each popped char with characters within first string. Increment comparisons. Remove compared char from trimmed string1
 *      Step 8: Print first string
 *      Step 9: Print second string
 *      Step 10: Print comparisons outcome
 *      Step 11: Print number of comparisons
 * 
 * Pseudocode Syntax:
 * 
 * Algorithm: Compare characters from one string to another and determine if both strings have the exact same character variables
 * Input: Two strings
 * Output: Boolean comparison conclusion, comparisons
 * 
 * Begin
 *      trimString1 <- String1 trim
 *      trimString2 <- String2 trim
 *      isAnagram <- true
 *      Comparisons <- 0
 * 
 *      if(trimString1 size doesn't equal trimString2 size)
 *          Comparisons = 1;
 *          isAnagram <- false;
 *          return isAnagram;
 * 
 *      else
 *          new charStack 
 *          for all characters in trimString2 do
 *              charStack.add(character);
 *          End loop
 * 
 *          while charStack is NOT empty do
 *              comparisons + 1
 *              if String1 contains charStack.peek() {
 *                  removedChar <- charStack.pop();
 *                  index <- index of removedChar in trimString1
 *                  String1 <- trimString1.remove(index);
 *              }
 *              else
 *                  isAnagram <-false;
 *                  break;
 *          End loop
 *      print String 1;
 *      pring String 2;
 *      print comparisons;
 *      print isAnagram;
 * End;
 * 
*****************************************************/

public class AnagramsCheck {
    public static void main(String[] args){
        java.util.Scanner read = new java.util.Scanner(System.in);
        String string1 = " ";
        String string2 = " ";
        boolean runMenu = true;
        while(runMenu){
            try{
                System.out.printf("""

                            -----------------MAIN MENU--------------
                            1. Read input string1 and string2
                            2. Run algorithm and display output
                            3. Exit program

                            Enter option number:\s""");
                int input = Integer.parseInt(read.nextLine());

                if(input < 0 || input > 3){
                    System.out.println("Invalid menu option. Please choose options 1-3");
                    continue;
                }

                switch(input){
                    // read user input
                    case 1:
                        System.out.print("\nEnter first string: ");
                        string1 = read.nextLine();
                        System.out.print("Enter second string: ");
                        string2 = read.nextLine();
                        break;
                    
                    // algorithm implementation
                    case 2:
                        checkAnagram(string1, string2);
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        read.close();
                        runMenu=false;
                }
            }catch(NumberFormatException e){
            System.out.println("Invalid menu option. Please choose options 1-3");
            }
        }
    }

    public static void checkAnagram(String string1, String string2){
        // trim strings of white all space
        String trimString1 = string1.replaceAll("\\s+","");
        String trimString2 = string2.replaceAll("\\s+","");
        // declarations to be used
        boolean isAnagram = true;
        int comparisons = 0;

        // comparing string sizes
        if(trimString1.length() != trimString2.length()){
            comparisons++; // +1 comparison
            isAnagram = false;
        }
        else {
            // declaring and populating char stack with characters from string2
            java.util.Stack<Character> charStack = new java.util.Stack<>();
            for(Character ch : trimString2.toCharArray()){
                charStack.add(ch);
            }

            // parsing String1 for the top of charStack
            while(!charStack.isEmpty()){
                // count comparison
                comparisons++;
                // match found
                if(trimString1.toLowerCase().contains(String.valueOf(charStack.peek()).toLowerCase())) {
                    // finding where the similar character is in string1 (case-insensitive)
                    char removedChar = charStack.pop();
                    int index = trimString1.toLowerCase().indexOf(Character.toLowerCase(removedChar));

                    // removing character from string1 and appending string1 
                    if (index != -1){
                        trimString1 = trimString1.substring(0, index) + trimString1.substring(index+1);
                    }

                } 
                // no match found
                else {
                    isAnagram = false;
                    break;
                }
            }
        }

        // print results
        if(isAnagram){
            System.out.printf(
                "%-20s  %s%n%-20s  %s%n%-20s  %s%n%-20s  %d%n%n",
                "String 1:", string1,
                        "String 2:", string2,
                        "Output:", "Strings are anagrams.",
                        "Comparisons:", comparisons
            );
        } else {
            System.out.printf(
                "%-20s  %5s%n%-20s  %5s%n%-20s  %5s%n%-20s  %d%n%n",
                "String 1:", string1,
                        "String 2:", string2,
                        "Output:", "Strings are not anagrams.",
                        "Comparisons:", comparisons
                        );
        }

    }
}
