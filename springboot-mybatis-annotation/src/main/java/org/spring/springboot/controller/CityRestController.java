package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xchunzhao on 02/05/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/api/city", method = RequestMethod.GET)
    @CrossOrigin
    public City findOneCity(@RequestParam(value = "cityName", required = true) String cityName) {
        //return cityService.findCityByName(cityName);
        //RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("http://10.161.12.123:11046/apps?appId=/sae-newui/acctmanmpls/amfeebackpls", String.class);
        //log.info("{}",forObject);
        System.out.println(forObject);
        return null;
    }

}
