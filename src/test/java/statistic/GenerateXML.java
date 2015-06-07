package statistic;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

import dataservice.DataService;
import server.businesslogic.BLController;
import server.businesslogic.SortMatchesByCalendar;
import server.data.DataController;
import server.po.MatchPO;
import blservice.BLService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class GenerateXML {
	@Test
	public void testSort() {
		DataService data = new DataController();
		ArrayList<MatchPO> h = data.getAllMatch();
		Collections.sort(h, new SortMatchesByCalendar());
		try
		{
			File f = Opendoc("result.xml");
			WritableWorkbook book = Workbook.createWorkbook(f);
			WritableSheet sheet = book.createSheet("第一页", 0);
			Label label = new Label(0, 0, "库存盘点");
			sheet.addCell(label);
			label = new Label(1, 0, "批次：");
			sheet.addCell(label);
			label = new Label(2, 0, "批号：");
			sheet.addCell(label);
			label = new Label(0, 1, "行号");
			sheet.addCell(label);
			label = new Label(1, 1, "名称");
			sheet.addCell(label);
			label = new Label(2, 1, "型号");
			sheet.addCell(label);
			label = new Label(3, 1, "库存数量");
			sheet.addCell(label);
			label = new Label(4, 1, "库存均价");
			sheet.addCell(label);
			/*for (int i = 2; i <= temp.size() + 1; i++)
			{
				label = new Label(0, i, Integer.toString(i - 1));
				sheet.addCell(label);
				CommodityVO comvo = vo.getList().get(i - 2);
				label = new Label(1, i, comvo.getName());
				sheet.addCell(label);
				label = new Label(2, i, comvo.getModel());
				sheet.addCell(label);
				label = new Label(3, i, Integer.toString(comvo.getNumber()));
				sheet.addCell(label);
				label = new Label(4, i, Double.toString(comvo.getIn()));
				sheet.addCell(label);
			}*/
			book.write();
			book.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public static File Opendoc(String s)
	{//参数为需要打开的文件路径
	    File f = new File(s);
	    if(!f.exists())//若文件不存在，自动创建
	    try
	    {
	    	if(f.getParentFile() != null)
	    		f.getParentFile().mkdirs();
	        f.createNewFile();
	        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(f));
	        oos.writeObject(null);
	        oos.close();
	    }
	    catch(IOException e)
	    {
	        System.out.println(e);
	    }
		return f;
	}
}
