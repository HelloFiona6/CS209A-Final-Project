package CS209A.project.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class QuestionTag {

    @Id
    private int questionId;
    private int tagId;

    // Getters and Setters
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
