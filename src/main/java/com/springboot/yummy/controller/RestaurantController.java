package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.entity.RestaurantDetail;
import com.springboot.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @Autowired  //自动装配
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService=restaurantService;
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean apply(@RequestBody Map<String, Object> requestMap){
        int rid= (Integer)requestMap.get("rid");
        String name=requestMap.get("name").toString();
        String kind=requestMap.get("kind").toString();
        String password=requestMap.get("password").toString();
        String location=requestMap.get("location").toString();
        String region=requestMap.get("region").toString();
        int owner= Integer.parseInt((String)requestMap.get("owner"));
        String certificate=requestMap.get("certificate").toString();
        String photo=requestMap.get("photo").toString();
        return restaurantService.addRestaurant(rid, name, password, location, region, owner, photo, certificate, kind);
    }

    @RequestMapping(value = "/getState", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int getState(@RequestBody Map<String, Object> requestMap){
        int owner= Integer.parseInt((String)requestMap.get("owner"));
        return restaurantService.getApplyState(owner);
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String,Object> getInfo(@RequestBody Map<String, Object> requestMap){
        int owner= Integer.parseInt((String)requestMap.get("owner"));
        return restaurantService.getInfo(owner);
    }

    @RequestMapping(value = "/getApplications", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Restaurant[] getApplications(){
        return restaurantService.getApplications();
    }

    @RequestMapping(value = "/getRestaurantDetail", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Restaurant getRestaurantDetail(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return restaurantService.getApplicationDetail(rid);
    }

    @RequestMapping(value = "/pass", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean pass(@RequestBody Map<String, Object> requestMap){
        int applicationId= Integer.parseInt((String)requestMap.get("applicationId"));
        return restaurantService.pass(applicationId);
    }

    @RequestMapping(value = "/veto", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean veto(@RequestBody Map<String, Object> requestMap){
        int applicationId= Integer.parseInt((String)requestMap.get("applicationId"));
        return restaurantService.veto(applicationId);
    }

    @RequestMapping(value = "/getIdCode", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public String getIdCode(@RequestBody Map<String, Object> requestMap){
        int owner= Integer.parseInt((String)requestMap.get("owner"));
        return restaurantService.getIdCode(owner);
    }

    @RequestMapping(value = "/getRestaurantList", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Restaurant[] getRestaurantList(){
        return restaurantService.getRestaurantList();
    }

    @RequestMapping(value = "/getRestaurantDetailByUser", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public RestaurantDetail getRestaurantDetailByUser(@RequestBody Map<String, Object> requestMap){
        int rid= (Integer)requestMap.get("rid");
        return restaurantService.getRestaurantDetailByUser(rid);
    }

    @RequestMapping(value = "/getName", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public String getName(@RequestBody Map<String, Object> requestMap){
        int rid= (Integer)requestMap.get("rid");
        return restaurantService.getName(rid);
    }
}
