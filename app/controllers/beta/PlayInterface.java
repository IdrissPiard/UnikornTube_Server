package controllers.beta;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import models.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.api.libs.Files;
import play.libs.Json;
import play.mvc.*;
import utils.bdd.*;

public class PlayInterface extends Controller {

	/* 
	JsonNode json = request().body().asJson();
	String name = json.findPath("name").getTextValue();
	return badRequest("Missing parameter [name]");
	return ok("Hello " + name);
	ObjectNode result = Json.newObject();
	result.put("status", "KO");
	return ok(result);
    @BodyParser.Of(Json.class)
	*/
	
	/* --- DATA --- */
       public static Result getComments(int videoId) {
    	   try {
    		   final ObjectMapper mapper = new ObjectMapper();
    		   List<Comment> c = CommentDAO.findComments(videoId);
    		   if(c == null){
    			   return internalServerError("Unknown error");
    		   }
    		   String r = mapper.writeValueAsString(c);
    		    JsonNode result = Json.parse(r);
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getPlaylists(int userId) {
    	   try {
    		   final ObjectMapper mapper = new ObjectMapper();
    		   List<Playlist> c = PlaylistDAO.getUserPlaylists(userId);
    		   if(c == null){
    			   return internalServerError("Unknown error");
    		   }
    		   String r = mapper.writeValueAsString(c);
    		    JsonNode result = Json.parse(r);
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getPlaylist(int playlistId) {
    	   try {
    		   final ObjectMapper mapper = new ObjectMapper();
    		   Playlist c = PlaylistDAO.findPlaylist(playlistId);
    		   if(c == null){
    			   return internalServerError("Unknown error");
    		   }
    		   String r = mapper.writeValueAsString(c);
    		    JsonNode result = Json.parse(r);
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getLastVideo() {
    	   try {
    		   final ObjectMapper mapper = new ObjectMapper();
    		   List<Video> c = playTestGenerator.genVideos();
    		   if(c == null){
    			   return internalServerError("Unknown error");
    		   }
    		   String r = mapper.writeValueAsString(c);
    		   JsonNode result = Json.parse(r);
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getPopularVideo() {
    	   try {
    		   final ObjectMapper mapper = new ObjectMapper();
    		   playTestGenerator.genVideos();
    		   List<Video> c = playTestGenerator.genVideos();
    		   if(c == null){
    			   return internalServerError("Unknown error");
    		   }
    		   String r = mapper.writeValueAsString(c);
    		   JsonNode result = Json.parse(r);
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getVideo(int videoId) {
    	   try {
    		   final ObjectMapper mapper = new ObjectMapper();
    		   Video c = playTestGenerator.genSingleVideo();
    		   if(c == null){
    			   return internalServerError("Unknown error");
    		   }
    		   String r = mapper.writeValueAsString(c);
    		   JsonNode result = Json.parse(r);
    		    
    		   //TODO augmenter les vues
    		   //TODO recommandation
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getVideoStream(int videoId) {
    		   File r = ServeVideo(videoId);
    		   if(r == null){
    			   return notFound("Video not found");
    		   }
    		   String[] s = r.getName().split("\\.");
    		   String contentType;
    		   String extention = s[s.length-1];
    		   switch(extention){
    		   case "mp4":
    			   contentType = "video/mp4";
    			   break;
    		   case "mov":
    			   contentType = "video/quicktime";
    			   break;
    		   default:
    			   contentType = "application/octet-stream";
    		   }
    		   
    		   	
    		   response().setHeader("Content-Disposition", "attachment; filename="+ r.getName());
    		   
    		   response().setContentType(contentType);
    		   return ok(r);
       }
       
       
       
       
      /* --- ACTION --- */
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result login() {
    	   
    	   JsonNode json = request().body().asJson();
    	   String username =  json.findPath("username").textValue();
    	   String password = json.findPath("password").textValue();
    	   
    	   if(username == null || password == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(UserDAO.findUserLogin(username,password)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return unauthorized("Wrong username or password");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result register() {
    	   
    	   JsonNode json = request().body().asJson();
    	   String username =  json.findPath("username").textValue();
    	   String password = json.findPath("password").textValue();
    	   String channel = json.findPath("channel").textValue();
    	   String mail = json.findPath("mail").textValue();
    	   
    	   if(username == null || password == null || channel == null || mail == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(UserDAO.create(username,password,mail,channel)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return unauthorized("User already exist");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result addVote(int videoId) {
    	   
    	   JsonNode json = request().body().asJson();
    	   int idUser =  json.findPath("idUser").intValue();
    	   int vote = json.findPath("vote").intValue();
    	   
    	   if(idUser <= 0 || (vote != 1 && vote != -1)){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(VideoDAO.vote(videoId,idUser,vote)){
    	   case 0:
    		   // DAO a utiliser ici
    		   return ok();
    	   case 1:
    		   return notFound("Video doesn't exist");
    	   case 2:
    		   return notFound("User doesn't exist");
    	   case -1:
    		   return badRequest("Incorrect parameters");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result addComment(int videoId) {
    	   
    	   JsonNode json = request().body().asJson();
    	   int idUser =  json.findPath("idUser").intValue();
    	   String comment = json.findPath("comment").textValue();
    	   
    	   if(idUser <= 0 || comment == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(CommentDAO.create(comment,videoId,idUser)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("Video doesn't exist");
    	   case 2:
    		   return notFound("User doesn't exist");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result addPlaylist() {
    	   
    	   JsonNode json = request().body().asJson();
    	   int idUser =  json.findPath("idUser").intValue();
    	   String playlistName = json.findPath("name").textValue();
    	   
    	   if(idUser <= 0 || playlistName == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(PlaylistDAO.create(idUser,playlistName)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("User doesn't exist");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result addVideoToPlaylist() {
    	   
    	   JsonNode json = request().body().asJson();
    	   int idPlaylist =  json.findPath("idPlaylist").intValue();
    	   int idVideo = json.findPath("idVideo").intValue();
    	   
    	   if(idPlaylist <= 0 || idVideo <= 0){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(PlaylistDAO.addVideoToPlaylist(idVideo,idPlaylist)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("Playlist doesn't exist");
    	   case 2:
    		   return notFound("Video doesn't exist");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       @BodyParser.Of(BodyParser.Json.class)
       public static Result subscribe() {
    	   
    	   JsonNode json = request().body().asJson();
    	   int idUser =  json.findPath("idUser").intValue();
    	   int idSubscribed = json.findPath("idSubscribed").intValue();
    	   
    	   if(idUser <= 0 || idSubscribed <= 0){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(UserDAO.subscribe(idUser,idSubscribed)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("User doesn't exist");
    	   case 2:
    		   return notFound("User 2 doesn't exist");
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       public static Result videoUpload() {
    	   File video = request().body().asRaw().asFile();
    	  
    	   if(video == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(uploadVideo(video)){
    	   case 0:
    		   return ok();
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
       public static Result imageUpload() {
    	   File image = request().body().asRaw().asFile();
    	  
    	   if(image == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(uploadImage(image)){
    	   case 0:
    		   return ok();
    	   default:
    		   return internalServerError("Unknown error");
    	   }
       }
       
      static File ServeVideo(final int videoId) {
   		final String directory = "./files/video/";
   		
   		File f = new File(directory);
   		System.out.println("Looking for a file matching: \"" + Integer.toString(videoId) + "\"");
   		FilenameFilter filter = new FilenameFilter() {
   			
   			@Override
   			public boolean accept(File dir, String name) {
   				//System.out.println(dir.getName() + "\\" + name);
   				if(name.contains(Integer.toString(videoId))){
   					System.out.println("Found at " +  name);
   					return true;
   				}
   				return false;
   			}
   		};
   		
   		File[] files = f.listFiles(filter);
   		if(files.length == 0){
   			System.err.println("No files found for " + f + "\\" + videoId + ".*");
   			return null;
   		}
   		
   		if(files.length > 1){
   			System.err.println("Multiple files found for " + directory + videoId + ".* There should be only 1");
   		}
   		
   		return files[0];
   	}

   	static int uploadVideo(File video) {
   		final String directory = "./files/video/";
   		File dest = new File(directory + "43.mp4");
   		
   		Files.copyFile(video, dest, true);
   		return 0;
   	}
   	
   	static int uploadImage(File image) {
   		final String directory = "./files/image/";
   		File dest = new File(directory + "image.png");
   		Files.copyFile(image, dest, true);
   		return 0;
   	}
       
       
       
       
}