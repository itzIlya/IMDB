
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class GetInput {

    public static HashMap<String,String> getDetails(){
        HashMap<String,String> details = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = scanner.nextLine();
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter lastname:");
        String lastname = scanner.nextLine();

        details.put("name",name);
        details.put("lastname",lastname);
        details.put("username",username);
        details.put("password", password);


        return details;
    }
    public static LocalDate getBirthday(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Birth year");
        String year = scanner.nextLine();
        System.out.println("Enter Birth month in integers");
        String month = scanner.nextLine();
        System.out.println("Enter day of Birth in integers");
        String day = scanner.nextLine();
        LocalDate dateOfBirth = LocalDate.of(Integer.parseInt( year), Integer.parseInt( month),Integer.parseInt( day));
        return  dateOfBirth;
    }
    public static HashMap<String, Object> getMovieDetails(){
        HashMap<String,Object> movieDetails = new HashMap<>();
        // TO DO
        return movieDetails;
    }

    public static Rating getNewRating(Member rater){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a rating between 1 and 10");
        int rateInt = Integer.parseInt(scanner.nextLine());
        Rate rate;
        switch (rateInt) {
            case 1:
                rate = Rate.ONE;
                break;
            case 2:
                rate = Rate.TWO;
                break;
            case 3:
                rate = Rate.THREE;
                break;
            case 4:
                rate = Rate.FOUR;
                break;
            case 5:
                rate = Rate.FIVE;
                break;
            case 6:
                rate = Rate.SIX;
                break;
            case 7:
                rate = Rate.SEVEN;
                break;
            case 8:
                rate = Rate.EIGHT;
                break;
            case 9:
                rate = Rate.NINE;
                break;
            case 10:
                rate = Rate.TEN;
                break;
            default:
                return null;
                // Handle the case where rateInt is not in the range 1 to 10
                // You might throw an exception, set a default value, or handle it in some way.
        }
        return new Rating(rate, rater);
    }

    public static Review getNewReview(Member rater){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a rating between 1 and 10");
        int rateInt = Integer.parseInt(scanner.nextLine());
        Rate rate;
        switch (rateInt) {
            case 1:
                rate = Rate.ONE;
                break;
            case 2:
                rate = Rate.TWO;
                break;
            case 3:
                rate = Rate.THREE;
                break;
            case 4:
                rate = Rate.FOUR;
                break;
            case 5:
                rate = Rate.FIVE;
                break;
            case 6:
                rate = Rate.SIX;
                break;
            case 7:
                rate = Rate.SEVEN;
                break;
            case 8:
                rate = Rate.EIGHT;
                break;
            case 9:
                rate = Rate.NINE;
                break;
            case 10:
                rate = Rate.TEN;
                break;
            default:
                return null;
            // Handle the case where rateInt is not in the range 1 to 10
            // You might throw an exception, set a default value, or handle it in some way.
        }
        System.out.println("Enter review title");
        String title = scanner.nextLine();
        System.out.println("Enter review body");
        String body = scanner.nextLine();
        System.out.println("Does this review contain spoilers?");
        boolean spoilerAlert =  scanner.nextLine().equalsIgnoreCase("y");
        return new Review(rate,rater,title,spoilerAlert,body);
    }

}
