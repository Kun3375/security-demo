package com.kun.security.wiremock;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * @author CaoZiye
 * @version 1.0 2017/11/13 23:12
 */
public class MockServer {
    
    public static void main(String[] args) throws IOException {
        configureFor(8888);
        removeAllMappings();
    
        ClassPathResource resource = new ClassPathResource("wiremock/response/order.1.json");
        String content = FileUtils.readFileToString(resource.getFile(),"UTF-8");
//        String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8"),"\n");
        
        stubFor(get(urlPathEqualTo("/order/1")).willReturn(aResponse().withBody(content).withStatus(200)));
    }
    
}
