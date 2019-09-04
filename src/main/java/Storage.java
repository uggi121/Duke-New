import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filepath;

    public Storage() {
        filepath = Paths.get(".").toAbsolutePath().getParent()
                .toString() + "\\data\\tasks.txt";
    }

    public ArrayList<String> loadTasks() throws FileNotFoundException {
        File f = new File(filepath);
        Scanner sc = new Scanner(f);
        ArrayList<String> tasks = new ArrayList<>();
        while (sc.hasNext()) {
            tasks.add(sc.nextLine());
        }
        sc.close();
        return tasks;
    }

    public void saveTasks(ArrayList<String> tasks) throws IOException {
        File f = new File(filepath);
        FileWriter writer = new FileWriter(f);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            for (String s : tasks) {
                writer.write(s + "\n");
            }
        } finally {
            writer.close();
        }
    }

    public void flushData() throws DukeException {
        try {
            File f = new File(filepath);
            f.delete();
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File not found. The directory entered is invalid");
        }
    }
}
