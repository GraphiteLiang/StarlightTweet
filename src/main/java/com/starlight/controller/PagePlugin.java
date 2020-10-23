package com.starlight.controller;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.*;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.springframework.stereotype.Component;

@Component
@Intercepts({@Signature(type= StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class PagePlugin implements Interceptor {
	public static final int count = 3;
	private int page=0;
	public PagePlugin() {
		
	}
	public Object intercept(Invocation invocation) throws Throwable {
		//通过StatementHandler获取执行的sql
	      StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
	      MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,
	    		  new DefaultObjectFactory(),
	    		  new DefaultObjectWrapperFactory(), 
	    		  new DefaultReflectorFactory());
	      MappedStatement mappedStatement = (MappedStatement)   
	    	       metaStatementHandler.getValue("delegate.mappedStatement");
	      if(!mappedStatement.getId().matches(".*Page") || page == -1) {
	    	  return invocation.proceed();
	      }
	      BoundSql boundSql = statementHandler.getBoundSql();
	      String sql = boundSql.getSql();
	      StringBuilder sb = new StringBuilder();
	      sb.append("select temp2.* from ( select temp.*, rownum row_id from ( ");  
	      sb.append(sql);  
	      sb.append(" ) temp where rownum <= ").append((page)*count);  
	      sb.append(") temp2 where temp2.row_id > ").append((page-1)*count);
	      metaStatementHandler.setValue("delegate.boundSql.sql", sb.toString());
	      return invocation.proceed();
	}
	public Object plugin(Object target) {
	    return Plugin.wrap(target, this);
	}
    public void setProperties(Properties properties) {
    	page = Integer.parseInt(properties.getProperty("page", "0"));
    }
}