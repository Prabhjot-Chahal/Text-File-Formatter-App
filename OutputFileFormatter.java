import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class OutputFileFormatter
{
    private Printstream file;

    public OutputFileFormatter(String filename) throws FileNotFoundException
    {
        File checkFile = new File(filename);

        if (!checkFile.isFile())
        {
            throw new FileNotFoundException(filename + " doesn't exist");
        }

        Outputfile = new Printstream(checkFile);

        printOutputFile();
    }

    private String formatStudent(Student student)
    {
        String[] courses = student.getCourses();

        String idString = Long.toString(student.id) + ", ";

        String studentName = student.getName() + ", ";

        String outputString = '';

        for (String course : courses)
        {
            float totalGrade = student.getGrades(course)[4];

            outputString = idString;
            outputString += studentName;
            outputString += course + ", ";
            outputString += String.format("%.1f\n", totalGrade);
        }
        
        return outputString;
    }

    private void printOutputFile(PrintStream output) 
    {
        this.students.forEach((Long id, Student student) -> {
            output.format(formatStudent());
        });
    }
}