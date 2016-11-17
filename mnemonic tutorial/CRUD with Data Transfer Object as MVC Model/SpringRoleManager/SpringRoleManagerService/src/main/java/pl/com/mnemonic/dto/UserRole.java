package pl.com.mnemonic.dto;

public enum UserRole {

	ADMIN(1), USER(2), PUBLIC(3);

	private Integer roleCode;

	UserRole(Integer roleCode) {
		this.roleCode = roleCode;
	}

	
	public UserRole fromRoleCode(int roleCode) {

		for (UserRole userRole : UserRole.values()) {

			if (roleCode == userRole.roleCode) {
				return userRole;
			}
		}

		throw new IllegalArgumentException("role code not found");
	}
}
