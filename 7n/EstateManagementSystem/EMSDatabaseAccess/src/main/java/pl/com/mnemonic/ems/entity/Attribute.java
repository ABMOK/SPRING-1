package pl.com.mnemonic.ems.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "attribute", catalog = "estates")
public class Attribute implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idattribute;
	private Set<CategoryAttribute> categoryAttributes = new HashSet<CategoryAttribute>(0);
	private Role role;
	private String attributeName;

	public Attribute() {
	}

	public Attribute(int idattribute) {
		this.idattribute = idattribute;
		
	}

	public Attribute(int idattribute, String attributeName) {
		this.idattribute = idattribute;
		
		this.attributeName = attributeName;
	}

	@Id
	@Column(name = "idattribute", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getIdattribute() {
		return this.idattribute;
	}

	public void setIdattribute(Integer idattribute) {
		this.idattribute = idattribute;
	}

	@Column(name = "attributeName", length = 45)
	public String getAttributeName() {
		return this.attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_idrole")
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attribute", orphanRemoval = true)
	public Set<CategoryAttribute> getCategoryAttributes() {
		return this.categoryAttributes;
		}

	public void setCategoryAttributes(Set<CategoryAttribute> categoryAttributes) {
		this.categoryAttributes = categoryAttributes;
		}

}
