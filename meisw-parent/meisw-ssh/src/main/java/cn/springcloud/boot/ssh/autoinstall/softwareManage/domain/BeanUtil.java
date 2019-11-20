package cn.springcloud.boot.ssh.autoinstall.softwareManage.domain;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.util.ClassUtils;

/**
 * bean copy.
 * 
 * @author tangxb
 * @since 2.1.1
 */
public class BeanUtil {

    /**
     * 对象copy
     * 
     * @param source
     * @param target
     * @return
     */
    public final static <T> T beanCopy(Object source, T target) {
        if (Objects.nonNull(target) && Objects.nonNull(source)) {
            BeanCopier beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
            beanCopier.copy(source, target, null);
            return target;
        }
        return null;
    }

    /**
     * 对象copy
     * 
     * @param source
     * @param target
     * @return
     */
    @SuppressWarnings("unchecked")
    public final static <T> T beanCopy(Object source, Class<T> target) {
        if (Objects.nonNull(target) && Objects.nonNull(source)) {
            BeanCopier beanCopier = BeanCopier.create(source.getClass(), target, false);
            T t = (T) newInstance(target);
            beanCopier.copy(source, t, null);
            return t;
        }
        return null;
    }

    /**
     * 集合对象copy
     * 
     * @param source
     * @param target
     * @return
     */
    @SuppressWarnings("unchecked")
    public final static <T> Collection<? extends Object> beansCopy(Collection<? extends Object> source,
            Class<?> target) {
        if (Objects.isNull(source) || Objects.isNull(target))
            return null;
        if (source.isEmpty())
            return Collections.emptyList();

        BeanCopier beanCopier = BeanCopier.create(source.iterator().next().getClass(), target, false);
        List<T> list = new ArrayList<T>(source.size());
        source.forEach(s -> {
            T t = (T) newInstance(target);
            beanCopier.copy(s, t, null);
            list.add(t);
        });
        return list;
    }

    /**
     * 实例对象
     * 
     * @param classt
     * @return
     */
    public final static Object newInstance(Class<?> classt) {
        try {
            return classt.newInstance();
        } catch (Exception e) {
            LoggerFactory.getLogger(BeanUtil.class).error("Class[" + classt + "]实例化出现异常!", e);
        }
        return null;
    }
    
    /**
     * 实例对象
     * 
     * @param className
     * @return
     */
    @SuppressWarnings("unchecked")
    public final static <T> T newInstance(String className) {
        try {
            Class<T> clazz = (Class<T>) Class.forName(className);
            return clazz.newInstance();
        } catch (Exception e) {
            LoggerFactory.getLogger(BeanUtil.class).error("Class[" + className + "]实例化出现异常!", e);
        }
        return null;
    }
    

    /**
     * 安全的设置字段值
     * 
     * @param object
     * @param field
     * @param value
     */
    public final static void setObjectFieldValue(Object object, Field field, Object value) {
        boolean isAccessible = field.isAccessible();
        try {
            if (!isAccessible) {
                field.setAccessible(true);
            }
            field.set(object, value);
        } catch (Exception e) {

        } finally {
            if (!isAccessible) {
                field.setAccessible(false);
            }
        }
    }

    /**
     * 安全的获取对象字段值
     * 
     * @param object
     * @param field
     * @return
     */
    public final static Object getObjectFieldValue(Object object, Field field) {
        boolean isAccessible = field.isAccessible();
        try {
            if (!isAccessible) {
                field.setAccessible(true);
            }
            return field.get(object);
        } catch (Exception e) {

        } finally {
            if (!isAccessible) {
                field.setAccessible(false);
            }
        }

        return null;
    }

    /**
     * 当object为指定对象时获取指定属性
     * 
     * @param object
     * @param clazz
     * @param name
     * @return
     */
    public final static Object getObjectFieldValue(Object object, String clazzName, String fieldName) {
        try {
            Class<?> clazz = ClassUtils.forName(clazzName, ClassUtils.getDefaultClassLoader());
            // 只有为指定对象获取field
            if (ClassUtils.isAssignableValue(clazz, object)) {
                // 获取私有的field
                Field field = object.getClass().getDeclaredField(fieldName);
                return getObjectFieldValue(object, field);
            }
        } catch (Throwable e) {
            // do nothing
        }

        return null;
    }
}
