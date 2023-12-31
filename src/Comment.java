import Exceptions.DuplicateReviewOrRatingInMovieException;

import java.util.ArrayList;

public class Comment implements ReportReceiver,NotificationSender{
    Comment replyTo;
    Member sender;
    String Body;
    ArrayList<Thumb> thumbs;

    public Comment(Comment replyTo, Member sender, String body) {
        this.replyTo = replyTo;
        this.sender = sender;
        this.Body = body;
        this.thumbs = new ArrayList<Thumb>();
    }

    public Comment getReplyTo() {
        return replyTo;
    }

    public Member getSender() {
        return sender;
    }

    public String getBody() {
        return Body;
    }

    public ArrayList<Thumb> getThumbs() {
        return thumbs;
    }

    public void setReplyTo(Comment replyTo) {
        this.replyTo = replyTo;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public void setBody(String body) {
        Body = body;
    }

    public void setThumbs(ArrayList<Thumb> thumbs) {
        this.thumbs = thumbs;
    }

    public int getThumbsUp(){
        int count=0;
        for (Thumb t : thumbs){
            if(t.thumbDir == ThumbDir.UP){
                count++;
            }
        }
        return count;
    }
    public int getThumbsDown(){
        int count=0;
        for (Thumb t : thumbs){
            if(t.thumbDir == ThumbDir.DOWN){
                count++;
            }
        }
        return count;
    }


    public boolean addNewThumb(Thumb thumb) throws DuplicateReviewOrRatingInMovieException {
        for(Thumb t: thumbs){
            if (thumb.getThumber().equals(t.getThumber())){
                throw new DuplicateReviewOrRatingInMovieException();
            }
        }
        if (thumb!=null){
            thumbs.add(thumb);
            return true;
        }
        return false;

    }
    @Override
    public Report reportThis(Object commentReport, Member sender,  String text) {
        CommentReport report = new CommentReport((CommentReportFlag) commentReport,sender,this,text);
        DBLists.addToUnhandledReports(report);
        return report;

    }


    @Override
    public void sendNotification( String title, String body,NotificationSender notificationSender, Member notificationReceiver) {
        if(title.equals("")){
            title = "Someone replied to your comment";
            body = "reply to: <"+ this.getReplyTo().getBody() + ">"+"\nreply: <" + this.Body+">";
        }
        notificationReceiver.getNotifications().add(new Notification(title,body,notificationSender,notificationReceiver));
    }
}
