package cn.yfjz.core.sys.service.impl;

import cn.yfjz.core.sys.domain.CodeKind;
import cn.yfjz.core.sys.service.CodeKindService;
import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Pager;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Query;
import com.avaje.ebean.SqlRow;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CodeKindServiceImpl implements CodeKindService{
	@Autowired
    private EbeanServer ebeanServer;

	public List codeKindTree(String id){
		List resultList = new ArrayList();
		if (StringUtils.isEmpty(id)){
			resultList.add(getRootNode());
		}else if (("#"+CodeConstant.CODEKIND_ROOT_ID).equals(id)){
			String sql = "select distinct code_group as code_group from p_code_kind order by code_group desc";
			List<SqlRow> list = ebeanServer.createSqlQuery(sql.toString()).findList();
			for(int i=0; i<list.size(); i++){
				resultList.add(getKindNode(list.get(i)));
			}
		}else {
			List<CodeKind> children = ebeanServer.find(CodeKind.class).where().eq("code_group", id).orderBy("id").findList();
			if(children!=null && children.size()>0){
				for(CodeKind child : children){
					resultList.add(getKindChildNode(child));
				}
			}
		}
		return  resultList;
	}
	private  Map getRootNode(){
		Map rootMap = new HashMap();
		rootMap.put("id", "#"+CodeConstant.CODEKIND_ROOT_ID);
		rootMap.put("name", CodeConstant.CODEKIND_ROOT_NAME);
		rootMap.put("isParent", true);
		return rootMap;
	}
	private Map getKindNode(SqlRow m){
		Map map=new HashMap();
		map.put("id", m.get("code_group"));
		map.put("name", m.get("code_group"));
		map.put("pId", "#"+CodeConstant.CODEKIND_ROOT_ID);
		map.put("isParent", true);
		return map;
	}
	private Map getKindChildNode(CodeKind kind){
		Map map=new HashMap();
		map.put("id", kind.getId());
		map.put("name", kind.getKindName());
		map.put("pId", kind.getCodeGroup());
		map.put("isParent", false);
		return map;
	}

	@Override
	public Pager queryPage(String pid, int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		Query<CodeKind> query = ebeanServer.find(CodeKind.class);
		if(StringUtils.isNotEmpty(pid)&& !pid.equals("#"+CodeConstant.CODEKIND_ROOT_ID)){
			query.where().eq("code_group", pid);
		}
        int count = query.findRowCount();
        List<CodeKind> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).orderBy("id").findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}

	@Override
	public void add(CodeKind dto) {
		ebeanServer.insert(dto);
	}

	@Override
	public void edit(CodeKind dto) {
		ebeanServer.update(dto);
	}

	@Override
	public void remove(String[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
			for(String id:ids){
				ebeanServer.delete(CodeKind.class, id);
			}
		}
	}

	@Override
	public CodeKind findById(String id) {
		return ebeanServer.find(CodeKind.class,id);
	}

}
