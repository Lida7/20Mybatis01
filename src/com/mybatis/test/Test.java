package com.mybatis.test;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.mybatis.vo.User;

public class Test {
	
	/**
	 * MyBatisʹ�÷���
	 * 1��������������ذ� http://www.mybatis.org/mybatis-3/
	 * 2������xml�������ļ�����ϸ���ÿ����ڹ����ҵ���ֱ��copy�����þͺ��ˣ���������dtd��
	 * 	 2.1 ����Ҫ����mapper������дsql���Ǹ��ļ���д�϶�Ӧ��·������
	 * 		     ���磺<mapper resource="com/mybatis/vo/userMapper.xml"/>
	 * 	 2.2�����ñ�����������ӳ���ļ��У�ֱ���ñ������Ͳ���дȫ������
	 * 		   ���ӣ�<typeAliases><typeAlias type="com.mybatis.vo.User" alias="User"/></typeAliases>
	 * 	 2.3������ͨ�� properties�����ⲿ�ļ� Ȼ����${xxx}�ķ�ʽд��xml��ȥ
	 * 3��Mapper��ʹ��
	 * 	 3.0 ��ʽ�����ڹ����ҵ���ֱ��copy�����þͺ��ˣ���������dtd��
	 * 	 3.1 �����ռ䣺 <mapper namespace="com.mybatis.vo.User">
	 * 	 3.2 ��ɾ�ò��ǩ��insert/update/delete/select Ȼ���ٱ�ǩ����д�϶�Ӧ���
	 * 	 3.3parameterType/resultType ��Ҫд��ȫ����,���߿�����2.2�ķ�ʽʹ�ñ���
	 * 	 3.4ռλ��#{xxxx} ����Ƕ�����xxxΪ�����������
	 * 4����ȡSqlSessionFactory/SqlSession
	 * 	 4.1ͨ�����������ȡxml�������ļ�
	 * 	 4.2 ͨ�� new SqlSessionFactoryBuilder().build(is)���sqlsessionfactory
	 * 	 4.3 ͨ�� sessionFactory.openSession() ���session
	 * 	 4.4����mapper
	 * 		mybatisͨ��session���� insert(xxxx)/update(xxxx)/delete(xxxx)/selectOne(xxx)/selectList(xxx)ִ�з���
	 * 		����xxx ��Ҫ��д3.1�� �����ռ�� ���ñ�ǩ��id  ��ʽ  �����ռ�.��ǩid
	 * 5��mybatisĬ�����ֶ�commit������ִ����sql֮����Ҫ�ֶ�commit/close
	 * 	    ������session.openSession(true);�����Զ��ύ
	 * 
	 * 6������֪ʶ���������棩���ֻ�������˽⣬�պ���Ҫ�������о���
	 * 	ע��ʹ�ö��������mapper�Ķ�����Ҫʵ�����л�
	 * 	 ֻ��Ҫ��mapper�ļ��м���  <cache />��ǩ����
	 * 	 ������ʹ������ sessionȥ��ѯ������ֱ��ύ���񣩣� ������û�и���ʱ����ѯ���򲻻��ٴν��������ѯ�������ᵽ���ݿ�ִ�����Σ�
	 * 	һ��ʹ������Щ���ݲ�Ƶ�����µı� ���߶�����׼ȷ��û����ô�ϸ�Ҫ��������
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		//ͨ�����������ȡxml�������ļ�
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
