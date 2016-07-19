/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Question;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Apollo
 */
public class QuestionDAO extends AbstractDAO<Question> {

    private static final String QUESTION_ID = "questionId";
    private static final String QUESTION_TITLE = "questionTitle";
    private static final String QUESTION_CONTENT = "questionContent";
    private static final String USERNAME = "username";
    private static final String CATEGORI_ID = "categoriesId";
    private static final String LIKE_NUMBER = "likeNumber";
    private static final String ANSWER_NUMBER = "answerNumber";
    private static final String SOLVED = "solved";
    private static final String TRUE_ANSWER_ID = "trueAnswerId";
    private static final String DATE_TIME = "dateTime";

    private static final QuestionDAO INSTANCE = new QuestionDAO();

    private QuestionDAO() {
    }

    public static QuestionDAO getInstance() {
        return INSTANCE;
    }

    @Override
    protected Question castFromDB(ResultSet record) {
        if (record == null) {
            return null;
        }
        Question question = new Question();
        try {
            question.setQuestionId(record.getInt(QUESTION_ID));
            question.setQuestionTitle(record.getString(QUESTION_TITLE).trim());
            question.setQuestionContent(record.getString(QUESTION_CONTENT).trim());
            question.setUsername(record.getString(USERNAME).trim());
            question.setCategoryId(record.getInt(CATEGORI_ID));
            question.setLikeNumber(record.getInt(LIKE_NUMBER));
            question.setAnswerNumber(record.getInt(ANSWER_NUMBER));
            question.setSolved(record.getBoolean(SOLVED));
            question.setTrueAnswerId(record.getInt(TRUE_ANSWER_ID));
            question.setDateTime(record.getString(DATE_TIME).trim());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return question;
    }

    private static final String SAVE_QUESTION
            = "INSERT INTO QUESTIONS (questionTitle, questionContent, userName, "
            + "categoriesId, likeNumber, answerNumber, dateTime) "
            + "VALUES (?,?,?,?,0,0,?);";

    public void save(Question question) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SAVE_QUESTION)) {
            preparedStatement.setString(1, question.getQuestionTitle().trim());
            preparedStatement.setString(2, question.getQuestionContent().trim());
            preparedStatement.setString(3, question.getUsername());
            preparedStatement.setInt(4, question.getCategoryId());
            preparedStatement.setDate(5, new Date(System.currentTimeMillis()));

            preparedStatement.execute();
        }
    }
    
    private static final String LIST_QUESTIONS
            = "SELECT * FROM QUESTIONS "
            + "ORDER BY solved ASC, answerNumber ASC, dateTime ASC "
            + "LIMIT ?,?;";

    public List<Question> listQuestions(int categoriesId, int skip, int take) throws SQLException {
        List<Question> result;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(LIST_QUESTIONS)) {
            preparedStatement.setInt(1, skip);
            preparedStatement.setInt(2, take);

            ResultSet resultSet = preparedStatement.executeQuery();
            result = castToList(resultSet);
        }
        return result;
    }

    private static final String LIST_QUESTIONS_BY_CATEGORY
            = "SELECT * FROM QUESTIONS "
            + "WHERE categoriesId = ? "
            + "ORDER BY solved ASC, answerNumber ASC, dateTime ASC "
            + "LIMIT ?,?;";

    public List<Question> listQuestionsByCategory(int categoriesId, int skip, int take) throws SQLException {
        List<Question> result;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(LIST_QUESTIONS_BY_CATEGORY)) {
            preparedStatement.setInt(1, categoriesId);
            preparedStatement.setInt(2, skip);
            preparedStatement.setInt(3, take);

            ResultSet resultSet = preparedStatement.executeQuery();
            result = castToList(resultSet);
        }
        return result;
    }

    private static final String GET_QUESTION
            = "SELECT * FROM QUESTIONS "
            + "WHERE questionId = ?;";

    public Question getQuestion(int questionId) throws SQLException {
        Question question = null;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_QUESTION)) {
            preparedStatement.setInt(1, questionId);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                question = castFromDB(resultSet);
            }
        }
        return question;
    }

    private static final String MARK_AS_SOLVED
            = "UPDATE QUESTIONS "
            + "SET solved = 1, trueAnswerId = ? "
            + "WHERE questionId = ?;";

    public void markAsSolved(int questionId, int trueAnswerId) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(MARK_AS_SOLVED)) {
            preparedStatement.setInt(1, trueAnswerId);
            preparedStatement.setInt(2, questionId);

            preparedStatement.executeUpdate();
        }
    }

    private static final String INCREASE_LIKE_NUMBER
            = "UPDATE QUESTIONS "
            + "SET likeNumber = ? "
            + "WHERE questionId = ?;";

    public void increaseLikeNumber(int questionId, int likeNumber) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INCREASE_LIKE_NUMBER)) {
            preparedStatement.setInt(1, likeNumber);
            preparedStatement.setInt(2, questionId);

            preparedStatement.executeUpdate();
        }
    }
}
