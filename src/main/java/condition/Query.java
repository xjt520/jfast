package condition;

import java.io.Serializable;
import java.util.List;

public class Query implements Serializable {
	
	private static final long serialVersionUID = 3485942287242734854L;

	public Query() {
	}

	public Query(String sql, List<Object> paramList) {
		this.paramList = paramList;
		this.sql = sql;
	}

	private String sql ;
	private List<Object> paramList;
	
	public List<Object> getParamList() {
		return paramList;
	}
	public void setParamList(List<Object> paramList) {
		this.paramList = paramList;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}

	@Override
	public String toString() {
		return "Query{" +
				"sql='" + sql + '\'' +
				", paramList=" + paramList +
				'}';
	}
}
