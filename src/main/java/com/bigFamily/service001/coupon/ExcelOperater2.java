package com.bigFamily.service001.coupon;//in ExcelOperater

import com.bigFamily.service001.redis.RedisServiceImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.UUID;


/**
 *导入优惠券适⽤范围信息
 * 1.id
 * 2.coupon_id
 * 3.catalog：数据类型 0=不限制范围，3=新零售商品，4=新零售类⽬
 * 4.code：商品code|类⽬code
 * 5.gmt_create
 * 6.gmt_modified
 */
@Component
public class ExcelOperater2 {

    @Autowired
    private  RedisServiceImpl redisService;


    public  void loadCouponCondition() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/优惠券适用范围信息.xls");

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
                Cell cell = readsheet.getCell(1, i);
                String tuyaCouponId = cell.getContents();
                String key = CouponConstant.TUYA_COUPON_ID_KEY+tuyaCouponId;
                String itnodeCouponId = (String) redisService.get(key);

                cell = readsheet.getCell(2, i);
                String catalog = cell.getContents();

                cell = readsheet.getCell(3, i);
                String code = cell.getContents().trim();
                String conditionVal = catalog+"&"+code;
                String conditionKey = CouponConstant.COUPON_CONDITION+itnodeCouponId;
                redisService.set(conditionKey,conditionVal);

            }


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            readwb.close();

        }

    }


}