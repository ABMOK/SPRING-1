package pl.com.mnemonic.ems.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user", catalog = "estates")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer iduser;
	private Placeuser placeuser;
	private String name;
	private String surname;
	private String mail;
	private String login;
	private String password;
	private String phoneNo;
	private String icon;
	private Date regdate;
	private Date lastLogDate;
	private Set<Classifiedad> classifiedads = new HashSet<Classifiedad>(0);
	private Set<Address> addresses = new HashSet<Address>(0);
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public User() {
	}

	public User(Placeuser placeuser, Date regdate) {
		this.placeuser = placeuser;
		this.regdate = regdate;
	}

	public User(Placeuser placeuser, String name, String surname, String mail,
			String login, String password, String phoneNo, String icon,
			Date regdate, Date lastLogDate, Set<Classifiedad> classifiedads, Set<Address> addresses,
			Set<UserRole>  userRoles) {
		this.placeuser = placeuser;
		this.name = name;
		this.surname = surname;
		this.mail = mail;
		this.login = login;
		this.password = password;
		this.phoneNo = phoneNo;
		this.icon = icon;
		this.regdate = regdate;
		this.lastLogDate = lastLogDate;
		this.classifiedads = classifiedads;
		this.addresses = addresses;
		this.userRoles = userRoles;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "iduser", unique = true, nullable = false)
	public Integer getIduser() {
		return this.iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "placeuser_idplaceuser", nullable = false)
	public Placeuser getPlaceuser() {
		return this.placeuser;
	}

	public void setPlaceuser(Placeuser placeuser) {
		this.placeuser = placeuser;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "surname", length = 45)
	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Column(name = "mail", length = 45)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "login", length = 45)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "phoneNo", length = 45)
	public String getPhoneNo() {
		return this.phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Column(name = "icon", length = 300)
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "regdate", nullable = false, length = 10)
	public Date getRegdate() {
		return this.regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "lastLogDate", length = 10)
	public Date getLastLogDate() {
		return this.lastLogDate;
	}

	public void setLastLogDate(Date lastLogDate) {
		this.lastLogDate = lastLogDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	public Set<Classifiedad> getClassifiedads() {
		return this.classifiedads;
	}

	public void setClassifiedads(Set<Classifiedad> classifiedads) {
		this.classifiedads = classifiedads;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
