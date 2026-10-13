package eist24l03p03.pagemicroservice.Controller;

import eist24l03p03.pagemicroservice.User;
import eist24l03p03.pagemicroservice.Tweet;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/page")
public class PageController {
    private static final String getActivityURL = "http://localhost:8084/activity/getActivity/";
    private static final String getFollowedListURL = "http://localhost:8084/activity/getFollowedList/";
    HttpHeaders headers = new HttpHeaders();
    // TODO: implement the methods of this class. Make a use of the provided data structures
    private RestTemplate restTemplate = new RestTemplate();

    // page of one user
    @GetMapping("/getTimeLine/{id}")
    public List<Tweet> getTimeLine(@PathVariable("id") int userID) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        return restTemplate.getForObject(getActivityURL + userID , List.class);

    }


    @GetMapping("/getHomePage/{id}")
    public List<Tweet> getHomePage(@PathVariable("id") int userID) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        List<User> users = restTemplate.getForObject(getFollowedListURL + userID , List.class);
        List<Tweet> result = new ArrayList<>();
        for(User user : users){
            result.addAll(getTimeLine(user.getUserID()));
        }
        return result;
    }
}