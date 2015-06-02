package controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import models.Video;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.*;
import play.libs.Json;
import play.mvc.*;
import views.html.index;

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
       
       
}