package dungnt_ptit.com.securitystandard.ulti.constant.enums;

import java.util.stream.Stream;

public enum ProcessOrder{
    JUST_ORDER(3), PROCCESSING_ORDER(1), SHIPED_ORDER(2);
    int code;
    ProcessOrder(int code){
        this.code = code;
    }

    public int getCode(){
        return this.code;
    }

    public static ProcessOrder of(int code){
        return Stream.of(ProcessOrder.values())
                .filter(p -> p.code==code)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}