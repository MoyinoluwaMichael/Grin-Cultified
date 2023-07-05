package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.data.models.Address;
import com.semicolon.grincultified.data.models.Admin;
import com.semicolon.grincultified.data.models.AdminType;
import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.AdminRepository;
import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import static com.semicolon.grincultified.utilities.AppUtils.REGISTERED_SUCCESSFULLY;

@Service
@AllArgsConstructor
public class AdminServiceImplementation implements AdminService {
    AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    @Override
    public AdminResponse adminRegistration(AdminRegistrationRequest adminRegistrationRequest) {
        User user = modelMapper.map(adminRegistrationRequest, User.class);
        Address address = modelMapper.map(adminRegistrationRequest, Address.class);
        user.setAddress(address);
        Admin admin = new Admin();
        admin.setAdminType(AdminType.ORDINARY);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);
        AdminResponse adminResponse = modelMapper.map(savedAdmin, AdminResponse.class);
        UserResponse userResponse = modelMapper.map(savedAdmin.getUser(), UserResponse.class);
        adminResponse.setUserResponse(userResponse);
        return adminResponse;
    }
}
