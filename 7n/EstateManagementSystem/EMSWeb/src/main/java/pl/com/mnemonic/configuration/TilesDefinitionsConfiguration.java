package pl.com.mnemonic.configuration;

import org.apache.log4j.Logger;
import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

import java.util.HashMap;
import java.util.Map;

public class TilesDefinitionsConfiguration implements DefinitionsFactory {
	static final Logger logger = Logger.getLogger(TilesDefinitionsConfiguration.class);
	private static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
		
	private static final Attribute TEMPLATE = new Attribute("/WEB-INF/layout/layout.jsp");
	
	private static final Attribute ESTATES_TEMPLATE = new Attribute("/WEB-INF/layout/template.jsp");

	/**
	 * Prepares Tiles template
	 * @param name view name
	 * @param title view title
	 * @param body view body
     */
	private static void addLayoutTemplate(String name, String title, String body) {
		logger.debug("Configuring layout");
		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("title", new Attribute(title));
		attributes.put("header", new Attribute("/WEB-INF/layout/template/header.jsp"));
		attributes.put("top", new Attribute("/WEB-INF/layout/template/top.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/layout/template/menu.jsp"));		
		attributes.put("content", new Attribute(body));
		attributes.put("footer", new Attribute("/WEB-INF/layout/template/footer.jsp"));
		tilesDefinitions.put(name, new Definition(name, TEMPLATE, attributes));
	}

	/**
	 * Prepares Tiles template
	 * @param name view name
	 * @param title view title
	 * @param body view body
	 * @param menu view menu
	 */
	private static void addEstatesTemplate(String name, String title, String body, String menu) {
		logger.debug("Configuring layout");
		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("title", new Attribute(title));
		attributes.put("header", new Attribute("/WEB-INF/layout/template/header.jsp"));
		attributes.put("top", new Attribute("/WEB-INF/layout/template/top.jsp"));
		attributes.put("menu", new Attribute(menu));		
		attributes.put("content", new Attribute(body));
		attributes.put("footer", new Attribute("/WEB-INF/layout/template/footer.jsp"));
		tilesDefinitions.put(name, new Definition(name, ESTATES_TEMPLATE, attributes));
	}

	/**
	 * Prepares all application view definitions
	 */
	public static void addDefinitions() {
		addLayoutTemplate("login", "Login", "/WEB-INF/views/login.jsp");
		
		addEstatesTemplate("estates","Managed estates","/WEB-INF/views/administration/estates.jsp", "/WEB-INF/layout/estates/menuFacilities.jsp");
		addEstatesTemplate("estate","Manage estate","/WEB-INF/views/administration/estate.jsp", "/WEB-INF/layout/estates/menuFacilities.jsp");
		addEstatesTemplate("places","Places list","/WEB-INF/views/administration/places.jsp", "/WEB-INF/layout/estates/menuFacilities.jsp");
		addEstatesTemplate("place","Manage place","/WEB-INF/views/administration/place.jsp", "/WEB-INF/layout/estates/menuFacilities.jsp");

		addEstatesTemplate("media","Facility media list","/WEB-INF/views/administration/media.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");
		addEstatesTemplate("medium","Facility medium","/WEB-INF/views/administration/medium.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");

		addEstatesTemplate("tenants","Tenants list","/WEB-INF/views/administration/tenants.jsp", "/WEB-INF/layout/estates/menuUsers.jsp");
		addEstatesTemplate("tenant","New","/WEB-INF/views/administration/tenant.jsp", "/WEB-INF/layout/estates/menuUsers.jsp");
		addEstatesTemplate("administration","administration","/WEB-INF/views/administration/administration.jsp", "/WEB-INF/layout/estates/menuUsers.jsp");
		addEstatesTemplate("newuser","","/WEB-INF/views/administration/newuser.jsp", "/WEB-INF/layout/estates/menuUsers.jsp");

		addEstatesTemplate("finances","Finances","/WEB-INF/views/administration/finances.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");
		addEstatesTemplate("reporting","Reporting","/WEB-INF/views/administration/reporting.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");
		addEstatesTemplate("expenses","expenses","/WEB-INF/views/administration/expenses.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");
		addEstatesTemplate("expense","expense","/WEB-INF/views/administration/expense.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");
		addEstatesTemplate("counters","counters","/WEB-INF/views/administration/counters.jsp", "/WEB-INF/layout/estates/menuFinances.jsp");


		addEstatesTemplate("adsadmin","administration","/WEB-INF/views/administration/adsadmin.jsp", "/WEB-INF/layout/estates/menu.jsp");
		addEstatesTemplate("ad","ad","/WEB-INF/views/administration/ad.jsp", "/WEB-INF/layout/estates/menu.jsp");



		//addMainLayout("template", "Tiles template",	"/WEB-INF/layout/layout.jsp");	??	
		addLayoutTemplate("registrationform", "Registration Form", "/WEB-INF/views/public/registration.jsp");//jak kolorowy glowny, /register
		
		//addShortLayout("login", "Login", "/WEB-INF/views/login.jsp");
		
		addLayoutTemplate("public", "Public view", "/WEB-INF/views/public/users.jsp"); //kolorowy glowny
		addLayoutTemplate("welcome", "Welcome!","/WEB-INF/views/welcome.jsp");
		//addMainLayout("estates", "Managed estates","/WEB-INF/views/welcome.jsp");
		//addMainLayout("facility","Facility details","/WEB-INF/views/administration/estate.jsp");
		//addLayoutTemplate("tenants","Tenants list","/WEB-INF/views/administration/tenants.jsp");
		addLayoutTemplate("error","error","/WEB-INF/error.jsp");


		//to ten brzydki layout
		//addMyLayout("mylayout", "","/WEB-INF/layout/mylayout.jsp");
		addLayoutTemplate("users","","/WEB-INF/views/administration/users.jsp");//(/administration)
	}

	/**
	 * Allows tiles to resolve view from definitions
	 * @param name
	 * @param tilesContext
     * @return view definition
     */
	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		return tilesDefinitions.get(name);
	}

}
