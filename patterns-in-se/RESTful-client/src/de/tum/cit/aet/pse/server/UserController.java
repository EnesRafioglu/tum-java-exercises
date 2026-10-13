package de.tum.cit.aet.pse.server;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * this class provides dummy values for local testing
 */
@RestController
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
    @GetMapping("/users")
    public String getAllUsers() {
        return """
                {
                  "_embedded": {
                    "userList": [
                      {
                        "id": "1849566069",
                        "name": "Stephan Krusche",
                        "_links": {
                          "self": {
                            "href": "http://localhost:8080/users/1849566069"
                          },
                          "users": {
                            "href": "http://localhost:8080/users"
                          },
                          "rentals": {
                            "href": "http://localhost:8080/users/1849566069/rentals"
                          }
                        }
                      },
                      {
                        "id": "1957353818",
                        "name": "Jan Philip Bernius",
                        "_links": {
                          "self": {
                            "href": "http://localhost:8080/users/1957353818"
                          },
                          "users": {
                            "href": "http://localhost:8080/users"
                          }
                        }
                      }
                    ]
                  },
                  "_links": {
                    "self": {
                      "href": "http://localhost:8080/users"
                    }
                  }
                }""";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable String id) {
        return String.format("{%n"
                + "  \"id\": \"%s\",%n"
                + "  \"name\": \"Stephan Krusche\",%n"
                + "  \"_links\": {%n"
                + "     \"self\": {%n"
                + "      \"href\": \"http://localhost:8080/users/%s\"%n"
                + "    },%n"
                + "    \"users\": {%n"
                + "      \"href\": \"http://localhost:8080/users\"%n"
                + "    },%n"
                + "    \"rentals\": {%n"
                + "      \"href\": \"http://localhost:8080/users/%s/rentals\"%n"
                + "    }%n"
                + "  }%n"
                + "}", id, id, id);
    }
}
