import Exceptions.*;
import org.ietf.jgss.GSSName;

import javax.management.relation.RoleInfo;
import javax.management.relation.RoleStatus;
import java.security.spec.EdDSAParameterSpec;
import java.time.LocalDate;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static boolean editorMode = false;
    static boolean adminMode = false;
    static Member guestUser = new Member("guest", "guest",AccessLevel.GUEST,"guest","guest");
    static Member activeUser = guestUser;
    public static void main(String[] args) {
        DBLists.initialSetup();
        firstPage();
    }

    //first page after login
    public static void firstPage() {
        String action;
        while (true) {
            System.out.println("1.Account\n2.Movies\n3.People\n4.Forums\n5.Notifications\n6.Editor panel\n7.Admin panel\n8.View DMs\nQ.Logout\nX.Exit");
            action = scanner.nextLine().toLowerCase();
            switch (action) {
                case "1": {
                    loginPage();
                    return;
                }

                case "2": {
                    getMovie();
                    return;
                }
                case "3": {
                    getPeople();
                    break;
                }
                case "4":{
                    getForums();
                    return;
                }

                case "5":{
                    getNotifications();
                    break;
                }
                case "6":{
                    editorPanel();
                    break;
                }
                case "7":{
                    adminPannel();
                    break;
                }
                case "8":{
                    getDMs();
                }
                case "q": {
                    logout();
                    break;
                }
                case "x": {
                    System.exit(0);
                    break;
                }
                default:{
                    System.out.println("-!-Invalid option");
                }

            }
        }
    }
    public static void getDMs() {

        ArrayList<Forum> DMs = activeUser.getDMs();
        int counter = 1;
        System.out.println("#### #### #### DMs #### #### ####");
        for (Forum f : DMs) {
            System.out.println(counter + ". " + f.getTopic());
            counter++;
        }
        System.out.println("#### #### #### #### #### #### ####");

        System.out.println("Enter DM index: ");
        String index = scanner.nextLine();
        int i = 0;
        try {
            i = Integer.parseInt(index);
            if (i > DMs.size()) {
                throw new Exception();
            }
        } catch (Exception e) {
            System.out.println("Not a valid index");
            firstPage();
            return;
        }
        forumOptions(DMs.get(i - 1));
    }


    // Log user out
    public static void logout(){
        activeUser = guestUser;
        adminMode = false;
        editorMode = false;
        loginPage();
    }
    // Login or sign up
    public static Member loginPage() {

        String actiona;

        Member user = activeUser; // Useri ke gharare login kone
        boolean condition = true;
        while (condition) {
            System.out.println("Enter action:\n1.Login\n2.Sign Up\n3.Your account \n M.Main page");
            actiona  = scanner.nextLine();
            switch (actiona) {
                case "1": {

                    System.out.println("login:\nEnter Username");
                    String username = scanner.nextLine();
                    System.out.println("EnterPassword");
                    String password = scanner.nextLine();
                    user = UserManagement.Login(username, String.valueOf(password.hashCode()));
                    if (user != null) {
                        System.out.println("Login successful");
                        System.out.println("welcome " + user.getName() + " " + user.getLastName());
                        System.out.println("AccessLevel: " + user.getAccessLevel());
                        condition = false;
                        break;
                    }

                    System.out.println("login failed :(");
                    break;

                }
                case "2": {

                    try {
                        user = UserManagement.SignUp(GetInput.getDetails());condition = false;break;
                    } catch (InvalidSignUpFieldsException e) {
                        System.out.println("Invalid fields");
                        user = null;
                    } catch (InvalidUserNameException e) {
                        System.out.println("Username already taken");
                        user = null;
                    }
                    if (user != null) {
                        System.out.println("You have successfully signed up!");
                        break;
                    }
                    System.out.println("Failed to sign up! ");
                    break;
                }
                case "3":{
                    if (!activeUser.getAccessLevel().equals(AccessLevel.GUEST)){
                        System.out.println("Username: "+activeUser.getUsername());
                        System.out.println("Name: "+activeUser.getName());
                        System.out.println("Lastname: "+activeUser.getLastName());
                        System.out.println("1.Edit username\n2.Edit Password\n3.Edit Name\n4.Edit Lastname\n5.View favorites\n6.View watchlist\n7.View Your Recommended Movies\nM.Main page");
                        String action  = scanner.nextLine();
                        switch (action) {
                            case "1": {
                                System.out.println("Enter new username");
                                activeUser.setUsername(scanner.nextLine());
                                condition = false;
                                break;
                            }
                            case "3": {
                                System.out.println("Enter new name:");
                                activeUser.setName(scanner.nextLine());
                                condition = false;
                                break;
                            }
                            case "2": {
                                System.out.println("Enter new password");
                                activeUser.setPassword(String.valueOf(scanner.nextLine().hashCode()));
                                condition = false;
                                break;
                            }
                            case "4": {
                                System.out.println("Enter new lastname:");
                                activeUser.setLastName(scanner.nextLine());
                                condition = false;
                                break;
                            }

                            case "5":{
                                ArrayList<Movie> movies = activeUser.getFavorites();
                                for(Movie m: movies){
                                    System.out.println("### ### ### ### ### ### ###");
                                    System.out.println(m.getTitle());
                                }
                                System.out.println("### ### ### ### ### ### ###");
                                break;
                            }
                            case "6":{
                                ArrayList<Movie> movies = activeUser.getWatchlist();
                                for(Movie m: movies){
                                    System.out.println("### ### ### ### ### ### ###");
                                    System.out.println(m.getTitle());
                                }
                                System.out.println("### ### ### ### ### ### ###");
                                break;
                            }
                            case "7":{
                                activeUser.recommendMovies();
                                ArrayList<Movie> movies = activeUser.getRecommendedMovies();
                                for(Movie m: movies){
                                    System.out.println("### ### ### ### ### ### ###");
                                    System.out.println(m.getTitle());
                                }
                                System.out.println("### ### ### ### ### ### ###");
                                break;
                            }
                            default:
                                System.out.println("Invalid option");
                                break;
                        }
                    }
                }
                case "M": {
                    firstPage();
                    break;
                }
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }
        System.out.println("You are logged in!");
        activeUser = user;
        firstPage();
        return user;
    }

    public static void getNotifications(){
        ArrayList<Notification> notifications = activeUser.getNotifications();
        for(Notification n : notifications){
            if (n.getNotificationStatus().equals(NotificationStatus.UNREAD)) {
                System.out.println("### ### ### ### ### ### ###");
                System.out.println(n.getTitle());
                System.out.println(n.getBody());
                System.out.println(n.getTimestamp());
                n.setNotificationStatus(NotificationStatus.READ);
            }
        }
        System.out.println("### ### ### ###  ### ### ###");
    }

    public static void adminPannel(){
        if(!(activeUser.getAccessLevel()==AccessLevel.ADMIN)){
            System.out.println("User access level is not Admin");
            return;
        }
        adminMode = true;
        while(true){
            System.out.println("1. View suggestions\n2. View Reports\n3.Manage people in Database\n4.Manage Movies in Database\nM.Main page(you will exit admin mode)");
            String action = scanner.nextLine();
            switch (action){
                case "1":{
                    ArrayList<Suggestion> unhandledSuggestionsArraylist = DBLists.getUnhandledSuggestions();
                    int counter =1;
                    for(Suggestion s : unhandledSuggestionsArraylist){
                        System.out.println("#" + counter);
                        System.out.println(s.getEditor().getName() +" "+ s.getEditor().getLastName());
                        System.out.println("Editor username : " + s.getEditor().getUsername());
                        System.out.println("Suggestion description"+s.getSuggestionDescription());
                        System.out.println("Method to be called for approval: "+s.getMethodToCall().toString());
                        System.out.println("New value to be set for object: "+s.getNewValue());
                        System.out.println("### ### ### ### ### ### ### ### ###");
                    }
                    System.out.println("Enter index to edit");
                    String index = scanner.nextLine();
                    int i=0;
                    try{
                        i = Integer.parseInt(index);
                        if (i > unhandledSuggestionsArraylist.size()){
                            throw new Exception();
                        }
                    }
                    catch (Exception e){
                        System.out.println("Not a valid index");
                        continue;
                    }
                    System.out.println("Do you want ot 1.approve or 2.disapprove this suggestion?");
                    action = scanner.nextLine();
                    if(action.equals("1")){
                        try {
                            unhandledSuggestionsArraylist.get(i - 1).approveSuggestion(activeUser);
                        }
                        catch (AdminAccessRequiredException adminAccessRequiredException){
                            System.out.println("You are not an Admin!");
                            firstPage();
                            return;
                        }
                    }
                    else if (action.equals("2")){
                        try {
                            unhandledSuggestionsArraylist.get(i - 1).disapproveSuggestion(activeUser);
                        }
                        catch (AdminAccessRequiredException adminAccessRequiredException){
                            System.out.println("You are not an Admin!");
                            firstPage();
                            return;
                        }

                    }
                    else {
                        System.out.println("Invalid option!");
                        continue;
                    }
                    break;
                }
                case "2":{
                    ArrayList<Report> unhandledReportsArraylist = DBLists.getUnhandledReports();
                    int counter =1;
                    for(Report s : unhandledReportsArraylist){
                        System.out.println("#" + counter);
                        System.out.println(s.getSender().getName() +" "+ s.getSender().getLastName());
                        System.out.println("Editor username : " + s.getSender().getUsername());
                        System.out.println("Report description: "+s.getText());
                        System.out.println("### ### ### ### ### ### ### ### ###");
                        counter++;
                    }
                    System.out.println("Enter index to edit: ");
                    String index = scanner.nextLine();
                    int i=0;
                    try{
                        i = Integer.parseInt(index);
                        if (i > unhandledReportsArraylist.size()){
                            throw new Exception();
                        }
                    }
                    catch (Exception e){
                        System.out.println("Not a valid index");
                        continue;
                    }
                    System.out.println("Enter <1> if you wish to handle this report");
                    action = scanner.nextLine();
                    if(action.equals("1")){
                        try {
                            unhandledReportsArraylist.get(i - 1).handleReport(activeUser);
                        }
                        catch (AdminAccessRequiredException adminAccessRequiredException){
                            System.out.println("You are not an Admin!");
                            firstPage();
                            return;
                        }
                    }

                    else {
                        System.out.println("Invalid option!");
                        continue;
                    }
                    break;
                }
                case "3":{
                    System.out.println("1.Add new person\n2.Find person in db\nM.Main page");

                    action = scanner.nextLine();
                    if(action.equals("2")) {
                        System.out.println("Enter person name:");
                        Optional<Person> personName = DBLists.searchPerson(scanner.nextLine());
                        if (personName.isEmpty()) {
                         System.out.println(" person not found");
                         continue;
                        } else {
                         System.out.println("Do you wish to delete this person?(y/n)");
                         if(scanner.nextLine().equalsIgnoreCase("y")){
                             DBLists.removeFromPersonArrayList(personName.get());
                         }
                        }
                    }
                    else if (action.equals("1")){
                        System.out.println("Enter person name");
                        String name = scanner.nextLine();
                        System.out.println("Enter person Lastname");
                        String lastName = scanner.nextLine();
                        Person newPerson = new Person(name, lastName);
                        DBLists.addToPersonArrayList(newPerson);
                        System.out.println("Person created successfully");
                    }
                    else if(action.equals("M")){
                        firstPage();
                        return;
                    }
                }
                case "4":{

                    System.out.println("1.Add a movie to Database\n2.Remove a movie from Database");
                    action = scanner.nextLine();

                    Movie resultMovie;
                    switch (action) {

                        case "2": {
                            System.out.println("Enter a lower bound for release date: YYYY-MM-DD");
                            String lowerDate = scanner.nextLine();
                            LocalDate fromDate = lowerDate.equals("") ? LocalDate.parse("0000-01-01") : LocalDate.parse(lowerDate);
                            System.out.println("Enter an upper bound for release date: YYYY-MM-DD");
                            String upperDate = scanner.nextLine();
                            LocalDate toDate = upperDate.equals("") ? LocalDate.parse("3000-01-01") : LocalDate.parse(upperDate);
                            System.out.println("Enter the name of the movie you want to search");
                            String searchText = scanner.nextLine();
                            System.out.println("Enter a lower bound for rating");
                            String intinp = scanner.nextLine();
                            int ratingFrom = intinp.equals("") ? 0 : Integer.parseInt(intinp);
                            System.out.println("Enter an upper bound for rating");
                            intinp = scanner.nextLine();
                            int ratingTo = intinp.equals("") ? 10 : Integer.parseInt(intinp);
                            System.out.println("Enter genre");
                            String genre = scanner.nextLine();
                            System.out.println("Movies in database: ");
                            ArrayList<Movie> resMovies = DBLists.searchMovie(fromDate, toDate, searchText, ratingFrom, ratingTo, genre);
                            int count = 1;
                            for (Movie mov : resMovies) {
                                System.out.println(count++ + ": " + mov.getTitle());
                            }
                            int index;
                            try {
                                index = Integer.parseInt(scanner.nextLine());
                                resultMovie = resMovies.get(index - 1);
                            } catch (Exception e) {
                                break;
                            }
                            System.out.println("Do you wish to delete this movie?(y/n)");
                            action = scanner.nextLine();
                            if (action.equalsIgnoreCase("y")) {
                                DBLists.removeFromMovieArrayList(resultMovie);
                            }
                            break;
                        }
                        case "1": {
                            System.out.println("Enter movie title: ");
                            String title = scanner.nextLine();
                            System.out.println("Enter movie genre: ");
                            String genre = scanner.nextLine();
                            System.out.println("Enter release date:(YYYY-MM-DD)");
                            LocalDate releaseDate = LocalDate.parse(scanner.nextLine());
                            System.out.println("Enter movie language: ");
                            String language = scanner.nextLine();
                            resultMovie = new Movie(title, genre, releaseDate,language);
                            DBLists.addToMovieArrayList(resultMovie);
                            break;
                        }
                    }
                }
                case "M":{
                    adminMode = false;
                    firstPage();
                    return;
                }
            }
        }
    }
    public static void editorPanel(){
        if (!(activeUser.getAccessLevel()==AccessLevel.EDITOR)){
            System.out.println("User access level is not editor");
            return;
        }
        editorMode = true;
        while(true){
            System.out.println("1.View movies\n2.View People\nM.Main page( you will exit editor mode)");
            String action = scanner.nextLine();
            switch (action){
                case "1":{
                    getMovie();
                    break;
                }
                case "2":{
                    break;
                }
                case "M":{
                    firstPage();
                    editorMode = false;
                    break;
                }
                default:
                    System.out.println("invalid option");
            }
        }
    }

    public static void getForums(){
        ArrayList<Forum> forums = DBLists.getForumArraylist();
        int counter = 1;
        System.out.println("#### #### #### forums #### #### ####");
        for(Forum f: forums){
            System.out.println(counter + ". " + f.getTopic());
            counter++;
        }
        System.out.println("#### #### #### #### #### #### ####");
        System.out.println("1. Create new forum\n2. Enter forum\n3. Main page");
        String action = scanner.nextLine();
        switch (action) {
            case "1": {
                System.out.println("Enter forum title: ");
                String title = scanner.nextLine();
                DBLists.getForumArraylist().add(new Forum(title, activeUser));
                getForums();
                break;
            }
            case "2":{
                System.out.println("Enter forum index: ");
                String index = scanner.nextLine();
                int i = 0;
                try {
                    i = Integer.parseInt(index);
                    if (i > forums.size()) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Not a valid index");
                    firstPage();
                    return;
                }
                forumOptions(forums.get(i - 1));
                return;
            }
            case "3":{
                firstPage();
                return;
            }
            default:{
                System.out.println("-!-!-!-Invalid Option");
                firstPage();
                return;
            }
        }

    }
    public static void forumOptions(Forum forum){

        while(true){
            System.out.println("1.View Comments/Messages\n2.Report\n3.Main page");
            String inp = scanner.nextLine();
            switch (inp){
                case "1":{
                    boolean s = true;
                    while(s) {
                    ArrayList<Comment> comments = forum.getComments();
                    int counter = 1;
                    for(Comment c: comments){
                        System.out.println("### ### ### ### ### ### ### ###");
                        System.out.println("#"+counter);
                        System.out.println("    " + c.getSender().getUsername()+":");
                        System.out.println("... Reply to :"+ (comments.indexOf(c.getReplyTo()) + 1));
                        System.out.println("    " + c.getBody());
                        System.out.println("Thumbs Up: "+c.getThumbsUp());
                        System.out.println("Thumbs Down: "+ c.getThumbsDown());
                        counter++;
                    }
                    System.out.println("--- --- --- --- --- --- --- ---");

                    System.out.println("1. Write new comment\n2.Report a comment\n3.Thumb a comment(Like or dislike)\n4.Go back");
                    String aciton = scanner.nextLine();

                        switch (aciton) {
                            case "3": {

                                System.out.println("Enter comment index to thumb");
                                String index = scanner.nextLine();
                                int i = 0;
                                try {
                                    i = Integer.parseInt(index);
                                    if (i > comments.size()) {
                                        throw new Exception();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Not a valid index");
                                    continue;
                                }
                                System.out.println("1.Up 2.Down");
                                ThumbDir thumbDir = scanner.nextLine().equals("1") ? ThumbDir.UP : ThumbDir.DOWN;
                                try {
                                    comments.get(i - 1).addNewThumb(new Thumb(thumbDir, activeUser));
                                } catch (DuplicateReviewOrRatingInMovieException e) {
                                    System.out.println("User has already thumbed this comment");
                                }
                                break;
                            }
                            case "1": {
                                System.out.println("Enter the comment that you want to reply to: enter 0 if this is not a reply");
                                String index = scanner.nextLine();
                                int i = 0;
                                try {
                                    i = Integer.parseInt(index);
                                    if (i > comments.size()) {
                                        throw new Exception();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Not a valid index");
                                    continue;
                                }
                                System.out.println("Enter comment body");
                                String text = scanner.nextLine();
                                Comment reply;
                                if (index.equals("0")) {
                                    reply = null;
                                } else {
                                    reply = comments.get(i - 1);
                                }
                                Comment newComment = new Comment(reply, activeUser, text);
                                if (reply != null && !reply.getSender().equals(newComment.getSender())) {
                                    newComment.sendNotification("", "", newComment, reply.getSender());
                                }
                                comments.add(newComment);
                                break;
                            }
                            case "2": {
                                System.out.println("Enter comment index to report");
                                String index = scanner.nextLine();
                                int i = 0;
                                try {
                                    i = Integer.parseInt(index);
                                    if (i > comments.size()) {
                                        throw new Exception();
                                    }
                                } catch (Exception e) {
                                    System.out.println("Not a valid index");
                                    continue;
                                }
                                Comment rep;
                                if (index.equals("0")) {
                                    rep = null;
                                } else {
                                    rep = comments.get(i - 1);
                                }
                                System.out.println("Enter report body");
                                String text = scanner.nextLine();
                                System.out.println("Why are you reporting this comment?");
                                ArrayList<CommentReportFlag> commentReportFlags = new ArrayList<>(Arrays.asList(CommentReportFlag.values()));
                                counter = 1;
                                for (CommentReportFlag f : commentReportFlags) {
                                    System.out.println(counter + ". " + f);
                                    counter++;
                                }
                                CommentReport commentReport = new CommentReport(commentReportFlags.get(Integer.parseInt(scanner.nextLine()) - 1), activeUser, rep, text);
                                ArrayList<Person> members = DBLists.getPersonArrayList();
                                for (Person p : members) {
                                    if (p instanceof Member) {
                                        if (((Member) p).getAccessLevel().equals(AccessLevel.ADMIN)) {
                                            commentReport.sendNotification("", "", commentReport, (Member) p);
                                        }
                                    }
                                }

                                DBLists.addToUnhandledReports(commentReport);
                                break;
                            }
                            case "4":
                            {
                                s = false;
                                break;
                            }
                            default:
                                System.out.println("Invalid option");
                                break;
                        }
                    }
                    break;
                }
                case "2":{
                    System.out.println("Enter report body");
                    String text = scanner.nextLine();
                    System.out.println("Why are you reporting this forum?");
                    ArrayList<ForumReportFlag> forumReportFlags = new ArrayList<>( Arrays.asList(ForumReportFlag.values()));
                    int counter = 1;
                    for (ForumReportFlag f : forumReportFlags) {
                        System.out.println(counter + ". " + f);
                        counter++;
                    }

                    ForumReport forumReport = new ForumReport( activeUser,forum , text,forumReportFlags.get(Integer.parseInt(scanner.nextLine()) - 1));
                    ArrayList<Person> members = DBLists.getPersonArrayList();
                    for(Person p : members){
                        if (p instanceof Member){
                            if (((Member) p).getAccessLevel().equals(AccessLevel.ADMIN)){
                                forumReport.sendNotification("","",forumReport,(Member)p);
                            }
                        }
                    }
                    DBLists.addToUnhandledReports(forumReport);
                    break;
                }
                case "3":{
                    firstPage();
                    return;
                }
                default:
                    continue;
            }
        }
    }

    public static void getPeople(){
        System.out.println("Enter the person who you want to search for");
        Optional<Person> personName = DBLists.searchPerson(scanner.nextLine());
        if (personName.isEmpty()) {
            System.out.println("Person not found in database.");
            firstPage();
            return;
        }
        Person foundPerson = personName.get();
        System.out.println("### ### ### ### ### ### ###");
        System.out.println("Person found: ");
        System.out.println("    Name: "+ foundPerson.getName() +" "+ foundPerson.getLastName());
        System.out.println("Followers: " + foundPerson.getFollowers().size());

        if (!foundPerson.getRoleInMovies().isEmpty()) {
            System.out.println("Associated Movies: ");
            ArrayList<RoleInMovie> roleInMovies = foundPerson.getRoleInMovies();
            for(RoleInMovie r : roleInMovies){
                System.out.println("Movie: "+ r.getMovie().getTitle());
                System.out.println("Role" + r.getRole().toString());
            }
        }
        if (!activeUser.getAccessLevel().equals(AccessLevel.GUEST)) {
            System.out.println("Person actions: ");
            System.out.println("1.Follow/Unfollow\n2.Report\n3.DM");
            String inp = scanner.nextLine();
            switch (inp) {
                case "1": {
                    if (foundPerson.getFollowers().contains(activeUser)) {
                        foundPerson.getFollowers().remove(activeUser);
                        activeUser.getFollowings().remove(foundPerson);
                    } else {
                        foundPerson.getFollowers().add(activeUser);
                        activeUser.getFollowings().add(foundPerson);
                    }
                    break;
                }
                case "2": {
                    System.out.println("Enter report body");
                    String text = scanner.nextLine();
                    System.out.println("Why are you reporting this person?");
                    ArrayList<PersonReportFlag> personReportFlags = new ArrayList<>( Arrays.asList(PersonReportFlag.values()));
                    int counter = 1;
                    for (PersonReportFlag f : personReportFlags) {
                        System.out.println(counter + ". " + f);
                        counter++;
                    }

                    PersonReport personReport = new PersonReport(activeUser, foundPerson, text, personReportFlags.get(Integer.parseInt(scanner.nextLine()) - 1));
                    ArrayList<Person> members = DBLists.getPersonArrayList();
                    for(Person p : members){
                        if (p instanceof Member){
                            if (((Member) p).getAccessLevel().equals(AccessLevel.ADMIN)){
                                personReport.sendNotification("","",personReport,(Member)p);
                            }
                        }
                    }
                    DBLists.addToUnhandledReports(personReport);
                    break;
                }
                case "3":
                {
                    if(foundPerson instanceof Member) {
                        forumOptions(activeUser.newDM((Member) foundPerson));
                    }
                    else {
                        System.out.println("This profile does not belong to a registered Member");
                        break;
                    }
                }

            }
        }
    }
    public static void getMovie() {
        Movie resultMovie = null;
        while (resultMovie == null) {
            System.out.println("""
                        actions:
                        1.View movies in IMDB
                        2.Look up a movie in IMDB
                        3.Go to Main page
                        """);
            String action = (scanner.nextLine());

            switch (action) {
                case "1": {
                    ArrayList<Movie> movieList = DBLists.getMovieArrayList();
                    int counter = 1;
                    for (Movie movie : movieList) {
                        System.out.println(counter + ". " + movie.getTitle());
                        counter++;
                    }
                    System.out.println("Enter movie index: ");
                    String index = scanner.nextLine();
                    if (index.equals(" ")){
                        continue;
                    }

                    resultMovie = movieList.get(Integer.parseInt(index) - 1);
                    break;
                }
                case "2": {
                    System.out.println("Enter a lower bound for release date: YYYY-MM-DD");
                    String lowerDate = scanner.nextLine();
                    LocalDate fromDate = lowerDate.equals("") ? LocalDate.parse("0000-01-01") : LocalDate.parse(lowerDate);
                    System.out.println("Enter an upper bound for release date: YYYY-MM-DD");
                    String upperDate = scanner.nextLine();
                    LocalDate toDate = upperDate.equals("") ? LocalDate.parse("3000-01-01") : LocalDate.parse(upperDate);
                    System.out.println("Enter the name of the movie you want to search");
                    String searchText = scanner.nextLine();
                    System.out.println("Enter a lower bound for rating");
                    String intinp = scanner.nextLine();
                    int ratingFrom = intinp.equals("") ? 0 : Integer.parseInt(intinp);
                    System.out.println("Enter an upper bound for rating");
                    intinp = scanner.nextLine();
                    int ratingTo = intinp.equals("") ? 10 : Integer.parseInt(intinp);
                    System.out.println("Enter genre");
                    String genre = scanner.nextLine();

                    ArrayList<Movie> resMovies = DBLists.searchMovie(fromDate, toDate, searchText, ratingFrom, ratingTo, genre);
                    int count = 1;
                    for (Movie mov : resMovies) {
                        System.out.println(count++ + ": " + mov.getTitle());
                    }
                    int index = Integer.parseInt(scanner.nextLine());
                    resultMovie = resMovies.get(index - 1);
                    break;
                }
                case "3":
                    firstPage();
                    return;
                default: {
                    System.out.println("Invalid option");
                    break;
                }
            }
        }
        if(editorMode){
            movieOptionsEditorMode(resultMovie);
        }
        else {
            movieOptions(resultMovie);
        }
    }


    public static void movieOptionsEditorMode(Movie movie){
        while (true) {
            System.out.println("Movie title: " + movie.getTitle() + "\n" +
                    "Rating: " + movie.getAverageRating() + "\n" +
                    "release date: " + movie.getReleaseDate());
            System.out.println("""
                    1.Edit title
                    2.Edit release date
                    3.Edit genre
                    4.Edit Roles
                    5.Edit trivia/goofs/quotes/soundtracks
                    M.Mainpage
                    """);
            String action = scanner.nextLine();
            switch (action){
                case "M":{
                    firstPage();
                    return;
                }
                case "1":{
                    System.out.println("Enter new title: ");
                    String newTitle= scanner.nextLine();
                    System.out.println("Enter why you want to perform this edit:");
                    String suggestionDescription = scanner.nextLine();
                    DBLists.addToUnhandledSuggestions(new Suggestion(activeUser,suggestionDescription,movie::setTitle,newTitle));
                    break;
                }
                case "2":{
                    System.out.println("Enter new release date:(YYYY-MM-DD)");
                    LocalDate newReleaseDate = LocalDate.parse(scanner.nextLine());
                    String suggestionDescription = scanner.nextLine();
                    DBLists.addToUnhandledSuggestions(new Suggestion(activeUser,suggestionDescription,movie::setReleaseDate,newReleaseDate));
                    break;
                }
                case "3":{
                    System.out.println("Enter new genre: ");
                    String newGenre= scanner.nextLine();
                    System.out.println("Enter why you want to perform this edit:");
                    String suggestionDescription = scanner.nextLine();
                    DBLists.addToUnhandledSuggestions(new Suggestion(activeUser,suggestionDescription,movie::setGenre,newGenre));
                    break;
                }
                case "4":{
                    ArrayList<RoleInMovie> roleInMovies = movie.getRoles();
                    int counter = 1;
                    for(RoleInMovie r: roleInMovies){
                        System.out.println(counter + ". ");
                        System.out.println(r.getPerson().getName() + " " + r.getPerson().getName());
                        System.out.println("Role: " + r.getRole());
                        counter++;
                    }
                    System.out.println("Do you want to 1.delete an existing role or 2. add a new role?");
                    action = scanner.nextLine();
                    switch (action){
                        case "1":{
                            System.out.println("Which index do you want to delete?");
                            String index = scanner.nextLine();
                            int i=0;
                            try{
                                i = Integer.parseInt(index);
                                if (i > roleInMovies.size()){
                                    throw new Exception();
                                }
                            }
                            catch (Exception e){
                                System.out.println("Not a valid index");
                                continue;
                            }
                            System.out.println("Enter why you want to perform this edit:");
                            String suggestionDescription = scanner.nextLine();
                            DBLists.addToUnhandledSuggestions(new Suggestion(activeUser,suggestionDescription,movie::removeFromRole,roleInMovies.get(i-1)));
                            break;
                        }
                        case "2":{
                            System.out.println("Enter the person who you want to search for");
                            Optional<Person> personName = DBLists.searchPerson(scanner.nextLine());
                            if (personName.isEmpty()) {
                                System.out.println("Person not found in database.");
                                firstPage();
                                return;
                            }

                            System.out.println("Enter the role for this person");
                            ArrayList<Role> roles = new ArrayList<>( Arrays.asList(Role.values()));
                            counter = 1;
                            for (Role r : roles) {
                                System.out.println(counter + ". " + r);
                                counter++;
                            }
                            Role role = roles.get(Integer.parseInt(scanner.nextLine())-1);
                            RoleInMovie newRoleInMovie = new RoleInMovie(role,movie,personName.get());

                            System.out.println("Enter why you want to perform this edit:");
                            String suggestionDescription = scanner.nextLine();
                            DBLists.addToUnhandledSuggestions(new Suggestion(activeUser,suggestionDescription,movie::addToRoles,newRoleInMovie));
                            break;
                        }
                        default:
                            System.out.println("Invalid option");
                            firstPage();
                    }
                }
            }
        }
    }

    public static void movieOptions(Movie movie) {
        while (true) {
            System.out.println("Movie title: " + movie.getTitle() + "\n" +
                    "Rating: " + movie.getAverageRating() + "\n" +
                    "release date: " + movie.getReleaseDate());
            System.out.println("""
                    1.View ratings and reviews
                    2.Write a review
                    3.Rate this movie
                    4.Edit Review or Rating (You can only edit the ones that you have submitted)
                    5.View trivia
                    6.View goofs
                    7.View quotes
                    8.View soundtracks
                    9.Edit trivia/goofs/quotes/soundtracks
                    10.View roles
                    11.Add to watchlist
                    12.Add to favorites
                    M.Main page
                    """);
            String options = scanner.nextLine();
            switch (options){
                case "M":{
                    firstPage();
                    return;
                }
                case "1":{
                    ArrayList<Rating> ratingArrayList = movie.getRatings();
                    int counter = 1;
                    for(Rating r: ratingArrayList){
                        System.out.println("#############################");
                        if (r instanceof Review){
                            System.out.println("#"+counter);
                            System.out.println("~~~"+r.rate.toString());
                            System.out.println("<<<"+((Review)r).getRater().getUsername()+":");
                            System.out.println("    "+((Review)r).getTitle());
                            System.out.println("    \""+ ((Review)r).getBody()+"\"");
                            System.out.println("+++ThumbsUp:"+((Review)r).getThumbsUp());
                            System.out.println(("---ThumbsDown:")+((Review)r).getThumbsDown());
                        }
                        else{
                            System.out.println("~~~"+r.rate.toString());
                            System.out.println("<<<"+(r).getRater().getUsername()+":");
                        }
                        System.out.println("#############################");
                        counter++;
                    }
                    System.out.println("Enter review index or press enter to skip");

                    String index = scanner.nextLine();
                    int i=0;
                    try{
                        i = Integer.parseInt(index);
                        if (i > ratingArrayList.size()){
                            throw new Exception();
                        }
                        if(!(ratingArrayList.get(i-1) instanceof Review)){
                            throw new Exception();
                        }
                    }
                    catch (Exception e){
                        System.out.println("Not a valid index");
                        continue;
                    }
                    System.out.println("1.Report\n2.Like\n3.Dislike\n4.Go back");
                    String action = scanner.nextLine();
                    switch (action) {
                        case "1": {

                            System.out.println("Enter report body");
                            String text = scanner.nextLine();
                            System.out.println("Why are you reporting this review?");
                            ArrayList<ReviewReportFlag> reviewReportFlags = new ArrayList<>( Arrays.asList(ReviewReportFlag.values()));
                            counter = 1;
                            for (ReviewReportFlag f : reviewReportFlags) {
                                System.out.println(counter + ". " + f);
                                counter++;
                            }
                            ReviewReport reviewReport = new ReviewReport( activeUser,(Review)ratingArrayList.get(i-1) , text,reviewReportFlags.get(Integer.parseInt(scanner.nextLine()) - 1));

                            ArrayList<Person> members = DBLists.getPersonArrayList();
                            for(Person p : members){
                                if (p instanceof Member){
                                    if (((Member) p).getAccessLevel().equals(AccessLevel.ADMIN)){
                                        reviewReport.sendNotification("","",reviewReport,(Member)p);
                                    }
                                }
                            }
                            DBLists.addToUnhandledReports(reviewReport);
                            break;
                        }
                        case "2": {
                            try {
                                ((Review) ratingArrayList.get(i - 1)).addNewThumb((new Thumb(ThumbDir.UP, activeUser)));
                                break;
                            } catch (DuplicateReviewOrRatingInMovieException duplicateReviewOrRatingInMovieException) {
                                System.out.println("User has already thumbed :)");
                                break;
                            }
                        }
                        case "3":{
                            try {
                                ((Review) ratingArrayList.get(i - 1)).addNewThumb((new Thumb(ThumbDir.DOWN, activeUser)));
                                break;
                            } catch (DuplicateReviewOrRatingInMovieException duplicateReviewOrRatingInMovieException) {
                                System.out.println("User has already thumbed :)");
                                break;
                            }
                        }
                        default:
                            break;
                    }
                    break;
                }
                case "2":{
                    if(activeUser.getAccessLevel()==AccessLevel.GUEST){
                        System.out.println("You need to login to write a review");
                        break;
                    }
                    Rate rate;
                    System.out.println("How would you rate this movie?(1/2/3/4/5/6/7/8/9/10");
                    try {
                         rate = Rating.numToRate(Integer.parseInt(scanner.nextLine()));
                    }
                    catch (InvalidRatingException invalidRatingException){
                        System.out.println("Invalid input");
                        break;
                    }
                    System.out.println("Write your review title");
                    String reviewTitle = scanner.nextLine();
                    System.out.println("Write your review: ");
                    String reviewBody = scanner.nextLine();
                    System.out.println("Does your review contain any spoilers?(y/n)");
                    String spoilerAlert = scanner.nextLine();
                    boolean sp= spoilerAlert.equalsIgnoreCase("y");
                    try {
                        movie.addRating(new Review(rate, activeUser, reviewTitle, sp, reviewBody));
                    }
                    catch (GuestAccessRefusedException guestAccessRefusedException){
                        System.out.println("You need to login to write a review");
                        break;
                    }
                    catch (DuplicateReviewOrRatingInMovieException duplicateReviewOrRatingInMovieException){
                        System.out.println("A review or rating already exists for this user Edit that review or rating.");
                        break;
                    }
                    break;
                    }
                case "3":
                {
                    if(activeUser.getAccessLevel()==AccessLevel.GUEST){
                        System.out.println("You need to login to write a review");
                        break;
                    }
                    Rate rate;
                    System.out.println("How would you rate this movie?(1/2/3/4/5/6/7/8/9/10");
                    try {
                        rate = Rating.numToRate(Integer.parseInt(scanner.nextLine()));
                    }
                    catch (InvalidRatingException invalidRatingException){
                        System.out.println("Invalid input");
                        break;
                    }
                    try {
                        movie.addRating(new Rating(rate, activeUser));
                    }
                    catch (GuestAccessRefusedException guestAccessRefusedException){
                        System.out.println("You need to login to write a review");
                        break;
                    }
                    catch (DuplicateReviewOrRatingInMovieException duplicateReviewOrRatingInMovieException){
                        System.out.println("A review or rating already exists for this user Edit that review or rating.");
                        break;
                    }
                    break;
                }
                case "4":{
                    ArrayList<Rating> ratingArrayList = movie.getRatings();
                    for (Rating r : ratingArrayList){
                        if (r.getRater().equals(activeUser)){
                            System.out.println("Would you like to add a 1.review or 2.rating?");
                            String inp = scanner.nextLine();
                            switch (inp) {
                                case "2": {
                                    if (activeUser.getAccessLevel() == AccessLevel.GUEST) {
                                        System.out.println("You need to login to write a review");
                                        break;
                                    }
                                    Rate rate;
                                    System.out.println("How would you rate this movie?(1/2/3/4/5/6/7/8/9/10");
                                    try {
                                        rate = Rating.numToRate(Integer.parseInt(scanner.nextLine()));
                                    } catch (InvalidRatingException invalidRatingException) {
                                        System.out.println("Invalid input");
                                        break;
                                    }

                                    try {
                                        movie.removeRating(activeUser,r);
                                        movie.addRating(new Rating(rate, activeUser));

                                        break;
                                    } catch (GuestAccessRefusedException guestAccessRefusedException) {
                                        System.out.println("You need to login to write a review");
                                        break;
                                    } catch (
                                            DuplicateReviewOrRatingInMovieException duplicateReviewOrRatingInMovieException) {
                                        System.out.println("A review or rating already exists for this user Edit that review or rating.");
                                        break;
                                    }

                                }
                                case "1":
                                {
                                    if(activeUser.getAccessLevel()==AccessLevel.GUEST){
                                        System.out.println("You need to login to write a review");
                                        break;
                                    }
                                    Rate rate;
                                    System.out.println("How would you rate this movie?(1/2/3/4/5/6/7/8/9/10");
                                    try {
                                        rate = Rating.numToRate(Integer.parseInt(scanner.nextLine()));
                                    }
                                    catch (InvalidRatingException invalidRatingException){
                                        System.out.println("Invalid input");
                                        break;
                                    }
                                    System.out.println("Write your review title");
                                    String reviewTitle = scanner.nextLine();
                                    System.out.println("Write your review: ");
                                    String reviewBody = scanner.nextLine();
                                    System.out.println("Does your review contain any spoilers?(y/n)");
                                    String spoilerAlert = scanner.nextLine();
                                    boolean sp= spoilerAlert.equalsIgnoreCase("y");
                                    try {
                                        movie.removeRating(activeUser,r);
                                        movie.addRating(new Review(rate, activeUser, reviewTitle, sp, reviewBody));
                                    }
                                    catch (GuestAccessRefusedException guestAccessRefusedException){
                                        System.out.println("You need to login to write a review");
                                        break;
                                    }
                                    catch (DuplicateReviewOrRatingInMovieException duplicateReviewOrRatingInMovieException){
                                        System.out.println("A review or rating already exists for this user Edit that review or rating.");
                                        break;
                                    }
                                    break;
                                }
                                default: {
                                    System.out.println("Invalid input");
                                    break;
                                }
                            }
                        }
                    }
                }
                case "5":{
                    ArrayList<TriviaQuoteGoof> triviaQuoteGoofs = movie.getTrivia();
                    for (TriviaQuoteGoof trivia : triviaQuoteGoofs){
                        System.out.println("#######################################");
                        System.out.println("    "+trivia.Text);
                        System.out.println("#######################################");
                    }
                    break;
                }
                case "6":{
                    ArrayList<TriviaQuoteGoof> triviaQuoteGoofs = movie.getGoofs();
                    for (TriviaQuoteGoof trivia : triviaQuoteGoofs){
                        System.out.println("#######################################");
                        System.out.println("    "+trivia.Text);
                        System.out.println("#######################################");
                    }
                    break;
                }
                case "7":{
                    ArrayList<TriviaQuoteGoof> triviaQuoteGoofs = movie.getQuotes();
                    for (TriviaQuoteGoof trivia : triviaQuoteGoofs){
                        System.out.println("#######################################");
                        System.out.println("    "+trivia.Text);
                        System.out.println("#######################################");
                    }
                    break;
                }
                case "8": {
                    ArrayList<SoundTrack> soundTracks = movie.getSoundTracks();
                    for (SoundTrack s : soundTracks) {
                        System.out.println("#######################################");
                        System.out.println("SoundTrack Title: " + s.getTitle());
                        System.out.println("Composer(s): " + s.getComposer().getName() + " " + s.getComposer().getLastName());
                        System.out.println("Performer(s): " + s.getPerformedBy());
                        System.out.println("#######################################");
                    }
                    break;
                }
                case "9": {
                    while (true) {
                        System.out.println("Add 1.Trivia 2.Goof 3.Quote 4.Soundtrack 5.Go back to movies");
                        String add_type = scanner.nextLine();
                        switch (add_type) {
                            case "5":{
                                getMovie();
                                return;
                            }
                            case "1": {
                                System.out.println("Enter trivia text:");
                                String text = scanner.nextLine();
                                if (!text.equals("")) {
                                    try{
                                    movie.getTrivia().add(new TriviaQuoteGoof(activeUser, TriviaQuoteGoofType.TRIVIA, text));}
                                    catch (GuestAccessRefusedException guestAccessRefusedException){
                                        System.out.println("Only members can contribute");
                                    }
                                } else {
                                    System.out.println("Trivia text cannot be empty");
                                    continue;
                                }
                                break;
                            }
                            case "2": {
                                System.out.println("Enter goof text:");
                                String text = scanner.nextLine();
                                if (!text.equals("")) {
                                    try{
                                        movie.getGoofs().add(new TriviaQuoteGoof(activeUser, TriviaQuoteGoofType.TRIVIA, text));}
                                    catch (GuestAccessRefusedException guestAccessRefusedException){
                                        System.out.println("Only members can contribute");
                                    }
                                } else {
                                    System.out.println("Goof text cannot be empty");
                                    continue;
                                }
                                break;
                            }
                            case "3": {
                                System.out.println("Enter quote text:");
                                String text = scanner.nextLine();
                                if (!text.equals("")) {
                                    try{
                                        movie.getQuotes().add(new TriviaQuoteGoof(activeUser, TriviaQuoteGoofType.TRIVIA, text));}
                                    catch (GuestAccessRefusedException guestAccessRefusedException){
                                        System.out.println("Only members can contribute");
                                    }
                                } else {
                                    System.out.println("Quote text cannot be empty");
                                    continue;
                                }
                                break;
                            }
                            case "4": {
                                System.out.println("Enter the composer name: (Person must exist in database)");
                                Optional<Person> composerName = DBLists.searchPerson(scanner.nextLine());
                                if (composerName.isEmpty()) {
                                    System.out.println("Person not found in database.");
                                    continue;
                                }
                                ArrayList<Person> performers = new ArrayList<>();
                                Optional<Person> performerName;
                                while(true){
                                    System.out.println("Enter the name of the performer:(Press return if there are no performers left");
                                    String inp = scanner.nextLine();
                                    if(inp.equals("")){
                                        break;
                                    }
                                    performerName = DBLists.searchPerson(inp);
                                    if(performerName.isEmpty()){
                                        System.out.println("Person not found in database.");
                                        continue;
                                    }
                                    performers.add(performerName.get());
                                }
                                System.out.println("Enter sound track title");
                                String title = scanner.nextLine();
                                try{
                                movie.getSoundTracks().add(new SoundTrack(activeUser,composerName.get(),performers,title));}
                                catch (GuestAccessRefusedException guestAccessRefusedException){
                                    System.out.println("Only members can contribute");
                                }
                                break;
                            }
                        }
                    }
                }
                case "10":{
                    ArrayList<RoleInMovie> roleInMovieArrayList = movie.getRoles();
                    for(RoleInMovie r: roleInMovieArrayList) {
                        System.out.println("### ### ### ### ### ### ### ###");
                        System.out.println(r.getRole());
                        System.out.println(r.getPerson().getName() + " " + r.getPerson().getLastName());
                    }
                    break;
                }
                case "11":{
                    if(!activeUser.getAccessLevel().equals(AccessLevel.GUEST)){
                        activeUser.getWatchlist().add(movie);
                    }
                    break;
                }
                case "12":{
                    if(!activeUser.getAccessLevel().equals(AccessLevel.GUEST)){
                        activeUser.getFavorites().add(movie);
                    }break;
                }
            }
        }
    }
}