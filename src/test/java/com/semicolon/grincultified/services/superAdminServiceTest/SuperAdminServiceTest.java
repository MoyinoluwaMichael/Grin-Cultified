package com.semicolon.grincultified.services.superAdminServiceTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.semicolon.grincultified.data.repositories.AdminInvitationRepository;
import com.semicolon.grincultified.dtos.responses.GenericResponse;
import com.semicolon.grincultified.services.superAdminService.SuperAdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
public class SuperAdminServiceTest {
    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private AdminInvitationRepository adminInvitationRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test void testThatSuperAdminCanSendInvitationLinkToAUser() throws Exception {
        adminInvitationRepository.deleteAll();
        GenericResponse<String> message = superAdminService.sendInvitationLink("michael.ogunmoroti@softenginegroup.net");
        assertThat(message).isNotNull();
    }
}
