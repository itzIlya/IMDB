import java.util.ArrayList;

public class ForumReport extends Report{
    ForumReportFlag forumreportFlag;

    public ForumReport(Member sender, ReportReceiver receiver, String text, ForumReportFlag forumreportFlag) {
        super(sender, receiver, text);
        this.forumreportFlag = forumreportFlag;
    }
}
