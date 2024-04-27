package com.backend.controller.admin;

import com.backend.entity.Company;
import com.backend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AvatarController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company/avatar/{companyId}")
    @ResponseBody
    public ResponseEntity<byte[]> getAvatar(@PathVariable("companyId") int companyId) {
        Company company = companyService.getCompanyById(companyId);
        byte[] avatarData = company.getAvatar();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(avatarData);
    }
}
