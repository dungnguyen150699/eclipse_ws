package com.java8.springboot.java.java8;

import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;


// Nguồn // https://viblo.asia/p/regular-expressions-regex-khong-he-kho-nhu-nhung-gi-ban-thay-ii-L4x5xg3YlBM
@Log4j2
public class Regex {
	/*
	 * Link học:https://www.w3schools.com/java/java_regex.asp
	 *  https://viettuts.vn/java/su-dung-regex-trong-java
	 * Lớp Matcher
		Nó implements interface MatchResult, cung cấp bộ máy xử lý biểu thức chính quy để thao tác với chuỗi ký tự.
		
		No.	Phương thức	Mô tả
		1	boolean matches()	kiểm tra xem biểu thức chính quy có khớp với mẫu hay không.
		2	boolean find()	tìm biểu thức tiếp theo khớp với mẫu.
		3	boolean find(int start)	tìm biểu thức tiếp theo khớp với mẫu từ số bắt đầu đã cho.
		4	String group()	trả về chuỗi con phù hợp.
		5	int start()	trả về vị trí bắt đầu của chuỗi con phù hợp.
		6	int end()	trả về vị trí kết thúc của chuỗi con phù hợp.
		7	int groupCount()	trả về tổng số các chuỗi con phù hợp.
	 * Lớp Pattern
		Đây là phiên bản được biên dịch của một biểu thức chính quy. Nó được sử dụng để xác định một mẫu cho bộ máy regex.
		
		No.	Phương thức	Mô tả
		1	static Pattern compile(String regex)	biên dịch regex đã cho và trả về thể hiện của Pattern.
		2	Matcher matcher(CharSequence input)	tạo một matcher khớp với đầu vào đã cho với mẫu.
		3	static boolean matches(String regex, CharSequence input)	Nó biên dịch biểu thức chính quy và tìm kiếm các chuỗi con từ chuỗi input phù hợp với mẫu regex.
		4	String[] split(CharSequence input)	chia chuỗi input đã cho thành mảng các kết quả trùng khớp với mẫu đã cho.
		5	String pattern()	trả về mẫu regex.
		6   ?=.* Dùng để check nó có ký thứ nào phù hợp ko
		7   ?= Bằng với ==, equal trong java 
		8   ?! Bằng với != java
	 */
	
	// Biển số xe bao gồm ít nhất 1 chữ và 1 số           ?=.*
	// Key ?= chỉ dùng để check bẳng .* là khớp với bất kì ký tự nào => (\\w) cũng khớp với bất kì ký tự nào
	public static final String REGISTER_NO_OTHER = "(?=.*\\d)(?=.*[a-zA-Z])(([a-zA-Z0-9]{1,8})-([a-zA-Z0-9]{1,8}))";
	public static final String CHECK_REVESER = "(\\w)(\\w)\\2\\1";
	public static final String CHECK_DUPLICATE_2C= "(\\w)\\1";
	public static final String CHECK_DUPLICATE_2C_ANY= "^.*(.)\\1.*$";
	public static final String CHECK_NO_DUPLICATE_2C= "(?:(\\w)(?!\\1))+$";
	public static final String CHECK_VALIDATE_MAP="(-?\\d{0,3})\\.?(\\d{0,14})";
	public static final String REGEX_INTEGER = "[0-9]*";
	public static final String REGEX_MAP = "(-?\\d{0,3})\\.?(\\d{0,14})";
	
