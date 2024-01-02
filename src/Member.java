import java.io.File;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.util.*;

public class Member extends Person {
    AccessLevel accessLevel;
    File image;
    String username;
    String password;
    ArrayList<ContactPoint> contactPoints;
    Geoloc address;
    LocalDate dateOfBirth;
    ArrayList<Thumb> thumbs;
    ArrayList<Review> reviews;
    ArrayList<Movie>watchlist;
    ArrayList<Movie> favorites;
    ArrayList<Movie> classicsToSee;
    ArrayList<Movie> recommendedMovies;
    ArrayList<Person> followings;
    ArrayList<Notification> notifications;
    ArrayList<Forum> DMs;


    //Constructor

    public Member(String username,
                  String password,
                  AccessLevel accessLevel,
                  String name,
                  String lastName) {
        super(name, lastName);
        this.username = username;
        this.password = password;
        this.accessLevel = accessLevel;
        this.thumbs = new ArrayList<>();
        this.watchlist = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this. classicsToSee = new ArrayList<>();
        this.recommendedMovies = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.DMs =  new ArrayList<>();
    }

    // getters & setters
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public File getImage() {
        return image;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<ContactPoint> getContactPoints() {
        return contactPoints;
    }

    public Geoloc getAddress() {
        return address;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public ArrayList<Thumb> getThumbs() {
        return thumbs;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public ArrayList<Movie> getWatchlist() {
        return watchlist;
    }

    public ArrayList<Movie> getFavorites() {
        return favorites;
    }

    public ArrayList<Movie> getClassicsToSee() {
        return classicsToSee;
    }

    public ArrayList<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }

    public ArrayList<Person> getFollowings() {
        return followings;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }





    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContactPoints(ArrayList<ContactPoint> contactPoints) {
        this.contactPoints = contactPoints;
    }
    public void setNewContactPoint(ContactPoint contactPoint){
        this.contactPoints.add(contactPoint);
    }
    public void setAddress(Geoloc address) {
        this.address = address;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setThumbs(ArrayList<Thumb> thumbs) {
        this.thumbs = thumbs;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void setWatchlist(ArrayList<Movie> watchlist) {
        this.watchlist = watchlist;
    }

    public void setFavorites(ArrayList<Movie> favorites) {
        this.favorites = favorites;
    }

    public void setClassicsToSee(ArrayList<Movie> classicsToSee) {
        this.classicsToSee = classicsToSee;
    }

    public void setRecommendedMovies(ArrayList<Movie> recommendedMovies) {
        this.recommendedMovies = recommendedMovies;
    }

    public void setFollowings(ArrayList<Person> followings) {
        this.followings = followings;
    }

    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public ArrayList<Forum> getDMs() {
        return DMs;
    }

    public void setDMs(ArrayList<Forum> DMs) {
        this.DMs = DMs;
    }

    public Forum newDM(Member receiver){
        Forum newDM = new Forum(this.getUsername() +"-"+receiver.getUsername(), this);
        DMs.add(newDM);
        receiver.getDMs().add(newDM);
        return newDM;
    }


    public Person addNewPersonToDatabase(Member actionPerformer, Member newMember) { // the person who wantes to perform this action
        if (actionPerformer.getAccessLevel() == AccessLevel.ADMIN) {
            DBLists.addToPersonArrayList(newMember);
            return newMember;
        } else {
            System.out.println("Access denied. User with permission<" + actionPerformer.getAccessLevel().toString() + "> does not have Admin permissions");
            return null;
        }
    }


    // In mitonest yek class joda bashe vali kheili kare khassi anjam namide pas haminja gozashtamesh.
    public void recommendMovies(){
        recommendedMovies = new ArrayList<Movie>();
        ArrayList<Movie> MovieDB=  DBLists.getMovieArrayList();
        for (Movie fave : favorites){
            for(Movie movie : MovieDB){
                if(movie.getGenre().equals(fave.getGenre()) && !recommendedMovies.contains(movie)){
                    recommendedMovies.add(movie);
                }
            }
        }
        for(Movie classic : classicsToSee){
            for(Movie movie : MovieDB){
                if (movie.getGenre().equals(classic.getGenre()) && !recommendedMovies.contains(movie)){
                    recommendedMovies.add(movie);
                }
            }
        }
        for(Movie wl : watchlist){
            for(Movie movie : MovieDB){
                if (movie.getGenre().equals(wl.getGenre()) && !recommendedMovies.contains(movie)){
                    recommendedMovies.add(movie);
                }
            }
        }
    }
}
