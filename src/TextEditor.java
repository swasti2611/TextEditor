import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newFile,openFile,saveFile;
    JMenuItem cut,copy,paste,selectAll,close;

    JTextArea textArea;
    TextEditor(){
        frame=new JFrame();




        //initilize menubar
        menuBar=new JMenuBar();
        //intilize textarea
        textArea=new JTextArea();



        file=new JMenu("file");
        edit=new JMenu("edit");
        //initilize menu items

        newFile=new JMenuItem("new Window");
        openFile=new JMenuItem("open File");
        saveFile=new JMenuItem("save File");

        //add ctions listeners to new file
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

       file.add(newFile);
       file.add(openFile);
       file.add(saveFile);

       //inilitiliz edit meny items
        cut=new JMenuItem("cut");
        copy=new JMenuItem("copy");
        paste=new JMenuItem("paste");
        selectAll=new JMenuItem("selectAll");
        close=new JMenuItem("close");


        //add actions to edit menuItems
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

//



        //add menu to menubar
        menuBar.add(file);
        menuBar.add(edit);


        frame.setJMenuBar(menuBar);

        //create content panel
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area
        panel.add(textArea,BorderLayout.CENTER);

        //create scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //addscroll to pannel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);


        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");

        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
          textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            System.exit(0);
        }
        if(actionEvent.getSource()==openFile){
            //open a file
            JFileChooser fileChooser=new JFileChooser("c");
            int chooseOption= fileChooser.showOpenDialog(null);
            //if clock on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){

                File file =fileChooser.getSelectedFile();
                String filepath=file.getPath();
                try{
                    //initilize file reader
                    FileReader fileReader=new FileReader(filepath);
                    //initiliz buffer reqader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    while ((intermediate=bufferedReader.readLine())!=null){
                        output=output+intermediate+"\n";
                    }

                   //set the output string to textarea
                    textArea.setText(output);
                }catch(FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
if(actionEvent.getSource()==saveFile){
    JFileChooser fileChooser=new JFileChooser("C");
    //get choose option file chooser
    int chooseOption=fileChooser.showSaveDialog(null);
    //cheack if we clicked save button
    if(chooseOption==JFileChooser.APPROVE_OPTION){
        //create directorybpath
        File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
        try{
           FileWriter fileWriter=new FileWriter(file);

           //initilize buffer reader
            BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
            //write contents of text area file
            textArea.write(bufferedWriter);
            bufferedWriter.close();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
 if(actionEvent.getSource()==newFile){
     TextEditor newTextEditor=new TextEditor();
 }
    }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();

    }
}