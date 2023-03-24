package observer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Example1 {
	private static HotGirl hotGirl = new HotGirl();
	public static void main(String[] args) {
		hotGirl.needAttention();
	}
}

class HotGirl
{
    private boolean needAttention = false;

    // Some of boys crushing this instance :))
    public List<Boy> friendZone = Arrays.asList(new Boy("Anh Dũng"), new Boy("Anh Linh"));

    public void postFacebook()
    {
        System.out.println("Complete post");
        needAttention = true;
    }

    // State of instance. When state change, observe will know and react
    public void needAttention(){
        postFacebook();
        notify1();
    }

    public void notify1(){
        for(Boy b: friendZone)
        {
            b.Care();
        }
    }

    // Register observer. 
    public void AddToZone(Boy b)
    {
        friendZone.add(b);
    }
}

class Boy
{
    public String name;

    public Boy(String name)
    {
        this.name = name;
    }

    public void Care()
    {
        System.out.printf("%s Are you OK? %s",this.name,"\n");
    }
}
