package utils.bdd;

import java.util.List;

import models.Comment;
import models.Playlist;
import models.User;
import models.Video;

public class TestBDD {
	
	public static void main(String[] args) {
		//User create
		UserDAO.create("test", "test", "test@example.com", "TESTYOLO");
		UserDAO.create("test2", "test2", "test@example.com", "TESTYOLOKIVAETREREMOVE");
		UserDAO.create("test3", "test2", "test@example.com", "TESTYOLOKIVAETREREMOVE");

		//User findAll
		List<User> lu = UserDAO.findAll();
		System.out.println("FindAll Complete");
		
		//User remove
		UserDAO.remove(lu.get(2).id);
		System.out.println("Remove Complete");
		
		//User find
		User u1 = UserDAO.find(lu.get(0).id);
		System.out.println("Find Complete username : "+u1.username);
		
		
		//User findUserLogin
		if(UserDAO.findUserLogin("test", "test") == 0)
			System.out.println("Login Complete");
		
		//User subscribe
		if(UserDAO.subscribe(lu.get(0).id, lu.get(1).id) == 0)
			System.out.println("Subscribe Complete");
		
		//User getUserSubscribe
		List<User> lu1 = UserDAO.getUserSubscribe(lu.get(0).id);
		System.out.println("getSubscribe Complete -> 1er sub : "+lu1.get(0).username);
		
		
		
		//Video create
		String[] tags = {"un","deux"};
		int i = VideoDAO.create("video1", "une description yolo", u1.id, tags);
		if(i>0) System.out.println("Add video Complete");
		
		//Video find
		Video v = VideoDAO.find(i);
		System.out.println("Find Complete : "+v.title);
		
		//Video addView
		VideoDAO.addView(i);
		VideoDAO.addView(i);
		VideoDAO.addView(i);
		VideoDAO.addView(i);
		
		//Video
		VideoDAO.vote(i, u1.id, 1);
		VideoDAO.vote(i, lu.get(0).id, -1);
		VideoDAO.vote(i, lu.get(1).id, -1);
		
		List<Video> vids = VideoDAO.getLastVideo();
		System.out.println("Find Complete : "+vids.get(0).title);
		
		List<Video> vids2 = VideoDAO.getPopularVideo();
		System.out.println("Find Complete : "+vids2.get(0).title);
		
		List<Video> vids3 = VideoDAO.recherche("vid");
		System.out.println("Search Complete : "+vids3.get(0).title);
		
		VideoDAO.addTagToVideo(v.id, "LOLOLOL");
		
		List<String> tags1 = VideoDAO.findTagVideo(v.id);
		System.out.println("Tags : "+tags1.toString());
		
		
		PlaylistDAO.create(lu.get(0).id, "uneplaylist");
		
		System.out.println("Video dans la playlist :");
		for(Video vi : PlaylistDAO.findPlaylist(1).videos) {
			System.out.println(vi.title);
		}
		System.out.println();
		
		List<Playlist> playlists = PlaylistDAO.getUserPlaylists(lu.get(0).id);
		System.out.println("Playlists : "+playlists.get(0).title);
		
		PlaylistDAO.addVideoToPlaylist(v.id, 1);
		
		
		CommentDAO.create("uncommentaire", v.id, lu.get(0).id);
		
		CommentDAO.repToComment("unautrecommentaire", v.id, 1);
		
		List<Comment> comments = CommentDAO.findComments(v.id);
		System.out.println("Commentaires : "+playlists.get(0).title);
		
		
	}

}
