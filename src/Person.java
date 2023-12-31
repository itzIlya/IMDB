import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.UUID;

public class Person implements ReportReceiver{
    String name;
    String lastName;
    UUID uuid;
    ArrayList<Member> followers;
    ArrayList<RoleInMovie> roleInMovies ;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
        this.uuid = UUID.randomUUID();
        this.followers = new ArrayList<>();
        this.roleInMovies = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ArrayList<Member> getFollowers() {
        return followers;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setFollowers(ArrayList<Member> followers) {
        this.followers = followers;
    }

    public ArrayList<RoleInMovie> getRoleInMovies() {
        return roleInMovies;
    }

    public void setRoleInMovies(ArrayList<RoleInMovie> roleInMovies) {
        this.roleInMovies = roleInMovies;
    }

    public Person addNewPersonToDatabase(Member member) {
        if (member.getAccessLevel() == AccessLevel.ADMIN) {
            DBLists.addToPersonArrayList(this);
            return this;
        } else {
            System.out.println("Access denied. User with permission<" + member.getAccessLevel().toString() + "> does not have Admin permissions");
            return null;
        }
    }
    public boolean removePersonFromDatabase(Member member){
        if(member.getAccessLevel()==AccessLevel.ADMIN){
            if(DBLists.removeFromPersonArrayList(this)){
                return true;
            }
            else{
                System.out.println("Failed to remove person from database");
                return false;
            }
        }
        else{
            System.out.println("Access denied. User with permission<"+member.getAccessLevel().toString()+"> does not have Admin permissions");
            return false;
        }
    }
    @Override
    public Report reportThis(Object personReportFlag, Member sender, String text) {
        PersonReport report = new PersonReport( sender,  this,  text,(PersonReportFlag) personReportFlag);
        DBLists.addToUnhandledReports(report);
        return  new PersonReport( sender,  this,  text,(PersonReportFlag) personReportFlag);
    }
}
