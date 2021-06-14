package com.bigFamily.service001.coupon;//in ExcelOperater

import com.bigFamily.service001.redis.RedisServiceImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;


/**
 *导入itnode商品数据
 * 1.id 商品id
 * 2.ty_commodity_code 涂鸦商品码
 */
@Component
public class ExcelOperater2C {

    @Autowired
    private  RedisServiceImpl redisService;


    public  void loadProducts() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/product_thin.xls");

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
                Cell cell = readsheet.getCell(0, i);
                String productId = cell.getContents();



                cell = readsheet.getCell(1, i);
                String tuyaProductCode = cell.getContents();
                String key = CouponConstant.TUYPA_PRODUCT_CODE+tuyaProductCode;

                redisService.set(key,productId);

            }


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            readwb.close();

        }

    }


}