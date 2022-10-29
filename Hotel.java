import java.io.Serializable;


public class Hotel implements Serializable {
    private static final long serialVersionUID = 205L;
    private String hotelName;
    private int stars;
    private String review;
    private int priceRange;
    private String location;
    private HotelType hotelType;

    // Instantiates a new Hotel object
    public Hotel(String hotelName, int stars, String review, int priceRange, String location, HotelType hotelType) {
        this.hotelName = hotelName;
        this.stars = stars;
        this.review = review;
        this.priceRange = priceRange;
        this.location = location;
        this.hotelType = hotelType;
    }
    
    //Returns hotelName
    public String getHotelName() {
        return hotelName;
    }
    
    //Returns stars
    public int getStars() {
        return stars;
    }

    //Returns priceRange
    public int getPriceRange() {
        return priceRange;
    }

    //Returns location
    public String getLocation() {
        return location;
    }

    //Returns review
    public String getReview() {
        return review;
    }

    //Returns hotelType
    public HotelType getHotelType() {
        return hotelType;
    }

    public String toString() {
        String starString = "";
        String priceString = "";
        for (int i = 0; i < getStars(); i++) {
            starString += "*";
        }
        for (int i = 0; i < getPriceRange(); i++) {
            priceString += "$";
        }
        return (hotelName + " hotel\n" + starString + "\t\t" + priceString + "\n" + hotelType.toString() + "Location: " + location + "\n" + "Review:\t" + review + "\n\n");
    }
}
