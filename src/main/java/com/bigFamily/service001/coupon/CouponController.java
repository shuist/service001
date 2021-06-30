package com.bigFamily.service001.coupon;

import com.bigFamily.service001.couponDetail.ExcelOperater7;
import com.bigFamily.service001.couponDetail.ExcelOperater10;
import com.bigFamily.service001.couponDetail.ExcelOperater8;
import com.bigFamily.service001.couponDetail.ExcelOperater9;
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

    @Autowired
    private ExcelOperater7 excelOperater7;

    @Autowired
    private ExcelOperater8 excelOperater8;

    @Autowired
    private ExcelOperater9 excelOperater9;

    @Autowired
    private ExcelOperater10 excelOperater10;

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

    @GetMapping(value="/loadMembers")
    @ResponseBody
    public String loadMembers(){
        excelOperater7.loadMembers();
        return "success";
    }

    @GetMapping(value="/loadTuyaOrders")
    @ResponseBody
    public String loadTuyaOrders(){
        excelOperater8.loadTuyaOrders();
        return "success";
    }


    @GetMapping(value="/loadOrderCoupons")
    @ResponseBody
    public String loadOrderCoupons(){
        excelOperater9.loadOrderCoupons();
        return "success";
    }

    //初始化优惠券:1.已使用的优惠券2.未使用的优惠券
    @GetMapping(value="/initCouponDetail/{tuyaCouponStatus}")
    @ResponseBody
    public String initCouponDetail(@PathVariable(value = "tuyaCouponStatus", required = false) String tuyaCouponStatus){
        excelOperater10.initCouponDetail(tuyaCouponStatus);
        return "success";
    }
}
