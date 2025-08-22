import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.io.File;

public class AnhaneGUIManager {

    private ArrayList<ImageIcon> privList;
    public AnhaneGUIManager(){
        privList = new ArrayList<ImageIcon>();
    }

    //"AnhaneClicks.txt"
    public static void saveToFile(int count){
        
        try (PrintWriter pr = new PrintWriter(new FileWriter("AnhaneClicks.txt"))){
            pr.println(count);
        }
        catch (IOException e){
            System.out.println("Error with saving! "+ e.getMessage());
        }

    }

    public static int loadFromFile(){
        try (Scanner fs = new Scanner(new File("AnhaneClicks.txt"))){
            int num = fs.nextInt();
            return num;
        }
        catch (FileNotFoundException e){
            System.out.println("Error save file not found: "+e.getMessage());
        }
        return 0;

    }

    public ArrayList<ImageIcon> addAllImages(){
        String folderPath = Paths.get("src","images").toString();
        File folder = new File(folderPath);
        

        File[] files = folder.listFiles();

        if (files != null){
            for (File f: files){
                if (f.isFile() && isImageFile(f.getName())){
                    ImageIcon a = new ImageIcon(f.getAbsolutePath());
                    privList.add(a);
                }
            }
        }
        else{
            System.out.println("Images folder is empty");
        }
        return privList;
        
        
    }
    public boolean isImageFile(String name){
        name = name.toLowerCase();
        if (name.endsWith(".png") || name.endsWith(".jpg")|| name.endsWith(".jpeg")){
            return true;
        }
        return false;
    }
    
}
