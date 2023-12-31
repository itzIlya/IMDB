import javax.xml.stream.FactoryConfigurationError;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class DBLists {
    private static ArrayList<Person> personArrayList = new ArrayList<>();
    private static ArrayList<Movie> movieArrayList = new ArrayList<>();
    private static ArrayList<Forum> forumArraylist = new ArrayList<>();
    private static ArrayList<Report> unhandledReports = new ArrayList<>();
    private static ArrayList<Report> handledReports = new ArrayList<>();
    private static ArrayList<Suggestion> handledSuggestions = new ArrayList<>();
    private static ArrayList<Suggestion> unhandledSuggestions = new ArrayList<>();


    public static void initialSetup(){
        personArrayList.add(new Person("Johny", "sins"));
        personArrayList.add(new Person("Hans", "Zimmer"));
        personArrayList.add(new Member("itzilya",
                String.valueOf("password1".hashCode()),
                AccessLevel.ADMIN,
                "ilya",
                "atashsoda"));
        personArrayList.add(new Member("gangeditor",
                String.valueOf("password2".hashCode()),
                AccessLevel.EDITOR,
                "gang",
                "editor"));
        Movie movie1 = new Movie("The grand budapest hotel","Drama", LocalDate.of(2000, Month.APRIL,20),"english");
        movieArrayList.add(movie1);
        Movie movie2 = new Movie("V for vendetta","Action-Drama", LocalDate.of(1998, Month.JUNE,12),"english");
        movieArrayList.add(movie2);


    }

    public static ArrayList<Suggestion> getHandledSuggestions() {
        return handledSuggestions;
    }

    public static ArrayList<Suggestion> getUnhandledSuggestions() {
        return unhandledSuggestions;
    }

    public static boolean addToMovieArrayList(Movie newMovie) {
        if(newMovie != null) {
            movieArrayList.add(newMovie);
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean removeFromMovieArrayList(Movie movie){
        if(movie != null){
            if (movieArrayList.contains(movie)){
                movieArrayList.remove(movie);
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }

    public static boolean addToPersonArrayList(Person person) {
        if(person != null) {
            personArrayList.add(person);
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean removeFromPersonArrayList(Person person){
        if(person != null){
            if (personArrayList.contains(person)){
                personArrayList.remove(person);
                return true;
            }
            else {
                return false;
            }
        }
        else{
            return false;
        }
    }
    public static ArrayList<Person> getPersonArrayList() {
        return personArrayList;
    }
    public static ArrayList<Movie> getMovieArrayList() {
        return movieArrayList;
    }

    public static ArrayList<Report> getUnhandledReports() {
        return unhandledReports;
    }

    public static ArrayList<Report> getHandledReports() {
        return handledReports;
    }
    public static boolean addToUnhandledReports(Report report){
        if(report !=null) {
            unhandledReports.add(report);
            return true;
        }
        else {
            System.out.println("Report instance = NULL or report has already been handled");
            return false;
        }
    }
    public static boolean removeFromUnhandledReports(Report report){
        if(report != null && unhandledReports.contains(report)){
            unhandledReports.remove(report);
            return true;
        }
        else {
            System.out.println("Report instance = NULL or report not in List");
            return false;
        }
    }

    public static boolean addToHandledReports(Report report){
        if(report !=null) {
            handledReports.add(report);
            return true;
        }
        else {
            System.out.println("Report instance = NULL or report has already been handled");
            return false;
        }
    }
    public static boolean addToUnhandledSuggestions(Suggestion suggestion){
        if(suggestion !=null) {
            unhandledSuggestions.add(suggestion);
            return true;
        }
        else {
            System.out.println("suggestion instance = NULL or report has already been handled");
            return false;
        }
    }
    public static boolean removeFromUnhandledSuggestions(Suggestion suggestion){
        if(suggestion != null && unhandledSuggestions.contains(suggestion)){
            unhandledSuggestions.remove(suggestion);
            return true;
        }
        else {
            System.out.println("Suggestion instance = NULL or report not in List");
            return false;
        }
    }

    public static boolean addToHandledSuggestions(Suggestion report){
        if(report !=null) {
            handledSuggestions.add(report);
            return true;
        }
        else {
            System.out.println("Report instance = NULL or report has already been handled");
            return false;
        }
    }

    public static ArrayList<Movie> searchMovie(LocalDate fromDate, LocalDate toDate, String search, int ratingFrom, int ratingTo, String genre){

        ArrayList<Movie> result = new ArrayList<>();
        if (!search.equals("")) {
            Optional<Movie> foundBasedOnUniqueText = movieArrayList.stream().filter(movie -> movie.getTitle().equalsIgnoreCase(search)).findFirst(); // find kardan ye movie bar asase specific title
            foundBasedOnUniqueText.ifPresent(result::add);
            ArrayList<String> searchSubstrings = new ArrayList<>(Arrays.asList(search.split(" ")));
            for (String substring : searchSubstrings) { // Miad bar asase tak take substring ha check mikone
                Optional<Movie> mov = movieArrayList.stream().filter(movie -> movie.getTitle().toLowerCase().contains(substring.toLowerCase())).findFirst();
                mov.ifPresent(result::add); // Ya inke mishod nevesht : mov.ifpresent(movie -> result.add(movie));
            }
            for(Movie movie : result){
                if (movie.getReleaseDate().isBefore(fromDate) || movie.getReleaseDate().isAfter(toDate) || movie.getAverageRating() <ratingFrom || movie.getAverageRating() > ratingFrom ){
                    result.remove(movie);
                }
                else if(!genre.equals("")){
                    if(!movie.getGenre().equalsIgnoreCase(genre)){
                        result.remove(movie);
                    }
                }
            }
            return result;
        }

        else{
            for(Movie movie : movieArrayList){
                boolean bool1 = movie.getReleaseDate().isAfter(fromDate);
                boolean bool2 = movie.getReleaseDate().isBefore(toDate);
                double here = movie.getAverageRating();
                boolean bool3 = movie.getAverageRating() >=ratingFrom;
                boolean bool4 = movie.getAverageRating() <= ratingTo;
                boolean bool5 = movie.getAverageRating()==0;
                if (movie.getReleaseDate().isAfter(fromDate) && movie.getReleaseDate().isBefore(toDate) && ((movie.getAverageRating() >=ratingFrom && movie.getAverageRating() <= ratingTo) || movie.getAverageRating()==0)){
                    result.add(movie);
                }
                if(!genre.equals("")){
                    if(movie.getGenre().equalsIgnoreCase(genre)){
                        result.add(movie);
                    }
                }
            }
            return result;
        }
    }

    public static ArrayList<Forum> getForumArraylist() {
        return forumArraylist;
    }

    public static void setForumArraylist(ArrayList<Forum> forumArraylist) {
        DBLists.forumArraylist = forumArraylist;
    }

    public static Optional<Person> searchPerson(String search){


        if (!search.equals("")) {
            Optional<Person> foundBasedOnUniqueText = personArrayList.stream().filter(person -> (person.getName() + " " + person.getLastName()).equalsIgnoreCase(search)).findFirst(); // find kardan ye movie bar asase specific title
            if(foundBasedOnUniqueText.isPresent()){
                return foundBasedOnUniqueText;
            }
            ArrayList<String> searchSubstrings = new ArrayList<>(Arrays.asList(search.split(" ")));
            for (String substring : searchSubstrings) { // Miad bar asase tak take substring ha check mikone
                Optional<Person> per = personArrayList.stream().filter(person -> (person.getName() + " " + person.getLastName()).toLowerCase().contains(substring.toLowerCase())).findFirst();
                if(per.isPresent()){
                    return per;
                }
            }
        }
        return Optional.empty();

    }

}
