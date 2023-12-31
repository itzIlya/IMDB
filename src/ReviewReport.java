public class ReviewReport extends Report{
    ReviewReportFlag reviewReportFlag;

    public ReviewReport(Member sender, ReportReceiver receiver, String text, ReviewReportFlag reviewReportFlag) {
        super(sender, receiver, text);
        this.reviewReportFlag = reviewReportFlag;
    }

}
