package condition;

import java.io.Serializable;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;

public class JsonPage<T> implements Serializable {
	
	private static final long serialVersionUID = 3538073778301481122L;
	
	private int total;
	private int page;
	private List<T> rows;

    public JsonPage(Page<T> page){
        this.rows = page.getList();
        this.page = page.getPageNumber();
        this.total = page.getTotalRow();
    }

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
}
