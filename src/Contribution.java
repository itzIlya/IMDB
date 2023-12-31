import java.sql.Time;
import java.sql.Timestamp;

public class Contribution {
    Member contributor;
    ContributionType type;
    String data;
    private final Timestamp timeOfCreation;

    public Contribution(Member contributor, ContributionType type, String data) {
        this.contributor = contributor;
        this.type = type;
        this.data = data;
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

    public ContributionType getType() {
        return type;
    }

    public void setType(ContributionType type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
