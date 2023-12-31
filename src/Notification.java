import java.sql.Timestamp;

public class Notification {
    NotificationStatus notificationStatus;
    String Title;
    String Body;
    // option to redirect?
    NotificationSender notificationSender;
    Member notificationReceiver;
    Timestamp timestamp;

    public Notification(String title, String body, NotificationSender notificationSender, Member notificationReceiver) {
        this.notificationStatus = NotificationStatus.UNREAD;
        Title = title;
        Body = body;
        this.notificationSender = notificationSender;
        this.notificationReceiver = notificationReceiver;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public String getTitle() {
        return Title;
    }

    public String getBody() {
        return Body;
    }

    public NotificationSender getNotificationSender() {
        return notificationSender;
    }

    public Member getNotificationReceiver() {
        return notificationReceiver;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
