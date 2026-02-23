import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Paths;

import java.net.URL;
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

    public ArrayList<ImageIcon> addAllImages() {
        privList.clear();
    
        try {
            URL folderURL = getClass().getResource("/images");
            if (folderURL == null) {
                System.out.println("Images folder not found!");
                return privList;
            }
    
            File folder = new File(folderURL.toURI());
            File[] files = folder.listFiles();
    
            if (files != null) {
                for (File f : files) {
                    if (f.isFile() && isImageFile(f.getName())) {
                        URL imgURL = getClass().getResource("/images/" + f.getName());
                        privList.add(new ImageIcon(imgURL)); 
                    }
                }
            }
    
        } catch (Exception e) {
            e.printStackTrace();
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
