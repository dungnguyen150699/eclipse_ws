package com.java8.springboot.java.enumx;

public enum EnumImpl implements EnumInterface{
//	TH1
	ENUM_IMPL_SUM(1){
		public int caclulator(int a, int b) {
			// TODO Auto-generated method stub
			return a+b;
		}	
	}, 
	ENUM_IMPL_MNUS(2){
		public int caclulator(int a, int b) {
			// TODO Auto-generated method stub
			return a-b;
		}
	};
	
	private int key;
	
	private EnumImpl(int key) {
		this.key = key;
	}
	
	@Override
	public int getKey() {
		// TODO Auto-generated method stub
		return this.key;
	}
}
