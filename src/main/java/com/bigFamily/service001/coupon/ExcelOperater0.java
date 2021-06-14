package com.bigFamily.service001.coupon;//in ExcelOperater

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;

import java.io.*;
import java.text.SimpleDateFormat;


/**
 * 加工时间
 */

public class ExcelOperater0 {

    public static void handleTimespan() {

        Workbook readwb = null;

        try {

            //构建Workbook对象, 只读Workbook对象

            //直接从本地文件创建Workbook

            InputStream instream =
                    new FileInputStream("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/优惠券主信息.xls");

            readwb = Workbook.getWorkbook(instream);


            //Sheet的下标是从0开始

            //获取第一张Sheet表

            Sheet readsheet = readwb.getSheet(0);

            //获取Sheet表中所包含的总列数

            int rsColumns = readsheet.getColumns();

            //获取Sheet表中所包含的总行数

            int rsRows = readsheet.getRows();


            //利用已经创建的Excel工作薄,创建新的可写入的Excel工作薄
            File filewrite = new File("/Users/shuistyanlong/Documents/work/workspace/yangyi/couponByTuya/0.优惠券主信息(加工时间).xls");
            filewrite.createNewFile();
            OutputStream os = new FileOutputStream(filewrite);
            jxl.write.WritableWorkbook wwb = Workbook.createWorkbook(os);


            //读取第一张工作表
            WritableSheet ws = wwb.createSheet("sheet1", 0);

            for (int i = 0; i < rsRows; i++) {

                for (int j = 0; j < rsColumns; j++) {
                    Cell cell = readsheet.getCell(j, i);
                    String val = cell.getContents();
                    if (i != 0 && (j == 3 || j == 4 || j == 14 || j == 15)) {//优惠券生效时间
                        long tiemspan = Long.parseLong(val);
                        val = getDate(tiemspan);
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