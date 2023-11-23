import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

class OutputFileFormatter
{
    private PrintStream file;

    public OutputFileFormatter(String filename) throws FileNotFoundException
    {
        File checkFile = new File(filename);

        if (!checkFile.isFile())
        {
            throw new FileNotFoundException(filename + " doesn't exist");
        }

        file = new PrintStream(checkFile);

        printOutputFile(file);
    }

    private String formatStudent(Student student)
    {
        String[] courses = student.getCourses();

        String idString = Long.toString(student.getId()) + ", ";

        String studentName = student.getName() + ", ";

        String outputString = "";

        for (String course : courses)
        {
            float totalGrade = 0;
            try
            {
                totalGrade = student.getGrades(course)[4];
            }
            catch (Exception error)
            {
                System.out.println("Course Code doesn't exist");
            }

            outputString = idString;
            outputString += studentName;
            outputString += course + ", ";
            outputString += String.format("%.1f\n", totalGrade);
        }
        
        return outputString;
    }

    private void printOutputFile(PrintStream output) 
    {
        App.students.forEach((Long id, Student student) -> {
            output.format(formatStudent(student));
        });
    }
}