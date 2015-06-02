package models;

import java.util.List;

public class Video {
	private int id;
	private String title;
	private String coverURL;
	private String videoURL;
	private String vidDescription;
	private int totalLike;
	private int totalDislike;
	private int totalView;
	private int userID;
	private List<Comment> comments;
	
	
	public Video(int id, String title, String coverURL, String videoURL,
			String vidDescription, int totalLike, int totalDislike,
			int totalView, int userID, List<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.coverURL = coverURL;
		this.videoURL = videoURL;
		this.vidDescription = vidDescription;
		this.totalLike = totalLike;
		this.totalDislike = totalDislike;
		this.totalView = totalView;
		this.userID = userID;
		this.comments = comments;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCoverURL() {
		return coverURL;
	}


	public void setCoverURL(String coverURL) {
		this.coverURL = coverURL;
	}


	public String getVideoURL() {
		return videoURL;
	}


	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}


	public String getVidDescription() {
		return vidDescription;
	}


	public void setVidDescription(String vidDescription) {
		this.vidDescription = vidDescription;
	}


	public int getTotalLike() {
		return totalLike;
	}


	public void setTotalLike(int totalLike) {
		this.totalLike = totalLike;
	}


	public int getTotalDislike() {
		return totalDislike;
	}


	public void setTotalDislike(int totalDislike) {
		this.totalDislike = totalDislike;
	}


	public int getTotalView() {
		return totalView;
	}


	public void setTotalView(int totalView) {
		this.totalView = totalView;
	}


	public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	
}

