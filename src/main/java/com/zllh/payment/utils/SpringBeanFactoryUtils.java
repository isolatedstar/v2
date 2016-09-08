package com.zllh.payment.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Service;
@Service
public   class  SpringBeanFactoryUtils  implements  BeanFactoryAware {  
  
	private static BeanFactory beanFactory = null;  
    private static SpringBeanFactoryUtils factoryUtils = null;  
      
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {  
        SpringBeanFactoryUtils.beanFactory = beanFactory;  
    }  
    public static BeanFactory getBeanFactory() {  
        return beanFactory;  
    }  
    public static SpringBeanFactoryUtils getInstance(){  
        if(factoryUtils==null){  
            //factoryUtils = (SpringBeanFactoryUtils)beanFactory.getBean("springBeanFactoryUtils");  
            factoryUtils = new SpringBeanFactoryUtils();  
        }  
        return factoryUtils;  
    }  
    public static Object getBean(String name){  
        return beanFactory.getBean(name);  
    }
}  