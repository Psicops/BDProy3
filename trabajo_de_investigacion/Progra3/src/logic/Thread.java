package logic;

import java.util.ArrayList;
import java.util.List;
import logic.file.Video;

public class Thread {
    private final int matchID;
    private final String text;
    private final User poster;
    private final ArrayList<Video> videos;
    private ArrayList<Comment> comments;

    
    public Thread(int matchID, User poster, String text) {
        this.matchID = matchID;
        this.text = text;
        this.poster = poster;
        this.videos = new ArrayList();
        this.comments = new ArrayList();
    }
    
    public void setComment(ArrayList<Comment> com){
        this.comments = com;
    }
    
    public int getMatchID(){
        return matchID;
    }
    
    public String getText(){
        return text;
    }
    
    public User getUser(){
        return this.poster;
    }
    
    public ArrayList<Comment> getComments(){
        return comments;
    }
    
    private void addComment(List<Comment> parents, Comment parent, Comment child){
        for(Comment comment : parents){
            if(comment.equals(parent)){
                comment.addChild(child);
                child.setParent(comment);
                return;
            }
            else
                addComment(comment.getChildren(), comment, child);
        }
    }
    
    public void addComment(Comment child, Comment parent){
        addComment(comments, parent, child);
    }
    
    public void addComment(Comment comment){
        comments.add(comment);
        comment.setParent(null);
    }
    
    public void removeComment(Comment comment){
    }
    
    public List<Video> getVideos(){
        return videos;
    }
    
    public void addVideo(Video video){
        videos.add(video);
    }
    
    public boolean removeVideo(Video video){
        return videos.remove(video);
    }
    
    @Override
    public String toString(){
        return "Match #" + matchID + " by " + poster.getId();
    }
}