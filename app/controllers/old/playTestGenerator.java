package controllers.old;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import play.api.libs.Files;
import models.Comment;
import models.Playlist;
import models.Video;

public class playTestGenerator {
	
	public static List<Comment> genComment(){
		List<Comment> l = new ArrayList<Comment>();
		for(int x = 0; x< 5; x++){
			l.add(new Comment(x, x, x, "abcd", null));
		}
		return l;
	}
	
	public static List<Playlist> genPlaylist(){
		List<Playlist> l = new ArrayList<Playlist>();
		for(int x = 0; x< 5; x++){
			l.add(new Playlist(x, x, "xxxxx"));
		}
		return l;
	}
	
	public static List<Video> genVideos(){
		List<Video> l = new ArrayList<Video>();
		for(int x = 0; x< 5; x++){
			l.add(genSingleVideo());
		}
		return l;
	}
	
	public static Playlist genSinglePlaylist(){
		return new Playlist(42, 42, "Enyx",genVideos());

	}
	
	public static Video genSingleVideo(){
		Video v = new Video(42, "EnyxAllmighty", 42, 0, 42666, 42,"Dis iz a deskripssion","2014-09-07 07:09:42");
		// Requete user
		v.channel = "nx";
		v.username = "Enyx";
		// Requete tags
		v.tags = new String[]{"swag","mlg","pro","god"};
		return v;
	}
	
	public static int testLogin(String username, String password){
		 if(username.equals("Enyx") && password.equals("Password")){
  		   return 0;
  	   	 }
		 return 1;
	}

	public static int testRegister(String username, String password,String channel, String mail) {
		if(username.equals("Enyx") || channel.equals("nx") || mail.equals("root@enyx.me")){
	  		   return 1;
	  	}
		return 0;
	}

	public static int testVote(int videoId, int idUser, int vote) {
		if(videoId != 42){
			return 1;
		}
		if(idUser != 42){
			return 2;
		}
		if(vote != 1 && vote != -1){
			return -1;
		}
		return 0;
	}

	public static int testComment(int videoId, int idUser, String comment) {
		if(videoId != 42){
			return 1;
		}
		if(idUser != 42){
			return 2;
		}
		return 0;
	}
	
	public static int testAddPlaylist(int idUser, String playlistName) {
		if(idUser != 42){
			return 1;
		}
		return 0;
	}

	public static int testAddVideoToPlaylist(int idPlayer, int idVideo) {
		if(idPlayer != 42){
			return 1;
		}
		if(idVideo != 42){
			return 2;
		}
		return 0;
	}

	public static int testSubscribe(int idUser, int idSubscribed) {
		if(idUser != 42){
			return 1;
		}
		if(idSubscribed != 42){
			return 2;
		}
		return 0;
	}
	
	
	
	
	
	
	
}
