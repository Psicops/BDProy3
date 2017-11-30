package logic;

import logic.file.Image;

public class User {
    public final static String ADMINISTRATOR = "ADMINISTRATOR";
    public final static String REGULAR = "REGULAR";
    
    private final String id;
    private String password;
    private String status;
    private String email;
    private Image image;
    private boolean displayImage;
    private boolean displayEmail;

    public User(String id, String password, String status, String email, Image image, boolean displayEmail, boolean displayImage) {
        this.id = id;
        this.password = password;
        this.status = status;
        this.email = email;
        this.image = image;
        this.displayEmail = displayEmail;
        this.displayImage = displayImage;
    }

    public String getId(){
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public boolean getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(boolean displayImage) {
        this.displayImage = displayImage;
    }

    public boolean getDisplayEmail() {
        return displayEmail;
    }

    public void setDisplayEmail(boolean displayEmail) {
        this.displayEmail = displayEmail;
    }
    
    @Override
    public String toString(){
        if(displayEmail)
            return id + " (" + email + ")";
        else
            return id;
    }
}
