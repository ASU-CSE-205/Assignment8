// Assignment: 
// Name:
// StudentID:
// Lecture:
// Description:

import java.io.Serializable;
import java.util.ArrayList;

public class ReviewManager implements Serializable {
    // The serialVersionUID is used to verify compatibility of senders and
    // receivers. See the document for more details:
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
    private static final long serialVersionUID = 205L;

    ArrayList<Hotel> reviewList;

    public ReviewManager() {
        reviewList = new ArrayList<>();
    }

    /**
     * Add a Hotel object to the reviewList and return true if such an object
     * is added successfully. Otherwise, return false. Two hotels are
     * considered duplicated if they have exactly the same hotel name and
     * hotel type.
     * 
     * @param  hotelName     the name of the hotel
     * @param  stars         the number of stars for the hotel
     * @param  review        the hotel review
     * @param  priceRange    the integer price range
     * @param  type          the hotel's type
     * @param  location      the hotel location (street address)
     * @param  topFeature    most famous feature of the hotel
     * @return               true if the operation is successful; false otherwise
     */
    
    //Add a Hotel object to the reviewList and return true if such an object is added successfully
    public boolean addReview(String hotelName, int stars, String review, String priceRange, String type, String location, String topFeature) {
        if (hotelExists(hotelName, location) == -1) {
            int price = priceRange.length();
            HotelType newType = new HotelType(type, topFeature);
            Hotel newHotel = new Hotel(hotelName, stars, review, price, location, newType);
            reviewList.add(newHotel);
            return true;
        }
        return false;
    }

    //Search for a Hotel object in reviewList by both its name and location
    //Return the index of the object if it is found, -1 otherwise
    public int hotelExists(String hotelName, String location) {
        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).getHotelName().equalsIgnoreCase(hotelName) && reviewList.get(i).getLocation().equalsIgnoreCase(location)) {
                return i;
            }
        }
        return -1;
    }

    //Search for a hotel type in Hotel objects int he reivewList that has provided hotelType
    //Return the index of the Class object if found, -1 otherwise
    public ArrayList<Integer> hotelTypeExists(String hotelType) {
        ArrayList<Integer> checkHotelType = new ArrayList<Integer>();
        for (int i = 0; i < reviewList.size(); i++) {
            if (reviewList.get(i).getHotelType().getType().equalsIgnoreCase(hotelType)) {
                checkHotelType.add(i);
            }
        }
        if (checkHotelType.size() == 0) {
            checkHotelType.add(-1);
        }
        return checkHotelType;
    }

    //Getter method
    public Hotel getHotel(int index) {
        return reviewList.get(index);
    }

    //Remove a Hotel object with the given hotel name and location
    public boolean removeReview(String hotelName, String location) {
        int checkReview = hotelExists(hotelName, location);
        if (checkReview == -1) {
            return false;
        }
        else { 
            reviewList.remove(checkReview);
            return true;
        }
    }

    //Sort the reviewList by rating then hotel name in alphabetical order
    public void sortByRating() {
        Sorts.sort(reviewList, new ReviewRatingComparator());
    }

    //Sort the reviewList by hotel's type in the alphabetical order of the type of the hotel
    //then the hotel name 
    public void sortByHotelType() {
        Sorts.sort(reviewList, new ReviewHotelTypeComparator());
    }

    //List all Hotel objects in reviewList
    public String listReviews() {
        String output = "";
        for (int i = 0; i < reviewList.size(); i++) {
            output += reviewList.get(i).toString();
        }
        return output;
    }

    //Close the Hotel management system by clearing the reviewList
    public void closeReviewManager() {
        reviewList.clear();
    }

}
