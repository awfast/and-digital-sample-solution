package com.example.demo.controllers;

import com.example.demo.dto.ChangePhoneStatusDTO;
import com.example.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/all-phone-numbers", method = RequestMethod.GET)
    public List<String> getAllPhoneNumbers() {
        return demoService.getAllPhoneNumbers();
    }

    @RequestMapping(value = "/{customerId}/all-phone-numbers", method = RequestMethod.GET)
    public List<String> getAllPhoneNumbersForCustomerId(@PathVariable String customerId) {
       return demoService.getAllPhoneNumbersForCustomerId(customerId);
    }

    @RequestMapping(value = "/update-phone-number-status", method = RequestMethod.POST)
    public void updateCustomerPhoneNumberStatus(@RequestBody ChangePhoneStatusDTO dto) {
        demoService.changePhoneStatus(dto);
    }
}