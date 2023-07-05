package com.semicolon.grincultified.services.adminService;

import com.semicolon.grincultified.dtos.requests.AdminRegistrationRequest;
import com.semicolon.grincultified.dtos.responses.AdminResponse;


public interface AdminService {
    AdminResponse adminRegistration (AdminRegistrationRequest adminRegistrationRequest) ;
}
