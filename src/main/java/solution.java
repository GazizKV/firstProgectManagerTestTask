import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class solution {
    public static Set<Task> setOfTask = new HashSet<>();
    public static File file = new File("../resources/file.txt");

    public class Task {
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

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String[] inputStringLine;
            while (true) {
                inputStringLine = bufferedReader.readLine().split("\\t");
                if(inputStringLine[0].equals("login")) continue;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
