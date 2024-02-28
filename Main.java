import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create a quiz object
        Quiz1 quiz = new Quiz1();

        // Add questions to the quiz
        quiz.addQuestion(new Question("What is the capital of France?", List.of("Paris", "Berlin", "Madrid"), 0));
        quiz.addQuestion(new Question("What is the largest planet in our solar system?", List.of("Earth", "Mars", "Jupiter"), 2));
        quiz.addQuestion(new Question("What is the chemical symbol for water?", List.of("H2O", "CO2", "NH3"), 0));

        // Start the quiz
        quiz.startQuiz();
    }
}

class Question {
    private String questionText;
    private List<String> options;
    private int correctAnswer;

    public Question(String questionText, List<String> options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}

class Quiz1 {
    private List<Question> questions;

    public Quiz1() {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void displayQuestion(Question question) {
        System.out.println(question.getQuestionText());
        for (int i = 0; i < question.getOptions().size(); i++) {
            System.out.println((i + 1) + ". " + question.getOptions().get(i));
        }
    }

    public int evaluateResponse(int userResponse) {
        if (userResponse < 1 || userResponse > questions.size()) {
            System.out.println("Invalid response. Please enter a valid option number.");
            return -1;
        }
        int correctAnswer = questions.get(userResponse - 1).getCorrectAnswer();
        if (correctAnswer == userResponse - 1) {
            System.out.println("Correct!");
            return 1;
        } else {
            System.out.println("Incorrect. The correct answer is: " + (correctAnswer + 1));
            return 0;
        }
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        int correctAnswers = 0;
        int totalQuestions = questions.size();

        for (Question question : questions) {
            displayQuestion(question);
            int userResponse = -1;
            while (userResponse < 1 || userResponse > questions.size()) {
                System.out.print("Enter your answer: ");
                userResponse = scanner.nextInt();
            }
            int result = evaluateResponse(userResponse);
            if (result != -1) {
                correctAnswers += result;
            }
        }

        displayResults(correctAnswers, totalQuestions);
    }

    public void displayResults(int correctAnswers, int totalQuestions) {
        System.out.println("Quiz Results:");
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Total Questions: " + totalQuestions);
        System.out.println("Score: " + (correctAnswers / (float) totalQuestions) * 100 + "%");
    }
}