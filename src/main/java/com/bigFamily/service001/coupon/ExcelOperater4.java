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
 *导入优惠券金额
 * 1.id
 * 2.coupon_id
 * 3.type：数据类型 1=优惠券⾯值，2=优惠券使⽤最低需满⾜的⾦额
 * 4.value：⾦额，单位分
 * 5.gmt_create
 * 6.gmt_modified
 */
@Component
public class ExcelOperater4 {

    @Autowired
    private  RedisServiceImpl redisService;


    public  void loadCouponAmount() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/优惠券金额信息.xls");

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
                String type = cell.getContents();

                cell = readsheet.getCell(3, i);
                String value = cell.getContents().trim();

                //type：数据类型 1=优惠券⾯值，2=优惠券使⽤最低需满⾜的⾦额
                String couponAmount = CouponConstant.COUPON_AMOUNT+itnodeCouponId+type;
                redisService.set(couponAmount,value);

            }


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            readwb.close();

        }

    }


}