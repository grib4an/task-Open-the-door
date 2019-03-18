package templater;

import Model.Room;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        createRoom();
        SpringApplication.run(Application.class, args);
        PageController.streamWriter("");
    }

    private static void createRoom(){
        Room room1=new Room("blue");
        Room room2=new Room("green");
        Room room3=new Room("red");
        Room room4=new Room("yellow");
    }

}