	public static final String CHECK_HOT_LINE="((1800)|(1900))([0-9]{4})";
	public static final String CHECK_HOT_LINE2 = "(1800|1900){1}([0-9]{4})";
	public static final String CHECK_HOT_LINE3 = "[1800|1900]{1}[0-9]{4}";
	public static final String CHECK_LANDING_PHONE= "0%s[0-9]{7}";
	public static final String CHECK_YOUTUBE = "(?:https?:\\/{2})?(?:w{3}\\.)?youtu(?:be)?\\.(?:com|be)(?:\\/watch\\?v=|\\/)([^\\s&]+)";
	public static final String CHECK_YOUTUBE2 = "/\b(?:https?:\\/{2})?(?:w{3}\\.)?youtu(?:be)?\\.(?:com|be)(?:\\/watch\\?v=|\\/)([^\\s&]+)\\b/";
	public static final String CHECK_YOUTUBE3 = "^(?:https?:\\/\\/)?(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";
	public static final String CHECK_YOUTUBE4 = "(https?://)?(www\\.)?(yotu\\.be/|youtube\\.com/)?((.+/)?(watch(\\?v=|.+&v=))?(v=)?)([\\w_-]{11})(&.+)?";
	public static final String CHECK_YOUTUBE5 = "^(?:https?:\\/\\/)?(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";

	public static final String REGEX_YOUTUBE = "^(https?\\:\\/\\/)?(www\\.youtube\\.com|youtu\\.be)\\/.+$";
	public static final String REGEX_YOUTUBE2 = "(?:https?:\\/{2})?(?:w{3}\\.)?youtu(?:be)?\\.(?:com|be)(?:\\/watch\\?v=|\\/)([^\\s&]+)";
	
