import java.util.Comparator;

public class ReviewRatingComparator implements Comparator<Hotel> {
    public int compare(Hotel first, Hotel second) {
        int num = first.getStars() - second.getStars();
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
