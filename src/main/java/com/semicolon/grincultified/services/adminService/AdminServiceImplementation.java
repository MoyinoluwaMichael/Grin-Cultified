package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.data.models.*;
import com.semicolon.grincultified.data.repositories.AdminRepository;
import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AdminNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.services.adminInvitationService.AdminInvitationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;

import static com.semicolon.grincultified.data.models.Role.ORDINARY_ADMIN;
import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class AdminServiceImplementation implements AdminService {
    AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final AdminInvitationService adminInvitationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<AdminResponse> register(AdminRegistrationRequest adminRegistrationRequest) throws AdminInvitationNotFoundException, AuthenticationException {
        adminInvitationService.verifyInvitationForRegistration(adminRegistrationRequest.getEmailAddress());
        adminRegistrationRequest.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));
        User user = modelMapper.map(adminRegistrationRequest, User.class);
        Address address = modelMapper.map(adminRegistrationRequest, Address.class);
        user.setAddress(address);
        Admin admin = new Admin();
        admin.getUser().setRoles(new HashSet<>());
        admin.getUser().getRoles().add(ORDINARY_ADMIN);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);
        return new ResponseEntity(map(savedAdmin), HttpStatus.CREATED);
    }

    private AdminResponse map(Admin savedAdmin) {
        UserResponse userResponse = modelMapper.map(savedAdmin.getUser(), UserResponse.class);
        AdminResponse adminResponse = modelMapper.map(savedAdmin, AdminResponse.class);
        adminResponse.setUserResponse(userResponse);
        return adminResponse;
    }

    @Override
    public AdminResponse findByEmail(String emailAddress) throws AdminNotFoundException {
        Admin admin = adminRepository.findAdminByUser_EmailAddress(emailAddress).orElseThrow(()->new AdminNotFoundException(ADMIN_NOT_FOUND));
        return map(admin);
    }

    @Override
    public String validateDuplicateExistence(String emailAddress) throws AdminAlreadyExistException {
        Optional<Admin> admin = adminRepository.findAdminByUser_EmailAddress(emailAddress);
        if (admin.isPresent()) throw new AdminAlreadyExistException(ADMIN_ALREADY_EXISTS);
        return ADMIN_DOES_NOT_EXIST;
    }

    @Override
    public void deleteAll() {
        adminRepository.deleteAll();
    }
}
