package com.sunjoy.framework.dao.interceptor;

import java.util.HashSet;
import java.util.Set;

/**
 * 数据库方言
 * 
 */
public abstract class Dialect {
	/**
	 * 方言支持的数据库类型
	 */
	public static enum Type {
		MYSQL, ORACLE, POSTGRESQL, H2;

		private static final Set<String> SUPPORTED_TYPE = new HashSet<String>();
		static {
			for (Type type : Type.values()) {
				SUPPORTED_TYPE.add(type.toString());
			}
		}

		public static Boolean supportingDatabaseType(String dbType) {
			return SUPPORTED_TYPE.contains(dbType.toUpperCase());
		}
	}

	public abstract String getLimitSqlString(String sql, int skipResults, int maxResults);

	public abstract String getCountSqlString(String sql);

}