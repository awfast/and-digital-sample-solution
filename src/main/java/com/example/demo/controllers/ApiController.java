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

    /**
     * API Description
     * retrieve a list of all phone numbers held in our sample 'database'
     * 'database' here is represented by a Set of Customer 'entities'
     */

    /**
     * API endpoint
     *
     * GET /api/all-phone-numbers
     */

    /**
     * API implementation
     */
    @RequestMapping(value = "/all-phone-numbers", method = RequestMethod.GET)
    public List<String> getAllPhoneNumbers() {
        return demoService.getAllPhoneNumbers();
    }

    /**
     * API Description
     * retrieve a list of all phone numbers held in our sample 'database' for a specific customer id
     * 'database' here is represented by a Set of Customer 'entities'
     *
     * Required path variable:
        * customerId
        * Type: String
     */

    /**
     * API endpoint
     *
     * GET /api/123/all-phone-numbers
     */

    /**
     * API implementation
     */
    @RequestMapping(value = "/{customerId}/all-phone-numbers", method = RequestMethod.GET)
    public List<String> getAllPhoneNumbersForCustomerId(@PathVariable String customerId) {
       return demoService.getAllPhoneNumbersForCustomerId(customerId);
    }


    /**
     * API Description
     * update the status of a customer's phone number
     * status can be active or inactive, represented by true or false respectively
     *
     * Required the following DTO as RequestBody parameter:
     * ChangePhoneStatusDTO - consists of
        *  String name - customer name
        *  String customerId;
        *  String phoneNumber;
        *  boolean status;
     */

    /**
     * API endpoint
     *
     * GET /api/update-phone-number-status
     */

    /**
     * API implementation
     */
    @RequestMapping(value = "/update-phone-number-status", method = RequestMethod.POST)
    public void updateCustomerPhoneNumberStatus(@RequestBody ChangePhoneStatusDTO dto) {
        demoService.changePhoneStatus(dto);
    }
}