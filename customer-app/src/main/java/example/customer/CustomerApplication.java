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
package example.customer;

import javax.annotation.PostConstruct;

import example.customer.domain.Address;
import example.customer.domain.Customer;
import example.customer.domain.CustomerRepository;
import example.customer.domain.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot application bootstrap class to run a customer service and
 * configure integration with a remote system that exposes a REST resource
 * to lookup stores by location.
 *
 * @author Oliver Gierke
 * @author Stephane Nicoll
 */
@SpringBootApplication
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Service
	static class DataInitializr {

		private final CustomerRepository customers;

		@Autowired
		public DataInitializr(CustomerRepository customers) {
			this.customers = customers;
		}

		@PostConstruct
		public void init() {

			Customer customer = new Customer("Oliver", "Gierke");
			customer.setAddress(
					new Address("625 Avenue of the Americas", "10011", "New York", new Location(40.740337, -73.995146)));

			customers.save(customer);
		}
	}

}
