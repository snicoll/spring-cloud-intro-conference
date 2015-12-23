package example.customer.web;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import example.customer.domain.Customer;
import example.customer.domain.CustomerRepository;
import example.customer.integration.store.Store;
import example.customer.integration.store.StoreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Expose additional {@link Customer}-related operations.
 *
 * @author Stephane Nicoll
 */
@RestController
public class CustomerController {

	private final CustomerRepository customerRepository;

	private final StoreService storeService;

	@Autowired
	public CustomerController(CustomerRepository customerRepository, StoreService storeService) {
		this.customerRepository = customerRepository;
		this.storeService = storeService;
	}

	@RequestMapping("/customers/{id}/profile")
	public Profile showProfile(@PathVariable Long id) {
		Customer customer = customerRepository.findOne(id);
		Collection<Resource<Store>> stores = storeService.fetchStoreNearbyFor(customer, "50km");

		return new Profile(customer, stores);
	}


	static class Profile {

		private final Customer customer;

		private final Collection<Resource<Store>> nearestStores;

		public Profile(Customer customer, Collection<Resource<Store>> nearestStores) {
			this.customer = customer;
			this.nearestStores = nearestStores;
		}

		@JsonUnwrapped
		public Customer getCustomer() {
			return customer;
		}

		public Collection<Resource<Store>> getNearestStores() {
			return nearestStores;
		}
	}

}
