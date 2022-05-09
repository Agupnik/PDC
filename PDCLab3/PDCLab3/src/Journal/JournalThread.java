package Journal;

import java.util.List;
import java.util.Random;

public class JournalThread extends Thread
{
    private static final int MIN_MARK = 60;
    private static final int MAX_MARK = 100;

    private final Journal journal;
    private final int weeks;
    private final Random random;

    public JournalThread(Journal journal, int weeks)
    {
        this.journal = journal;
        this.weeks = weeks;
        random = new Random();
    }

    @Override
    public void run()
    {
        for (int i = 0; i < weeks; i++)
        {
            var count = random.nextInt(countStudents());
            for (int j = 0; j < count; j++)
            {
                int mark = random.nextInt(MAX_MARK - MIN_MARK) + MIN_MARK;
                journal.setMark(getRandomStudent(), mark);
            }
        }
    }

    private int countStudents()
    {
        return journal.getStudents().size();
    }

    private Student getRandomStudent()
    {
        List<Student> students = journal.getStudents();
        return students.get(random.nextInt(students.size()));
    }
}
