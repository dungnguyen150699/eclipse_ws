package com.java8.springboot.java.java8.Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;

public class StreamCollections {
    private enum values {
        black("0"),
        brown("1"),
        red("2"),
        orange("3"),
        yellow("4"),
        green("5"),
        blue("6"),
        violet("7"),
        grey("8"),
        white("9");
        private String value;
        private values(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
    int value(String[] colors) {
        String resistorValue = Arrays.stream(colors).limit(2)
                .map(color -> values.valueOf(color).getValue()) // enum cung su dc valueof hay day
                .collect(Collectors.joining());
        System.out.println(resistorValue);
        return Integer.parseInt(resistorValue);
    }
    
    int value2(String []colors) {
    	String str = Arrays.stream(colors).limit(2)
    			.map(color -> values.valueOf(color).getValue())
    			.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
    			.toString();
    	return Integer.parseInt(str);
    }
    public static void main( String...strings ) {
    	StreamCollections pd = new StreamCollections();
    	String[] input = { "green", "brown", "orange" };
    	System.out.println(pd.value2(input));
    	System.out.println( Boolean.valueOf(0+""));
    }
}


class MicroBlog_procode {
    private static final int MAX_POST_LENGTH = 5;
    public String truncate(final String input) {

    		return input.codePoints().limit(MAX_POST_LENGTH)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
    		.toString();
//       StringBuilder str = new StringBuilder();
//    		   .forEach(cp ->{
//    			   str.append(Character.toChars(cp));
//    		   });
//       input.codePoints().forEach(cp -> System.out.println(Character.toChars(cp)));
//       System.out.println(str);
//       return str.toString();
    }
    
	public static void main(String...strings) {
    	new MicroBlog_procode().truncate("❄🌡🤧🤒🏥🕰😀");
    	new MicroBlog_procode().truncate("a=πr²");
    	new MicroBlog_procode().truncate("ά");
    	new MicroBlog_procode().truncate("∅⊊ℕ⊊ℤ⊊ℚ⊊ℝ⊊ℂ");
    	new MicroBlog_procode().truncate("Fly 🛫");
    	new MicroBlog_procode().truncate("Добър");
    	new MicroBlog_procode().truncate("Bärteppich");
	}
}

class StreamSort {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");

        /* 
		List<String> sortedList = list.stream()
			.sorted(Comparator.naturalOrder())
			.collect(Collectors.toList());
			
        List<String> sortedList = list.stream()
			.sorted((o1,o2)-> o1.compareTo(o2))
			.collect(Collectors.toList());
		*/

		List<String> sortedList = list.stream().sorted().collect(Collectors.toList());
		
        sortedList.forEach(System.out::println);

    }
}

class StreamSortReverseOrder {

    public static void main(String[] args) {

        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");

        /*
		List<String> sortedList = list.stream()
			.sorted((o1,o2)-> o2.compareTo(o1))
			.collect(Collectors.toList());
		*/
		
        List<String> sortedList = list.stream()
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());

        sortedList.forEach(System.out::println);

    }
}

