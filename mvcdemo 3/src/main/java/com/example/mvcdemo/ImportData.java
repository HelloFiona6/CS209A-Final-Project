package com.example.mvcdemo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;

public class ImportData {

    private static Connection con = null;
    private static Statement stmt = null;

    private static void openDB(Properties prop) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:postgresql://" + prop.getProperty("host") + "/" + prop.getProperty("database");
        System.out.println(url);
        try {
            con = DriverManager.getConnection(url, prop);
            System.out.println(con);
           /* if (con != null) {
                System.out.println("Successfully connected to the database "
                        + prop.getProperty("database") + " as " + prop.getProperty("user"));
                con.setAutoCommit(false);
            }*/
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    private static void closeDB() {
        if (con != null) {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                con.close();
                con = null;
            } catch (Exception ignored) {
            }
        }
    }

    public static void clearDataInTable() {
        String[] tables = {"questions", "users", "tags", "question_tags", "answers"};
        for (String table : tables) {
            try {
                stmt = con.createStatement();
                String sql = "DELETE FROM " + table;
                stmt.executeUpdate(sql);
                System.out.println("Data cleared from table: " + table);
            } catch (SQLException e) {
                System.err.println("Error clearing data from table: " + table);
                System.err.println(e.getMessage());
            }
        }
    }

    private static Properties loadDBUser() {
        Properties properties = new Properties();
        try {
            properties.load(new InputStreamReader(new FileInputStream("src/main/resources/dbUser.properties")));
            // System.out.println(properties);
            return properties;
        } catch (IOException e) {
            System.err.println("can not find db user file");
            throw new RuntimeException(e);
        }
    }

