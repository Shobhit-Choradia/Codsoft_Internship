import java.util.*;

class Marks
{
    String name;
    public HashMap<String, Integer> map = new HashMap<>();
    Scanner sc = new Scanner(System.in);

    public Marks(String studName)
    {
        this.name = studName;
        System.out.println("Enter the number of subjects do you have: ");
        int nos = sc.nextInt();

        for (int i = 0; i < nos; i++)
        {
            System.out.println("Enter the subject name: ");
            String subName = sc.next();
            subName = errorSubjectCheck(subName);
            System.out.println("How many marks you scored in " + subName + ": ");
            int score = sc.nextInt();
            score = errorMarkCheck(score);
            map.put(subName, score);
        }
    }

    int totMarks()
    {
        int totalMarks = 0;
        for (Map.Entry<String, Integer> mp : map.entrySet())
        {
            totalMarks += mp.getValue();
        }
        return totalMarks;
    }

    int avg()
    {
        int totalMarks = totMarks();
        return totalMarks / map.size();
    }

    String grade()
    {
        int marks = avg();
        if (marks > 90) return "A Grade";
        if (marks > 80) return "B Grade";
        if (marks > 70) return "C Grade";
        if (marks > 60) return "D Grade";
        return "Below passing";
    }

    void display()
    {
        System.out.println();
        System.out.println("student name - " + name);
        for (Map.Entry<String, Integer> mp : map.entrySet())
        {
            System.out.println("The marks scored in "+mp.getKey()+" is: "+mp.getValue());
        }
        System.out.println();
    }

    void detailsDisplay()
    {
        System.out.println();
        System.out.println("The total marks " + name + " scored is  - " + totMarks());
        System.out.println("The percentage score of " + name + " is - " + avg());
        System.out.println("The grade score of " + name + " is      - " + grade());
        System.out.println();
    }

    void subjectMarks(){
        System.out.println("Enter the subject name: ");
        String subName = sc.next();
        System.out.println("The marks scored in " + subName + " is " +  map.get(subName));
        System.out.println();
    }

    void update()
    {
        System.out.println("Enter the subject name: ");
        String subjectName = sc.next();
        if(map.containsKey(subjectName))
        {
            System.out.println("Enter the updated marks: ");
            int marks = sc.nextInt();
            marks = errorMarkCheck(marks);
            map.put(subjectName, marks);
            System.out.println("The marks scored in " + subjectName + " is " + map.get(subjectName));
            System.out.println();
        }
        else
        {
            System.out.println("Given subject not in data. want to include it (Y/N) - ");
            String choice = sc.next();
            if (Objects.equals(choice, "Y"))
            {
                addSubject(subjectName);
            }
            else
            {
                System.out.println("Ending process of this choice");
                System.out.println();
            }
        }
    }

    void delete()
    {
        System.out.println("Enter the subject name: ");
        String sn = sc.next();
        if(map.containsKey(sn))
        {
            map.remove(sn);
        }
        else
        {
            System.out.println("Error \n Given subject not in data / already deleted \n enter subject to be deleted - ");
            delete();
        }
        System.out.println();
    }

    void addSubject()
    {
        System.out.println("Enter the subject name: ");
        String sub = sc.next();
        sub = errorSubjectCheck(sub);
        System.out.println("Enter the marks: ");
        int ms = sc.nextInt();
        ms = errorMarkCheck(ms);
        map.put(sub, ms);
    }

    void addSubject(String sub)
    {
        System.out.println("Enter the marks: ");
        int ms = sc.nextInt();
        ms = errorMarkCheck(ms);
        map.put(sub, ms);
    }

    int errorMarkCheck(int score)
    {
        while (score < 0 || score > 100)
        {
            System.out.println("Input error \n please re-enter the marks");
            score = sc.nextInt();
        }
        return score;
    }

    String errorSubjectCheck(String subject)
    {
        while(map.containsKey(subject))
        {
            System.out.println("Input error \n please re-enter the subject");
            subject = sc.next();
        }
    return subject;
    }
}

public class Grade
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        // student name
        System.out.println("Enter the your name: ");
        String name = sc.next();
        Marks student1 = new Marks(name);
        student1.display();
        student1.detailsDisplay();
        int choice = 0;

        while (choice != -1)
        {
            System.out.println("""
                    If you want to see the marks of one particular subject then type 1\s
                    If you want to update the marks of particular subject then type  2\s
                    If you want to delete details of particular subject then type    3\s
                    If you want to add detail of new subject then type               4\s
                    If you want to see details                                       5\s
                    If you want to exit then type                                   -1""");
            choice = sc.nextInt();
            switch (choice)
            {
                case 1:
                    student1.subjectMarks();
                    continue;

                case 2:
                    student1.update();
                    continue;

                case 3:
                    student1.delete();
                    continue;

                case 4:
                    student1.addSubject();
                    continue;

                case 5:
                    student1.display();
                    continue;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
                    System.out.println();
            }
        }
        System.out.println("Final marks table and details - ");
        student1.display();
        student1.detailsDisplay();
        sc.close();
    }
}