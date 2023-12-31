public class RoleInMovie {
    private Role role;
    private Movie movie;
    private Person person;

    public RoleInMovie(Role role,Movie movie, Person person) {
        this.role = role;
        this.movie =movie;
        this.person = person;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
