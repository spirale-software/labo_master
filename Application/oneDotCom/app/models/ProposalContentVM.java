package models;

import java.io.File;
import java.util.Date;

public class ProposalContentVM {
	private String title;
	private String content;
	private String link;
	private String image;
	private Date date;
	private String place;
	private String media;
	private File mediaFile;
	private File imageFile;
	private boolean isFacebookSelected;
	private boolean isTwitterSelected;
	private boolean isMailingListSelected;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getMedia() {
		return media;
	}
	public void setMedia(String media) {
		this.media = media;
	}
	public File getMediaFile() {
		return mediaFile;
	}
	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}
	public File getImageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}
	public boolean isFacebookSelected() {
		return isFacebookSelected;
	}
	public void setFacebookSelected(boolean isFacebookSelected) {
		this.isFacebookSelected = isFacebookSelected;
	}
	public boolean isTwitterSelected() {
		return isTwitterSelected;
	}
	public void setTwitterSelected(boolean isTwitterSelected) {
		this.isTwitterSelected = isTwitterSelected;
	}
	public boolean isMailingListSelected() {
		return isMailingListSelected;
	}
	public void setMailingListSelected(boolean isMailingListSelected) {
		this.isMailingListSelected = isMailingListSelected;
	}
	
	
	
}
