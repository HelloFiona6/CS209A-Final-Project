package CS209A.project.demo;

// User.java
public class User {
    private int userId;
    private int accountId;
    private int reputation;
    private String userType;
    private String profileImage;
    private String displayName;
    private String link;

    public int getUserId() {
        return userId;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getReputation() {
        return reputation;
    }

    public String getUserType() {
        return userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLink() {
        return link;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
