package example.customer.integration.store;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonSetter;
import example.customer.domain.Location;

/**
 * Store representation.
 *
 * @author Stephane Nicoll
 */
public class Store {

	private String name;

	private Location location;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@SuppressWarnings("unchecked")
	@JsonSetter("address")
	void setAddressLocation(Map<String, Object> content) {
		Map<String, Object> location = (Map<String, Object>) content.get("location");
		if (location != null) {
			setLocation(new Location((double) location.get("y"),
					(double) location.get("x")));
		}
	}

	@Override
	public String toString() {
		return "Store{" + "name='" + name + '\'' +
				", location=" + location +
				'}';
	}

}
