public class CommentReport extends Report{
    CommentReportFlag commentReportFlag;
    public CommentReport(CommentReportFlag commentReportFlag,Member sender, ReportReceiver receiver, String text) {
        super(sender,receiver,text);
        this.commentReportFlag = commentReportFlag;
    }
}
