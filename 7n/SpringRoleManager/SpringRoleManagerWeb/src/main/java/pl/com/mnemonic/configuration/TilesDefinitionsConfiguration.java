package pl.com.mnemonic.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;

public class TilesDefinitionsConfiguration implements DefinitionsFactory {

	private static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
	private static final Attribute TEMPLATE = new Attribute("/WEB-INF/layout/layout.jsp");

	/**
	 * Prepares Tiles template
	 * 
	 * @param name view name
	 * @param title view title
	 * @param body view body
	 */
	private static void addLayoutTemplate(String name, String title, String body) {
		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		attributes.put("title", new Attribute(title));
		attributes.put("header", new Attribute("/WEB-INF/layout/header.jsp"));
		attributes.put("menu", new Attribute("/WEB-INF/layout/menu.jsp"));
		attributes.put("content", new Attribute(body));
		attributes.put("footer", new Attribute("/WEB-INF/layout/footer.jsp"));
		tilesDefinitions.put(name, new Definition(name, TEMPLATE, attributes));
	}

	/**
	 * Prepares all application view definitions
	 */
	public static void addDefinitions() {
		addLayoutTemplate("login", "Login", "/WEB-INF/views/login.jsp");
		addLayoutTemplate("welcome", "Welcome!", "/WEB-INF/views/welcome.jsp");
		addLayoutTemplate("error", "error", "/WEB-INF/error.jsp");
		addLayoutTemplate("users", "", "/WEB-INF/views/users.jsp");
	}

	/**
	 * Allows tiles to resolve view from definitions
	 * 
	 * @param name
	 * @param tilesContext
	 * @return view definition
	 */
	@Override
	public Definition getDefinition(String name, Request tilesContext) {
		return tilesDefinitions.get(name);
	}

}
