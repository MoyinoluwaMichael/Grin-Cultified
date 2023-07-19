package com.semicolon.grincultified.services.farmerService;

import com.semicolon.grincultified.data.models.Farmer;
import com.semicolon.grincultified.dtos.requests.FarmerRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.FarmerResponse;
import com.semicolon.grincultified.dtos.responses.GenericResponse;

import java.util.List;

public interface FarmerService {
    GenericResponse<FarmerResponse> registerFarmer(FarmerRegistrationRequest farmerRegistrationRequest);
    List<Farmer> getAllFarmers();

    void deleteAll();

    Long count();
}
