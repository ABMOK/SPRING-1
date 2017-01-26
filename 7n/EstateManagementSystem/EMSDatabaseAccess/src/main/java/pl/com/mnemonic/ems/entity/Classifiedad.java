package pl.com.mnemonic.ems.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "classifiedad", catalog = "estates")
public class Classifiedad implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idclassifiedAd;
	private Category category;
	private User user;
	private String title;
	private String shortDescription;
	private String longDescription;
	private String photolink;

	public Classifiedad() {
	}

	public Classifiedad(int idclassifiedAd, Category category, User user) {
		this.idclassifiedAd = idclassifiedAd;
		this.category = category;
		this.user = user;
	}

	public Classifiedad(int idclassifiedAd, Category category, User user,
			String title, String shortDescription, String longDescription,
			String photolink) {
		this.idclassifiedAd = idclassifiedAd;
		this.category = category;
		this.user = user;
		this.title = title;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.photolink = photolink;
	}

	@Id
	@Column(name = "idclassifiedAd", unique = true, nullable = false)
	public int getIdclassifiedAd() {
		return this.idclassifiedAd;
	}

	public void setIdclassifiedAd(int idclassifiedAd) {
		this.idclassifiedAd = idclassifiedAd;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_idcategory", nullable = false)
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_iduser", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "title", length = 45)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "shortDescription", length = 300)
	public String getShortDescription() {
		return this.shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	@Column(name = "longDescription", length = 1500)
	public String getLongDescription() {
		return this.longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	@Column(name = "photolink", length = 45)
	public String getPhotolink() {
		return this.photolink;
	}

	public void setPhotolink(String photolink) {
		this.photolink = photolink;
	}

}
