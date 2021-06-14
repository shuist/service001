package com.bigFamily.service001.couponDetail;//in ExcelOperater

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.SimpleDateFormat;


/**
 * 生成对应的渠道优惠券关联
 */
@Component
public class ExcelOperater7 {

    public void initCouponDetail() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/用户已领取优惠券数据.xls");

            readwb = Workbook.getWorkbook(instream);


            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();


            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            File filewrite = new File("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/7.用户领取优惠券数据.xls");
            filewrite.createNewFile();
            OutputStream os = new FileOutputStream(filewrite);
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);


            //读取第一张工作表
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            for (int i = 0; i < rsRows; i++) {

                if(i==0){
                    Label label = new Label(0, i, "id");
                    ws.addCell(label);
                    label = new Label(1, i, "coupon_id");
                    ws.addCell(label);
                    label = new Label(2, i, "user_id");
                    ws.addCell(label);
                    label = new Label(3, i, "unique_id");
                    ws.addCell(label);
                    label = new Label(4, i, "apply_time");
                    ws.addCell(label);
                    label = new Label(5, i, "use_time");
                    ws.addCell(label);
                    label = new Label(6, i, "status");
                    ws.addCell(label);
                    label = new Label(7, i, "order_id");
                    ws.addCell(label);
                    label = new Label(8, i, "deleted");
                    ws.addCell(label);
                    label = new Label(9, i, "create_by");
                    ws.addCell(label);
                    label = new Label(10, i, "update_by");
                    ws.addCell(label);
                    label = new Label(11, i, "create_time");
                    ws.addCell(label);
                    label = new Label(12, i, "update_time");
                    ws.addCell(label);
                    label = new Label(13, i, "code");
                    ws.addCell(label);
                    label = new Label(14, i, "channel_id");
                    ws.addCell(label);
                    label = new Label(15, i, "apply_channel_id");
                    ws.addCell(label);
                    label = new Label(16, i, "expire_time");
                    ws.addCell(label);
                    continue;
                }

                for (int j = 0; j < 10; j++) {
                    int temp = j+1;
                    String val = "";
                    if (temp == 1) {//生成id

                    }
                    if (temp == 2) {//coupon_id
                        Cell cell = readsheet.getCell(0, i);
                        val = cell.getContents();
                    }
                    if (temp == 3) {//channel_id
                        val = "6";
                    }
                    if (temp == 4) {//channel_name
                        val = "飞利浦之家";
                    }
                    if (temp == 5) {//is_publish
                        val = "1";
                    }
                    if (temp == 6) {//created_by
                        val = "涂鸦导入";
                    }
                    if (temp == 7) {//updated_by
                        val = "";
                    }
                    if (temp == 8) {//create_time

                    }
                    if (temp == 9) {//update_time

                    }
                    if (temp == 10) {//qr_code
                        val = "https://philips-shop-uat.oss-cn-shanghai.aliyuncs.com/coupon/images/772f36daeb734260a0b9d03e162cd9c1.png?Expires=1907292982&OSSAccessKeyId=LTAI4FwEi4gRwEkW4qkMYW3C&Signature=TCYUARuhsI4Laiufl2%2F6NRNuszY%3D";
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


    private static String getDate(long time1) {
        String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time1);
//        System.out.println("10位数的时间戳（秒）-----》Date:"+result1);
//        Date date1 = new Date(time1*1000);
//        System.out.println(date1);
//        //(13位数)，以毫秒为单位进行转化
//        double time2 = 1515730332000d;
//        String result2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time2);
//        System.out.println("13位数的时间戳（毫秒）--->Date:"+result2);
//        System.out.println("---------------------------------------------");
        return result1;
    }

}