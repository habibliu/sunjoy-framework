package com.sunjoy.framework.dao.interceptor;

public class PostgreSqlDialect extends Dialect {

	@Override
	public String getLimitSqlString(String sql, int skipResults, int maxResults) {
		sql = sql.replaceAll("[\r\n]", " ").replaceAll("\\s{2,}", " ")
				+ " limit " + maxResults + " offset " + skipResults;
		return sql;
	}

	@Override
	public String getCountSqlString(String sql) {
		return PostgreSqlHelper.getCountString(sql);
	}

}