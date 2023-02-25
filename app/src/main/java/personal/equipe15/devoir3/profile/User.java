package personal.equipe15.devoir3.profile;

import java.util.ArrayList;

/**
 * A user's profile: has groups
 * */
public class User extends Profile {

    private ArrayList<Profile> groups;

    public User(String name, String description, int id) {

        super(name, description, id);
        this.groups = new ArrayList<Profile>();
    }

    public void addGroup(Group group){

        //Add the group to the user's group list
        groups.add(group);

        //Add the user to the group
        group.addMember(this);
    }

    public ArrayList<Profile> getGroups(){

        return groups;
    }

    @Override
    public String toString(){
        String str = super.toString();
        str += "\nGroups:";
        for(Profile group: groups){
            str += "\n  -" + group.name;
        }
        return str;
    }
}
