package com.haibo.futwo.web.datasource;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 动态数据源切面类</br> 通过在service包中的service接口或者接口方法标注(接口方法标注会覆盖接口标注)信息获取数据源
 *
 */
public class DataSourceAspect implements MethodBeforeAdvice {

    // private static Logger logger = Logger.getLogger(DataSourceAspect.class);

    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        if (method == null) {
            return;
        }
        DataSource dataSource = null;
        try {
            // 首先取接口标注，标注在接口、方法上
            if (target != null) {
                Class<?>[] infs = target.getClass().getInterfaces();
                if (infs != null && infs.length > 0) {
                    for (Class<?> inf : infs) {
                        // 如果接口上又标注，缓存该标注
                        if (inf.isAnnotationPresent(DataSource.class)) {
                            dataSource = inf.getAnnotation(DataSource.class);
                        }
                        // 如果接口方法上又标注，最终使用该标注，接口方法标注优先于接口标注
                        Method infMethodhod = inf.getMethod(method.getName(),
                                method.getParameterTypes());
                        if (infMethodhod.isAnnotationPresent(DataSource.class)) {
                            dataSource = infMethodhod
                                    .getAnnotation(DataSource.class);
                        }
                    }
                }
            }
            // 取方法标注，方法标注覆盖类标注
            if (method != null && method.isAnnotationPresent(DataSource.class)) {
                dataSource = method.getAnnotation(DataSource.class);
            }
        } catch (Exception e) {
            // logger.error("数据源读写分离异常：", e);
            e.printStackTrace();
        }
        if (dataSource != null) {
            DynamicDataSourceHolder.putDataSource(dataSource.value());
        }else {
        		DynamicDataSourceHolder.putDataSource(null);
		}
    }
}
