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
 * 1.id 系统生成
 * 2.code 系统生成
 * 3.qr_code 固定二维码链接
 * 4.type 0
 * 5.scope ""
 * 6.product_id ""
 * 7.category_id ""
 * 8.name 同步name
 * 9.start_time 同步effective_begin_time
 * 10.end_time 同步effective_end_time
 * 11.limit_times 同步count_per_user
 * 12.total_account ""
 * 13.derate_account ""
 * 14.rebate ""
 * 15.number 同步remaining_count
 * 16.status 1
 * 17.activity_id  ""
 * 18.use_activity_id ""
 * 19.version 0
 * 20.deleted 0
 * 21.create_by ""
 * 22.update_by ""
 * 23.create_time 同步gmt_create
 * 24.update_time 同步gmt_modified
 * 25.sort 0
 * 26.show_area 0
 * 27.enabled 0
 * 28.subtract_score ""
 * 29.level ""
 * 30.valid_date ""
 * 31.valid_type ""
 */
@Component
public class ExcelOperater1 {

    @Autowired
    private  RedisServiceImpl redisService;


    public  void initCouponData() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/0.优惠券主信息(加工时间).xls");

            readwb = Workbook.getWorkbook(instream);


            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();


            //利用已经创建的Excel工作薄 ,创建新的可写入的Excel工作薄
            File filewrite = new File("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/1.fss优惠券初始化.xls");
            filewrite.createNewFile();
            OutputStream os = new FileOutputStream(filewrite);
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);


            //读取第一张工作表
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            for (int i = 0; i < rsRows; i++) {

                if(i==0){
                    Label label = new Label(0, i, "id");
                    ws.addCell(label);
                    label = new Label(1, i, "code");
                    ws.addCell(label);
                    label = new Label(2, i, "qr_code");
                    ws.addCell(label);
                    label = new Label(3, i, "type");
                    ws.addCell(label);
                    label = new Label(4, i, "scope");
                    ws.addCell(label);
                    label = new Label(5, i, "product_id");
                    ws.addCell(label);
                    label = new Label(6, i, "category_id");
                    ws.addCell(label);
                    label = new Label(7, i, "name");
                    ws.addCell(label);
                    label = new Label(8, i, "start_time");
                    ws.addCell(label);
                    label = new Label(9, i, "end_time");
                    ws.addCell(label);
                    label = new Label(10, i, "limit_times");
                    ws.addCell(label);
                    label = new Label(11, i, "total_account");
                    ws.addCell(label);
                    label = new Label(12, i, "derate_account");
                    ws.addCell(label);
                    label = new Label(13, i, "rebate");
                    ws.addCell(label);
                    label = new Label(14, i, "number");
                    ws.addCell(label);
                    label = new Label(15, i, "status");
                    ws.addCell(label);
                    label = new Label(16, i, "activity_id");
                    ws.addCell(label);
                    label = new Label(17, i, "use_activity_id");
                    ws.addCell(label);
                    label = new Label(18, i, "version");
                    ws.addCell(label);
                    label = new Label(19, i, "deleted");
                    ws.addCell(label);
                    label = new Label(20, i, "create_by");
                    ws.addCell(label);
                    label = new Label(21, i, "update_by");
                    ws.addCell(label);
                    label = new Label(22, i, "create_time");
                    ws.addCell(label);
                    label = new Label(23, i, "update_time");
                    ws.addCell(label);
                    label = new Label(24, i, "sort");
                    ws.addCell(label);
                    label = new Label(25, i, "show_area");
                    ws.addCell(label);
                    label = new Label(26, i, "enabled");
                    ws.addCell(label);
                    label = new Label(27, i, "subtract_score");
                    ws.addCell(label);
                    label = new Label(28, i, "level");
                    ws.addCell(label);
                    label = new Label(29, i, "valid_date");
                    ws.addCell(label);
                    label = new Label(30, i, "valid_type");
                    ws.addCell(label);
                    label = new Label(31, i, "tuya_coupon_code");
                    ws.addCell(label);
                    label = new Label(32, i, "tuya_coupon_id");
                    ws.addCell(label);
                    continue;
                }


                for (int j = 0; j < 33; j++) {


                    int temp = j+1;
                    String val = "";
                    if (temp == 1) {//生成id
                        val = UUID.randomUUID().toString().replaceAll("-", "");

                        Cell cell = readsheet.getCell(0, i);
                        String tuyaCouponId = cell.getContents();
                        String key = CouponConstant.TUYA_COUPON_ID_KEY+tuyaCouponId;
                        redisService.set(key,val);

//                        cell = readsheet.getCell(1, i);
//                        String tuyaCouponCode = cell.getContents().trim();
//                        String tuyaCouponCodekey = CouponConstant.TUYA_COUPON_CODE+tuyaCouponCode;
//                        redisService.set(tuyaCouponCodekey,val);
                    }
                    if (temp == 2) {//生成code
                        val = RandomUtils.nextInt(10, 100) + StringUtil.generateShortUUID();

                        Cell cell = readsheet.getCell(0, i);
                        String tuyaCouponId = cell.getContents();
                        String key = CouponConstant.TUYA_COUPON_ID_KEY2+tuyaCouponId;
                        redisService.set(key,val);
                    }
                    if (temp == 3) {//固定二维码
                        val = "https://philips-shop-uat.oss-cn-shanghai.aliyuncs.com/coupon/images/772f36daeb734260a0b9d03e162cd9c1.png?Expires=1907292982&OSSAccessKeyId=LTAI4FwEi4gRwEkW4qkMYW3C&Signature=TCYUARuhsI4Laiufl2%2F6NRNuszY%3D";
                    }
                    if (temp == 4) {//type=0
                        val = "0";
                    }
                    if (temp == 5) {//scope
                        val = "";
                    }
                    if (temp == 6) {//product_id
                        val = "";
                    }
                    if (temp == 7) {//category_id
                        val = "";
                    }
                    if (temp == 8) {//同步name
                        Cell cell = readsheet.getCell(2, i);
                        val = cell.getContents();
                    }
                    if (temp == 9) {//同步effective_begin_time
                        Cell cell = readsheet.getCell(3, i);
                        val = cell.getContents();
                    }
                    if (temp == 10) {//同步effective_end_time
                        Cell cell = readsheet.getCell(4, i);
                        val = cell.getContents();
                    }
                    if (temp == 11) {//同步count_per_user
                        Cell cell = readsheet.getCell(10, i);
                        val = cell.getContents();
                    }
                    if (temp == 12) {//total_account
                        val = "";
                    }
                    if (temp == 13) {//derate_account
                        val = "";
                    }
                    if (temp == 14) {//rebate
                        val = "";
                    }
                    if (temp == 15) {//同步remaining_count
                        Cell cell = readsheet.getCell(9, i);
                        val = cell.getContents();
                    }
                    if (temp == 16) {//status
                        val = "1";
                    }
                    if (temp == 17) {//activity_id
                        val = redisService.increment().toString();
                    }
                    if (temp == 18) {//use_activity_id
                        val = "";
                    }
                    if (temp == 19) {//version
                        val = "0";
                    }
                    if (temp == 20) {//deleted
                        val = "0";
                    }
                    if (temp == 21) {//create_by
                        val = "涂鸦导入";
                    }
                    if (temp == 22) {//update_by
                        val = "";
                    }
                    if (temp == 23) {//同步gmt_create
                        Cell cell = readsheet.getCell(14, i);
                        val = cell.getContents();
                    }
                    if (temp == 24) {//同步gmt_modified
                        Cell cell = readsheet.getCell(15, i);
                        val = cell.getContents();
                    }
                    if (temp == 25) {//sort
                        val = "0";
                    }

                    if (temp == 26) {//show_area
                        val = "0";
                    }
                    if (temp == 27) {//enabled
                        val = "0";
                    }
                    if (temp == 28) {//subtract_score
                        val = "";
                    }

                    if (temp == 29) {//level
                        val = "";
                    }
                    if (temp == 30) {//valid_date
                        val = "";
                    }
                    if (temp == 31) {//valid_type
                        val = "1";
                    }
                    if (temp == 32) {//tuya_coupon_code
                        Cell cell = readsheet.getCell(1, i);
                        val = cell.getContents();
                    }
                    if (temp == 33) {//tuya_coupon_id
                        Cell cell = readsheet.getCell(0, i);
                        val = cell.getContents();
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