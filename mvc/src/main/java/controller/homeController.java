package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.alibaba.fastjson.JSON;

import czy.site.model.JobStatus;
import czy.site.model.ProcessType;
import model.ListItem;
import model.ResponseModel;
import model.User;

import java.util.ArrayList;

import org.springframework.http.*;

@Controller
public class homeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "index";// view name
	}

	@RequestMapping(value = "/common/getjobstatuslist")
	@ResponseBody
	public ResponseModel<ArrayList<ListItem>> GetJobStatusList(String isInit) {
		ArrayList<ListItem> listItems = new ArrayList<ListItem>();
        if(isInit.equals("1"))
        {
            listItems.add(0,new ListItem("-选择-","-1"));
        }
        for(JobStatus item : JobStatus.values()) {
        	listItems.add(new ListItem(item.getName(),String.valueOf(item.getIndex())));
        }
        
        ResponseModel<ArrayList<ListItem>> obj = new ResponseModel<ArrayList<ListItem>>(listItems);
        //String t = JSON.toJSONString(obj);
        
        return obj;
	}

	@RequestMapping(value = "/common/getjoblevellist")
	@ResponseBody
	public ResponseModel<ArrayList<ListItem>> GetJobLevelList(String isInit) {
		ArrayList<ListItem> listItems = new ArrayList<ListItem>();
        if(isInit.equals("1"))
        {
            listItems.add(0,new ListItem("-选择-","-1"));
        }
        for(ProcessType item : ProcessType.values()) {
        	listItems.add(new ListItem(item.getName(),String.valueOf(item.getIndex())));
        }
        
        ResponseModel<ArrayList<ListItem>> obj = new ResponseModel<ArrayList<ListItem>>(listItems);
        //String t = JSON.toJSONString(obj);
        
        return obj;
	}
}
