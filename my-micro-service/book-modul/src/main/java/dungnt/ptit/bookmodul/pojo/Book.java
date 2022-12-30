package dungnt.ptit.bookmodul.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private String name;
    private String author;

    public String toString(){
        return "name: " + name +"\n" +
                "author: " + author;
    }
}
