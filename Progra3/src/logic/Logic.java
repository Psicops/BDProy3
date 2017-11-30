package logic;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import logic.file.FileExtensionException;
import logic.file.Image;
import logic.file.Video;

public class Logic {
    
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private static Logic instance;

    private Logic(){
    }

    public static Logic getInstance(){
        if(instance == null)
            instance = new Logic();
        return instance;
    }
    
    public void addUser(User user) throws Exception, UnknownHostException{
        BasicDBObject nuevoUsuario = new BasicDBObject();
        DBCollection users = Conexion.getTabla("users");
        nuevoUsuario.put("name", user.getId());
        String pass = Encrypter.encrypt(user.getPassword());
        nuevoUsuario.put("pass", pass);
        nuevoUsuario.put("status", user.getStatus());
        nuevoUsuario.put("email", user.getEmail());
        if(user.getDisplayEmail())
            nuevoUsuario.put("displayE", "1");
        else
            nuevoUsuario.put("displayE", "0");
        if(user.getDisplayImage())
            nuevoUsuario.put("displayP", "1");
        else
            nuevoUsuario.put("displayP", "0");
        users.insert(nuevoUsuario);
        addImage(user.getImage(), user.getId());
    }
    
    public User getUser(String username, String password) throws Exception{
        DBCollection users = Conexion.getTabla("users");
        BasicDBObject Usuario = new BasicDBObject();
        Usuario.put("name", username);
        String pass = Encrypter.encrypt(password);
        Usuario.put("pass", pass);
        DBCursor result = users.find(Usuario);
        User us = null;
        DBObject res = null;
        while(result.hasNext()){
            res = result.next();
        }
        boolean disP = false;
        boolean disE = false;
        Image im = getImage(username);
        if("1".equals(res.get("displayE")))
            disE = true;
        if("1".equals(res.get("displayP")))
            disP = true;
        us = new User(res.get("name").toString(), res.get("pass").toString(), res.get("status").toString(),
                res.get("email").toString(), im, disE, disP);
        return us;
    }
    
    public User getUserName(String username) throws Exception{
        DBCollection users = Conexion.getTabla("users");
        BasicDBObject Usuario = new BasicDBObject();
        Usuario.put("name", username);
        DBCursor result = users.find(Usuario);
        User us = null;
        DBObject res = null;
        while(result.hasNext()){
            res = result.next();
        }
        boolean disP = false;
        boolean disE = false;
        Image im = getImage(username);
        if("1".equals(res.get("displayE")))
            disE = true;
        if("1".equals(res.get("displayP")))
            disP = true;
        us = new User(res.get("name").toString(), res.get("pass").toString(), res.get("status").toString(),
                res.get("email").toString(), im, disE, disP);
        return us;
    }
    
    public void addImage(Image image, String imageName) throws UnknownHostException, IOException{
        GridFS gfsImageCollection = new GridFS(Conexion.getDB(), "image");
        GridFSInputFile gfsFile = gfsImageCollection.createFile(image);
        gfsFile.setFilename(imageName);
        gfsFile.save();
    }
    
    public Image getImage(String imageName) throws UnknownHostException, IOException, FileExtensionException{
        GridFS gfsImageCollection = new GridFS(Conexion.getDB(), "image");
        GridFSDBFile gridFSImage = gfsImageCollection.findOne(imageName);
        gridFSImage.writeTo("temp\\"+imageName+".jpg");
        return new Image("temp\\"+imageName+".jpg");
    }
    
    public void addVideo(Video video, String videoName) throws UnknownHostException, IOException{
        GridFS gfsVideoCollection = new GridFS(Conexion.getDB(), "video");
        GridFSInputFile gfsFile = gfsVideoCollection.createFile(video);
        gfsFile.setFilename(videoName);
        gfsFile.save();
    }

    public Video getVideo(String videoName) throws UnknownHostException, IOException, FileExtensionException{
        GridFS gfsVideoCollection = new GridFS(Conexion.getDB(), "video");
        GridFSDBFile gridVideo = gfsVideoCollection.findOne(videoName);
        gridVideo.writeTo("temp\\"+videoName+".mp4");
        return new Video("temp\\"+videoName+".mp4");
    }
    
    public void addThread(Thread thread) throws Exception, UnknownHostException{
        BasicDBObject thr = new BasicDBObject();
        DBCollection threads = Conexion.getTabla("threads");
        thr.put("matchID",thread.getMatchID());
        thr.put("poster",thread.getUser().getId());
        thr.put("text",thread.getText());
        thr.put("cantV", thread.getVideos().size());
        threads.insert(thr);
        int i = 0;
        for(Video video: thread.getVideos()){
            addVideo(video, "" +thread.getMatchID()+i);
            i++;
        }
    }
    
    public void deleteThread(Thread thread) throws Exception, UnknownHostException{
        BasicDBObject thr = new BasicDBObject();
        DBCollection threads = Conexion.getTabla("threads");
        thr.put("matchID",thread.getMatchID());
        threads.remove(thr);
        //Falta borrar comentarios del thread y demás**
    }
    
    public List<Thread> getThreads() throws Exception{
        ArrayList<Thread> threads = new ArrayList();
        DBCollection colThreads = Conexion.getTabla("threads");
        DBCursor cursor = colThreads.find();
        DBObject thread = null;
        Thread th = null;
        while(cursor.hasNext()){
            thread = cursor.next();
            th = new Thread(Integer.parseInt(thread.get("matchID").toString()),
                    getUserName(thread.get("poster").toString()), thread.get("text").toString());
            int i = 0;
            int y = Integer.parseInt(thread.get("cantV").toString());
            while(i < y){
                th.addVideo(getVideo(thread.get("matchID").toString()+i));
                i++;
            }
            th.setComment(getComment(th));
            threads.add(th);   
        }
        return threads;
    }

