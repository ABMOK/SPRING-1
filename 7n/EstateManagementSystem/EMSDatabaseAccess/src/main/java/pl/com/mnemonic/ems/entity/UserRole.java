package pl.com.mnemonic.ems.entity;

// Generated 2015-10-25 13:34:10 by Hibernate Tools 4.0.0

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * UserRole generated by hbm2java
 */
@Entity
@Table(name = "user_role", catalog = "estates")
public class UserRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer iduserRole;
	private User user;
	private Role role;
	private Integer placeownerIdplaceowner;
	private Integer placetenantIdplacetenant;

	public UserRole() {
	}

	public UserRole(int placeownerIdplaceowner, int placetenantIdplacetenant) {
		this.placeownerIdplaceowner = placeownerIdplaceowner;
		this.placetenantIdplacetenant = placetenantIdplacetenant;
	}

	public UserRole(User user, Role role, Integer placeownerIdplaceowner,
			Integer placetenantIdplacetenant) {
		this.user = user;
		this.role = role;
		this.placeownerIdplaceowner = placeownerIdplaceowner;
		this.placetenantIdplacetenant = placetenantIdplacetenant;
	}

	public UserRole(User user, Role role) {
		this.user = user;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "iduser_role", unique = true, nullable = false)
	public Integer getIduserRole() {
		return this.iduserRole;
	}

	public void setIduserRole(Integer iduserRole) {
		this.iduserRole = iduserRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_iduser")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_idrole")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "placeowner_idplaceowner", nullable = false)
	public Integer getPlaceownerIdplaceowner() {
		return this.placeownerIdplaceowner;
	}

	public void setPlaceownerIdplaceowner(Integer placeownerIdplaceowner) {
		this.placeownerIdplaceowner = placeownerIdplaceowner;
	}

	@Column(name = "placetenant_idplacetenant", nullable = false)
	public Integer getPlacetenantIdplacetenant() {
		return this.placetenantIdplacetenant;
	}

	public void setPlacetenantIdplacetenant(Integer placetenantIdplacetenant) {
		this.placetenantIdplacetenant = placetenantIdplacetenant;
	}

}