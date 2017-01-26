package pl.com.mnemonic.ems.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role", catalog = "estates")
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idrole;
	private String roleCode;
	private String roleName;
	private String roleDescription;
	private Set<UserRole> userRoles = new HashSet<UserRole>(0);

	public Role() {
	}

	public Role(int idrole) {
		this.idrole = idrole;
	}

	public Role(int idrole, String roleCode, String roleName,
			String roleDescription, Set<UserRole> userRoles) {
		this.idrole = idrole;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleDescription = roleDescription;
		this.userRoles = userRoles;
	}

	@Id
	@Column(name = "idrole", unique = true, nullable = false)
	public int getIdrole() {
		return this.idrole;
	}

	public void setIdrole(int idrole) {
		this.idrole = idrole;
	}

	@Column(name = "roleCode", length = 45)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "roleName", length = 45)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleDescription", length = 300)
	public String getRoleDescription() {
		return this.roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
	public Set<UserRole>  getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(Set<UserRole>  userRoles) {
		this.userRoles = userRoles;
	}

}
