# Challenge: Integration Tests mit Java Spring Boot für REST-basierte CRUD Anwendung

## Aufgabe

* Integration Tests für alle Endpoints bereitstellen
* Bereitstellung des Codes in eigenem GitHub-Repo

## Links

* [Übersicht zu Test-Varianten für Spring Controller](https://thepracticaldeveloper.com/2017/07/30/guide-spring-boot-controller-tests/)
* [Live-Coding (Mai)](https://www.youtube.com/watch?v=BZhdj5ZGGc8&feature=youtu.be)
* [Live-Coding zu MVC-Tests (Februar)](https://www.youtube.com/watch?v=TGcE7lEqhN4&list=PLVBvhDBS_eGWm3_N3sx95lGhBWkxuv_Qn&index=17&t=3513s)
* [Testing OAuth](https://www.baeldung.com/oauth-api-testing-with-spring-mvc)

### REST-Endpoints

| Method | Endpoint / example request | Purpose                     | Response code                      |
|--------|----------------------------|-----------------------------|------------------------------------|
| GET    | ```/items```               | Retrieve all item resources | 200 (OK)                           |
| GET    | ```/items/4711```          | Retrieve item resource 4711 | 200 (OK) / 404 (NOT FOUND)         |
| POST   | ```/items```               | Create a new item resource  | 201 (Created)                      |
| PUT    | ```/items/4711```          | Update item resource 4711   | 200 (OK) / 404 (NOT FOUND)         |
| DELETE | ```/items/4711```          | Delete item resource 4711   | 204 (No Content) / 404 (NOT FOUND) |

### Grundlegender Aufbau wie im Live-Coding demonstriert

```java
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemRestControllerTest {

	private static final String BASE_PATH = "/api/v1/items";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ItemRepository repo;

	@BeforeEach
	void setupRepo() {
		repo.deleteAll();
	}

	@Test
	void shouldBeAbleToUploadAnItem() {
                fail();
	}
	
	@Test
	void shouldReadAllItems() {
		fail();
	}

	@Test
	void shouldFindOneItem() {
		fail();
	}

	@Test
	void shouldFindNoItemForUnknownId() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldBeAbleToDeleteAnItem() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldNotBeAbleToDeleteAnItemWithUnknownId() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldBeAbleToReplaceAnItem() throws URISyntaxException {
		fail();
	}

	@Test
	void shouldNotBeAbleToReplaceAnItemWithUnknownId() throws URISyntaxException {
		fail();
	}

	private ResponseEntity<Item> givenAnInsertedItem() {
		Item item = buildLawnMower();
		return restTemplate.postForEntity(BASE_PATH, item, Item.class);
	}

	private Item buildLawnMower() {
		Item item = Item.builder().name("Lawn mower").amount(1).lastUsed(Date.valueOf("2019-05-01"))
				.location("Basement").build();
		return item;
	}

	private Item buildLawnTrimmer() {
		Item item = Item.builder().name("Lawn trimmer").amount(1).lastUsed(Date.valueOf("2018-05-01"))
				.location("Basement").build();
		return item;
	}

}
```


