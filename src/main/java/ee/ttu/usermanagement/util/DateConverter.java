package ee.ttu.usermanagement.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.opensymphony.xwork2.conversion.TypeConversionException;

public class DateConverter extends StrutsTypeConverter {

	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class toClass) {
		String dateString = values[0];
		if (dateString == null || dateString.trim().isEmpty()) {
			return null;
		}

		Date date = null;
		if (toClass == Date.class) {
			try {
				date = DATE_FORMAT.parse(dateString);
			} catch (ParseException e) {
				throw new TypeConversionException("Unable to convert to Date type the string " +
												   dateString);
			}
		}

		return date;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public String convertToString(Map context, Object object) {
		if (object != null && object instanceof Date) {
			return DATE_FORMAT.format(object);
		}

		return "";
	}

}
