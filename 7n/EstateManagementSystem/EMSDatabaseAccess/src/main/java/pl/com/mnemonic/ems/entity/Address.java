package pl.com.mnemonic.ems.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "address", catalog = "estates")
public class Address implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idaddress;
	private User user;
	private String country;
	private String countryCode;
	private String cityName;
	private String cityCode;
	private String streetName;
	private String districtName;
	private String buildingNo;
	private String officeNo;
	private Set<Place> places = new HashSet<Place>(0);
	private Set<Facility> facilities = new HashSet<Facility>(0);

	public Address() {
	}

	public Address(User user) {
		this.user = user;
	}

	public Address(User user, String country, String countryCode,
			String cityName, String cityCode, String streetName,
			String districtName, String buildingNo, String officeNo, Set<Place> places) {
		this.user = user;
		this.country = country;
		this.countryCode = countryCode;
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.streetName = streetName;
		this.districtName = districtName;
		this.buildingNo = buildingNo;
		this.officeNo = officeNo;
		this.places = places;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idaddress", unique = true, nullable = false)
	public Integer getIdaddress() {
		return this.idaddress;
	}

	public void setIdaddress(Integer idaddress) {
		this.idaddress = idaddress;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_iduser", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "country", length = 45)
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "countryCode", length = 45)
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@Column(name = "cityName", length = 45)
	public String getCityName() {
		return this.cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name = "cityCode", length = 45)
	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(name = "streetName", length = 200)
	public String getStreetName() {
		return this.streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name = "districtName", length = 45)
	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	@Column(name = "buildingNo", length = 45)
	public String getBuildingNo() {
		return this.buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	@Column(name = "officeNo", length = 45)
	public String getOfficeNo() {
		return this.officeNo;
	}

	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Place> getPlaces() {
		return this.places;
	}

	public void setPlaces(Set<Place> places) {
		this.places = places;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "address")
	public Set<Facility> getFacilities() {
		return this.facilities;
	}

	public void setFacilities(Set<Facility> facilities) {
		this.facilities = facilities;
	}

}
