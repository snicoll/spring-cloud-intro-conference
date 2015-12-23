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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity to represent a {@link Store}.
 *
 * @author Oliver Gierke
 */
@Document
public class Store {

	private final @Id String id;

	private final String name;

	private final Address address;

	public Store(String name, Address address) {

		this.name = name;
		this.address = address;
		this.id = null;
	}

	protected Store() {

		this.id = null;
		this.name = null;
		this.address = null;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Store{" + "id='" + id + '\'' +
				", name='" + name + '\'' +
				", address=" + address +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Store store = (Store) o;

		if (id != null ? !id.equals(store.id) : store.id != null) return false;
		if (name != null ? !name.equals(store.name) : store.name != null) return false;
		return address != null ? address.equals(store.address) : store.address == null;

	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

}
