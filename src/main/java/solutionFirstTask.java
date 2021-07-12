import java.io.*;
import java.util.*;

public class solutionFirstTask {
    public static ArrayList<Task> arrayList = new ArrayList<>();
    public static File file = new File("C:\\Users\\valit\\progectManagerTestTask\\src\\main\\resources\\file.txt");

    public static class Task {
        Integer tid;
        Double microtasks;
        Calendar assinged_ts;
        Calendar closed_ts;
        Double averageTimePerMicrotask;

        public Task(Integer tid, Double microtasks, Calendar assinged_ts, Calendar closed_ts) {
            this.tid = tid;
            this.microtasks = microtasks;
            this.assinged_ts = assinged_ts;
            this.closed_ts = closed_ts;
            averageTimePerMicrotask = (closed_ts.getTimeInMillis() - assinged_ts.getTimeInMillis())/(microtasks*1000);
        }
    }

    public static Task parseNextTaskAndGetNewTask(String[] nextLine) {
        Calendar calendarAssigned_ts = new GregorianCalendar();
        calendarAssigned_ts.set(Calendar.YEAR, Integer.parseInt(nextLine[3].split("-")[0]));
        calendarAssigned_ts.set(Calendar.MONTH, Integer.parseInt(nextLine[3].split("-")[1]));
        calendarAssigned_ts.set(Calendar.DATE, Integer.parseInt(nextLine[3].split("-")[2]));
        calendarAssigned_ts.set(Calendar.HOUR, Integer.parseInt(nextLine[4].split(":")[0]));
        calendarAssigned_ts.set(Calendar.MINUTE, Integer.parseInt(nextLine[4].split(":")[1]));
        calendarAssigned_ts.set(Calendar.SECOND, Integer.parseInt(nextLine[4].split(":")[2]));
        Calendar calendarClosed_ts = new GregorianCalendar();
        calendarClosed_ts.set(Calendar.YEAR, Integer.parseInt(nextLine[5].split("-")[0]));
        calendarClosed_ts.set(Calendar.MONTH, Integer.parseInt(nextLine[5].split("-")[1]));
        calendarClosed_ts.set(Calendar.DATE, Integer.parseInt(nextLine[5].split("-")[2]));
        calendarClosed_ts.set(Calendar.HOUR, Integer.parseInt(nextLine[6].split(":")[0]));
        calendarClosed_ts.set(Calendar.MINUTE, Integer.parseInt(nextLine[6].split(":")[1]));
        calendarClosed_ts.set(Calendar.SECOND, Integer.parseInt(nextLine[6].split(":")[2]));
        return new Task(Integer.parseInt(nextLine[1].substring(0, nextLine[1].length()-2)),
                Double.parseDouble(nextLine[2]),
                calendarAssigned_ts,
                calendarClosed_ts);
    }

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String[] inputStringLine;
            while (bufferedReader.ready()) {
                inputStringLine = bufferedReader.readLine().split("[\\t ]");
                if(!inputStringLine[0].contains("0")) continue;
                else arrayList.add(parseNextTaskAndGetNewTask(inputStringLine));
            }
            if(arrayList.size() > 10 ) {
                for (int i = 0; i < 10; i++) {
                    System.out.println(arrayList.get(i).averageTimePerMicrotask);
                    System.out.println(arrayList.get(i).assinged_ts.getTime());
                    System.out.println(arrayList.get(i).closed_ts.getTime());
                    System.out.println(arrayList.get(i).tid);
                }
            }
            Double sumAverageTimePerTask = new Double(0.0);
            for (Task task : arrayList) {
                sumAverageTimePerTask += task.averageTimePerMicrotask;
            }
            Double generalAverageTimePerTask = sumAverageTimePerTask/arrayList.size();
            System.out.println(generalAverageTimePerTask);
            Double costOfOneMicrotask = generalAverageTimePerTask/30;
            System.out.println("Cost of average task for cost of 30 sec = N" + "\t" +costOfOneMicrotask + " * N");
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
