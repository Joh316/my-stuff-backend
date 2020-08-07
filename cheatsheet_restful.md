# RESTful Cheatsheet

DISCLAIMER: This document is mostly *not* about pure REST (or "RESTful") APIs in the original sense intended by Roy Fielding. To my knowledge, most APIs found in the wild do not really qualify to be called RESTful, as they only use a subset of the ideas described by Roy Fielding.

## Commonly found RESTful concepts

The following concepts are usually found in real-world web APIs that are described as RESTful:

* Resource-orientation: A single base URI per resource type, e.g. ```/notes```
* Usage of HTTP verbs for CRUD operations (see table below)
* Usage of HTTP response codes
* JSON is often used as the premiere resource representation

| Method | Endpoint / example request |           Purpose           |   Response code  |
|:------:|:--------------------------:|:---------------------------:|:----------------:|
| GET    | ```/notes```               | Retrieve all note resources |     200 (OK)     |
| GET    | ```/notes/4711```          | Retrieve note resource 4711 |     200 (OK)     |
| POST   | ```/notes```               | Create a new note resource  |   201 (Created)  |
| PUT    | ```/notes/4711```          | Update note resource 4711   |     200 (OK)     |
| DELETE | ```/notes/4711```          | Delete note resource 4711   | 204 (No Content) |

http://petstore.swagger.io/#/pet/findPetsByStatus

## REST as intended by Roy Fielding

### Six guiding principles

1. Client-server architecture
1. Statelessness
1. Cacheability
1. Layered system
1. Code on demand (optional)
1. Uniform interface

### Richardson Maturity model

https://www.crummy.com/writing/speaking/2008-QCon/act3.html
https://martinfowler.com/articles/richardsonMaturityModel.html

#### Levels

* Level 0 - The swamp of POX (Plain Old XML)
* Level 1 - Resources
* Level 2 - HTTP Verbs
* Level 3 - Hypermedia Controls (HATEOAS)

#### Discussion

* https://roy.gbiv.com/untangled/2008/rest-apis-must-be-hypertext-driven
* https://intercoolerjs.org/2016/05/08/hatoeas-is-for-humans.html
* https://medium.com/@andreasreiser94/why-hateoas-is-useless-and-what-that-means-for-rest-a65194471bc8

## Spring

https://www.springboottutorial.com/spring-boot-crud-rest-service-with-jpa-hibernate

### Basic CRUD controller

```java
@RestController
@RequestMapping("/api/v1/")
public class NotesRestController {

	private final NotesRepository repository;

        @Autowired
	public NotesRestController(NotesRepository notesRepository) {
		this.repository = notesRepository;
	}

	@GetMapping("notes")
	public List<Notes> getAll() {
		return repository.findAll();
	}

	@GetMapping("notes/{id}")
	public Notes get(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PostMapping("notes")
	public Notes create(@RequestBody Notes newNote) {
		return repository.save(newNote);
	}

	@PutMapping("notes/{id}")
	public Notes replace(@RequestBody Notes newNote, @PathVariable Long id) {
		return repository.findById(id).map(note -> {
			note.setNotizenname(newNote.getNotizenname());
			note.setNotizentext(newNote.getNotizentext());
			return repository.save(note);
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@DeleteMapping("notes/{id}")
	public void delete(@PathVariable Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

}
```

### Swagger

* https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
* http://localhost:8080/v2/api-docs
* http://localhost:8080/swagger-ui.html


### Infinite recursion
http://www.appsdeveloperblog.com/infinite-recursion-in-objects-with-bidirectional-relationships/
