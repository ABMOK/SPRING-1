package pl.com.mnemonic.ems.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "placeuser", catalog = "estates")
public class Placeuser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idplaceuser;
	private Place place;
	private String usageType;
	private Date startdate;
	private Date enddate;
	private Integer placeAddressIdaddress;
	private Set<Report> reports = new HashSet<Report>(0);
	private Set<User> users = new HashSet<User>(0);

	
	public Placeuser() {
	}

	public Placeuser(String usageType) {

		this.usageType = usageType;
	}

	public Placeuser(Place place, String usageType, Date startdate) {
		this.place = place;
		this.usageType = usageType;
		this.startdate = startdate;
	}

	public Placeuser(Place place, String usageType, Date startdate, Date enddate, Integer placeAddressIdaddress,
			Set<Report> reports, Set<User> users) {
		this.place = place;
		this.usageType = usageType;
		this.startdate = startdate;
		this.enddate = enddate;
		this.placeAddressIdaddress = placeAddressIdaddress;
		this.reports = reports;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idplaceuser", unique = true, nullable = false)
	public Integer getIdplaceuser() {
		return this.idplaceuser;
	}

	public void setIdplaceuser(Integer idplaceuser) {
		this.idplaceuser = idplaceuser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_idplace")
	public Place getPlace() {
		return this.place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	@Column(name = "usageType", nullable = false, length = 45)
	public String getUsageType() {
		return this.usageType;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "placeuser", orphanRemoval = true)
	public Set<Report> getReports() {
		return this.reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startdate", length = 10)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "enddate", length = 10)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	
	@Column(name = "place_address_idaddress")
	public Integer getPlaceAddressIdaddress() {
		return this.placeAddressIdaddress;
	}

	public void setPlaceAddressIdaddress(Integer placeAddressIdaddress) {
		this.placeAddressIdaddress = placeAddressIdaddress;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "placeuser", orphanRemoval = true)
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
