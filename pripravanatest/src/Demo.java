import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Demo extends JFrame{
    private JPanel panelMain;
    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenuItem miOpenFile;

    public File selectedFile;
    public String line;
    public String parametry = "";

    public Demo(){
        initComponents();

    }

    private void initComponents(){
        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuBar.add(menuFile);

        miOpenFile = new JMenuItem("File");
        menuFile.add(miOpenFile);

        miOpenFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                readFile();
            }
        });

        setJMenuBar(menuBar);
    }

    private void readFile(){
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();

            try(Scanner sc = new Scanner(
                    new BufferedReader(
                            new FileReader(selectedFile)
                    )
            )) {
                while(sc.hasNextLine()){
                    line = sc.nextLine();
                    parametry += line;
                }
            } catch (FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Demo d = new Demo();
        d.setContentPane(d.panelMain);
        d.setTitle("Příprava na Test");
        d.setDefaultCloseOperation(EXIT_ON_CLOSE);
        d.setSize(500,500);
        d.setVisible(true);
    }
}
