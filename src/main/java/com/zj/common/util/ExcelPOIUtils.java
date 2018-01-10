package com.zj.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**   
*    
* 项目名称：stuSys   
* 类名称：ExcelPOIUtils   
* 类描述：基于POI方式实现excel导出工具类
* 创建人：jzhang  
* 创建时间：2018年1月10日 上午10:20:54   
* 联系方式：1104975916@qq.com 
*      
*/
public class ExcelPOIUtils<T> {
	
	/**
	 * 基于POI的固定列excel导出
	 * @param response
	 * @param datas 集合数据
	 * @param cellNames 表格列名称
	 * 
	 */
	public void exportExcel(String[] headers, String title, Collection<T> datas,OutputStream out){
		//创建HSSFWorkbook对象(excel的文档对象)
		@SuppressWarnings("resource")
		HSSFWorkbook wb = new HSSFWorkbook();
	      
		//建立新的sheet对象（excel的表单）,当然也可以不设置sheet名称
		//HSSFSheet sheet = wb.createSheet();
		HSSFSheet sheet = wb.createSheet("学员信息");
		
		//设置列宽
		//设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
		//实际应该自适应列宽
		sheet.setColumnWidth(3, 256 * 20);//设置身份证的列宽
		sheet.setColumnWidth(4, 256 * 13);//设置手机号的列宽
		sheet.setColumnWidth(7, 256 * 16);//设置专业的列宽
		
		
		//在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		HSSFRow row1 = sheet.createRow(0);
		
		//创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
		HSSFCell cell = row1.createCell(0);
		
		//设置单元格内容
		cell.setCellValue(title);
		
		//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,headers.length));
		
		//在sheet里创建第二行
		HSSFRow cellName = sheet.createRow(1); 
		
        //创建单元格并设置单元格列名称
		if(headers != null && headers.length > 0) {
			for (int i = 0; i < headers.length; i++) {
				cellName.createCell(i).setCellValue(headers[i]);
			}
		}else {//使用默认列名
			for (int i = 0; i < datas.size(); i++) {
				cellName.createCell(i).setCellValue("cellName" + i);
			}
		}
		
		//封装数据
		Iterator<T> it = datas.iterator();
		int index = 1;
		HSSFRow rowDatas;
		while (it.hasNext()) {
			index ++;
			rowDatas = sheet.createRow(index);
			T t = it.next();
			
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields = t.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				//创建列
				HSSFCell cellDatas = rowDatas.createCell(i);
				
				Field field = fields[i];
                String fieldName = field.getName();//获得属性名称
                String getMethodName = "get"
                        + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                
                try {
					Class<? extends Object> tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,new Class[]{});
					Object value = getMethod.invoke(t, new Object[]{});
					cellDatas.setCellValue(value.toString());
					
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
            wb.write(out);
        }catch (IOException e) {
            e.printStackTrace();
        }
	}
	
}
