package logic.file;

import java.io.File;
import java.util.Arrays;

public class Video extends File{
    private static final String[] extensions = {".mp4"};
    
    private final String path;
    
    public Video(String path) throws FileExtensionException{
        super(path);
        this.path = path;
        String extension = path.substring(path.lastIndexOf("."));
        if(!Arrays.asList(extensions).contains(extension))
            throw new FileExtensionException(extension, extensions);
    }
    
    @Override
    public String toString(){
        return path.substring(path.lastIndexOf("\\")+1, path.length());
    }
}
