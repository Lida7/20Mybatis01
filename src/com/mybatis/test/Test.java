package com.mybatis.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.vo.User;

public class Test {
	
	/**
	 * MyBatis使用方法
	 * 1、到官网下载相关包 http://www.mybatis.org/mybatis-3/
	 * 2、创建xml的配置文件，详细配置可以在官网找到，直接copy过来用就好了（建议引入dtd）
	 * 	 2.1 ：需要配置mapper，即是写sql的那个文件，写上对应的路径即可
	 * 		     例如：<mapper resource="com/mybatis/vo/userMapper.xml"/>
	 * 	 2.2：配置别名，可以再映射文件中，直接用别名，就不用写全类名了
	 * 		   例子：<typeAliases><typeAlias type="com.mybatis.vo.User" alias="User"/></typeAliases>
	 * 	 2.3：可以通过 properties引入外部文件 然后用${xxx}的方式写到xml中去
	 * 3、Mapper的使用
	 * 	 3.0 格式可以在官网找到，直接copy过来用就好了（建议引入dtd）
	 * 	 3.1 命名空间： <mapper namespace="com.mybatis.vo.User">
	 * 	 3.2 增删该查标签：insert/update/delete/select 然后再标签里面写上对应语句
	 * 	 3.3parameterType/resultType 需要写上全类名,或者可以用2.2的方式使用别名
	 * 	 3.4占位符#{xxxx} 如果是对象，则xxx为对象的属性名
	 * 4、获取SqlSessionFactory/SqlSession
	 * 	 4.1通过类加载器获取xml的配置文件
	 * 	 4.2 通过 new SqlSessionFactoryBuilder().build(is)获得sqlsessionfactory
	 * 	 4.3 通过 sessionFactory.openSession() 获得session
	 * 	 4.4调用mapper
	 * 		mybatis通过session调用 insert(xxxx)/update(xxxx)/delete(xxxx)/selectOne(xxx)/selectList(xxx)执行方法
	 * 		其中xxx 需要填写3.1的 命名空间和 调用标签的id  格式  命名空间.标签id
	 * 5、mybatis默认是手动commit，所以执行完sql之后，需要手动commit/close
	 * 	    可以在session.openSession(true);设置自动提交
	 * 
	 * 6、额外知识（二级缓存）这个只做了下了解，日后需要再深入研究吧
	 * 	注意使用二级缓存的mapper的对象需要实现序列化
	 * 	 只需要再mapper文件中加入  <cache />标签即可
	 * 	 这样即使用两个 session去查询（必须分别提交事务）， 当数据没有更新时做查询，则不会再次进行物理查询（即不会到数据库执行两次）
	 * 	一般使用在那些数据不频繁更新的表 或者对数据准确性没有那么严格要求的情况中
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		//通过类加载器获取xml的配置文件
		InputStream is = Test.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
		
		System.out.println(is);
		
		SqlSessionFactory sessionFactory =  new SqlSessionFactoryBuilder().build(is);
		
		System.out.println(sessionFactory);
		
		SqlSession session = sessionFactory.openSession();
		
		System.out.println(session);
		
		
		String nameSpace = "com.mybatis.vo.User";
		
		String addValue = nameSpace+".addUser";
		/*int row = 0;
		row = session.insert(addValue,new User(0, "Tester2", 1));
		System.out.println(row);
		
		String updateValue = nameSpace+".updateUser";
		row = session.update(updateValue,new User(1, "Tester1", 1));
		System.out.println(row);
		*/
		
		
		String selectValue = nameSpace+".selectUser";
		User user = session.selectOne(selectValue,1);
		System.out.println(user);
		
		String selectAllValue = nameSpace+".selectAllUser";
		List<User> users = session.selectList(selectAllValue);
		System.out.println(users);
		
		String selectCountValue = nameSpace+".selectCount";
		long count = session.selectOne(selectCountValue);
		System.out.println(count);
		
		
		session.commit();
		session.close();
	}
}
