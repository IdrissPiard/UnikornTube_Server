package controllers;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.Json;
import play.mvc.*;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;

public class PlayInterface extends Controller {

	/* JsonNode json = request().body().asJson();
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
    		   String r = mapper.writeValueAsString(playTestGenerator.genComment());
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
    		   String r = mapper.writeValueAsString(playTestGenerator.genPlaylist());
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
    		   String r = mapper.writeValueAsString(playTestGenerator.genSinglePlaylist());
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
    		   String r = mapper.writeValueAsString(playTestGenerator.genVideos());
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
    		   String r = mapper.writeValueAsString(playTestGenerator.genVideos());
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
    		   String r = mapper.writeValueAsString(playTestGenerator.genSingleVideo());
    		    JsonNode result = Json.parse(r);
    		   return ok(result);
		} catch (IOException e) {
			e.printStackTrace();
			return internalServerError(e.toString());
		}
       }
       
       public static Result getVideoStream(int videoId) {
    		   File r = playTestGenerator.ServeVideo(videoId);
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
    	   
    	   switch(playTestGenerator.testLogin(username, password)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return unauthorized("Wrong username or password");
    	   default:
    		   return internalServerError("Unknow error");
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
    	   
    	   switch(playTestGenerator.testRegister(username, password,channel,mail)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return unauthorized("User already exist");
    	   default:
    		   return internalServerError("Unknow error");
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
    	   
    	   switch(playTestGenerator.testVote(videoId,idUser,vote)){
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
    		   return internalServerError("Unknow error");
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
    	   
    	   switch(playTestGenerator.testComment(videoId,idUser,comment)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("Video doesn't exist");
    	   case 2:
    		   return notFound("User doesn't exist");
    	   default:
    		   return internalServerError("Unknow error");
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
    	   
    	   switch(playTestGenerator.testAddPlaylist(idUser,playlistName)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("User doesn't exist");
    	   default:
    		   return internalServerError("Unknow error");
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
    	   
    	   switch(playTestGenerator.testAddVideoToPlaylist(idPlaylist,idVideo)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("Playlist doesn't exist");
    	   case 2:
    		   return notFound("Video doesn't exist");
    	   default:
    		   return internalServerError("Unknow error");
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
    	   
    	   switch(playTestGenerator.testSubscribe(idUser,idSubscribed)){
    	   case 0:
    		   return ok();
    	   case 1:
    		   return notFound("User doesn't exist");
    	   case 2:
    		   return notFound("User 2 doesn't exist");
    	   default:
    		   return internalServerError("Unknow error");
    	   }
       }
       
       public static Result videoUpload() {
    	   File video = request().body().asRaw().asFile();
    	  
    	   if(video == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(playTestGenerator.uploadVideo(video)){
    	   case 0:
    		   return ok();
    	   default:
    		   return internalServerError("Unknow error");
    	   }
       }
       
       public static Result imageUpload() {
    	   File image = request().body().asRaw().asFile();
    	  
    	   if(image == null){
    		   return badRequest("Incorrect parameters");
    	   }
    	   
    	   switch(playTestGenerator.uploadImage(image)){
    	   case 0:
    		   return ok();
    	   default:
    		   return internalServerError("Unknow error");
    	   }
       }
       
       
       
       
}