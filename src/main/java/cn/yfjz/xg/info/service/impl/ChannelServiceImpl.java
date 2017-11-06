package cn.yfjz.xg.info.service.impl;

import cn.yfjz.core.util.CodeConstant;
import cn.yfjz.core.util.Expression;
import cn.yfjz.core.util.Pager;
import cn.yfjz.core.util.QueryUtils;
import cn.yfjz.xg.domain.Channel;
import cn.yfjz.xg.info.service.ChannelService;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.OrderBy;
import com.avaje.ebean.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuanye
 * @Date：2016年8月22日 
 * @Time：下午4:19:40 
 */
@Service
public class ChannelServiceImpl implements ChannelService {
    @Autowired
    private EbeanServer ebeanServer;

	@Override
	public Channel findById(Long id) {
		return ebeanServer.find(Channel.class,id);
	}

	@Override
	public List<Map<String, Object>> tree(String pId) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
		if(StringUtils.isEmpty(pId)){
			Channel root = ebeanServer.find(Channel.class, CodeConstant.CHANNEL_ROOT_DEFAULT_ID);
			Map<String,Object> rootMap = new HashMap<String,Object>();
			if(null!=root){
				rootMap.put("id", root.getId());
				rootMap.put("name", root.getName());
			}else{
				rootMap.put("id",CodeConstant.CHANNEL_ROOT_DEFAULT_ID);
				rootMap.put("name",CodeConstant.CHANNEL_ROOT_DEFAULT_ID);
			}
			rootMap.put("isParent", "true");
			resultList.add(rootMap);
		}else{
			List<Expression> params = new ArrayList<Expression>();
            params.add(new Expression("father_id","=",pId));
            Query<Channel> query = QueryUtils.getQuery(Channel.class, ebeanServer, "find channel", params);
            OrderBy<Channel> orderBy = new OrderBy<Channel>("priority");
            List<Channel> children = query.setOrder(orderBy).findList();
            if(children!=null && children.size()>0){
				for(Channel child : children){
					resultList.add(changeToMap(child));
				}
			}
		}
		return resultList;
	}

	private Map<String,Object> changeToMap(Channel channel){
		Map<String,Object> channelMap=new HashMap<String,Object>();
		channelMap.put("id", channel.getId());
		channelMap.put("name", channel.getName());
		channelMap.put("pId", channel.getParent().getId());
		List<Expression> params = new ArrayList<Expression>();
		params.add(new Expression("father_id","=",channel.getId()));
		Query<Channel> query = QueryUtils.getQuery(Channel.class, ebeanServer, "find channel", params);
        int count = query.findRowCount();
		if(count>0){
			channelMap.put("isParent", "true");
		}
		return channelMap;
	}
	
	@Override
	public Pager queryPage(Map<String,Object> paramMap,Long pId,int pageNum, int pageSize) {
		Pager page = new Pager(pageNum, pageSize);
		List<Expression> params = new ArrayList<Expression>();
		if(pId == null)
			pId = Long.parseLong(CodeConstant.CHANNEL_ROOT_DEFAULT_ID);
        params.add(new Expression("father_id","=",pId));
        for (String key : paramMap.keySet()) {
        	params.add(new Expression(key," like ","%"+paramMap.get(key)+"%"));
        }
        Query<Channel> query = QueryUtils.getQuery(Channel.class, ebeanServer, "find channel", params);
        int count = query.findRowCount();
        OrderBy<Channel> orderBy = new OrderBy<Channel>("priority");
        List<Channel> list = query.setFirstRow(page.getStart()).setMaxRows(pageSize).setOrder(orderBy).findList();
        page.setRecords(count);
        page.setRows(list);
        return page;
	}

	@Override
	public void add(Channel dto) {
        ebeanServer.insert(dto);
	}
	
	@Override
	public void edit(Channel dto) {
		ebeanServer.update(dto);
	}
	
	@Override
	public void remove(String[] ids) {
		if(ArrayUtils.isNotEmpty(ids)){
            for(String id:ids){
                Long tempId = Long.parseLong(id);
                ebeanServer.delete(Channel.class, tempId);
            }
        }
	}

	@Override
	public List getChannels() {
		StringBuffer sql = new StringBuffer(); 
		sql.append("select ch.* from xg_channel ch where ch.id in（select co.channel_id from xg_content co）");
		List list = ebeanServer.createSqlQuery(sql.toString()).findList();
		return list ;
	}
}