    public void changeThread(Thread oldThread, Thread newThread){
        
    }
    
    public void addComment(Thread thread, Comment comment)throws Exception, UnknownHostException{
        thread.addComment(comment);
        BasicDBObject comm = new BasicDBObject();
        BasicDBObject commNum = new BasicDBObject();
        DBCollection comments = Conexion.getTabla("comments");
        String fecha = new SimpleDateFormat(DATE_FORMAT).format(comment.getDate());
        System.out.println(thread.getMatchID());
        comm.put("matchID", thread.getMatchID());
        commNum.put("matchID", thread.getMatchID());
        System.out.println(comment.getPoster().getId());
        comm.put("poster", comment.getPoster().getId());
        System.out.println(fecha);
        comm.put("date", fecha);
        System.out.println(comment.getText());
        comm.put("text", comment.getText());
        System.out.println(comments.find(commNum).count());
        comm.put("numComment", comments.find(commNum).count()+1);
        comments.insert(comm);
    }
    
    public ArrayList<Comment> getComment(Thread th)throws Exception{
        ArrayList<Comment> com = new ArrayList();
        BasicDBObject mID = new BasicDBObject();
        mID.put("matchID", th.getMatchID());
        DBCollection comments = Conexion.getTabla("comments");
        DBCursor cr = comments.find(mID);
        DBObject obj = null;
        Comment topC = null;
        while(cr.hasNext()){
            obj = cr.next();
            ArrayList<Comment> hijos = new ArrayList();
            Date fecha = new SimpleDateFormat(DATE_FORMAT).parse(obj.get("date").toString());
            topC = new Comment(getUserName(obj.get("poster").toString()), fecha, obj.get("text").toString());
            com.add(topC);
        }
        return com;
    }

    public int getNumCom(Thread thread, Comment com)throws Exception, UnknownHostException{
        BasicDBObject comm = new BasicDBObject();
        comm.put("matchID", thread.getMatchID());
        String fecha = new SimpleDateFormat(DATE_FORMAT).format(com.getDate());
        comm.put("date", fecha);
        DBCollection comments = Conexion.getTabla("comments");
        DBCursor cursor = comments.find(comm);
        DBObject res = null;
        while(cursor.hasNext()){
            res = cursor.next();
        }
        String ID = res.get("numComment").toString();
        int numero = Integer.parseInt(ID);
        return numero;
    }
    
    public void addReply(Thread thread, Comment commentP, Comment reply) throws Exception, UnknownHostException{
        thread.addComment(reply, commentP);
        BasicDBObject comm = new BasicDBObject();
        DBCollection replies = Conexion.getTabla("replies");
        String fecha = new SimpleDateFormat(DATE_FORMAT).format(reply.getDate());
        comm.put("matchID", thread.getMatchID());
        comm.put("comentarioP", getNumCom(thread, commentP));
        comm.put("poster", reply.getPoster().getId());
        comm.put("date", fecha);
        comm.put("text", reply.getText());
        replies.insert(comm);
    }
    
    public ArrayList<Comment> getReply(Thread th, int padre, Comment p)throws Exception{
        ArrayList<Comment> reply = null;
        BasicDBObject mID = new BasicDBObject();
        mID.put("matchID", th.getMatchID());
        mID.put("comentarioP", padre);
        DBCollection replies = Conexion.getTabla("replies");
        DBCursor cr = replies.find(mID);
        DBObject obj = null;
        Comment repli = null;
        while(cr.hasNext()){
            obj = cr.next();
            Date fecha = new SimpleDateFormat(DATE_FORMAT).parse(obj.get("date").toString());
            repli = new Comment(getUserName(obj.get("poster").toString()), fecha, obj.get("text").toString(), p);
            System.out.println(repli.toString());
            reply.add(repli);
            System.out.println("sale");
        }
        return reply;
    }
    
    public boolean getUsuarios(String id) throws UnknownHostException{
        boolean registrado = false;
        DBCollection tabla = Conexion.getTabla("users");
        BasicDBObject user = new BasicDBObject();
        user.put("name",id);
        DBCursor cursor = tabla.find(user);
        while(cursor.hasNext()){
            if(id.equals(cursor.next()));
                registrado = true;
        }
        return registrado;
    }
    
    public boolean getPartido(int numP) throws SQLException{
        boolean partido = false;
        ResultSet rs = Conexion.getRS("select numeropartido from partidos where numeropartido = " + numP);
        while(rs.next()){
            if(numP == rs.getInt("numeropartido"));
                partido = true;
        }
        return partido;
    }
    
    public int getCantidadPartidos() throws SQLException{
        ResultSet rs = Conexion.getRS("select count(*) as cantidad from partidos");
        while(rs.next()){
             return rs.getInt("cantidad");
        }
        throw new SQLException("No matches registered to comment on.");
    }
    
    public String getEquipos(int numP) throws SQLException{
        String equipos = "";
        String team1 = "";
        String team2 = "";
        ResultSet rs = Conexion.getRS("select numeropartido, equipo1, equipo2 from partidos where numeropartido = " + numP);
        while(rs.next()){
            team1 += rs.getString("equipo1");
            team2 += rs.getString("equipo2");
        }
        ResultSet rs2 = Conexion.getRS("select nombrePais from equipo where codigoPais = '" + team1+"'");
        while(rs2.next()){
            equipos += rs2.getString("nombrePais") +"("+team1+")";
        }
        equipos += " VS ";
        ResultSet rs3 = Conexion.getRS("select nombrePais from equipo where codigoPais = '" + team2+"'");
        while(rs3.next()){
            equipos += rs3.getString("nombrePais") +"("+team2+")";
        }
        return equipos;
    }

}