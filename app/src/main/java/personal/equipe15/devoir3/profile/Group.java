package personal.equipe15.devoir3.profile;

import java.util.ArrayList;

/**
 * An group's profile: has members
 * */
public class Group extends Profile {

    private ArrayList<Profile> members;

    public Group(String name, String description, int id) {
        super(name, description, id);
        members = new ArrayList<>();
    }

    public ArrayList<Profile> getMembers(){
        return this.members;
    }

    public void addMember(User profile){
        this.members.add(profile);
    }

    @Override
    public String toString(){
        String str = super.toString();
        str += "\nMembers:";
        for(Profile member: members){
            str += "\n  -" + member.name;
        }
        return str;
    }
}
