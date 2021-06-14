package com.bigFamily.service001.coupon;

import com.bigFamily.service001.couponchannel.ExcelOperater6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CouponController {

    @Autowired
    private ExcelOperater1 excelOperater1;

    @Autowired
    private ExcelOperater2 excelOperater2;

    @Autowired
    private ExcelOperater2C excelOperater2c;

    @Autowired
    private ExcelOperater3 excelOperater3;

    @Autowired
    private ExcelOperater4 excelOperater4;

    @Autowired
    private ExcelOperater5 excelOperater5;

    @Autowired
    private ExcelOperater6 excelOperater6;

    @RequestMapping(value="/handleCouponTimespan", method= RequestMethod.GET)
    @ResponseBody
    public String handleCouponTimespan(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExcelOperater0.handleTimespan();
        return "success";
    }

    @RequestMapping(value="/initCouponData", method= RequestMethod.GET)
    @ResponseBody
    public String initCouponData(HttpServletRequest request, HttpServletResponse response) throws IOException {
        excelOperater1.initCouponData();
        return "success";
    }

    @GetMapping(value="/loadCouponCondition")
    @ResponseBody
    public String loadCouponCondition(){
        excelOperater2.loadCouponCondition();
        return "success";
    }

    @GetMapping(value="/loadProducts")
    @ResponseBody
    public String loadProducts(){
        excelOperater2c.loadProducts();
        return "success";
    }

    @GetMapping(value="/addCouponCondition")
    @ResponseBody
    public String addCouponCondition(){
        excelOperater3.addCouponCondition();
        return "success";
    }

    @GetMapping(value="/loadCouponAmount")
    @ResponseBody
    public String loadCouponAmount(){
        excelOperater4.loadCouponAmount();
        return "success";
    }

    @GetMapping(value="/addCouponAmount")
    @ResponseBody
    public String addCouponAmount(){
        excelOperater5.addCouponAmount();
        return "success";
    }

    @GetMapping(value="/buildCouponChannel")
    @ResponseBody
    public String buildCouponChannel(){
        excelOperater6.buildCouponChannel();
        return "success";
    }
}
