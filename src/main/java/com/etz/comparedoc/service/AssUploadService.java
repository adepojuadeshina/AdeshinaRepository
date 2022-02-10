package com.etz.comparedoc.service;


import com.etz.comparedoc.domain.request.CompareRequest;
import com.etz.comparedoc.domain.response.BaseResponse;
import com.etz.comparedoc.domain.response.ResponseConstants;
import com.etz.comparedoc.domain.response.CompareResponse;
import com.etz.comparedoc.model.Doc;
import com.etz.comparedoc.repository.AssRepository;
import com.etz.comparedoc.repository.ComparedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;


@Service
public class AssUploadService {
    @Autowired
    private AssRepository assRepository;
    @Autowired
    private ComparedRepository comparedRepository;

    public BaseResponse saveFile(MultipartFile file) {
        BaseResponse response = new BaseResponse();
        Long startTime = System.currentTimeMillis();
        String docName = file.getOriginalFilename();


        try {
            Doc doc = new Doc(docName, file.getBytes());
            assRepository.save(doc);
            response.setResponseCode(ResponseConstants.SUCCESS_CREATION_CODE);
            response.setResponseMessage(ResponseConstants.SUCCESS_CREATION_MESSAGE);
        } catch (IOException e) {
            response.setResponseCode(ResponseConstants.ERROR_SAVING_CODE);
            response.setResponseMessage(ResponseConstants.ERROR_SAVING_MESSAGE);
            e.printStackTrace();
        }
        response.setTimeStamp(System.currentTimeMillis() - startTime);
        return response;
    }

    public Optional<Doc> getFile(Integer studentId) {
        return assRepository.findById(studentId);
    }



    public CompareResponse compareFile(CompareRequest compareRequest) throws IOException {
        boolean similar = false;
        String similarWords;
        StringBuilder sb = new StringBuilder();

        CompareResponse compareResponse = new CompareResponse();
        Optional<Doc> doc1 = getFile(compareRequest.getFirstStudentId());
        Optional<Doc> doc2 = getFile(compareRequest.getSecondStudentId());
        compareResponse.setFirstStudentName(doc1.get().getStudentName());
        compareResponse.setSecondStudentName(doc2.get().getStudentName());

        InputStream inputStream = new ByteArrayInputStream(doc1.get().getData());
        InputStream inputStream2 = new ByteArrayInputStream(doc2.get().getData());
        StringBuilder similarities = new StringBuilder();
        StringBuilder ssb = new StringBuilder();
        StringBuilder ssb2 = new StringBuilder();
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(StandardCharsets.UTF_8.name())));
            Reader reader2 = new BufferedReader(new InputStreamReader(inputStream2, Charset.forName(StandardCharsets.UTF_8.name())));
            int c = 0;
            int p = 0;
            while ((c = reader.read()) != -1 && (p = reader2.read()) != -1) {

                ssb2.append((char) p);
                ssb.append((char) c);
            }
        } catch (Exception ex) {

        }

        int index = 0;
        int similarity = 0;
        double percentage;

        for (char word : ssb.toString().toCharArray()) {

            if (word == ssb2.toString().charAt(index)) {
                similarities.append(word);
                similarity++;
            }
            index++;
        }
        double numChar = ssb.toString().length();
        double divisor = similarity / numChar;

        percentage = divisor * 100;
        compareResponse.setSimilarLineText(similarities.toString());
        compareResponse.setPercentageSimilarities(String.valueOf(percentage));
        comparedRepository.save(compareResponse);
//        System.out.println("divisor================ " + divisor);
//        System.out.println("numChar================ " + numChar);
//        System.out.println("similarity================ " + similarity);
//        System.out.println("word================ " + similarities.toString());
//        System.out.println("percentage================ " + percentage);
        return compareResponse;

    }
}
