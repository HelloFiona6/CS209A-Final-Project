package CS209A.project.demo;

// Answer.java
public class Answer {
    private int answerId;
    private int questionId;
    private User owner;
    private int commentCount;
    private int downVoteCount;
    private int upVoteCount;
    private boolean isAccepted;
    private int score;
    private int lastActivityDate;
    private int creationDate;
    private String link;
    private String body;

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public User getOwner() {
        return owner;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public int getDownVoteCount() {
        return downVoteCount;
    }

    public int getUpVoteCount() {
        return upVoteCount;
    }

    public boolean isAccepted() {
        return isAccepted;
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

    public String getBody() {
        return body;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public void setDownVoteCount(int downVoteCount) {
        this.downVoteCount = downVoteCount;
    }

    public void setUpVoteCount(int upVoteCount) {
        this.upVoteCount = upVoteCount;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLastActivityDate(int lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getOwnerUserId() {
        return owner.getUserId();
    }
}
