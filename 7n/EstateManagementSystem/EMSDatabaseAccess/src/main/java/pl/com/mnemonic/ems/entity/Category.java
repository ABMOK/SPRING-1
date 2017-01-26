package pl.com.mnemonic.ems.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "category", catalog = "estates")
public class Category implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idcategory;
	private String categoryName;
	private String categoryDescriptionShort;
	private String categoryDescriptionLong;
	private String categoryIcon;
	private Set<Classifiedad> classifiedads = new HashSet<Classifiedad>(0);
	private Set<CategoryAttribute> categoryAttributes = new HashSet<CategoryAttribute>(0);
	
	public Category() {
	}

	public Category(int idcategory) {
		this.idcategory = idcategory;
	}

	public Category(int idcategory, Category category,
			String categoryName, String categoryDescriptionShort,
			String categoryDescriptionLong, String categoryIcon,
			Set<Classifiedad> classifiedads, Set<Category> categories) {
		this.idcategory = idcategory;
		this.categoryName = categoryName;
		this.categoryDescriptionShort = categoryDescriptionShort;
		this.categoryDescriptionLong = categoryDescriptionLong;
		this.categoryIcon = categoryIcon;
		this.classifiedads = classifiedads;
	}

	@Id
	@Column(name = "idcategory", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdcategory() {
		return this.idcategory;
	}

	public void setIdcategory(Integer idcategory) {
		this.idcategory = idcategory;
	}

	@Column(name = "categoryName", length = 45)
	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Column(name = "categoryDescriptionShort", length = 200)
	public String getCategoryDescriptionShort() {
		return this.categoryDescriptionShort;
	}

	public void setCategoryDescriptionShort(String categoryDescriptionShort) {
		this.categoryDescriptionShort = categoryDescriptionShort;
	}

	@Column(name = "categoryDescriptionLong", length = 1500)
	public String getCategoryDescriptionLong() {
		return this.categoryDescriptionLong;
	}

	public void setCategoryDescriptionLong(String categoryDescriptionLong) {
		this.categoryDescriptionLong = categoryDescriptionLong;
	}

	@Column(name = "categoryIcon", length = 100)
	public String getCategoryIcon() {
		return this.categoryIcon;
	}

	public void setCategoryIcon(String categoryIcon) {
		this.categoryIcon = categoryIcon;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Classifiedad> getClassifiedads() {
		return this.classifiedads;
	}

	public void setClassifiedads(Set<Classifiedad> classifiedads) {
		this.classifiedads = classifiedads;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", orphanRemoval = true)
	public Set<CategoryAttribute> getCategoryAttributes() {
		return this.categoryAttributes;
		}

	public void setCategoryAttributes(Set<CategoryAttribute> categoryAttributes) {
		this.categoryAttributes = categoryAttributes;
		}
}
