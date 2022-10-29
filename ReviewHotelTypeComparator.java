import java.util.Comparator;

public class ReviewHotelTypeComparator implements Comparator<Hotel>{
    public int compare(Hotel first, Hotel second) {
        int num = first.getHotelType().getType().compareTo(second.getHotelType().getType()); 
        if (num == 0) {
            num = first.getPriceRange() - second.getPriceRange();
        }
        if (num == 0) {
            num = first.getHotelName().compareTo(second.getHotelName());
        }
        if (num == 0) {
            num = first.getLocation().compareTo(second.getLocation());
        }
        if (num == 0) {
            num = first.getReview().compareTo(second.getReview());
        }
        return num;
    }
}
