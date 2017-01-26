package pl.com.mnemonic.ems.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "facility", catalog = "estates")
public class Facility implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idfacility;
	private Address address;
	private String description;
	private String type;
	private Integer area;
	private Set<Expense> expenses = new HashSet<Expense>(0);
	private Set<Media> medias = new HashSet<Media>(0);
	private Set<Place> places = new HashSet<Place>(0);

	public Facility() {
	}

	public Facility(String description, String type, Set<Place> places) {
		this.description = description;
		this.type = type;
		this.places = places;
	}
	
	public Facility(String description, String type, Set<Media> medias, Set<Place> places) {
		this.description = description;
		this.type = type;
		this.places = places;
		this.medias = medias;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idfacility", unique = true, nullable = false)
	public Integer getIdfacility() {
		return this.idfacility;
	}

	public void setIdfacility(Integer idfacility) {
		this.idfacility = idfacility;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_idaddress", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "type", length = 45)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facility", orphanRemoval = true)
	public Set<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facility", orphanRemoval = true)
	public Set<Media> getMedias() {
		return this.medias;
	}

	public void setMedias(Set<Media> medias) {
		this.medias = medias;
	}

	@Column(name = "area")
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "facility", orphanRemoval = true)
	public Set<Expense> getExpenses() {
		return expenses;
	}

	public void setExpenses(Set<Expense> expenses) {
		this.expenses = expenses;
	}

}
