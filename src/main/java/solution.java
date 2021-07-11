import java.io.*;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.*;

public class solution {
    public static Set<Task> setOfTask = new HashSet<>();
    public static File file = new File("../resources/file.txt");

    public static class Task {
        Double tid;
        Short microtasks;
        Calendar assinged_ts;
        Calendar closed_ts;

        public Task(Double tid, Short microtasks, Calendar assinged_ts, Calendar closed_ts) {
            this.tid = tid;
            this.microtasks = microtasks;
            this.assinged_ts = assinged_ts;
            this.closed_ts = closed_ts;
        }
    }

    public static Task parseNextTask(String[] nextLine) {
        Calendar calendarAssigned_ts = new GregorianCalendar();
        calendarAssigned_ts.set(Calendar.YEAR, Integer.parseInt(nextLine[3].split("-")[0]));
        calendarAssigned_ts.set(Calendar.MONTH, Integer.parseInt(nextLine[3].split("-")[1]));
        calendarAssigned_ts.set(Calendar.DATE, Integer.parseInt(nextLine[3].split("-")[2]));
        calendarAssigned_ts.set(Calendar.HOUR, Integer.parseInt(nextLine[4].split(":")[0]));
        calendarAssigned_ts.set(Calendar.MINUTE, Integer.parseInt(nextLine[4].split(":")[1]));
        calendarAssigned_ts.set(Calendar.SECOND, Integer.parseInt(nextLine[4].split(":")[2]));
        Calendar calendarClosed_ts = new GregorianCalendar();
        calendarClosed_ts.set(Calendar.YEAR, Integer.parseInt(nextLine[4].split("-")[0]));
        calendarClosed_ts.set(Calendar.MONTH, Integer.parseInt(nextLine[4].split("-")[1]));
        calendarClosed_ts.set(Calendar.DATE, Integer.parseInt(nextLine[4].split("-")[2]));
        calendarClosed_ts.set(Calendar.HOUR, Integer.parseInt(nextLine[5].split(":")[0]));
        calendarClosed_ts.set(Calendar.MINUTE, Integer.parseInt(nextLine[5].split(":")[1]));
        calendarClosed_ts.set(Calendar.SECOND, Integer.parseInt(nextLine[5].split(":")[2]));
        return new Task(Double.parseDouble(nextLine[1]),
                Short.parseShort(nextLine[2]),
                calendarAssigned_ts,
                calendarClosed_ts);
    }

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String[] inputStringLine;
            while (true) {
                inputStringLine = bufferedReader.readLine().split("\\t");
                if(inputStringLine[0].equals("login")) continue;
                else if(inputStringLine.length == 0) break;
                else {
                    setOfTask.add(parseNextTask(inputStringLine));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
