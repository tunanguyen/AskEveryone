/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entity.Answer;
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
public class AnswerDAO extends AbstractDAO<Answer> {

    private static final String ANSWER_ID = "answerId";
    private static final String ANSWER_CONTENT = "questionContent";
    private static final String USERNAME = "username";
    private static final String QUESTION_ID = "questionId";
    private static final String LIKE_NUMBER = "likeNumber";
    private static final String DISLIKE_NUMBER = "dislikeNumber";
    private static final String DATE_TIME = "dateTime";

    private static final AnswerDAO INSTANCE = new AnswerDAO();

    private AnswerDAO() {
    }

    public static AnswerDAO getInstance() {
        return INSTANCE;
    }

    @Override
    protected Answer castFromDB(ResultSet record) {
        if (record == null) {
            return null;
        }
        Answer answer = new Answer();
        try {

            answer.setAnswerId(record.getInt(ANSWER_ID));
            answer.setAnswerContent(record.getString(ANSWER_CONTENT).trim());
            answer.setUsername(record.getString(USERNAME).trim());
            answer.setQuestionId(record.getInt(QUESTION_ID));
            answer.setLikeNumber(record.getInt(LIKE_NUMBER));
            answer.setDislikeNumber(record.getInt(DISLIKE_NUMBER));
            answer.setDateTime(record.getString(DATE_TIME).trim());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return answer;
    }

    private static final String SAVE_ANSWER
            = "INSERT INTO ANSWERS (questionContent, username, questionId, likeNumber, dislikeNumber, dateTime) "
            + "VALUES (?,?,?,0,0,?)";

    private static final String INCREASE_QUESTION_ANSWER_NUMBER
            = "UPDATE QUESTION "
            + "SET answerNumber = ? "
            + "WHERE questionId = ?;";

    public void save(Answer answer, Question question) throws SQLException {
        PreparedStatement preparedStatementInser = null;
        PreparedStatement preparedStatementUpdate = null;
        try {
            getConnection().setAutoCommit(false);   //Begin transaction

            preparedStatementInser = getConnection().prepareStatement(SAVE_ANSWER);
            preparedStatementInser.setString(1, answer.getAnswerContent().trim());
            preparedStatementInser.setString(2, answer.getUsername().trim());
            preparedStatementInser.setInt(3, answer.getQuestionId());
            preparedStatementInser.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatementInser.executeUpdate();

            preparedStatementUpdate = getConnection().prepareStatement(INCREASE_QUESTION_ANSWER_NUMBER);
            preparedStatementUpdate.setInt(1, question.getAnswerNumber());
            preparedStatementUpdate.setInt(1, question.getQuestionId());
            preparedStatementInser.executeUpdate();

            getConnection().commit();   //End transaction
        } catch (SQLException se) {
            se.printStackTrace();
            getConnection().rollback();
        } finally {
            closePrepareStatement(preparedStatementInser);
            closePrepareStatement(preparedStatementUpdate);
            getConnection().setAutoCommit(true);
        }
    }

    private void closePrepareStatement(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null) {
            preparedStatement.close();
        }
    }

    private static final String LIST_ANSWERS
            = "SELECT * FROM ANSWERS "
            + "WHERE questionId = ? "
            + "ORDER BY likeNumber DESC, disLikeNumber ASC, dateTime ASC "
            + "LIMIT ?,?;";

    public List<Answer> listAnswers(int questionId, int skip, int take) throws SQLException {
        List<Answer> result;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(LIST_ANSWERS)) {
            preparedStatement.setInt(1, questionId);
            preparedStatement.setInt(1, skip);
            preparedStatement.setInt(1, take);

            ResultSet resultSet = preparedStatement.executeQuery();
            result = castToList(resultSet);
        }
        return result;
    }

    private static final String INCREASE_LIKE_NUMBER
            = "UPDATE ANSWERS "
            + "SET likeNumber = ?"
            + "WHERE answerId = ?;";

    public void increaseLikeNumber(int answerId, int likeNumber) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INCREASE_LIKE_NUMBER)) {
            preparedStatement.setInt(1, likeNumber);
            preparedStatement.setInt(2, answerId);

            preparedStatement.executeUpdate();
        }
    }

    private static final String INCREASE_DISLIKE_NUMBER
            = "UPDATE ANSWERS "
            + "SET likeNumber = ?"
            + "WHERE answerId = ?;";

    public void increaseDislikeNumber(int answerId, int dislikeNumber) throws SQLException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INCREASE_DISLIKE_NUMBER)) {
            preparedStatement.setInt(1, dislikeNumber);
            preparedStatement.setInt(2, answerId);

            preparedStatement.executeUpdate();
        }
    }

}
