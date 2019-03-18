package templater;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import Model.Room;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


@Controller
public class PageController{

    private int doorNumber=0;

    @GetMapping("/doors")
    public String page() {
        return "page";
    }

    @GetMapping("/room")
    public String room(@RequestParam String door, Model model){
            model.addAttribute("name", door);
            doorNumber = Integer.valueOf(door) - 1;

            if (streamReader().startsWith(String.valueOf(doorNumber)))
                Room.list.get(doorNumber).setLight(Boolean.valueOf(streamReader().substring(2, streamReader().indexOf("'"))));

            model.addAttribute("bgcolor", Room.list.get(doorNumber).getColor());

            if (Room.list.get(doorNumber).isLight()) {
                model.addAttribute("enter", "off");
            } else {
                model.addAttribute("enter", "on");
            }


            return "page2";
    }

    @PostMapping("/room")
    public String pagePush(@RequestParam String enterLigth, Model model){
        model.addAttribute("name",doorNumber+1);

        if (enterLigth.equals("on")) {
            Room.list.get(doorNumber).setLight(true);
            model.addAttribute("enter", "off");
        } else {
            Room.list.get(doorNumber).setLight(false);
            model.addAttribute("enter", "on");
        }

        streamWriter(Room.list.get(doorNumber).toString());
        
        model.addAttribute("bgcolor",Room.list.get(doorNumber).getColor());

        return "page2";
    }




    public static void streamWriter(String status){
        try(FileWriter writer=new FileWriter("src\\main\\resources\\status.txt",false)){
            writer.write(status);
            writer.append("\n");

        }catch (IOException ex){
            System.out.println("error");
        }
    }

    private String streamReader(){

        String response;
        char[] mas=new char[10];
        int i=0;


        try(FileReader reader=new FileReader("src\\main\\resources\\status.txt")) {
            int c;
            while((c=reader.read())!=-1){
                mas[i]=(char)c;
                i++;
            }
        }catch (IOException ex){
            System.out.println("error");
         }


        response=String.valueOf(mas);
        response=response.substring(0,response.indexOf("\n"))+"'";
        return response;
    }


}
