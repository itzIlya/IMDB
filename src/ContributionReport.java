public class ContributionReport extends Report{
    ContributionReportFlag contributionReportFlag;

    public ContributionReport(Member sender, ReportReceiver receiver, String text, ContributionReportFlag contributionReportFlag) {
        super(sender, receiver, text);
        this.contributionReportFlag = contributionReportFlag;
    }
}
