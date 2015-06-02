package controllers;

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
			l.add(new Comment(x, x, x, "abcd"));
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
		return new Video(42, "EnyxAllmighty", "nxalmghty", 42, 0, 42666, 42);
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

	public static File ServeVideo(final int videoId) {
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

	public static int uploadVideo(File video) {
		final String directory = "./files/video/";
		File dest = new File(directory + "43.mp4");
		
		Files.copyFile(video, dest, true);
		return 0;
	}
	
	public static int uploadImage(File image) {
		final String directory = "./files/image/";
		File dest = new File(directory + "image.png");
		Files.copyFile(image, dest, true);
		return 0;
	}
	
	
	
	
	
	
	
}
