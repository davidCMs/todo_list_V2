import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveLoad {
    static String fileExtension = ".tls";
    static String loadedFile;
    static void saveFileWithExtension(List<Task> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName + fileExtension))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Task> loadFileWhitExtension(String fileName) {
        List<Task> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName + fileExtension))) {
            list = (List<Task>) ois.readObject();
            System.out.println("Loaded tasks from file: " + fileName + fileExtension);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadedFile = fileName;
        return list;
    }
    static void saveFile(List<Task> list, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Task> loadFile(String fileName) {
        List<Task> list = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            list = (List<Task>) ois.readObject();
            System.out.println("Loaded tasks from file: " + fileName + fileExtension);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        loadedFile = fileName;
        return list;
    }
    public static String getLoadedFile() {
        return loadedFile;
    }
    public static void setLoadedFile(String setLoadedFile) {
        loadedFile = setLoadedFile;
    }
}
