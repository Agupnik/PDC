package Journal;

import java.util.ArrayList;
import java.util.List;

public class Group
{
    private final String name;
    private final List<Student> students;

    public Group(String name)
    {
        this.name = name;
        students = new ArrayList<>();
    }

    public void addStudent(Student student)
    {
        students.add(student);
        student.setGroup(name);
    }

    public String getName()
    {
        return name;
    }

    public List<Student> getStudents()
    {
        return students;
    }
}
