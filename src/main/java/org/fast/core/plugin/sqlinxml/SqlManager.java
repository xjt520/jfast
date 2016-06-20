package org.fast.core.plugin.sqlinxml;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class SqlManager {
	private static Map<String, String> sqlMap = new HashMap<String, String>();
	/***
	 * 从sql.xml中获取对应的Sqlgroup下的sql
	 * @param groupNameAndsqlId 如groupname.sqlid
	 * @return
	 */
	public static String sql(String groupNameAndsqlId) {
		return sqlMap.get(groupNameAndsqlId);
	}

	static void clearSqlMap() {
		sqlMap.clear();
	}

	static void parseSqlXml() {
		File file = new File(SqlManager.class.getClassLoader().getResource("sql").getFile());
		File[] files = file.listFiles(new FileFilter() {
			public boolean accept(File pathname) {
				if (pathname.getName().endsWith("sql.xml")) {
					return true;
				}
				return false;
			}
		});
		for (File xmlfile : files) {
			SqlGroup group = null;
			try {
				JAXBContext context = JAXBContext.newInstance(SqlGroup.class);
				Unmarshaller unmarshaller = context.createUnmarshaller();
				group = (SqlGroup) unmarshaller.unmarshal(xmlfile);
			} catch (JAXBException e) {
				throw new RuntimeException(e);
			}
			String name = group.name;
			if (name == null || name.trim().equals("")) {
				name = xmlfile.getName();
			}
			for (SqlItem sqlItem : group.sqlItems) {
				System.err.println("正在初始化sql**{"+name + "." + sqlItem.id+":"+sqlItem.value+"}");
				sqlMap.put(name + "." + sqlItem.id, sqlItem.value);
			}
		}
	}

	public static void main(String[] args) {
		parseSqlXml();
		System.out.println(sqlMap);
	}

}