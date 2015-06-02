package controllers;

import java.util.ArrayList;
import java.util.List;

import controllers.test.Comment;
import controllers.test.Playlist;
import controllers.test.Video;

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
	
	
}
