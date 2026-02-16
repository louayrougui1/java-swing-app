package Model;

import javax.swing.*;
import java.util.ArrayList;

public class DataProfile {
    static ArrayList<Profile> data = new ArrayList<>();

    public static void resetData(){
        data = new ArrayList<>();
    }
    public static void addProfile(Profile p){
        for(Profile user:data){
            if(user.pseudo.equals(p.pseudo)){
                return;
            }
        }
        data.add(p);
    }

    public static void delProfile(int index){
        data.remove(index);
        System.out.println(data);
    }

    public static Profile getProfile(int index){
        return data.get(index);
    }

    public static Profile getProfile(String pseudo){
        for (Profile user:data){
            if(user.pseudo.equals(pseudo)){
                return user;
            }
        }
        return null;
    }
    public static void modProfile(Profile profile){
        for (Profile user:data){
            if(user.pseudo.equals(profile.pseudo)){
                user.setNom(profile.getNom());
                user.setPrenom(profile.getPrenom());
                return;
            }
        }
    }

    public static ArrayList<Profile> getData() {
        return data;
    }
}
