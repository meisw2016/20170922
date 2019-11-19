package cn.springcloud.meisw.jpa.util.common;

import java.io.Serializable;

public class StuVo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer age;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "StuVo [name=" + name + ", age=" + age + "]";
	}
	
}
