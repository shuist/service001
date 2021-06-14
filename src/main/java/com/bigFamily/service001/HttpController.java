package com.bigFamily.service001;

import com.bigFamily.service001.coupon.CouponConstant;
import com.bigFamily.service001.redis.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
public class HttpController {

    @Autowired
    private RedisServiceImpl redisService;

    @RequestMapping(value="/test", method= RequestMethod.GET)
    @ResponseBody
    public String getMethod(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> result = new HashMap<String, Object>();

        Enumeration<String> headers = request.getHeaderNames();
        while(headers.hasMoreElements()) {
            String headerName = headers.nextElement().toString();
            result.put(headerName, request.getHeader(headerName));
        }

        return "hello jenkins";
    }

    @GetMapping(value="/setDataToRedis/{tuyaCouponId}")
    @ResponseBody
    public String setDataToRedis(@PathVariable(value = "tuyaCouponId", required = false) String tuyaCouponId) throws IOException {
        String key = CouponConstant.TUYA_COUPON_ID_KEY+tuyaCouponId;
        String value = "itnode"+tuyaCouponId;
        redisService.set(key,value);
        return "success";
    }

    @GetMapping(value="/getDataFromRedis/{tuyaCouponId}")
    @ResponseBody
    public String getDataFromRedis(@PathVariable(value = "tuyaCouponId", required = false) String tuyaCouponId) throws IOException {
        String key = CouponConstant.TUYA_COUPON_ID_KEY+tuyaCouponId;
        String reslut = (String) redisService.get(key);
        return reslut;
    }

    @GetMapping(value="/removeAllTuyaCoupon/{redisKey}")
    @ResponseBody
    public String removeAllTuyaCoupon(@PathVariable(value = "redisKey", required = false) String redisKey) throws IOException {
        Set<String> keys = redisService.keys(redisKey);
        redisService.deleteSet(keys);
        return "success";
    }
}

