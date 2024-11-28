package ry.tech.speedban;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private String questionText; // Текст вопроса с пропуском
    private String correctAnswer; // Правильный ответ
    private List<String> options; // Список вариантов ответов

    public Question(String questionText, String correctAnswer, List<String> options) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getOptions() {
        return options;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionText='" + questionText + '\'' +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", options=" + options +
                '}';
    }
}
