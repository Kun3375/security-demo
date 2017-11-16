package com.kun.security.web.controller;

import com.kun.security.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/12 22:34
 */
@RestController
@RequestMapping("/file")
public class FileController {
    
    @PostMapping
    public FileInfo fileUpload(MultipartFile file) throws IOException {
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        
        File destFile = new File(UUID.randomUUID().toString() + ".txt");
        file.transferTo(destFile);
        
        return new FileInfo(destFile.getName(), destFile.getCanonicalPath());
    }
    
    @GetMapping("/{id}")
    public void fileDownload(@PathVariable("id") String id,
                             HttpServletResponse response) throws IOException {
        
        try (InputStream inputStream = new FileInputStream(new File("security-demo",id + ".txt"));
             OutputStream outputStream = response.getOutputStream()) {
            
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=test.txt");
            
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
        
    }
    
}
