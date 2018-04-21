package czy.site.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import czy.site.dao.myjobMapper;
import czy.site.model.myjob;

@Service
public class jobservice {

	@Autowired
	private myjobMapper myjobmapper;
	
    public myjob selectById(int id) {
    	return myjobmapper.selectById(id);
    }

	public List<myjob> selectByCondition(myjob model){
		List<myjob> t = myjobmapper.selectByCondition(model);
		return t;
	}

	public int deleteByPrimaryKey(int id){
		return myjobmapper.deleteByPrimaryKey(id);
	}

	public int insert(myjob model){
		return myjobmapper.insert(model);
	}

	public int updateByPrimaryKeySelective(myjob model){
		return myjobmapper.updateByPrimaryKeySelective(model);
	}
	
	public int selectExsitByNameAndId(String jobName,int id){
		return myjobmapper.selectExsitByNameAndId(jobName, id);
	}
}
