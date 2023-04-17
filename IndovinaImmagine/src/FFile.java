import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
public class FFile {
    public FFile() {}

    public static void write(Object obj, String fileName) {
        try {

            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(obj);
            objectOut.close();

        } catch (Exception e) {
            //e.printStackTrace();
        }
    }
}