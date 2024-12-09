package CS209A.project.demo;

import java.util.List;

// Question.java
public class Question {
    private int questionId;
    private String title;
    private String body;
    private User owner;
    private int commentCount;
    private boolean isAnswered;
    private int viewCount;
    private int favoriteCount;
    private int downVoteCount;
    private int upVoteCount;
    private int answerCount;
    private int score;
    private int lastActivityDate;
    private int creationDate;
    private String link;
    private List<Tag> tags;
    private List<Answer> answers;

    public int getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getOwner() {
        return owner;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public int getAnswerCount() {
        return answerCount;
    }

    public int getScore() {
        return score;
    }

    public int getLastActivityDate() {
        return lastActivityDate;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public String getLink() {
        return link;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public void setLastActivityDate(int lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAnswerCount(int answerCount) {
        this.answerCount = answerCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
