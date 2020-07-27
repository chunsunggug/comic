package com.project.comic.book;

import java.util.Map;

public abstract class AbstractQueryModel {
	public abstract String toURLParam();
	public abstract void setMapParam(Map map_param);
}
