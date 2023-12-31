import java.sql.Timestamp;
import java.util.ArrayList;

public class Forum implements ReportReceiver{
    private String Topic;
    private Member creator;
    private ArrayList<Comment> comments;
    private final Timestamp dateOfCreation;

    public Forum(String topic, Member creator) {
        Topic = topic;
        this.creator = creator;
        this.comments = new ArrayList<Comment>();
        this.dateOfCreation = new Timestamp(System.currentTimeMillis());
    }

    public String getTopic() {
        return Topic;
    }

    public Member getCreator() {
        return creator;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public Timestamp getDateOfCreation() {
        return dateOfCreation;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public void setCreator(Member creator) {
        this.creator = creator;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }


    @Override
    public Report reportThis(Object forumReport, Member sender,  String text) {
        ForumReport report = new ForumReport(sender,this,text,(ForumReportFlag) forumReport);
        DBLists.addToUnhandledReports(report);
        return report;

    }
}
