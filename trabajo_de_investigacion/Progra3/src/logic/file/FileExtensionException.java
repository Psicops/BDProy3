package logic.file;

import java.util.Arrays;
import java.util.List;

public class FileExtensionException extends Exception {
    private final String errorExtension;
    private final String[] permittedExtensions;
    
    public FileExtensionException(String extension, String[] permittedExtensions){
        this.errorExtension = extension;
        this.permittedExtensions = permittedExtensions;
    }
    
    public String getExtension(){
        return errorExtension;
    }
    
    public List<String> getPermittedExtensions(){
        return Arrays.asList(permittedExtensions);
    }
    
    @Override
    public String getMessage(){
        return "File extension '"+errorExtension+"' not allowed.";
    }
    
    public String getMessageVerbose(){
        String message = "File extension '"+errorExtension+"' not allowed.\nPermitted extensions: ";
        for(int i = 0; i < permittedExtensions.length; i++){
            if(i < permittedExtensions.length-1)
                message += "'"+permittedExtensions[i]+"', ";
            else
                message += "'"+permittedExtensions[i]+"'.";
        }
        return message;
    }
}
