package Journal;

import java.util.Random;

public class Main
{
    private static final int GROUPS = 3;
    private static final int MIN_STUDENT_NUMBER = 15;
    private static final int MAX_STUDENT_NUMBER = 30;
    private static final int THREADS = 4;
    private static final int WEEKS = 7;

    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException
    {
        Journal journal = createJournal();
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++)
        {
            Thread thread = new JournalThread(journal, WEEKS);
            threads[i] = thread;
            thread.start();
        }
        for (int i = 0; i < THREADS; i++)
        {
            threads[i].join();
        }
        System.out.println(journal);
    }

    private static Journal createJournal()
    {
        var journal = new Journal();
        for (int i = 0; i < GROUPS; i++)
        {
            journal.addGroup(createGroup(i + 1));
        }
        return journal;
    }

    private static Group createGroup(int groupNumber)
    {
        Group group = new Group("IT-9" + groupNumber);
        var count = random.nextInt(MAX_STUDENT_NUMBER - MIN_STUDENT_NUMBER) + MIN_STUDENT_NUMBER;
        for (int i = 0; i < count; i++)
        {
            group.addStudent(createStudent(i + 1));
        }
        return group;
    }

    private static Student createStudent(int i)
    {
        return new Student("Student " + i);
    }
}
