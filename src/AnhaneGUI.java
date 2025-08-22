import java.util.*;
import javax.swing.*;
import java.util.List;
import java.util.random.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnhaneGUI implements ActionListener{
    private int count = AnhaneGUIManager.loadFromFile();;
    private JLabel label;
    private JPanel panel;
    private JFrame frame;
    private JButton button;
    private JButton delete;
    
    //FIXME implement a loop to add all images.
    private JLabel imageLabel;
    private ArrayList<ImageIcon> images;
    private int currIndex = 0;
    private Random random = new Random();

    public AnhaneGUI(){
        frame = new JFrame();
        images = new ArrayList<>();

        AnhaneGUIManager pics = new AnhaneGUIManager();
        images = pics.addAllImages();
        

        button = new JButton("Click for free kohane pics");
        button.addActionListener(this);
        delete = new JButton("Click to delete history [i dont judge ;)]: ");
        delete.addActionListener(this);

        label = new JLabel("Number of times caught lacking: "+count);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        imageLabel = new JLabel();


        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(button);
        panel.add(delete);
        panel.add(imageLabel);
        
        //default setup
        frame.add(panel,BorderLayout.CENTER);
        //frame.setSize(500,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("An Simulator");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        new AnhaneGUI();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button){
            count++;
            AnhaneGUIManager.saveToFile(count);
            label.setText("Number of times caught lacking: "+count);

            int rand = random.nextInt(images.size());

            currIndex = rand;
            //currIndex = images.size()-1; testing purposes
            imageLabel.setIcon(images.get(currIndex));

        }
        else if (e.getSource() == delete){
            count = 0;
            AnhaneGUIManager.saveToFile(count);
            label.setText("Number of times caught lacking: " + count);
            imageLabel.setIcon(new ImageIcon(getClass().getResource("/images/exit1.jpeg")));
        }
        
        
    }
    
}
