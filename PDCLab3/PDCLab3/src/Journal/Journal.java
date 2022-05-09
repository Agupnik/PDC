package Journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Journal
{
    private final List<Group> groups;
    private final HashMap<Student, Integer> marks;

    public Journal()
    {
        groups = new ArrayList<>();
        marks = new HashMap<>();
    }

    public void addGroup(Group group)
    {
        groups.add(group);
    }

    public List<Group> getGroups()
    {
        return groups;
    }

    public List<Student> getStudents()
    {
        var students = new ArrayList<Student>();
        var groups = getGroups();
        for (Group group: groups) {
            students.addAll(group.getStudents());
        }
        return students;
    }

    public synchronized void setMark(Student student, int mark)
    {
//        System.out.println(student.toString());
//        System.out.println(mark);
        marks.put(student, mark);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (Group group : groups)
        {
            builder.append(group.getName());
            builder.append(":");
            builder.append("\n");
            for (Student student : group.getStudents())
            {
                builder.append(student.toString());
                builder.append(" - ");
                if (marks.containsKey(student))
                {
                    builder.append(marks.get(student));
                }
                builder.append("\n");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
