package com.backend.controller.admin;

import com.backend.entity.Company;
import com.backend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ImageController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid company Id:" + id));
        byte[] image = company.getAvatar();

        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = getImageMediaType(image);
        headers.setContentType(mediaType);

        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }

    private MediaType getImageMediaType(byte[] image) {
        if (startsWith(image, new byte[]{(byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47})) {
            return MediaType.IMAGE_PNG;
        } else if (startsWithAny(image, Arrays.asList(
                new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE0},
                new byte[]{(byte) 0xFF, (byte) 0xD8, (byte) 0xFF, (byte) 0xE1}))) {
            return MediaType.IMAGE_JPEG;
//        } else if (startsWithAny(image, Arrays.asList(
//                new byte[]{(byte) 0x3C, (byte) 0x3F, (byte) 0x78, (byte) 0x6D, (byte) 0x6C},
//                new byte[]{(byte) 0x3C, (byte) 0x3F, (byte) 0x78, (byte) 0x6D, (byte) 0x6C, (byte) 0x20}))) {
//            return MediaType.IMAGE_SVG_XML;
        } else {
            return MediaType.IMAGE_JPEG; // Default to JPEG if format is unknown
        }
    }

    private boolean startsWith(byte[] source, byte[] prefix) {
        if (source.length < prefix.length) {
            return false;
        }
        for (int i = 0; i < prefix.length; i++) {
            if (source[i] != prefix[i]) {
                return false;
            }
        }
        return true;
    }

    private boolean startsWithAny(byte[] source, List<byte[]> prefixes) {
        for (byte[] prefix : prefixes) {
            if (startsWith(source, prefix)) {
                return true;
            }
        }
        return false;
    }
}
