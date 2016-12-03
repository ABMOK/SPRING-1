package pl.com.mnemonic.controller;

public class RoleManagerUriConstants {

	public static final String ADMINISTRATION = "/administration";
	public static final String USERS = "/users";
	public static final String USER = "/{idu}";
	public static final String DELETE_USER = USERS + "/delete" + USER;
	public static final String UPDATE_USER = USERS + "/update" + USER;
}
