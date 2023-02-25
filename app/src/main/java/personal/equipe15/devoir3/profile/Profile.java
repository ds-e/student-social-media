package personal.equipe15.devoir3.profile;

import java.util.ArrayList;

public abstract class Profile {

    public String name;
    public String description;
    private ArrayList<String> interests;
    private ArrayList<String> courses;
    public int id;
    private boolean isFriend;

    public Profile(String name, String description, int id){
        this.name = name;
        this.description = description;
        this.interests = new ArrayList<String>();
        this.courses = new ArrayList<String>();
        this.id = id;
        isFriend = false;
    }

    public boolean isFriend(){
        return isFriend;
    }

    public void addFriend(){
        isFriend = true;
    }

    public String getName() {
        return this.name;
    }

    public void addInterest(String interest){
        interests.add(interest);
    }

    public void addCourse(String course){ courses.add(course); }

    /**
     * Tells whether the current profile matches the search
     * @param search: the search string
     * @param type: the type of filter
     *            1: interests
     *            2: courses
     *            3: name
     *            otherwise, return true
     * @return true if the current profile matches the filter
     * */
    public boolean filter(String search, int type){

        //Filter by interest
        if(type == 1){

            for(String s: interests){
                if(s.contains(search)) return true;
            }
            return false;
        }
        //filter by course
        else if(type == 2){
            for(String s: courses){
                if(s.contains(search)) return true;
            }
            return false;
        }
        //filter by name
        else if(type == 3){
            return name.contains(search);
        }
        //no filter
        return true;
    }

    public String getDetails(){
        String str = description;
        str += "\nInterests: ";
        for(String interest: interests){
            str += interest +", ";
        }
        str = str.substring(0, str.length()-2);
        str += "\nCourses: ";
        for(String course: courses){
            str += course +", ";
        }
        str = str.substring(0, str.length()-2);
        return str;
    }


    @Override
    public String toString(){
        String str =  name + "'s profile" + "\nDescription: " + description + "\nInterests:";
        for(String interest: interests){
            str += "\n  -" + interest;
        }
        str += "\nCourses:";
        for(String course: courses){
            str += "\n  -" + course;
        }
        return str;
    }



    /**
     * Static part:
     * Responsible for simulating data in our application
     * */

    private static ArrayList<Profile> data;

    /**
     * Generate data for execution
     * */
    public static ArrayList<Profile> getData() {

        if(data != null){
            return data;
        }

        ArrayList<Profile> profiles = new ArrayList<Profile>();

        User Dana = new User("Dana", "Dana's profile description", 0);
        Dana.addCourse("IFT2905");
        Dana.addFriend();
        Dana.addInterest("Interface");
        User Pascal = new User("Pascal", "Pascal's profile description", 1);
        Pascal.addCourse("IFT2905");
        Pascal.addFriend();
        Pascal.addInterest("Chess");
        User Philippe = new User("Philippe", "Philippe's profile description", 2);
        Philippe.addCourse("IFT2905");
        Philippe.addFriend();
        Philippe.addInterest("Interface");
        User Vincent = new User("Vincent", "Vincent's profile description", 3);
        Vincent.addCourse("IFT2905");
        Vincent.addInterest("Chess");
        Vincent.addFriend();

        Group ChessClub = new Group("Chess club", "Chess club's page description", 4);
        ChessClub.addInterest("Chess");
        ChessClub.addFriend();
        Group IFT2905Course = new Group("IFT2905 help group", "IFT2905's page description", 5);
        IFT2905Course.addCourse("IFT2905");
        IFT2905Course.addInterest("Interface");
        IFT2905Course.addFriend();

        profiles.add(Dana);
        profiles.add(Pascal);
        profiles.add(Philippe);
        profiles.add(Vincent);

        profiles.add(ChessClub);
        profiles.add(IFT2905Course);

        Vincent.addGroup(IFT2905Course);
        Pascal.addGroup(IFT2905Course);
        Dana.addGroup(IFT2905Course);
        Philippe.addGroup(IFT2905Course);

        Vincent.addGroup(ChessClub);
        Pascal.addGroup(ChessClub);

        for(int i = 6; i < 25; i++){
            profiles.add(new User("Random profile " + i, "Random description", i));
        }

        data = profiles;
        return data;
    }



}
