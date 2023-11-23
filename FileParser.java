import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FileParser
{
    private Scanner file;

    public FileParser(String filename) throws FileNotFoundException
    {
        File checkFile = new File(filename);

        if (!checkFile.isFile())
        {
            throw new FileNotFoundException(filename + " doesn't exist");
        }

        file = new Scanner(checkFile);
    }
}