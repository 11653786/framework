package com.yt.util.yt.myutils;

/**
 * Created by Administrator on 2016/3/18 0018.
 */

import com.yt.util.dhqjr.DateUtil;
import com.yt.util.yt.annotation.RestAttribute;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * EXCEL文档导出工具类
 *
 * @author guanping
 * @version 2.0, 2015年7月21日
 * @since com.dongrongonline 2.0
 */
public class ExportExcel<T> {

    // 日期格式
    private String datePattern = DateUtil.DATETIMESHOWFORMAT;

    /**
     * 利用开源组件POI动态导出EXCEL文档 利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据 以EXCEL
     * 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param headers 表格属性列名数组
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。
     *                此方法支持的javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @param pattern 如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
     * @see
     */
    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为18个字节
        sheet.setDefaultColumnWidth(18);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("楷体");
        font.setFontHeightInPoints((short) 15);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("楷体");
        font.setFontHeightInPoints((short) 10);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("东融在线"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("guanping");

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 设置货币格式
        CellStyle cellStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("#,##0.00"));
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                HSSFCell cell = row.createCell(i, HSSFCell.CELL_TYPE_STRING);
                // 设置CELL格式为文本格式
                // (如果不设置则默认是HSSFCell.CELL_TYPE_STRING格式，但是输出后格式是“常规”)
                // “常规”单元格格式表示不包含任何特定的数据格式，这时如果双击单元格数据就会恢复本地excel默认的单元格格式
                HSSFDataFormat format = workbook.createDataFormat();
                style2.setDataFormat(format.getFormat("@")); // 字符串类型所对应的是数据格式为"@"
                cell.setCellStyle(style2);
                Field field = fields[i];
                String fieldName = field.getName();
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    @SuppressWarnings("rawtypes")
                    Class tCls = t.getClass();
                    @SuppressWarnings("unchecked")
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
                    String textValue = null;
                    if (value instanceof Integer) {
                        int intValue = (Integer) value;
                        cell.setCellValue(intValue);
                    } else if (value instanceof Float) {
                        float fValue = (Float) value;
                        textValue = new HSSFRichTextString(String.valueOf(fValue)).getString();
                        cell.setCellValue(textValue);
                    } else if (value instanceof Double) {
                        double dValue = (Double) value;
                        textValue = new HSSFRichTextString(String.valueOf(dValue)).getString();
                        cell.setCellValue(textValue);
                    } else if (value instanceof BigDecimal) {
                        BigDecimal bValue = (BigDecimal) value;
                        cell.setCellStyle(cellStyle);
                        cell.setCellValue(bValue == null ? 0d : bValue.doubleValue());
                    } else if (value instanceof Long) {
                        long longValue = (Long) value;
                        cell.setCellValue(longValue);
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                        cell.setCellValue(textValue);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else if (value instanceof List) {
                        @SuppressWarnings("unchecked")
                        List<Object> listValue = (List<Object>) value;
                        if (!StringUtils.isEmpty(listValue)) {
                            for (int j = 0; j < listValue.size(); j++) {
                                Object obj = listValue.get(j);
                                HSSFCell cel = row.createCell(i + j, HSSFCell.CELL_TYPE_STRING);
                                if (obj instanceof BigDecimal) {
                                    BigDecimal bValue = (BigDecimal) obj;
                                    cel.setCellStyle(cellStyle);
                                    cel.setCellValue(bValue == null ? 0d : bValue.doubleValue());
                                } else {
                                    cel.setCellStyle(style2);
                                    textValue = obj == null ? "" : obj.toString();
                                    cel.setCellValue(textValue);
                                }
                            }
                        }
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value == null ? "" : value.toString();
                        cell.setCellValue(textValue);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            if (workbook != null) {
//                try {
//                    //workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    /**
     * 利用开源组件POI动态导出EXCEL文档 利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据 以EXCEL
     * 的形式输出到指定IO设备上
     *
     * @param title   表格标题名
     * @param dataset 需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。
     * @param out     与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
     * @see
     */
    public void exportNewExcel(String title, Collection<T> dataset, OutputStream out) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为18个字节
        sheet.setDefaultColumnWidth(23);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontName("楷体");
        font.setFontHeightInPoints((short) 15);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("楷体");
        font.setFontHeightInPoints((short) 10);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置,详见文档
        HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        comment.setString(new HSSFRichTextString("东融在线"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
        comment.setAuthor("www.dhqjr.com");

        HSSFRow row = sheet.createRow(0);

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        List<String> methodNames = new ArrayList<String>();
        while (it.hasNext()) {
            index++;

            T t = (T) it.next();
            Field[] fields = t.getClass().getDeclaredFields();
            int callIdx = 0;
            if (index == 1) { // 产生表格标题行
                for (int i = 0; i < fields.length; i++) {
                    Field field = fields[i];
                    String name = getTitleName(field);
                    if (name != null) {
                        HSSFCell cell = row.createCell(callIdx);
                        cell.setCellStyle(style);
                        cell.setCellValue(name);

                        methodNames.add(field.getName());
                        callIdx++;
                    }
                }
            }

            row = sheet.createRow(index);
            callIdx = 0;
            for (String name : methodNames) {
                Object obj = getValue(name, t);
                if (obj != null) {
                    HSSFCell cell = row.createCell(callIdx, HSSFCell.CELL_TYPE_STRING);
                    cell.setCellStyle(style2);
                    cell.setCellValue(obj.toString());

                    callIdx++;
                }
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        finally {
//            if (workbook != null) {
//                try {
//                    // workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    private String getTitleName(Field field) {
        RestAttribute attribute = field.getAnnotation(RestAttribute.class);
        if (attribute != null) {
            return attribute.name();
        }
        return null;
    }

    private Object getValue(String methodName, T t) {
        try {
            PropertyDescriptor pd = new PropertyDescriptor(methodName, t.getClass());
            Method get = pd.getReadMethod();
            Object value = get.invoke(t, new Object[]{});
            if (value instanceof Date) {
                return !StringUtils.isEmpty(value) ? "--" : DateUtil.getDateString((Date) value, datePattern);
            }
            return StringUtils.isEmpty(value) ? "--" : value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }
}