	public static final String REGEX_BASE64 = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=)?$";
	public static final String REGEX_BASE64_2 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
	public static final String REGEX_EMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	
	/*
	let e=/(\w)(\w)\2\1/; // Biểu thức trong () được nghi nhớ , Biểu thức /1 ghi nhớ () thứ nhất /2() ghi nhớ () thứ 2
	e.test("aabb"); //false // Và tóm lại cái này sẽ đảo ngược thì nó mới đúng
	e.test("abba"); //true 
	e.test("abab"); //false
	
	\w đại diện cho tất cả các ký tự chữ và số, nếu bạn viết hoa w thành W, điều đó có nghĩa là \W đại diện cho tất cả các ký tự không phải số và chữ.
	( ) biểu thức trong dấu ngoặc đơn được ghi nhớ cho lần sử dụng sau.
	\1 ghi nhớ và sử dụng kết quả phép match của biểu thức đầu tiên ở trong dấu ngoặc đơn, \2 sử dụng cho cặp dấu ngoặc đơn thứ 2.
	a(?!b) một tập hợp của dấu ngoặc đơn, dấu hỏi chấm và dấu chấm than (?!), gọi là biểu thức look ahead, nó match với a khi và chỉ khi a không được theo sau bởi b.
	a(?=b) match với a khi và chỉ khi a được theo sau bởi b.
	(?:a) nhóm mà nó có thể quên, nó tìm kiếm a nhưng không ghi nhớ nó, bạn không thể sử dụng \1 để dùng lại kết quả phép match.
	Quy tắc 
		1.Trong [//w{2}] là sai trong [] ko thể chứa {} vì thực tế nó chỉ xuất hiện 1 lần trong []
		2.Ko thể dùng (?!, ?= ,?: kết hợp với \1 \2, ...) khi mà ko có 1 () tổng quát vd (\\w)(!=1) là sai ; (?(\\w)(!=\1)) mới đúng
	*/
	
	public static boolean matcher(String str ,String regex) {
		/*
		 * Sự khác biệt khi dùng Method Matcher của String và Matcher của Regex
		 * 1. Khi dùng method matcher của String thì mặc định nó sẽ check tất cả pattern Regex từ đầu đến cuối
		 * 2. Khi dùng Matcher của Regex thì nó sẽ check Xem chuỗi có khớp với 1 phần ( Chuỗi con của Pattern REGEX ) hay không
		 * dùng ^ để bắt đầu $ để kết thúc với Matcher của Regex  
		 */
		
		Pattern regexCompiled = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = regexCompiled.matcher(str);
		return regexMatcher.find();
	}	
	
	private static Integer x;
	public static void main(String[] args) {		
		Regex rg = new Regex();

//		System.out.println(rg.matcher("abba", CHECK_REVESER)); // true
//		System.out.println(rg.matcher("ab", CHECK_DUPLICATE_2C)); // false
//		System.out.println(rg.matcher("bb", CHECK_DUPLICATE_2C)); // true
//		System.out.println(rg.matcher("bb", CHECK_NO_DUPLICATE_2C)); // false
//		System.out.println(rg.matcher("ab", CHECK_NO_DUPLICATE_2C)); // false
//		System.out.println(rg.matcher("bxcffa", CHECK_DUPLICATE_2C_ANY)); // true
//		System.out.println(rg.matcher("'abc" + "",CHECK_VALIDATE_MAP));
		System.out.println(rg.matcher("18008192",CHECK_HOT_LINE));  // true
		System.out.println(rg.matcher("18008192",CHECK_HOT_LINE2)); // true
		System.out.println(rg.matcher("18008192",CHECK_HOT_LINE3)); // true
	// in home
//		System.out.println(rg.matcher("abba", CHECK_REVESER)); // true
//		System.out.println(rg.matcher("ab", CHECK_DUPLICATE_2C)); // false
//		System.out.println(rg.matcher("bb", CHECK_DUPLICATE_2C)); // true
//		System.out.println(rg.matcher("bb", CHECK_NO_DUPLICATE_2C)); // false
//		System.out.println(rg.matcher("ab", CHECK_NO_DUPLICATE_2C)); // false
//		System.out.println(rg.matcher("bxcffa", CHECK_DUPLICATE_2C_ANY)); // true
//		//System.out.println(rg.matcher(null, REGEX_INTEGER)); // null pointer
//		System.out.println(rg.matcher("", REGEX_MAP)); // true
//		System.out.println(rg.matcher("https://www.youtube.com/watch?v=9EwqXZtviTM", REGEX_YOUTUBE2));
		try {
//			System.out.println(rg.matcher("https://www.youtube.com/watch?v=WIm1GgfRz6M&list=RDWIm1GgfRz6M&start_radio=1&rv=WIm1GgfRz6M&t=3", CHECK_YOUTUBE));
//			System.out.println(rg.matcher("https://www.youtube.com/watch?v=WIm1GgfRz6M&list=RDWIm1GgfRz6M&start_radio=1&rv=WIm1GgfRz6M&t=3", CHECK_YOUTUBE2));
//			System.out.println(rg.matcher("https://www.youtube.com/watch?v=WIm1GgfRz6M&list=RDWIm1GgfRz6M&start_radio=1&rv=WIm1GgfRz6M&t=3", CHECK_YOUTUBE3));
//			System.out.println(rg.matcher("https://www.youtube.com/watch?v=WIm1GgfRz6M&list=RDWIm1GgfRz6M&start_radio=1&rv=WIm1GgfRz6M&t=3", CHECK_YOUTUBE4));
//			System.out.println(rg.matcher("https://www.youtube.com/watch?v=WIm1GgfRz6M&list=RDWIm1GgfRz6M&start_radio=1&rv=WIm1GgfRz6M&t=3", CHECK_YOUTUBE5));
//			System.out.println(rg.matcher("abd", REGEX_BASE64));
//			System.out.println(rg.matcher("sdsd", REGEX_BASE64_2));
//			System.out.println(rg.matcher("dungnguyen150699@gmail.com", REGEX_EMAIL));
		}catch(PatternSyntaxException ex) {
			System.out.println(ex.getIndex());
			System.out.println(ex.getDescription());
			System.out.println(ex.getPattern());
			System.out.println(ex.getCause());
		}
//		String regex_landing_phone = String.format(CHECK_LANDING_PHONE, "210");
//		System.out.println(rg.matcher("02108989994",regex_landing_phone));
		}

}

