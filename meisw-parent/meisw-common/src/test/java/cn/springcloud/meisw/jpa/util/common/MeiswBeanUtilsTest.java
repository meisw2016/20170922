package cn.springcloud.meisw.jpa.util.common;

import java.util.ArrayList;
import java.util.List;

import cn.springcloud.meisw.jpa.common.MeiswBeanUtils;

public class MeiswBeanUtilsTest {
	public static void main(String[] args) {
		List<Stu> stuList = new ArrayList<Stu>();
		Stu st1 = new Stu("张三",10);
		stuList.add(st1);
		Stu st2 = new Stu("李四",20);
		stuList.add(st2);
		List<StuVo> voList = new ArrayList<StuVo>();
		
		voList = MeiswBeanUtils.copyProperties(stuList, StuVo.class);
		for(StuVo vo:voList) {
			System.out.println(vo);
		}
	}

}
