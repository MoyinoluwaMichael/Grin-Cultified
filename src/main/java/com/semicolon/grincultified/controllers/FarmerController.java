package com.semicolon.grincultified.controllers;

import com.semicolon.grincultified.data.models.Farmer;
import com.semicolon.grincultified.dtos.requests.FarmerRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.FarmerResponse;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.services.farmerService.FarmerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semicolon/cultify/v1/api/farmer")
@AllArgsConstructor
public class FarmerController {
    private final FarmerService farmerService;

    @PostMapping("/registerFarmer")
    public ResponseEntity<GenericResponse<FarmerResponse>> registerFarmer(@RequestBody FarmerRegistrationRequest farmerRegistrationRequest){
        return new ResponseEntity<>(farmerService.registerFarmer(farmerRegistrationRequest), HttpStatus.CREATED);
    }

    @GetMapping("/getAllFarmers")
    public ResponseEntity<List<Farmer>> getAllFarmers(){
        return new ResponseEntity<>(farmerService.getAllFarmers(), HttpStatus.OK);
    }
}
