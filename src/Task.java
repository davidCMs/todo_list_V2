import java.io.Serializable;

public class Task implements Serializable {
    private String Name;
    private String Description;
    private boolean Complete;
    public Task(String name, String description, boolean complete) {
        Name = name;
        Description = description;
        Complete = complete;

    }
    public String getName() {
        return Name;
    }
    public boolean isComplete() {
        return Complete;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setComplete(boolean complete) {
        Complete = complete;
    }
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getSiplifedString() {

        return "Name: " + Name + ", description: " + Description + ", complete: " + Complete;
    }
}
