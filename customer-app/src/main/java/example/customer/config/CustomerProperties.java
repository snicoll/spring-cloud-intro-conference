package example.customer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Customer app settings.
 *
 * @author Stephane Nicoll
 */
@ConfigurationProperties("app.customer")
public class CustomerProperties {

	private final Store store = new Store();

	public Store getStore() {
		return store;
	}

	public static class Store {

		/**
		 * URL of the store service.
		 */
		String url = "http://localhost:8081";

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			if (url.endsWith("/")) {
				this.url = url.substring(0, url.length() - 1);
			}
			else {
				this.url = url;
			}
		}

		public String getSearchUrlFor(String searchMethod) {
			return String.format("%s/stores/search/%s", url, searchMethod);
		}
	}

}
