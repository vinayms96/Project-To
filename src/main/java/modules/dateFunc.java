package modules;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateFunc {
	
	public static String getDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("E d/M hh:mm");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
