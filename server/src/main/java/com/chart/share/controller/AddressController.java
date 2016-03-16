package com.chart.share.controller;

import com.chart.share.domain.Address;
import com.chart.share.repository.AddressRepository;
import com.chart.share.repository.PhysicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jenn on 3/16/16.
 */

@RestController
@RequestMapping("/service")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PhysicianRepository physicianRepository;

    @Autowired
    private SequenceGenerator sequenceGenerator;


    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    public Address getAddress(@PathVariable long id){
        Address returnAddress;
        returnAddress = addressRepository.findOne(id);
        return returnAddress;
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    public Address createAddress(@RequestBody Address address){
        long id = sequenceGenerator.invoke();
        address.setId(id);
        return addressRepository.save(address);
    }

    @RequestMapping(value = "address/{id}", method = RequestMethod.DELETE)
    public Boolean deleteAddress(@PathVariable long id){
        addressRepository.delete(id);
        return true;
    }
}
