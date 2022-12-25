package com.java8.springboot.java.javareflection;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Slf4j
public class ReadMe {
	/* 
	 * * Attention! Field in Reflect is Intance not in Class
	 * Field []fields = this.clazz.getFields(); // Only get all field have access modify is PUBLIC
	 * Field field = this.clazz.getDeclaredField("longValue"); // Can get Field althrought it is private access;
	 * field.setAccessible(true); // After you get field by name should set Access it is ignor access(public,private,protect) by this way.
	 * field.get(model)  get Field value in Object class have Field
	 * field.set(model, 4l); set Field value in Object have Field
	 * field.getType().getName() Dùng cái này để Biết type sau đó set type này 
	 */

	

	  public static Object parseValueByClass(Class toClass, Object value) {
	    if (value == null || toClass == null) {
	      return null;
	    }

	    String clazzName = toClass.getSimpleName();

	    switch (clazzName) {
	      case "char": {
	        value = parseChar(value);
	        break;
	      }

	      case "String": {
	        value = value.toString().trim();
	        break;
	      }

	      case "boolean":
	      case "Boolean": {
	        value = parseBoolean(value);
	        break;
	      }

	      case "byte":
	      case "Byte": {
	        value = parseByte(value);
	        break;
	      }

	      case "short":
	      case "Short": {
	        value = parseShort(value);
	        break;
	      }

	      case "int":
	      case "Integer": {
	        value = parseInt(value);
	        break;
	      }

	      case "long":
	      case "Long": {
	        value = parseLong(value);
	        break;
	      }

	      case "float":
	      case "Float": {
	        value = parseFloat(value);
	        break;
	      }

	      case "double":
	      case "Double": {
	        value = parseDouble(value);
	        break;
	      }

	      case "Date": {
	        value = parseDate(value);
	        break;
	      }

	      case "Instant": {
	        value = parseInstant(value);
	        break;
	      }

	      case "Enum": {
	        value = parseEnum(toClass, value);
	        break;
	      }

	      case "Map": {
	        value = parseMap(value);
	        break;
	      }

	      case "BigDecimal": {
	        value = parseBigDecimal(value);
	        break;
	      }

	      default: {
	        break;
	      }
	    }

	    return value;
	  }
	  
	  

	  private static Object parseMap(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    return (Map) value;
	  }

	  private static Object parseEnum(Class toClass, Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    String valueStr = value.toString().trim();
	    return Enum.valueOf(toClass, valueStr);
	  }

	  protected static final String[] formats = {"yyyy-MM-dd HH:mm:ss", "dd/MM/yyyy", "dd-MMM-yyyy", "MM dd, yyyy", "dd-MMM-yyyy",
	          "E, MMM dd yyyy", "E, MMM dd yyyy HH:mm:ss", "dd-MMM-yyyy HH:mm:ss", ""};

	  private static Date parseDate(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    String dateStr = value.toString();

	    for (String format : formats) {
	      Date date = null;
	      try {
	        DateFormat formatter = new SimpleDateFormat(format);
	        date = formatter.parse(dateStr);
	      } catch (Exception ex) {
	        log.error(ex.getMessage(), ex);
	      }

	      if (date != null) {
	        return date;
	      }
	    }

	    try {
	      Date date = (Date) value;
	      return date;
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	      return new Date();
	    }
	  }

	  private static Object parseInstant(Object value) {
	    return value == null ? null : parseDate(value).toInstant();//NOSONAR
	  }

	  private static Object parseFloat(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    Double doubleVal = parseDouble(value);
	    return doubleVal == null ? null : doubleVal.floatValue();
	  }

	  private static Object parseLong(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    Double doubleVal = parseDouble(value);
	    return doubleVal == null ? null : doubleVal.longValue();
	  }

	  private static Object parseInt(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    Double doubleVal = parseDouble(value);
	    return doubleVal == null ? null : doubleVal.intValue();
	  }

	  private static Object parseShort(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    Double doubleVal = parseDouble(value);
	    return doubleVal == null ? null : doubleVal.shortValue();
	  }

	  private static Object parseByte(Object value) {
	    if (value == null) {
	      return null;//NOSONAR
	    }
	    Double doubleVal = parseDouble(value);
	    return doubleVal == null ? null : doubleVal.byteValue();
	  }

	  private static Object parseBoolean(Object value) {
	    if (value == null) {
	      return null;
	    }
	    return (Boolean) value;
	  }

	  private static Object parseChar(Object value) {
	    return (value == null) ? '\u0000' : (char) value;
	  }

	  public static Double parseDouble(Object obj) {
	    if (obj == null) {
	      return null;//NOSONAR
	    }

	    try {
	      return Double.parseDouble(obj.toString());
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	    }

	    return null;
	  }

	  public static BigDecimal parseBigDecimal(Object obj) {
	    if (obj == null) {
	      return null;//NOSONAR
	    }

	    try {
	      return BigDecimal.valueOf(Double.valueOf(obj.toString()));
	    } catch (Exception e) {
	      log.error(e.getMessage(), e);
	    }

	    return null;
	  }
}
