package com.bigFamily.service001.couponDetail;//in ExcelOperater

import com.bigFamily.service001.coupon.CouponConstant;
import com.bigFamily.service001.redis.RedisServiceImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;


/**
 * 导入订单优惠券信息
 * 1.order_coupon_id 订单优惠券ID
 * 2.order_id 订单号
 * 3.code 邀请码
 */
@Component
public class ExcelOperater9 {

    @Autowired
    private  RedisServiceImpl redisService;


    public  void loadOrderCoupons() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/订单优惠券信息.xls");

            readwb = Workbook.getWorkbook(instream);


            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();


            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            for (int i = 0; i < rsRows; i++) {
                if(i==0){
                    continue;
                }
                Cell cell = readsheet.getCell(1, i);
                String orderId = cell.getContents().trim();

                cell = readsheet.getCell(2, i);
                String code = cell.getContents().trim();

                String orderIdkey = CouponConstant.TUYPA_ORDER_ID+orderId;
                String id = (String) redisService.get(orderIdkey);

                String key = CouponConstant.INVITATION_CODE+code;
//                System.out.println(orderId+":"+id);
                redisService.set(key,id);

            }


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            readwb.close();

        }

    }


}