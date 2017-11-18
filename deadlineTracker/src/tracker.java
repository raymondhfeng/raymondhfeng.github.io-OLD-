/**
 * Created by Raymond on 9/9/2017.
 */
import sun.reflect.generics.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static javax.script.ScriptEngine.FILENAME;

public class tracker {
    public static void main(String[] args) {
//        LocalDateTime currentTime = LocalDateTime.now();
//        System.out.println(currentTime);
//        LocalDateTime later = LocalDateTime.of(2017, 9, 22, 22,15,12);
//        System.out.println(later);
//        LocalDateTime until = LocalDateTime(later, LocalDateTime);
        TreeMap<String, String> deadlines = new TreeMap<>();
//        deadlines.put("2017-09-13T17:00:00Z", "CS170_HW_2");
//        deadlines.put("2017-09-24T23:59:00Z", "Pacman_Search_Contest");
//        deadlines.put("2017-10-04T19:00:00Z", "EE16A_Midterm_1");
//        deadlines.put("2017-11-06T19:00:00Z", "EE16A_Midterm_2");
//        deadlines.put("2017-12-13T11:30:00Z", "EE16A_Final");
        BufferedReader br = null;
        FileReader fr = null;
        try {

            //br = new BufferedReader(new FileReader(FILENAME));

            fr = new FileReader("C:\\Users\\Raymond\\Desktop\\raymondhfeng.github.io\\deadlineTracker\\src\\deadlines");
            br = new BufferedReader(fr);

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                String nextLine = br.readLine();
                deadlines.put(sCurrentLine, nextLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String> keyIterator = deadlines.keySet().iterator();
        String deadline = "";
        String deadlineName = "";
        while(keyIterator.hasNext()) {
            deadline = keyIterator.next();
            deadlineName = deadlines.get(deadline);
            final SimpleDateFormat apiFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
            apiFormat.setTimeZone(TimeZone.getTimeZone("PST"));
            final Date deadlineDate;
            try{
                deadlineDate = apiFormat.parse(deadline);
            } catch(java.text.ParseException e){
                System.out.println(e.getMessage());
                return;
            }
            final long millis = deadlineDate.getTime() - System.currentTimeMillis();

            final String hms = String.format("%02d:%02d:%02d:%02d",TimeUnit.MILLISECONDS.toDays(millis),
                    TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(millis)),
                    TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            System.out.println(deadlineName + ": " + hms);
        }
        JTextField myOutput = new JTextField(16);
        myOutput.setText("some text");

//        JTextArea textArea = new JTextArea(
//                "This is an editable JTextArea. " +
//                        "A text area is a \"plain\" text component, " +
//                        "which means that although it can display text " +
//                        "in any font, all of the text is in the same font."
//        );
//        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
//        new components.TextAreaDemo().setVisible(true);
    }
}
