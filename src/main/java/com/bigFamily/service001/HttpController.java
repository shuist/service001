package com.bigFamily.service001;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HttpController {

    @RequestMapping(value="/test", method= RequestMethod.GET)
    @ResponseBody
    public String getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();

        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String headerName = headers.nextElement().toString();
            result.put(headerName, request.getHeader(headerName));
        }

        return "hello";
    }
}

