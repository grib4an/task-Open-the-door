package Model;

import java.util.ArrayList;

public class Room {
    private String color;
    private Boolean light=false;
    public static ArrayList<Room> list=new ArrayList<>();

   public Room(String color){
        this.color=color;
        list.add(this);
    }

    public String getColor() {
        if (light)
            return color;
        else return "white";
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isLight() {
        return light;
    }

    public void setLight(boolean light) {
          this.light = light;
    }

    @Override
    public String toString() {
        return list.indexOf(this)+":"+light.toString();
    }
}
