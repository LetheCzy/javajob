package czy.site.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import czy.site.model.myjob;

@Repository
public interface myjobMapper {
	
	myjob selectById(int id);
	
	List<myjob> selectByCondition(myjob model);
	
	int deleteByPrimaryKey(int id);
	
	int insert(myjob model);
	
	int updateByPrimaryKeySelective(myjob model);
	
	int selectExsitByNameAndId(@Param("jobName") String jobName,@Param("id") int id);
}
