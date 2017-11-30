package logic.file;

import java.io.File;
import java.util.Arrays;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Image extends File{
    private static final String[] extensions = {".jpg"};
    
    public String path;
    
    public Image(String path) throws FileExtensionException{
        super(path);
        this.path = path;
        String extension = path.substring(path.lastIndexOf("."));
        if(!Arrays.asList(extensions).contains(extension))
            throw new FileExtensionException(extension, extensions);
    }
    
    public Icon toIcon(){
        return new ImageIcon(path);
    }
    
    public Icon toIcon(int height, int width){
        return new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(height, width, java.awt.Image.SCALE_DEFAULT));
    }
    
    @Override
    public String toString(){
        return path.substring(path.lastIndexOf("/"), path.length()-1);
    }
}
