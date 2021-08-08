package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

/*
 * 	����ȫ�����ڸ�ʽת����
 */
public class DateConvert implements Converter<String, Date>{

	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return simpleDateFormat.parse(source);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;
	}

}
