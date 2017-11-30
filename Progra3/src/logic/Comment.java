package logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment {
    private ArrayList<Comment> children;
    private Comment parent;
    private User poster;
    private Date date;
    private String text;

    public Comment(User poster, Date date, String text, Comment parent) {
        this.poster = poster;
        this.date = date;
        this.text = text;
        this.parent = parent;
        this.children = new ArrayList();
    }
    
    public Comment(User poster, Date date, String text) {
        this.poster = poster;
        this.date = date;
        this.text = text;
        this.parent = null;
        this.children = new ArrayList();
    }
    
    public boolean hasChildren(){
        return children.isEmpty();
    }

    public List<Comment> getChildren() {
        return children;
    }
    
    public void addChild(Comment child){
        children.add(child);
    }
    
    public boolean removeChild(Comment child){
        return children.remove(child);
    }
    
    public void setParent(Comment comment){
        this.parent = comment;
    }

    public Comment getParent() {
        return parent;
    }

    public User getPoster() {
        return poster;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    
    @Override
    public String toString(){
        String dateSTR = new SimpleDateFormat(Logic.DATE_FORMAT).format(date);
        return poster.toString() + " ["+dateSTR+"]: "+text;
    }
}
