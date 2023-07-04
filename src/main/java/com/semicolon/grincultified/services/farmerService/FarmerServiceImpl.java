package com.semicolon.grincultified.services.farmerService;

import com.semicolon.grincultified.data.models.Address;
import com.semicolon.grincultified.data.models.Farmer;
import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.FarmerRepo;
import com.semicolon.grincultified.dtos.requests.FarmerRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.FarmerResponse;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class FarmerServiceImpl implements FarmerService{
    private final FarmerRepo farmerRepo;
    private final ModelMapper modelMapper;
    @Override
    public GenericResponse<FarmerResponse> registerFarmer(FarmerRegistrationRequest farmerRegistrationRequest) {
        Address address = modelMapper.map(farmerRegistrationRequest, Address.class);
        User user = modelMapper.map(farmerRegistrationRequest, User.class);
        user.setAddress(address);
        Farmer farmer = modelMapper.map(farmerRegistrationRequest, Farmer.class);
        farmer.setUser(user);
        farmerRepo.save(farmer);
        return GenericResponse.<FarmerResponse>builder()
                .status(CREATED)
                .message(FARMER_REGISTRATION_SUCCESSFUL)
                .build();
    }

    @Override
    public List<Farmer> getAllFarmers() {
        return farmerRepo.findAll();
    }
}
