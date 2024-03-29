import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

class OutputFileFormatter
{
    private PrintStream file;

    public OutputFileFormatter(String filename) throws FileNotFoundException
    {
        File checkFile = new File(filename);

        try {
            checkFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file = new PrintStream(checkFile);
    }

    private String[] formatStudent(Student student)
    {
        String[] courses = student.getCourses();

        String idString = Long.toString(student.getId()) + ", ";

        String studentName = student.getName() + ", ";

        String[] results = new String[courses.length];

        int i = 0;
        for (String course : courses)
        {
            String outputString = "";
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
            results[i++] = outputString;

            System.out.print(outputString);
        }
        
        return results;
    }

    public void printOutputFile() 
    {
        App.students.forEach((Long id, Student student) -> {
            String[] results = formatStudent(student);
            for (String result : results)
            {
                file.format(result);
            }
        });
    }
}