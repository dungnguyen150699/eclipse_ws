package com.java8.springboot.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.util.ResourceUtils;

public class Call_JavaScript_FromJava {
	public static void main(String[] args) {
		@SuppressWarnings("removal")
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
		try {
			engine.eval("print('Welcom to javascript execution using java')");
			engine.eval(new FileReader("file/welcome.js"));
//			engine.eval(new FileReader(ResourceUtils.getFile("welcome.js")));
			Invocable invocable = (Invocable) engine;
			invocable.invokeFunction("sumOfTwoNumbers", 10,20);
		}catch(ScriptException | FileNotFoundException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
	
	public static int sumOfTwoNumbers(int input1, int input2) {
		return input1 + input2;
	}
}
