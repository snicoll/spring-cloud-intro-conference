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
package example.customer.domain;

import javax.persistence.Embeddable;

/**
 * @author Oliver Gierke
 */
@Embeddable
public class Address {

	private final String street, zipCode, city;

	private final Location location;

	public Address(String street, String zipCode, String city, Location location) {
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.location = location;
	}

	protected Address() {
		this(null, null, null, null);
	}

	public String getStreet() {
		return street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	public Location getLocation() {
		return location;
	}

	@Override
	public String toString() {
		return "Address{" + "street='" + street + '\'' +
				", zipCode='" + zipCode + '\'' +
				", city='" + city + '\'' +
				", location=" + location +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Address address = (Address) o;

		if (street != null ? !street.equals(address.street) : address.street != null) return false;
		if (zipCode != null ? !zipCode.equals(address.zipCode) : address.zipCode != null) return false;
		if (city != null ? !city.equals(address.city) : address.city != null) return false;
		return location != null ? location.equals(address.location) : address.location == null;

	}

	@Override
	public int hashCode() {
		int result = street != null ? street.hashCode() : 0;
		result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
		result = 31 * result + (city != null ? city.hashCode() : 0);
		result = 31 * result + (location != null ? location.hashCode() : 0);
		return result;
	}

}
