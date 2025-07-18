import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;


public class QuizFrame extends JFrame implements ActionListener {
    JLabel questionLabel, timerLabel;
    JRadioButton[] options = new JRadioButton[4];
    ButtonGroup group = new ButtonGroup();
    JButton nextButton, previousButton;
    int[] selectedAnswers;
    Timer timer;
    int current = 0, score = 0, time = 30;
    List<Question> questions = QuestionBank.getQuestions();
    boolean isTimeUp = false;


    public QuizFrame() {
        setTitle("Java Swing Quiz App");
        setSize(720, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridLayout(7, 1));
        mainPanel.setBackground(Color.decode("#e8f0fe"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        timerLabel = new JLabel("⏱ Time Left:60", SwingConstants.RIGHT);
        timerLabel.setFont(new Font("Verdana", Font.BOLD, 16));
        timerLabel.setForeground(new Color(255, 69, 0)); // OrangeRed
        mainPanel.add(timerLabel);
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Serif", Font.BOLD, 20));
        questionLabel.setForeground(new Color(25, 25, 112)); // MidnightBlue
        mainPanel.add(questionLabel);
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            options[i].setFont(new Font("SansSerif", Font.PLAIN, 15));
            options[i].setBackground(new Color(232, 240, 254));
            options[i].setFocusPainted(false);
            group.add(options[i]);
            mainPanel.add(options[i]);
        }
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#e8f0fe"));
        nextButton = new JButton("Next➡");
        styleButton(nextButton, "#007bff");
        nextButton.addActionListener(this);
        selectedAnswers = new int[questions.size()];
        Arrays.fill(selectedAnswers, -1);

        previousButton = new JButton("⬅Previous");
        styleButton(previousButton, "#6c757d");
        previousButton.addActionListener(e -> previousQuestion());
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        add(buttonPanel);
        loadQuestion();
        mainPanel.add(buttonPanel);
        add(mainPanel, BorderLayout.CENTER);
        timer = new Timer(1000, e -> {
            time--;
            timerLabel.setText("⏱ Time Left:" + time);
            if (time == 0) {
                isTimeUp = true;
                showResult();
            }
        });
        timer.start();
        setVisible(true);
    }

    void loadQuestion() {
        if (current >= questions.size()) {
            showResult();
            return;
        }
        timerLabel.setText("⏱ Time Left:" + time);
        group.clearSelection();
        Question q = questions.get(current);
        questionLabel.setText((current + 1) + "." + q.question);
        for (int i = 0; i < 4; i++) {
            options[i].setText(q.options[i]);
        }
        int selected = selectedAnswers[current];
        if (selected != -1) {
            options[selected].setSelected(true);
        }

        previousButton.setEnabled(current > 0);
    }

    void nextQuestion() {



        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedAnswers[current] = i;
            }
        }
        current++;
        loadQuestion();

    }

    void previousQuestion() {

        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected()) {
                selectedAnswers[current] = i;
            }
        }

        if (current > 0) {
            current--;
            loadQuestion();

        }
    }
    void showResult() {
        timer.stop();
        score = 0;
        int attempted = 0;

        for (int i = 0; i < questions.size(); i++) {
            if (selectedAnswers[i] != -1) {
                attempted++;
                if (selectedAnswers[i] == questions.get(i).correctAnswer) {
                    score++;
                }
            }
        }

        String message;
        if (isTimeUp) {
            message = "⏰ Time's Up!\n";
        } else {
            message = "✅ Quiz Completed!\n";
        }

        message += "📝 Attempted: " + attempted + "/" + questions.size() + "\n" +
                "🎯 Your Score: " + score + "/" + questions.size();

        JOptionPane.showMessageDialog(this, message);
        System.exit(0);
    }

    void styleButton(JButton button, String hexColor) {
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode(hexColor));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    public void actionPerformed(ActionEvent e) {
        nextQuestion();
    }
}