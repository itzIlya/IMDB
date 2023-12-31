import Exceptions.InvalidSignUpFieldsException;
import Exceptions.InvalidUserNameException;

import java.time.LocalDate;
import java.util.*;


public class UserManagement{

    public static Member Login(String username, String password){
        ArrayList<Person> memberArrayList = DBLists.getPersonArrayList();
        for (Person person : memberArrayList){
            if (person instanceof Member){
                Member member = (Member) person;
                if (member.getUsername().equals(username)){
                    if (member.getPassword().equals(password)){
                        return member;
                    }
                }
            }

        }
        return null;
    }
    public static Member SignUp(HashMap<String,String> userDetails) throws InvalidUserNameException, InvalidSignUpFieldsException {
        for (Map.Entry<String, String> entry : userDetails.entrySet()){
            if (entry.getValue().equals("")){
                throw new InvalidSignUpFieldsException();
            }
        }
            ArrayList<Person> memdb = DBLists.getPersonArrayList();
            for (Person m : memdb){
                if(m instanceof Member){
                    if(((Member) m).getUsername().equals(userDetails.get("username"))){
                        throw new InvalidUserNameException();
                    }
                }
            }
            try{
            Member newMember = new Member(userDetails.get("username"),
                    String.valueOf(userDetails.get("password").hashCode()),
                    AccessLevel.MEMBER,
                    userDetails.get("name"),
                    userDetails.get("lastname"));

            DBLists.addToPersonArrayList(newMember);
            return newMember;
        }
        catch (Exception exception){
            throw new InvalidSignUpFieldsException();
        }
        //HashMap<String,String> userDetails = GetInput.getDetails();

    }

}
