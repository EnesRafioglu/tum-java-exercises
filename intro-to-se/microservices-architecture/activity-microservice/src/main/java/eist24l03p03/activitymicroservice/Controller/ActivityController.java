package eist24l03p03.activitymicroservice.Controller;

import eist24l03p03.activitymicroservice.FollowRequest;
import eist24l03p03.activitymicroservice.Tweet;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import eist24l03p03.activitymicroservice.User;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    // TODO: implement the methods of this class. Make a use of the provided data structures
    private Map<Integer, List<Tweet>> userActivityMap = new HashMap<>();
    private Map<Integer, List<User>> userFollowedMap = new HashMap<>();
   @GetMapping("/getActivity/{id}")

    public List<Tweet> getActivity(@PathVariable("id") int userID) {
       List<Tweet> result = userActivityMap.get(userID);
       return result == null? new ArrayList<>() : result;
    }




    @PostMapping("/addActivity")

    public void addActivity(@RequestBody Tweet tweet) {
        userActivityMap.computeIfAbsent(tweet.getUser().getUserID(), k -> new ArrayList<>()).add(tweet);
    }

    @PostMapping("/addFollower")

    public void addFollower(@RequestBody FollowRequest followRequest) {
          User follower = followRequest.getFollower();
          User followed = followRequest.getFollowed();
          userFollowedMap.computeIfAbsent(follower.getUserID(), x -> new ArrayList<>()).add(followed);
    }

    @DeleteMapping("/deleteActivity")

    public void deleteActivity(@RequestBody Tweet tweet) {
       List<Tweet> a = userActivityMap.get(tweet.getUser().getUserID());
       if(a == null || !a.contains(tweet)) return;
       a.removeIf(t -> t.getTweetID() == tweet.getTweetID());
    }

    @DeleteMapping("/deleteFollower")
    public void deleteFollower(@RequestBody FollowRequest followRequest) {
       userFollowedMap.get(followRequest.getFollower().getUserID()).removeIf(x -> x.getUserID() == followRequest.getFollowed().getUserID());
    }

    @GetMapping("/getFollowedList/{id}")

    public List<User> getFollowedList(@PathVariable("id") int userID) {
        return userFollowedMap.computeIfAbsent(userID, x -> new ArrayList<>());
    }
}