package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.data.models.Address;
import com.semicolon.grincultified.data.models.Admin;
import com.semicolon.grincultified.data.models.User;
import com.semicolon.grincultified.data.repositories.AdminRepository;
import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;
import com.semicolon.grincultified.dtos.responses.UserResponse;
import com.semicolon.grincultified.exception.AdminAlreadyExistException;
import com.semicolon.grincultified.exception.AdminInvitationNotFoundException;
import com.semicolon.grincultified.exception.AuthenticationException;
import com.semicolon.grincultified.services.adminInvitationService.AdminInvitationService;
import com.semicolon.grincultified.utilities.JwtUtility;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.semicolon.grincultified.data.models.Role.ORDINARY_ADMIN;
import static com.semicolon.grincultified.data.models.Role.SUPER_ADMIN;
import static com.semicolon.grincultified.utilities.AppUtils.*;

@Service
@AllArgsConstructor
public class AdminServiceImplementation implements AdminService {
    AdminRepository adminRepository;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtility jwtUtil;
    private final AdminInvitationService adminInvitationService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<Map<String, Object>> register(AdminRegistrationRequest adminRegistrationRequest) throws AdminInvitationNotFoundException, AuthenticationException {
        String email = "";
        if (adminRegistrationRequest.getPhoneNumber() != null || adminRegistrationRequest.getPhoneNumber() != ""){
            email = adminInvitationService.verifyInvitationForRegistration(adminRegistrationRequest.getEmailAddress());
        }else email = adminRegistrationRequest.getEmailAddress();
        String password = adminRegistrationRequest.getPassword();
        adminRegistrationRequest.setEmailAddress(email);
        adminRegistrationRequest.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));
        User user = modelMapper.map(adminRegistrationRequest, User.class);
        Address address = modelMapper.map(adminRegistrationRequest, Address.class);
        user.setAddress(address);
        Admin admin = new Admin();
        admin.setUser(user);
        admin.getUser().setRoles(new HashSet<>());
        if (adminRegistrationRequest.getPhoneNumber() == null || adminRegistrationRequest.getPhoneNumber() == ""){
            admin.getUser().getRoles().add(ORDINARY_ADMIN);
        }else admin.getUser().getRoles().add(SUPER_ADMIN);
        admin.setUser(user);
        Admin savedAdmin = adminRepository.save(admin);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authResult = authenticationManager.authenticate(authentication);
        String accessToken = jwtUtil.generateAccessToken(authResult.getAuthorities());
        Map<String, Object> responseData = new HashMap<>();
        responseData.put(ACCESS_TOKEN_VALUE, accessToken);
        responseData.put(USER, map(savedAdmin));
        return ResponseEntity.ok().body(responseData);
    }

    private AdminResponse map(Admin savedAdmin) {
        UserResponse userResponse = modelMapper.map(savedAdmin.getUser(), UserResponse.class);
        AdminResponse adminResponse = modelMapper.map(savedAdmin, AdminResponse.class);
        adminResponse.setUserResponse(userResponse);
        return adminResponse;
    }

    @Override
    public AdminResponse findByEmail(String emailAddress) {
        Optional<Admin> admin = adminRepository.findAdminByUser_EmailAddress(emailAddress);
        return admin.map(this::map).orElse(null);
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

    @Override
    public Long count() {
        return adminRepository.count();
    }

    @Override
    public ResponseEntity<List<AdminResponse>> findAll() {
        List<AdminResponse> adminResponses = adminRepository.findAll().stream().map(this::map).toList();
        return ResponseEntity.ok().body(adminResponses);
    }
}
