package cn.springcloud.boot.ssh.autoinstall.softwareManage.domain;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;


/**
 * 查询对象.
 *
 * @author liwei17
 * @since 1.0.0
 */
public class QueryModel {
	protected static final Logger log = LoggerFactory.getLogger(QueryModel.class);
	private static final JavaType condMapType = TypeFactory.defaultInstance().constructMapType(Map.class, String.class,
			Object.class);
	private Map<String, Object> condition = new HashMap<String, Object>();

	/**
	 * 第几页(分页参数)
	 */
	private int page;

	/**
	 * 一页多少条数据(分页参数)
	 */
	private int size = 10;

	/**
	 * 排序设置
	 */
	private String sort;

	/**
	 * 排序设置，做防注入校验和格式化之后的值
	 */
	private String formatedOrderBy;

	public Map<String, Object> getCondition() {
		return condition;
	}

	/**
	 * 设置查询条件. 将Json字符串转为Map.
	 * 
	 * @param jsonCondition
	 */
	public void setCondition(String jsonCondition) {
		ObjectMapper jsonObj = new ObjectMapper();
		try {
			// update 2019/3/4 修改map.class 为JavaType
			this.condition = jsonObj.readValue(jsonCondition, condMapType);
		} catch (JsonParseException e) {
			log.error("将查询条件condition转换为map格式出错", e);
		} catch (JsonMappingException e) {
			log.error("将查询条件condition转换为map格式出错", e);
		} catch (IOException e) {
			log.error("将查询条件condition转换为map格式出错", e);
		}
	}

	/**
	 * condition添加key-value对
	 * 
	 * @param key
	 * @param value
	 */
	public void addCondition(String key, Object value) {
		this.condition.put(key, value);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getSort() {
		return this.formatedOrderBy;
	}

	public void setSort(String sort) {
		this.sort = sort;
		formateOrderBy(this.sort);
	}

	/**
	 * 格式化orderBy字段. 字段不合法时将不进行拼接,字段合法时会将驼峰自动转为下线的格式.
	 * 
	 * @param orderBy
	 */
	private void formateOrderBy(String orderBy) {
		if (orderBy == null || "".equals(orderBy.trim())) {
			return;
		}
		StringBuilder formatedOrderBy = new StringBuilder();
		String[] orders = orderBy.split(",");
		// 存在多个排序字段时分别校验
		for (String order : orders) {
			String[] items = order.trim().split("\\s+|\\t+");
			// 单个排序字段出现多于2个关键字时不合法
			if (items.length > 2) {
				log.error("orderBy[" + orderBy + "]字段不合法,不进行拼接");
				return;
			}
			// 第一个关键字为'or'或者'and'时不合法
			if (items.length >= 1) {
				String field = items[0];
				if ("and".equalsIgnoreCase(field.trim()) || "or".equalsIgnoreCase(field.trim())) {
					log.error("orderBy[" + orderBy + "]字段不合法,不进行拼接");
					return;
				}
			}
			// 第二个关键字不是'asc'也不是'desc'时不合法
			if (items.length >= 2) {
				String type = items[1];
				if (!"asc".equalsIgnoreCase(type) && !"desc".equals(type)) {
					log.error("orderBy[" + orderBy + "]字段不合法,不进行拼接");
					return;
				}
			}
			formatedOrderBy.append(StringUtil.camelhumpToUnderline(items[0]));
			if (items.length > 1) {
				formatedOrderBy.append(" " + items[1]);
			}
			formatedOrderBy.append(",");
		}
		String newOrderBy = formatedOrderBy.toString();
		if (newOrderBy.endsWith(",")) {
			newOrderBy = newOrderBy.substring(0, newOrderBy.length() - 1);
		}
		this.formatedOrderBy = newOrderBy;
	}
}
