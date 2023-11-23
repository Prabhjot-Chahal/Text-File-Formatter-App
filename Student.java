import java.util.HashMap;

class Student implements Comparable<Student>
{

    private String name;
    private long id;
    private HashMap<String, Float[]> courses = new HashMap<String, Float[]>();

    public Student(String name, long id)
    {
        this.setName(name);
        this.setId(id);
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public long getId()
    {
        return this.id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public boolean courseExists(String courseCode)
    {
        return this.courses.containsKey(courseCode);
    }

    public Float[] getGrades(String courseCode) throws Exception
    {
        if (!this.courses.containsKey(courseCode))
        {
            throw new Exception("Course Code doesn't exist!");
        }

        return this.courses.get(courseCode);
    }

    public void addCourse(String courseCode, Float[] grades) throws Exception
    {
        Float[] gradesToAdd = new Float[5];

        if (grades.length != 4)
        {
            String errorMessage = "Course should have exactly 4 grades, ";
            errorMessage += String.format("recieved %d grades", grades.length);
            throw new Exception(errorMessage);
        }

        int i = 0;
        float total = 0;
        for (float grade : grades)
        {
            if (grade < 0 || grade > 100)
            {
                String errorMessage = "Grade should be between 0 and 100 (inclusive), ";
                errorMessage += String.format("recieved %f as grade", grade);
                throw new Exception(errorMessage);
            }

            if (i == 3)
            {
                total += (grade * 0.4);
            }
            else
            {
                total += (grade * 0.2);
            }

            gradesToAdd[i] = grade;

            i++;
        }

        gradesToAdd[i] = total;
        courses.put(courseCode, gradesToAdd);
    }

    public int compareTo(Student other)
    {
        return Long.compare(this.id, other.id);
    }
}
