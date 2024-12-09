package CS209A.project.demo;


public class Tag {
    private int tagId;
    private String name;

    public Tag(int tagId, String name) {
        this.tagId = tagId;
        this.name = name;
    }

    public Tag() {
    }

    public int getTagId() {
        return tagId;
    }

    public String getName() {
        return name;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
