import java.util.HashMap;

public class App {

    public static HashMap<Long, Student> students = new HashMap<Long, Student>();

    public static void main(String[] args) {
        try {
            FileParser nameFile = new FileParser("NameFile.txt");
            FileParser courseFile = new FileParser("CourseFile.txt");
            OutputFileFormatter outFile = new OutputFileFormatter("output.txt");

            nameFile.readNameFile();
            courseFile.readCourseFile();
            outFile.printOutputFile();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}