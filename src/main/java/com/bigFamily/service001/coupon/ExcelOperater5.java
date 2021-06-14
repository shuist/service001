package com.bigFamily.service001.coupon;//in ExcelOperater

import com.bigFamily.service001.redis.RedisServiceImpl;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;


/**
 * add coupon amount
 */
@Component
public class ExcelOperater5 {

    @Autowired
    private RedisServiceImpl redisService;


    public void addCouponAmount() {
        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/3.fss优惠券(补充使用范围).xls");

            readwb = Workbook.getWorkbook(instream);


            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();


            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            File filewrite = new File("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/5.fss优惠券(补充满减金额).xls");
            filewrite.createNewFile();
            OutputStream os = new FileOutputStream(filewrite);
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);


            //读取第一张工作表
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            for (int i = 0; i < rsRows; i++) {
                for (int j = 0; j < rsColumns; j++) {
                    Cell cell = readsheet.getCell(j, i);
                    String val = cell.getContents();

                    //type：数据类型 1=优惠券⾯值，2=优惠券使⽤最低需满⾜的⾦额
                    if(i!=0 && j==11){//满额
                        cell = readsheet.getCell(0, i);
                        String itnodeCouponId = cell.getContents();;
                        String couponAmount = CouponConstant.COUPON_AMOUNT+itnodeCouponId+2;
                        val = (String) redisService.get(couponAmount);
                    }
                    if(i!=0 && j==12){//减额
                        cell = readsheet.getCell(0, i);
                        String itnodeCouponId = cell.getContents();;
                        String couponAmount = CouponConstant.COUPON_AMOUNT+itnodeCouponId+1;
                        val = (String) redisService.get(couponAmount);
                    }
                    Label label = new Label(j, i, val);
                    ws.addCell(label);
                }

            }

            //

            //写入Excel对象

            wwb.write();

            wwb.close();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            readwb.close();

        }

    }


}