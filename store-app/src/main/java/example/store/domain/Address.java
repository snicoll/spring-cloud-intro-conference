/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.store.domain;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

/**
 * Value object to represent an {@link Address}.
 *
 * @author Oliver Gierke
 */
public class Address {

	private final String street, city, zip;

	private final @GeoSpatialIndexed Point location;

	public Address(String street, String city, String zip, Point location) {
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.location = location;
	}

	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getZip() {
		return zip;
	}

	public Point getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Address{" + "street='" + street + '\'' +
				", city='" + city + '\'' +
				", zip='" + zip + '\'' +
				", location=" + location +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Address address = (Address) o;

		if (street != null ? !street.equals(address.street) : address.street != null) return false;
		if (city != null ? !city.equals(address.city) : address.city != null) return false;
		if (zip != null ? !zip.equals(address.zip) : address.zip != null) return false;
		return location != null ? location.equals(address.location) : address.location == null;

	}

	@Override
	public int hashCode() {
		int result = street != null ? street.hashCode() : 0;
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (zip != null ? zip.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		return result;
	}

}
