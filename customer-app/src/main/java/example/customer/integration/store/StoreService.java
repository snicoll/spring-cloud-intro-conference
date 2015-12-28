package example.customer.integration.store;

import java.util.Collection;
import java.util.Collections;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import example.customer.domain.Customer;
import example.customer.domain.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Proxy for the store app.
 *
 * @author Stephane Nicoll
 */
@Service
public class StoreService {

	private final RestOperations restOperations;

	@Autowired
	public StoreService(RestOperations restOperations) {
		this.restOperations = restOperations;
	}

	@HystrixCommand(fallbackMethod = "fallbackFetchStoreNearbyFor")
	public Collection<Resource<Store>> fetchStoreNearbyFor(Customer customer, String distance) {
		Location location = customer.getAddress().getLocation();
		String locationParam = String.format("%s,%s",
				location.getLatitude(), location.getLongitude());


		UriComponentsBuilder builder = UriComponentsBuilder
				.fromHttpUrl("http://store-app/stores/search/findByAddressLocationNear")
				.queryParam("size", 5)
				.queryParam("location", locationParam)
				.queryParam("distance", distance);
		RequestEntity<Void> request = RequestEntity
				.get(builder.build().encode().toUri())
				.accept(MediaTypes.HAL_JSON)
				.build();

		ResponseEntity<PagedResources<Resource<Store>>> result = restOperations.exchange(request,
				new ParameterizedTypeReference<PagedResources<Resource<Store>>>() {
				});

		return result.getBody().getContent();
	}

	public Collection<Store> fallbackFetchStoreNearbyFor(Customer customer, String distance) {
		return Collections.emptyList();
	}

}
