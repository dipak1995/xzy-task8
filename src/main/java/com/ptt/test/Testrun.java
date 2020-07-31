package com.ptt.test;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Testrun {

    //private static Logger logger = Logger.getLogger(Testrun.class);
    public static void main(String[] args){

        System.setProperty("java.rmi.server.hostname","49.235.187.63");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");


        //context.getBean("serverTest1");
        //logger.info("RMI启动就绪1");
        System.out.println("RMI启动就绪1");

        //context.getBean("serverTest2");
        //System.out.println("RMI启动就绪2");
    }
}
