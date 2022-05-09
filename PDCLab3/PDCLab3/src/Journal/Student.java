package Journal;

public class Student
{
    private final String name;
    private String group;

    public Student(String name)
    {
        this.name = name;
    }

    public void setGroup(String group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return name + " from group " + group;
    }
}