class RegexPattern {
    public static void main(String[] args) {
        String strTest = "Regex 15-05-2020, Nguyen Minh Duc 16/02/1998. Deadline 18_02_2020";
        Pattern patternDate = Pattern.compile("\\d{2}[-|/]\\d{2}[-|/]\\d{4}");
        Matcher matcher = patternDate.matcher(strTest);
         
        System.out.println("Ngày tháng năm trong chuỗi đầu vào: " + strTest +" là:");
        while(matcher.find()) {
            System.out.println(strTest.substring(matcher.start(), matcher.end()));
        }         
        String strTest2 = "15/05/2020";
        String strTest3 = "16/02/mdse";
        patternDate = Pattern.compile("^\\d{2}[-|/]\\d{2}[-|/]\\d{4}$");
        System.out.println("Chuỗi " + strTest2 + " có định dạng ngày tháng: "
                + patternDate.matcher(strTest2).matches());
         
        System.out.println("Chuỗi " + strTest3 + " có định dạng ngày tháng: "
                + patternDate.matcher(strTest3).matches());
       
    } 
}


class RegexCheck_RequireByCondition{

//    public boolean checkIfHaveNumber_AnyWhereInString(String str){
//        Pattern pattern =
//    }
}

//	 public static void main(String[] args) {
//		    Pattern pattern = Pattern.compile("w3schools", Pattern.CASE_INSENSITIVE);
//		    Matcher matcher = pattern.matcher("Visit W3Schools!");
//		    boolean matchFound = matcher.find();
//		    System.out.println(matcher.group(0));
//		    if(matchFound) {
//		      System.out.println("Match found");
//		    } else {
//		      System.out.println("Match not found");
//		    }
//		  }

//}

class Regex2{

	/*
		6   ?=.* Dùng để check nó có ký thứ nào phù hợp ko
		7   ?= Bằng với ==, equal trong java 
		8   ?! Bằng với != java
		
	\w đại diện cho tất cả các ký tự chữ và số, nếu bạn viết hoa w thành W, điều đó có nghĩa là \W đại diện cho tất cả các ký tự không phải số và chữ.
	( ) biểu thức trong dấu ngoặc đơn được ghi nhớ cho lần sử dụng sau.
	\1 ghi nhớ và sử dụng kết quả phép match của biểu thức đầu tiên ở trong dấu ngoặc đơn, \2 sử dụng cho cặp dấu ngoặc đơn thứ 2.
	a(?!b) match với a khi và chỉ khi a không được theo sau bởi b.
	a(?=b) match với a khi và chỉ khi a được theo sau bởi b.
	(?:a) nhóm mà nó có thể quên, nó tìm kiếm a nhưng không ghi nhớ nó, bạn không thể sử dụng \1 để dùng lại kết quả phép match.
	*/
	// Quy tắc trong [//w{2}] là sai trong [] ko thể chứa {} vì thực tế nó chỉ xuất hiện 1 lần trong []
	public static void main(String[] args) throws IOException {
		System.out.println(Regex.matcher("aa", "a(?!b)"));
		
		System.out.println(Regex.matcher("ab", "a(?!b)"));
		
		System.out.println(Regex.matcher("ab", "^(\\w)(?!\\1)$")); // Tức là m ko thể dùng ?!, ?= ,?: khi mà ko có 1 () tổng quát
	}
}


class Regex3{
	
	public boolean matcher(String str ,String regex) {
		Pattern regexCompiled = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher regexMatcher = regexCompiled.matcher(str);
		return regexMatcher.find();
	}	
	public static String CHECK_LIKE_CONTAIN = ",?%s,?";
	
	public static void main(String[] args) {
		Regex rg = new Regex();
		System.out.println(String.format(CHECK_LIKE_CONTAIN, "1"));
		System.out.println(rg.matcher("2",(String.format(CHECK_LIKE_CONTAIN, "1"))) );
		System.out.println(new Date());
		System.out.println(rg.matcher("df_dgf", "^\\w+$"));
		
		System.out.println(Math.pow(10,11)-1);
	}
}
