package com.etz.comparedoc.controller;

import com.etz.comparedoc.domain.request.CompareRequest;
import com.etz.comparedoc.domain.response.BaseResponse;
import com.etz.comparedoc.domain.response.CompareResponse;
import com.etz.comparedoc.repository.ComparedRepository;
import com.etz.comparedoc.service.AssUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.print.Pageable;
import java.io.IOException;
import java.util.List;


@RequestMapping("/api/comparedoc")
@RestController
public class AssController {
    @Autowired
    private AssUploadService assUploadService;

    @Autowired
    private ComparedRepository comparedRepository;

    @PostMapping("/upload")
    public BaseResponse uploadFile(@RequestParam("file") MultipartFile file) {
        BaseResponse baseResponse = assUploadService.saveFile(file);

        return baseResponse;
    }

    @PostMapping("/compare")
    public CompareResponse updateBankCred(@RequestBody CompareRequest compareRequest) throws IOException {
        CompareResponse compareResponse = assUploadService.compareFile(compareRequest);
        return compareResponse;
    }

    @GetMapping("/history")
    public List<CompareResponse> findAllHistory() {
        return comparedRepository.findAll();
    }
}
