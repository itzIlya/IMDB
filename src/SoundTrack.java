import Exceptions.GuestAccessRefusedException;

import java.sql.Timestamp;
import java.util.ArrayList;

public class SoundTrack {
    Member contributor;
    Person composer;
    ArrayList<Person> performedBy;
    String Title;
    private final Timestamp timeOfCreation;

    public SoundTrack(Member contributor, Person composer, ArrayList<Person> performedBy, String title) throws GuestAccessRefusedException {
        if (contributor.getAccessLevel().equals(AccessLevel.GUEST)){
            throw new GuestAccessRefusedException();
        }
        this.contributor = contributor;
        this.composer = composer;
        this.performedBy = performedBy;
        this.Title = title;
        this.timeOfCreation = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getTimeOfCreation() {
        return timeOfCreation;
    }

    public Member getContributor() {
        return contributor;
    }

    public void setContributor(Member contributor) {
        this.contributor = contributor;
    }

    public Person getComposer() {
        return composer;
    }

    public void setComposer(Person composer) {
        this.composer = composer;
    }

    public ArrayList<Person> getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(ArrayList<Person> performedBy) {
        this.performedBy = performedBy;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
