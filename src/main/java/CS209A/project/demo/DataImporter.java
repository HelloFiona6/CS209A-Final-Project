package CS209A.project.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.*;
import java.util.List;

public class DataImporter {

    private static final String URL = "jdbc:postgresql://localhost:5432/cs209a";
    private static final String USER = "test";
    private static final String PASSWORD = "123456";

    public static void main(String[] args) throws Exception {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentDir);
        // 1. 解析 JSON 文件
        // D:/code/JAVA/CS209A/project/src/main/java/CS209A/project/demo/questions.json
        ObjectMapper objectMapper = new ObjectMapper();
        List<Question> questions = objectMapper.readValue(new File("src/main/java/CS209A/project/demo/questions.json"),
                objectMapper.getTypeFactory().constructCollectionType(List.class, Question.class));

        // 2. 连接 PostgreSQL 数据库
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false); // 开启事务

            // 3. 插入数据
            for (Question question : questions) {
                // 插入用户数据
                insertUser(conn, question.getOwner());
                System.out.println("users插入完成");
                // 插入问题数据
                insertQuestion(conn, question);
                System.out.println("questions插入完成");
                // 插入标签数据
                insertTags(conn, question);
                System.out.println("tags插入完成");
                // 插入答案数据
                insertAnswers(conn, question);
                System.out.println("answers插入完成");
            }

            conn.commit(); // 提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertUser(Connection conn, User user) throws SQLException {
        String sql = "INSERT INTO users (user_id, account_id, reputation, user_type, profile_image, display_name, link) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, user.getUserId());
            stmt.setInt(2, user.getAccountId());
            stmt.setInt(3, user.getReputation());
            stmt.setString(4, user.getUserType());
            stmt.setString(5, user.getProfileImage());
            stmt.setString(6, user.getDisplayName());
            stmt.setString(7, user.getLink());
            stmt.executeUpdate();
        }
    }

    private static void insertQuestion(Connection conn, Question question) throws SQLException {
        String sql = "INSERT INTO questions (question_id, title, body, owner_user_id, comment_count, is_answered, view_count, favorite_count, down_vote_count, up_vote_count, answer_count, score, last_activity_date, creation_date, link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, question.getQuestionId());
            stmt.setString(2, question.getTitle());
            stmt.setString(3, question.getBody());
            stmt.setInt(4, question.getOwner().getUserId());
            stmt.setInt(5, question.getCommentCount());
            stmt.setBoolean(6, question.isAnswered());
            stmt.setInt(7, question.getViewCount());
            stmt.setInt(8, question.getFavoriteCount());
            stmt.setInt(9, question.getDownVoteCount());
            stmt.setInt(10, question.getUpVoteCount());
            stmt.setInt(11, question.getAnswerCount());
            stmt.setInt(12, question.getScore());
            stmt.setInt(13, question.getLastActivityDate());
            stmt.setInt(14, question.getCreationDate());
            stmt.setString(15, question.getLink());
            stmt.executeUpdate();
        }
    }

    private static void insertTags(Connection conn, Question question) throws SQLException {
        String sql = "INSERT INTO tags (name) VALUES (?) ON CONFLICT (name) DO NOTHING";
        for (Tag tag : question.getTags()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, tag.getName());
                stmt.executeUpdate();
            }
        }

        // 关联问题和标签
        sql = "INSERT INTO question_tags (question_id, tag_id) SELECT ?, tag_id FROM tags WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (Tag tag : question.getTags()) {
                stmt.setInt(1, question.getQuestionId());
                stmt.setString(2, tag.getName());
                stmt.executeUpdate();
            }
        }
    }

    // 插入与问题相关的所有答案
    private static void insertAnswers(Connection conn, Question question) throws SQLException {
        String sql = "INSERT INTO answers (answer_id, question_id, owner_user_id, comment_count, down_vote_count, " +
                "up_vote_count, is_accepted, score, last_activity_date, creation_date, link, body) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // 遍历问题的所有答案
        for (Answer answer : question.getAnswers()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, answer.getAnswerId());
                stmt.setInt(2, question.getQuestionId());
                stmt.setInt(3, answer.getOwnerUserId());
                stmt.setInt(4, answer.getCommentCount());
                stmt.setInt(5, answer.getDownVoteCount());
                stmt.setInt(6, answer.getUpVoteCount());
                stmt.setBoolean(7, answer.isAccepted());
                stmt.setInt(8, answer.getScore());
                stmt.setTimestamp(9, new Timestamp(answer.getLastActivityDate() * 1000L));
                stmt.setTimestamp(10, new Timestamp(answer.getCreationDate() * 1000L));
                stmt.setString(11, answer.getLink());
                stmt.setString(12, answer.getBody());

                // 执行插入操作
                stmt.executeUpdate();
            }
        }
    }

}