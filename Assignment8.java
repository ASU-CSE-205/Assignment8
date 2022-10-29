// Assignment: 
// Name:
// StudentID:
// Lecture:
// Description:

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class Assignment8 {
    public static void main(String[] args) {
        // Menu options
        char inputOpt = ' ';
        String inputLine;
        // Hotel and Hotel Type information
        String hotelName, hotelType;
        String review = null, location, topFeature, priceRange;

        int rating;
        // Output information
        String outFilename, inFilename;
        String outMsg, inMsg;
        // Hotel manager
        ReviewManager reviewManager = new ReviewManager();
        // Operation result
        boolean opResult;     
        
        try {
            printMenu();
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new Hotel Review
                        System.out.print("Please enter the hotel information:\n");
                        System.out.print("Enter the hotel name:\n");
                        hotelName = stdin.readLine().trim();
                        System.out.print("Enter the review:\n");
                        review = stdin.readLine().trim();
                        System.out.print("Enter the price range:\n");
                        priceRange = stdin.readLine().trim();
                        System.out.print("Enter the rating:\n");
                        rating = Integer.parseInt(stdin.readLine().trim());
                        System.out.print("Enter the hotel type:\n");
                        hotelType = stdin.readLine().trim();
                        System.out.print("Enter the location:\n");
                        location = stdin.readLine().trim();
                        System.out.print("Enter the hotel's top feature\n");
                        topFeature = stdin.readLine().trim();
                        
                        /*********************************************************************
                        * Complete the code by calling the addReview method.                 *
                        * If the review has been added successfully, show                    *
                        * "Hotel added\n" on screen, otherwise "Hotel NOT added\n" *
                        **********************************************************************/
                        boolean addReview = reviewManager.addReview(hotelName, rating, review, priceRange, hotelType, location, topFeature);
                        if (addReview) {
                            System.out.print("Hotel added\n");
                        }
                        else {
                            System.out.print("Hotel NOT added\n");
                        }
                        break;

                    case 'D': // Search a Hotel
                        System.out.print("Please enter the Hotel name to search:\n");
                        hotelName = stdin.readLine().trim();
                        System.out.print("Please enter the hotel's location':\n");
                        location = stdin.readLine().trim();
                        
                        /*********************************************************************
                        * Complete the code. If a hotel review exists, print            *
                        * "Hotel found. Here's the review:\n"                           *
                        * Otherwise, print "Hotel not found. Please try again\n"        *
                        **********************************************************************/    
                        int checkHotel = reviewManager.hotelExists(hotelName, location);
                        if (checkHotel == -1) {
                            System.out.print("Hotel not found. Please try again\n");
                        }
                        else {
                            System.out.print("Hotel found. Here's the review:\n");
                            System.out.println(reviewManager.getHotel(checkHotel).getReview());
                        }
                        break;


                    case 'E': // Search for a Hotel Type
                        System.out.print("Please enter the hotel type to search:\n");
                        hotelType = stdin.readLine().trim();
                        
                        /*******************************************************************************
                        * Complete the code. If a hotel type is found, show on the screen how many       *
                        * hotels match that type by printing                                  *
                        * "%s Hotels matching %s type were found:\n" followed by the reviews. *
                        * Otherwise, print "Hotel Type: %s was NOT found\n"                              *
                        ******************************************************************************/   
                        ArrayList<Integer> searchForType = new ArrayList<Integer>();
                        searchForType = reviewManager.hotelTypeExists(hotelType);
                        if (searchForType.get(0) == -1) {
                            System.out.print(String.format("Hotel Type: %s was NOT found\n", hotelType));
                        }
                        else { 
                            System.out.print(String.format("%s Hotels matching %s type were found:\n", searchForType.size(), hotelType));
                            for (int i = 0; i < searchForType.size(); i++) {
                                System.out.print(reviewManager.reviewList.get(searchForType.get(i)).toString());
                            }
                        }
                        break;
   
                    case 'L': // List hotel's reviews
                        System.out.print("\n" + reviewManager.listReviews() + "\n");
                        break;
                        
                     /******************************************************************************************
                     * Complete the code by adding two cases:                                                  *
                     * case 'N': sorts the hotel reviews by rating and prints "sorted by rating\n"        *
                     * case 'P': sorts the hotel reviews by hotel type and prints "sorted by hotel type\n" *
                     ******************************************************************************************/                        
                    case 'P' : //Sorts reviews by hotel type
                        reviewManager.sortByHotelType();
                        System.out.print("Sorted by hotel type\n");
                        break;
                        
                    case 'N': //Sort reviews by rating
                        reviewManager.sortByRating();
                        System.out.print("Sorted by rating\n"); 
                        break;
                        
                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a review
                        System.out.print("Please enter the hotel name of the review to remove:\n");
                        hotelName = stdin.readLine().trim();
                        System.out.print("Please enter the location to remove:\n");
                        location = stdin.readLine().trim();
                        
                        /*******************************************************************************
                        * Complete the code. If a review for a certain hotel at a given location  *
                        * is found, remove the review and print that it was removed. Otherwise         *
                        * print that it was NOT removed.                                               * 
                        *******************************************************************************/
                        boolean removeReview = reviewManager.removeReview(hotelName, location);
                        if (removeReview == false) {
                            System.out.print(hotelName + "," + location + " was NOT removed\n");
                        }
                        else {
                            System.out.print(hotelName + "," + location + " was removed\n");
                        }
                        break;
                        
                        
                    case 'T': // Close reviewList
                        reviewManager.closeReviewManager();
                        System.out.print("Hotel management system was reset\n");
                        break;

                    case 'U': // Write hotel names and reviews to a text file
                        System.out.print("Please enter a file name that we will write to:\n");
                        outFilename = stdin.readLine().trim();
                        System.out.print("Please enter the name of the hotel:\n");
                        hotelName = stdin.readLine().trim();
                        System.out.println("Please enter a review to save locally:\n");
                        review = stdin.readLine().trim();
                        outMsg = hotelName + "\n" + review + "\n";
                        
                        /*************************************************************************************
                        * Add a try and catch block to write the string outMsg into the user-specified file  *
                        * Then, print in the screen that the file " is written\n"                            *
                        * In case of an IO Exception, print "Write string inside the file error\n"           *                                                             
                        *************************************************************************************/                    
                        try {
                            FileWriter writeText = new FileWriter(outFilename);
                            writeText.write(outMsg);
                            writeText.flush();
                            writeText.close();
                            System.out.print(outFilename + " is written\n");
                        } 
                        catch (IOException e) {
                            System.out.print("Write string inside the file error\n");
                        }
                        break;

                    case 'V': // Read strings from a text file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        /******************************************************************************************
                        * Add a try and catch block to read from the above text file. Confirm that the file       *
                        * " was read\n" and then print "The contents of the file are:\n" followed by the contents *
                        * In case of an IO Exception, print "Read string from file error\n"                       *  
                        * In case of a file not found exception, print that the file " was not found\n"           *                                                             
                        ******************************************************************************************/ 
                        try {
                            BufferedReader read = new BufferedReader(new FileReader(inFilename));
                            String fromText = read.readLine();
                            System.out.print(inFilename = " was read\n");
                            System.out.print("The contents of the file are:\n" + fromText);
                            read.close();
                        }
                        catch (FileNotFoundException ex) {
                            System.out.print(inFilename + " was not found\n");
                        } 
                        catch (IOException e) {
                            System.out.print("Read string from file error\n");
                        }
                        break;
 
                    case 'W': // Serialize ReviewManager to a data file
                        System.out.print("Please enter a file name to write:\n");
                        outFilename = stdin.readLine().trim();
                        
                        /****************************************************************************
                        * Add a try and catch block to serialize ReviewManager to a data file.      *
                        * Catch two exceptions and print the corresponding messages on the screen:  *
                        * "Not serializable exception\n"                                            *
                        * "Data file written exception\n"                                           * 
                        ****************************************************************************/                    
                        try {
                        FileOutputStream out = new FileOutputStream(outFilename);
                        ObjectOutputStream objOut = new ObjectOutputStream(out);
                        objOut.writeObject(reviewManager);
                        objOut.close();
                        }
                        catch (NotSerializableException e) {
                            System.out.print("Not serializable exception\n");
                        }
                        catch(IOException ex) {
                            System.out.print("Data file written exception\n");
                        }
                        break;

                    case 'X': // Deserialize ReviewManager from a data file
                        System.out.print("Please enter a file name which we will read from:\n");
                        inFilename = stdin.readLine().trim();
                        
                        /*****************************************************************************
                         * Add a try and catch block to deserialize ReviewManager from a data file.  *
                         * Add a try and catch block to deserialize ReviewManager from a data file.  *
                         * Catch three exceptions and print the corresponding messages on the screen:*
                         * "Class not found exception\n"                                             *
                         * "Not serializable exception\n"                                            * 
                         * "Data file read exception\n"                                              *
                         ****************************************************************************/  
                        try {
                            ReviewManager deserialized = new ReviewManager();
                            FileOutputStream out = new FileOutputStream(inFilename);
                            ObjectOutputStream objOut = new ObjectOutputStream(out);
                            objOut.writeObject(deserialized);
                            objOut.close();
                            out.close();
                        }
                        catch (InvalidClassException e) {
                            System.out.print("Class not found exception\n");
                        }
                        catch (NotSerializableException e) {
                            System.out.print("Not serializable exception\n");
                        }
                        catch (IOException e) {
                            System.out.print("Class not found exception\n");
                        }
                        break;


                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
    }

    public static void printMenu() {
        System.out.println("Welcome to HotelAdvisor! ");
        System.out.println("Find or post reviews for your favorite (and not so favorite) hotels.");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
                + "D\t\tSearch for a hotel\n" + "E\t\tSearch for a hotel type\n"
                + "L\t\tList all reviews\n" + "N\t\tSort by stars\n" + "P\t\tSort by hotel type\n"
                + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
                + "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
                + "W\t\tSave reviews to a file\n"
                + "X\t\tUpload reviews from a file\n"
                + "T\t\t(admin) reset database\n"
                + "?\t\tDisplay Help\n");
    }
}
