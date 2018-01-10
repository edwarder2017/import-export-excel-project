package com.zj.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**   
*    
* 项目名称：stuSys   
* 类名称：ExcelUtils   
* 类描述：
* 创建人：jzhang  
* 创建时间：2018年1月10日 上午10:21:19   
* 联系方式：1104975916@qq.com 
*      
*/
public class ExcelUtils<T> {
	
	private Class<T> entityClass;
	
	
	public ExcelUtils() {
		super();
	}

	public ExcelUtils(Class<T> entityClass) {
		super();
		this.entityClass = entityClass;
	}

	/**
	 * 使用Java Excel Api 导出 Excel文件
	 * @param headers
	 * @param title
	 * @param datas
	 * @param out
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public void exportByJXL(String[] headers, String title, Collection<T> datas, OutputStream out) throws RowsExceededException, WriteException {
		try {
			//创建可写入的Excel工作薄，且内容将写入到输出流，并通过输出流输出给客户端浏览
			WritableWorkbook wk = Workbook.createWorkbook(out);
			
			///创建可写入的Excel工作表
		    WritableSheet sheet = wk.createSheet("成绩表", 0);
		 
			//把单元格（column, row）到单元格（column1, row1）进行合并。
			//mergeCells(column, row, column1, row1);
		    sheet.mergeCells(0, 0, headers.length - 1, 0);//单元格合并方法
			 
			//创建WritableFont 字体对象，参数依次表示黑体、字号12、粗体、非斜体、不带下划线、亮蓝色
			WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"),12,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.LIGHT_BLUE);
			 
			//创建WritableCellFormat对象，将该对象应用于单元格从而设置单元格的样式
			WritableCellFormat titleFormat = new WritableCellFormat();
			 
			//设置字体格式
			titleFormat.setFont(titleFont);
			 
			//设置文本水平居中对齐
			titleFormat.setAlignment(Alignment.CENTRE);
			
			//设置文本垂直居中对齐
			titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			 
			//设置背景颜色
			titleFormat.setBackground(Colour.GRAY_25);
			 
			//设置自动换行
			titleFormat.setWrap(true);
			 
			//添加Label对象，参数依次表示在第一列，第一行，内容，使用的格式
			 
			Label lab_00 = new Label(0, 0, title, titleFormat);
			 
			//将定义好的Label对象添加到工作表上，这样工作表的第一列第一行的内容为‘学员考试成绩一览表’并应用了titleFormat定义的样式
			sheet.addCell(lab_00);
			 
			WritableCellFormat cloumnTitleFormat=new WritableCellFormat();
			 
			cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"),10,WritableFont.BOLD,false));
			 
			cloumnTitleFormat.setAlignment(Alignment.CENTRE);
			
			//设置表格列
			for (int i = 0; i < headers.length; i++) {
				Label lab_01=new Label(i, 1, headers[i], cloumnTitleFormat);
				sheet.addCell(lab_01);
			}
			 
			//设置列表数据
			//封装数据
			Iterator<T> it = datas.iterator();
			int index = 1;
			while (it.hasNext()) {
				index ++;
				T t = it.next();
				
				// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
				Field[] fields = t.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
	                String fieldName = field.getName();//获得属性名称
	                String getMethodName = "get"
	                        + fieldName.substring(0, 1).toUpperCase()
	                        + fieldName.substring(1);
	                
	                //通过对象的get方法获得属性值
	                try {
						Class<? extends Object> tCls = t.getClass();
						Method getMethod = tCls.getMethod(getMethodName,new Class[]{});
						Object value = getMethod.invoke(t, new Object[]{});
						
						//将每列数据添加到单元格中
						sheet.addCell(new Label(i,index,value.toString()));
						
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			
			//将定义的工作表输出到之前指定的介质中（这里是客户端浏览器）
			wk.write();
			 
			//操作完成时，关闭对象，释放占用的内存空间   
			wk.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public List<T> importDatas(InputStream inputStream) {
		List<T> list = new ArrayList<>();
		try {
			Workbook wk = Workbook.getWorkbook(inputStream);
			
			//获取第一张Sheet表 
	        Sheet sheet = wk.getSheet(0);
	        
	        //获取总行数
			int rowNum = sheet.getRows();
			
			//从数据行开始迭代每一行
			for(int i = 2; i < rowNum; i++){
				//getCell(column,row)，表示取得指定列指定行的单元格（Cell）
				//getContents()获取单元格的内容，返回字符串数据。适用于字符型数据的单元格
				try {
					//创建实例
					T tobj = entityClass.newInstance();
					//获取class的字节码
					Class cl = tobj.getClass();
					//获得class的所有属性
					Field[] field = cl.getDeclaredFields();
					
					for (int j = 0; j < field.length; j++) {
						//获得属性名称
					    String propertyName = field[j].getName();
					    //获得set方法
					    String methodName = "set"
					      + propertyName.substring(0, 1).toUpperCase()
					      + propertyName.substring(1, propertyName.length());
					    
					    //获取属性类型
						Class typeclass = field[j].getType();
						
						//获得class中对应的set方法
						Method method = cl.getMethod(methodName,new Class[] { typeclass });
						
						System.out.println("method:" + method);
						System.out.println("typeclass:" + typeclass);
						System.out.println("cell:" + sheet.getCell(j, i).getContents());
						
						//类型判断
						//关联tobj,输入excel中值并执行该方法
						//如果有更多类型判断，需要再添加，比如boolean，float，double等等
						if (typeclass == String.class) {
							method.invoke(tobj, new Object[] { sheet.getCell(j, i).getContents()});
						}else if(typeclass == Integer.class) {
							method.invoke(tobj, new Object[] { Integer.valueOf(sheet.getCell(j, i).getContents())});
						}
					}
					//将单个对象添加到集合中
					list.add(tobj);
					
				}catch (IllegalAccessException e) {
					e.printStackTrace();
				}catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}catch (InstantiationException e) {
					e.printStackTrace();
				}
			}
			
			//返回结果
			return list;
			
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		return null;
	}
		
}
