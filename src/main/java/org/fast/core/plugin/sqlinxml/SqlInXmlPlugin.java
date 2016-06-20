package org.fast.core.plugin.sqlinxml;

import com.jfinal.plugin.IPlugin;

public class SqlInXmlPlugin implements IPlugin {

	public boolean start() {
		try {
			SqlManager.parseSqlXml();
		} catch (Exception e) {
			new RuntimeException(e);
		}
		return true;
	}

	public boolean stop() {
		SqlManager.clearSqlMap();
		return true;
	}

}