    private static void loadDataFromJson(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(new File(filePath));
            for (JsonNode node : rootNode) {
                JsonNode tags = node.get("tags");
                JsonNode answers = node.get("answers");
                JsonNode owner = node.get("owner");

                int questionId = node.get("question_id").asInt();
                String title = node.get("title").asText();
                String body = node.get("body").asText();
                int ownerUserId=Types.INTEGER;
                if(!owner.get("user_type").asText().equals("does_not_exist")){
                    ownerUserId=owner.get("user_id").asInt();
                }
                int commentCount = node.get("comment_count").asInt();
                boolean isAnswered = node.get("is_answered").asBoolean();
                int viewCount = node.get("view_count").asInt();
                int favoriteCount = node.get("favorite_count").asInt();
                int downVoteCount = node.get("down_vote_count").asInt();
                int upVoteCount = node.get("up_vote_count").asInt();
                int answerCount = node.get("answer_count").asInt();
                int score = node.get("score").asInt();
                long lastActivityDate = node.get("last_activity_date").asLong();  // Unix 时间戳，使用 asLong() 获取
                long creationDate = node.get("creation_date").asLong();  // Unix 时间戳，使用 asLong() 获取
                String link = node.get("link").asText();

                String sql = "INSERT INTO questions (question_id, title, body, owner_user_id, comment_count, " +
                        "is_answered, view_count, favorite_count, down_vote_count, up_vote_count, answer_count, " +
                        "score, last_activity_date, creation_date, link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, questionId);
                    ps.setString(2, title);
                    ps.setString(3, body);
                    if (ownerUserId==Types.INTEGER) {
                        ps.setNull(4, Types.INTEGER);
                    }else{
                        ps.setInt(4, ownerUserId);
                    }
                    ps.setInt(5, commentCount);
                    ps.setBoolean(6, isAnswered);
                    ps.setInt(7, viewCount);
                    ps.setInt(8, favoriteCount);
                    ps.setInt(9, downVoteCount);
                    ps.setInt(10, upVoteCount);
                    ps.setInt(11, answerCount);
                    ps.setInt(12, score);
                    ps.setLong(13, lastActivityDate);
                    ps.setLong(14, creationDate);
                    ps.setString(15, link);

                    ps.executeUpdate();
                    System.out.println("Inserted question: " + title);
                }

                // users
                if(owner.get("user_type").asText().equals("registered")){
                    insertUser(con, owner);
                }

                // tags
                insertTags(con, tags, questionId);

                // answer
                if (answers== null)continue;
                insertAnswer(con, answers);
            }
            System.out.println("Data loaded from JSON successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading data from JSON.");
        }
    }
    public static void insertAnswer(Connection con, JsonNode answers) {
        try {
            for (int k = 0; k < answers.size(); k++) {
                JsonNode ans = answers.get(k);
                int answerId = ans.get("answer_id").asInt();
                int questionIdA = ans.get("question_id").asInt();
                JsonNode ownerA = ans.get("owner");
                int ownerUserIdA=Types.INTEGER;
                if(!ownerA.get("user_type").asText().equals("does_not_exist")){
                    ownerUserIdA=ownerA.get("user_id").asInt();
                }
                int commentCountA = ans.get("comment_count").asInt();
                int downVoteCountA = ans.get("down_vote_count").asInt();
                int upVoteCountA = ans.get("up_vote_count").asInt();
                boolean isAccepted = ans.get("is_accepted").asBoolean();
                int scoreA = ans.get("score").asInt();
                long lastActivityDateA = ans.get("last_activity_date").asLong();
                long creationDateA = ans.get("creation_date").asLong();
                String linkA = ans.get("link").asText();
                String bodyA = ans.get("body").asText();

                String sql = "INSERT INTO answers (answer_id, question_id, owner_user_id, comment_count, " +
                        "down_vote_count, up_vote_count, is_accepted, score, last_activity_date, creation_date, " +
                        "link, body) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                try (PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setInt(1, answerId);
                    ps.setInt(2, questionIdA);
                    if (ownerUserIdA==Types.INTEGER) {
                        ps.setNull(3, Types.INTEGER);
                    }else{
                        ps.setInt(3, ownerUserIdA);
                    }
                    ps.setInt(4, commentCountA);
                    ps.setInt(5, downVoteCountA);
                    ps.setInt(6, upVoteCountA);
                    ps.setBoolean(7, isAccepted);
                    ps.setInt(8, scoreA);
                    ps.setLong(9, lastActivityDateA);
                    ps.setLong(10, creationDateA);
                    ps.setString(11, linkA);
                    ps.setString(12, bodyA);

                    // 执行更新
                    ps.executeUpdate();
                    System.out.println("Inserted answer with ID: " + answerId);
                }
                if (ownerA.get("user_type").asText().equals("does_not_exist")) continue;
                insertUser(con, ownerA);
            }
        } catch (SQLException e) {
            System.err.println("Error inserting data into answers table: " + e.getMessage());
        }
    }
    public static void insertTags(Connection con, JsonNode tags,int questionId) {
        try {
            for (int j = 0; j < tags.size(); j++) {
                String tag = tags.get(j).asText();

                String sql = "INSERT INTO tags (name) VALUES (?) ON CONFLICT (name) DO NOTHING RETURNING tag_id";
                try (PreparedStatement stmt3 = con.prepareStatement(sql)) {
                    stmt3.setString(1, tag);

                    ResultSet rs = stmt3.executeQuery();
                    int tagId = -1;
                    if (rs.next()) {
                        tagId = rs.getInt("tag_id");
                    }

                    if (tagId == -1) {
                        sql = "SELECT tag_id FROM tags WHERE name = ?";
                        try (PreparedStatement stmt4 = con.prepareStatement(sql)) {
                            stmt4.setString(1, tag);
                            rs = stmt4.executeQuery();
                            if (rs.next()) {
                                tagId = rs.getInt("tag_id");
                            }
                        }
                    }

                    if (tagId != -1) {
                        sql = "INSERT INTO question_tags (question_id, tag_id) VALUES (?, ?)";
                        try (PreparedStatement stmt5 = con.prepareStatement(sql)) {
                            stmt5.setInt(1, questionId);
                            stmt5.setInt(2, tagId); // 使用 tag_id 而不是 tag name
                            stmt5.executeUpdate();
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting data into tags table: " + e.getMessage());
        }
    }
    public static void insertUser(Connection con, JsonNode ownerNode) {
        try {
            // 获取用户数据
            int userId = ownerNode.get("user_id").asInt();
            int accountId = ownerNode.get("account_id").asInt();
            int reputation = ownerNode.get("reputation").asInt();
            int acceptRate = 0;
            if (ownerNode.get("accept_rate") != null) {
                acceptRate = ownerNode.get("accept_rate").asInt();
            }
            String userType = ownerNode.get("user_type").asText();
            String profileImage = ownerNode.get("profile_image").asText();
            String displayName = ownerNode.get("display_name").asText();
            String link = ownerNode.get("link").asText();

            // 检查用户是否已经存在
            String checkUserQuery = "SELECT user_id FROM users WHERE user_id = ?";
            try (PreparedStatement checkStmt = con.prepareStatement(checkUserQuery)) {
                checkStmt.setInt(1, userId);
                ResultSet rs = checkStmt.executeQuery();
                if (rs.next()) {
                    return;
                }
            }

            // 插入用户数据
            String sql = "INSERT INTO users (user_id, account_id, reputation, user_type, profile_image, " +
                    "display_name, link, accept_rate) VALUES (?, ?, ?, ?, ?, ?,?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, userId);
                ps.setInt(2, accountId);
                ps.setInt(3, reputation);
                ps.setString(4, userType);
                ps.setString(5, profileImage);
                ps.setString(6, displayName);
                ps.setString(7, link);
                ps.setInt(8, acceptRate);

                ps.executeUpdate();
                System.out.println("Inserted user with ID: " + userId);
            }

        } catch (SQLException e) {
            System.err.println("Error inserting data into users table: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
        Properties prop = loadDBUser();//导入配置文件

        openDB(prop);
        clearDataInTable();
        String filePath = "src/main/resources/questions.json";
        loadDataFromJson(filePath);

        closeDB();
    }
}