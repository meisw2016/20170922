package cn.springcloud.meisw.jpa.db2.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info_test_user")
public class User implements Serializable{
    private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	@Id
	@GeneratedValue
	private Long id;
	/**
	 * 姓名
	 */
	@Column(name = "name",nullable = false)
	private String name;
	/**
	 * 年龄
	 */
	@Column(name = "age", nullable = false)
	private Integer age;
	
    public Long getId() {
    	return id;
    }
	
    public void setId(Long id) {
    	this.id = id;
    }
	
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

	public User() {
	    super();
    }

	public User(String name, Integer age) {
	    super();
	    this.name = name;
	    this.age = age;
    }

	@Override
    public String toString() {
	    return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
    }
	
}
