import java.util.HashMap;

public class App {

    public static HashMap<Long, Student> students = new HashMap<Long, Student>();

    public static void main(String[] args) throws Exception {
        FileParser nameFile = new FileParser("NameFile.txt");
        FileParser courseFile = new FileParser("CourseFile.txt");
        OutputFileFormatter outFile = new OutputFileFormatter("output.txt");

        nameFile.readNameFile();
        courseFile.readCourseFile();
        outFile.printOutputFile();
    }
}