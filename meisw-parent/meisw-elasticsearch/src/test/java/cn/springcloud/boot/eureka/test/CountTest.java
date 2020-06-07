package cn.springcloud.boot.eureka.test;


public class CountTest {
	public static void main(String[] args) {
	    test1();
	    test2();
	    test3();
	    test4();
    }
	
	public static void test1(){
		int sum = 0;
	    for(int i = 0;i<=30;i++){
	    	sum += i*i;
	    }
	    System.out.println(sum);
	}
	
	public static void test2(){
		int sum = 0;
		for(int i =0;i<=30;i++){
			sum += Math.pow(i, 2);
		}
		System.out.println(sum);
	}
	public static void test3(){
		int sum = 0;
		for(int i =0;i<=30;i++){
			sum += Math.pow(i, 3);
		}
		System.out.println(sum);
	}
	
	public static void test4(){
		int a,b,sum = 0;
		for(a=1;a<=100;a++){
			for(b=2;b<a;b++){
				if(a%b==0){
					break;
				}
			}
			if(a==b){
				System.out.println(b);
				sum += b;
			}
		}
		System.out.println("100以內的素数之和为："+sum);
	}
}
