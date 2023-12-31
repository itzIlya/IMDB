public class PersonReport extends Report{
    PersonReportFlag personReportFlag;

    public PersonReport(Member sender, ReportReceiver receiver, String text, PersonReportFlag personReportFlag) {
        super(sender, receiver, text);
        this.personReportFlag = personReportFlag;
    }
}
