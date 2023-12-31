import Exceptions.DuplicateReviewOrRatingInMovieException;
import Exceptions.GuestAccessRefusedException;

import javax.print.attribute.standard.PrinterMakeAndModel;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Movie {
    private String title;
    private String plotSummary;
    private ArrayList<File> posters;
    private ArrayList<File>trailers;
    private String Genre;
    private LocalDate releaseDate;
    private String language;
    private ArrayList<RoleInMovie> roleInMovies;
    private ArrayList<TriviaQuoteGoof> trivia;
    private ArrayList<TriviaQuoteGoof> quotes;
    private ArrayList<TriviaQuoteGoof> goofs;
    private ArrayList<SoundTrack> soundTracks;
    private ArrayList<Rating> ratings;

    public Movie(String title,
                 //String plotSummary,
                 //ArrayList<File> posters,
                 //ArrayList<File> trailers,
                 String genre,
                 LocalDate releaseDate,
                 String language
                 //ArrayList<Role> roles,
                 //ArrayList<Movie> suggestedByGenre,
                 //ArrayList<Movie> suggestedBasedOnDirector,
                 //ArrayList<Movie> suggestedByWriter,
                 //ArrayList<TriviaQuoteGoof> trivia,
                 //ArrayList<TriviaQuoteGoof> quotes,
                 //ArrayList<TriviaQuoteGoof> goofs,
                 //ArrayList<SoundTrack> soundTracks
    ){
        this.title = title;
        this.plotSummary = null;
        this.posters = new ArrayList<>();
        this.trailers = new ArrayList<>();
        this.Genre = genre;
        this.releaseDate = releaseDate;
        this.language = language;
        this.roleInMovies = new ArrayList<>();
        this.trivia = new ArrayList<>();
        this.quotes = new ArrayList<>();
        this.goofs = new ArrayList<>();
        this.soundTracks = new ArrayList<>();
        this.ratings = new ArrayList<>();

    }

    public ArrayList<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getTitle() {
        return title;
    }

    public String getPlotSummary() {
        return plotSummary;
    }

    public ArrayList<File> getPosters() {
        return posters;
    }

    public ArrayList<File> getTrailers() {
        return trailers;
    }

    public String getGenre() {
        return Genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getLanguage() {
        return language;
    }

    public ArrayList<RoleInMovie> getRoles() {
        return roleInMovies;
    }


    public ArrayList<TriviaQuoteGoof> getTrivia() {
        return trivia;
    }

    public ArrayList<TriviaQuoteGoof> getQuotes() {
        return quotes;
    }

    public ArrayList<TriviaQuoteGoof> getGoofs() {
        return goofs;
    }

    public ArrayList<SoundTrack> getSoundTracks() {
        return soundTracks;
    }

    public void setTitle(Object title) {
        this.title = (String) title;

    }

    public void setPlotSummary(Object plotSummary) {
        this.plotSummary =(String) plotSummary;
    }

    public void setPosters(Object posters) {
        this.posters = (ArrayList<File>) posters;
    }

    public void AddPoster(File poster) {
        posters.add(poster);
    }
    public void setTrailers(ArrayList<File> trailers) {
        this.trailers = trailers;
    }
    public void addTrailer(File trailer) {
        trailers.add(trailer);
    }

    public void setGenre(Object genre) {
        Genre = (String) genre;
    }

    public void setReleaseDate(Object releaseDate) {
        this.releaseDate =(LocalDate) releaseDate;
    }

    public void setLanguage(Object language) {
        this.language = (String) language;
    }

    public void setRoles(Object roles) {
        this.roleInMovies = (ArrayList<RoleInMovie> )roles;
    }

    public void setTrivia(Object trivia) {
        this.trivia = (ArrayList<TriviaQuoteGoof>)trivia;
    }

    public void setQuotes(Object quotes) {
        this.quotes = (ArrayList<TriviaQuoteGoof>)quotes;
    }

    public void setGoofs(Object goofs) {
        this.goofs = (ArrayList<TriviaQuoteGoof>)goofs;
    }

    public void setSoundTracks(Object soundTracks) {
        this.soundTracks = (ArrayList<SoundTrack>)soundTracks;
    }

    public void addToRoles(Object roleInMovie){
        roleInMovies.add((RoleInMovie) roleInMovie);
    }
    public void addToGoofs(Object triviaquotegoof){
        goofs.add((TriviaQuoteGoof) triviaquotegoof);
    }
    public void addToTrivia(Object triviaquotegoof){
        trivia.add((TriviaQuoteGoof) triviaquotegoof);
    }
    public void addToQuote(Object triviaquotegoof){
        quotes.add((TriviaQuoteGoof) triviaquotegoof);
    }
    public void addToSoundtracks(Object triviaquotegoof){
        soundTracks.add((SoundTrack) triviaquotegoof);
    }
    public void removeFromRole(Object roleInMovie){
        roleInMovies.remove((RoleInMovie) roleInMovie);
    }
    public void removeFromGoofs(Object triviaquotegoof){
        goofs.remove((TriviaQuoteGoof) triviaquotegoof);
    }
    public void removeFromTrivia(Object triviaquotegoof){
        trivia.remove((TriviaQuoteGoof) triviaquotegoof);
    }
    public void removeFromQuotes(Object triviaquotegoof){
        quotes.remove((TriviaQuoteGoof) triviaquotegoof);
    }
    public void removeFromSoundtracks(Object triviaquotegoof){
        soundTracks.remove((SoundTrack) triviaquotegoof);
    }


    @Override
    public String toString() {
        return
                "title: " + title + '\n' +
                ", plotSummary: " + plotSummary + '\n' +
                ", posters: " + posters +
                ", trailers: " + trailers +
                ", Genre: " + Genre + '\n' +
                ", releaseDate: " + releaseDate +
                ", language: " + language + '\n' +
                ", roles: " + roleInMovies +
                ", trivia: " + trivia +
                ", quotes: " + quotes +
                ", goofs: " + goofs +
                ", soundTracks: " + soundTracks +
                ", ratings: " + ratings ;
    }

    public Movie addNewMovieToDatabase(Member member){
        if (member.getAccessLevel() == AccessLevel.ADMIN){
            /*
            HashMap<String, Object> movieDetails = GetInput.getMovieDetails();
            Movie newMovie = new Movie(movieDetails.get("title").toString(),
                    movieDetails.get("plotSummary").toString(),
                    movieDetails.get("genre").toString(),
                    (LocalDate) movieDetails.get("releaseDate"),
                    movieDetails.get("language").toString()
                    );

             */
            DBLists.addToMovieArrayList(this);
            return this;
        }
        else{
            System.out.println("Access denied. User with permission<"+member.getAccessLevel().toString()+"> does not have Admin permissions");
            return null;
        }
    }

    public boolean removeMovieFromDatabase(Member member){
        if (member.getAccessLevel() == AccessLevel.ADMIN){
            if (DBLists.removeFromMovieArrayList(this)){
                return true;
            }
            else {
                System.out.println("Failed to remove movie from database");
                return false;
            }
        }
        else{
            System.out.println("Access denied. User with permission<"+member.getAccessLevel().toString()+"> does not have Admin permissions");
            return false;
        }
    }

    public boolean removeRating(Member member, Rating rating){
        if (member.getAccessLevel()==AccessLevel.ADMIN){
            if (ratings.contains(rating)){
                ratings.remove(rating);
                return true;
            }
            else{
                System.out.println("Unable to remove rating/review");
                return false;
            }
        }
        else {
            System.out.println("Access denied. User with permission<"+member.getAccessLevel().toString()+"> does not have Admin permissions");
            return false;
        }
    }
    public boolean addRating(Rating rating) throws GuestAccessRefusedException, DuplicateReviewOrRatingInMovieException {
        if(rating.getRater().getAccessLevel()==AccessLevel.GUEST){
            throw new GuestAccessRefusedException();
        }
        for (Rating r : ratings){
            if (r.rater.equals(rating.rater)){
                throw new DuplicateReviewOrRatingInMovieException();
            }
        }
        ratings.add(rating);
        return true;
    }

    public boolean addReview(Review review) throws GuestAccessRefusedException, DuplicateReviewOrRatingInMovieException {
        if(review.getRater().getAccessLevel()==AccessLevel.GUEST){
            throw new GuestAccessRefusedException();
        }
        for(Rating r : ratings){
            if(r.getRater().equals(review.getRater())){
                    throw new DuplicateReviewOrRatingInMovieException();
            }
        }
        ratings.add(review);
        return true;
    }
    public boolean editReview(Review reviewToBeEdited, Review newReview){ // takes the current review that needs to be edited as the argument
        if (ratings.contains(reviewToBeEdited)){
            //Review newReview = GetInput.getNewReview(reviewToBeEdited.rater);
            reviewToBeEdited = newReview;
            return true;
        }
        return false;
    }

    public boolean removeRating_Review(Rating rating){ // has the ability to remove both ratings and reviews
        if(ratings.contains(rating)){
            ratings.remove(rating);
            return true;
        }
        return false;
    }

    public double getAverageRating(){
        double avg=0;
        int count = 0;
        for(Rating rating: ratings){
            avg += Rating.rateToNum(rating.rate);
            count++;
        }

        return ((avg==0 && count==0) ? 0 : avg/count);
    }


}
