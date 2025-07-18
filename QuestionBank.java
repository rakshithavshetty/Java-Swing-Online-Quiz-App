import java.util.*;

public class QuestionBank {
    public static List<Question> getQuestions() {
     List<Question> questions=new ArrayList<>();
        questions.add(new Question("What is the capital of India?",
                new String[]{"Delhi", "Mumbai", "Kolkata", "Chennai"}, 0));

        questions.add(new Question("Which company developed Java?",
                new String[]{"Apple", "Microsoft", "Sun Microsystems", "Google"}, 2));

        questions.add(new Question("Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherit", "super"}, 1));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Mars", "Jupiter", "Saturn"}, 1));

        questions.add(new Question("Who is the founder of Microsoft?",
                new String[]{"Steve Jobs", "Elon Musk", "Bill Gates", "Jeff Bezos"}, 2));

        questions.add(new Question("What does HTTP stand for?",
                new String[]{"HyperText Transfer Protocol", "HighText Transfer Protocol", "HyperText Transmission Process", "None of the above"}, 0));

        questions.add(new Question("Which data structure uses LIFO (Last In First Out)?",
                new String[]{"Queue", "Stack", "Array", "Tree"}, 1));

        questions.add(new Question("Which of the following is a Java keyword?",
                new String[]{"main", "class", "String", "print"}, 1));
     return questions;
    }
}
