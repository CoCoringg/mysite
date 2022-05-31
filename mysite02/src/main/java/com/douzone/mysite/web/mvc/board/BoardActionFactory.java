package com.douzone.mysite.web.mvc.board;

import com.douzone.web.mvc.Action;
import com.douzone.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("add".equals(actionName)) {
			action = new AddAction();
		} else if("addform".equals(actionName)) {
			action = new AddFormAction();
		} else if("view".equals(actionName)) {
			action = new ViewAction();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
