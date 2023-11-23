import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FileParser
{
    private Scanner file;

    public FileParser(String filename) throws Exception
    {
        File checkFile = new File(filename);

        if (!checkFile.isFile())
        {
            throw new FileNotFoundException(filename + " doesn't exist");
        }

        file = new Scanner(checkFile);
    }

    public void readNameFile() throws Exception
    {
        String line;
        String[] splitLine;
        Long id;
        Student student;
        String errorMessage;

        while (file.hasNextLine())
        {
            line = file.nextLine();
            splitLine = line.split(", ");
            
            if (splitLine.length != 2)
            {
                errorMessage = String.format(
                    "Expected 2 fields got %d fields", 
                    splitLine.length
                );
                throw new Exception(errorMessage);
            }

            try{
                id = Long.parseLong(splitLine[0].trim());
            }
            catch(Exception e)
            {
                errorMessage = "First Field must be a number";
                throw new Exception(errorMessage);
            }

            if (App.students.containsKey(id))
            {
                errorMessage = "Duplicate Student Encountered!";
                throw new Exception(errorMessage);
            }

            student = new Student(splitLine[1], id);

            App.students.put(id, student);
        }
    }

    public void readCourseFile() throws Exception
    {
        String line;
        String[] splitLine;
        long id;
        Student student;
        Float[] grades = new Float[4];
        String errorMessage;

        while(file.hasNextLine())
        {
            line = file.nextLine();
            splitLine = line.split(",");

            if (splitLine.length != 6)
            {
                errorMessage = String.format(
                    "Expected 6 fields got %d fields", 
                    splitLine.length
                );
                throw new Exception(errorMessage);
            }

            try{
                id = Long.parseLong(splitLine[0].trim());
            }
            catch(Exception e)
            {
                errorMessage = "First Field must be a number";
                throw new Exception(errorMessage);
            }

            int j = 2;
            for (int i = 0; i < grades.length; i++)
            {
                try {
                    grades[i] = Float.parseFloat(splitLine[j++].trim());
                } catch (Exception e) {
                    errorMessage = "All fields must be a number except 2nd Field";
                    throw new Exception(errorMessage);
                }
            }

            if (!App.students.containsKey(id))
            {
                errorMessage = "Student does not exist in NameFile";
                throw new Exception(errorMessage);
            }

            student = App.students.get(id);

            student.addCourse(splitLine[1], grades);
        }
    }
    
}