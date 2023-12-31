import Exceptions.AdminAccessRequiredException;

import java.rmi.server.UID;
import java.sql.Timestamp;
import java.util.UUID;

public class Report implements NotificationSender{
    final private UUID uuid;
    private ReportStatus reportStatus;
    private Member sender; // editor or member
    private ReportReceiver receiver; // the object being reported
    private String text;
    private Member reportHandler; // or suggestion handler
    private Timestamp time;

    public Report(Member sender, ReportReceiver receiver, String text) {
        this.sender = sender;
        this.receiver = receiver;
        this.text = text;
        this.uuid = UUID.randomUUID();
        this.reportStatus = ReportStatus.UNHANDLED;
        this.time = new Timestamp(System.currentTimeMillis());
    }

    public Member getReportHandler() {
        return reportHandler;
    }
    public void setReportHandler(Member reportHandler) {
        this.reportHandler = reportHandler;
    }

    public ReportStatus getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(ReportStatus reportStatus) {
        this.reportStatus = reportStatus;
    }

    public Member getSender() {
        return sender;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public ReportReceiver getReceiver() {
        return receiver;
    }

    public void setReceiver(ReportReceiver receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getUid() {
        return uuid;
    }


    public static void addNewReport(Report report){
        DBLists.addToUnhandledReports(report);
    }
    public void handleReport( Member reportHandler) throws AdminAccessRequiredException {

        if (reportHandler.getAccessLevel() == AccessLevel.ADMIN && this.getReportStatus().compareTo(ReportStatus.UNHANDLED) == 0) {
            this.setReportStatus(ReportStatus.HANDLED);
            this.setReportHandler(reportHandler);
            DBLists.addToHandledReports(this);
            DBLists.removeFromUnhandledReports(this);

        }
        else {
            throw new AdminAccessRequiredException();
        }
    }

    @Override
    public void sendNotification(String title, String body, NotificationSender notificationSender, Member notificationReceiver) {
        title = "New Report added!";
        body = "Report Sender: " + this.sender.getUsername() + "\nat: " + this.time + "\nStatus: "+ this.reportStatus;
        notificationReceiver.getNotifications().add(new Notification(title,body,notificationSender,notificationReceiver));
    }
}
