package pl.com.mnemonic.ems.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "place", catalog = "estates")
public class Place implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	
	private Integer idplace;
	private Address address;
	private Facility facility;
	private String description;
	private Integer area;
	private Integer residentCount;
	private Set<Payment> payments = new HashSet<Payment>(0);
	private Set<Placeuser> placeusers = new HashSet<Placeuser>(0);
	private Set<MediaCounter> mediaCounters = new HashSet<MediaCounter>(0);
	

	public Place() {
	}

	public Place(Address address, Facility facility) {

		this.address = address;
		this.facility = facility;
	}

	public Place(Address address, Facility facility, String description, Set<Payment> payments, Set<Placeuser> placeusers) {

		this.address = address;
		this.facility = facility;
		this.description = description;
		this.payments = payments;
		this.placeusers = placeusers;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idplace", unique = true, nullable = false)
	public Integer getIdplace() {
		return this.idplace;
	}

	public void setIdplace(Integer idplace) {
		this.idplace = idplace;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_idaddress", nullable = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_idfacility", nullable = false)
	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	@Column(name = "description", length = 1000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "area")
	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "residentCount")
	public Integer getResidentCount() {
		return residentCount;
	}

	public void setResidentCount(Integer residentCount) {
		this.residentCount = residentCount;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place", orphanRemoval = true)
	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place", orphanRemoval = true)
	public Set<Placeuser> getPlaceusers() {
		return this.placeusers;
	}

	public void setPlaceusers(Set<Placeuser> placeusers) {
		this.placeusers = placeusers;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "place", orphanRemoval = true)
	public Set<MediaCounter> getMediaCounters() {
		return this.mediaCounters;
	}

	public void setMediaCounters(Set<MediaCounter> mediaCounters) {
		this.mediaCounters = mediaCounters;
	}

}
