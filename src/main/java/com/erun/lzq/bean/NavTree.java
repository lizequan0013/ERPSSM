package com.erun.lzq.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavTree {
	
	//	id：节点的 id，它对于加载远程数据很重要。
	//	text：要显示的节点文本。
	//	state：节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
	//	checked：指示节点是否被选中。
	//	attributes：给一个节点添加的自定义属性。
	//	children：定义了一些子节点的节点数组
	
	private String id;
	private String text;
	private String state;
	private Boolean checked;
	private Map<String ,Object> attributes = new HashMap<String, Object>();;
	private List<NavTree> children  =new ArrayList<NavTree>();
	
	public void clear() {
		this.id = null;
		this.text = null;
		this.state = null;
		this.checked = null;
		this.attributes.clear();
		this.children.clear();
         
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setStateOpen() {
		this.state = "open";
	}
	public void setStateClose() {
		this.state = "close";
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public void addAttributes(String key,Object value) {
		getAttributes().put(key, value);
	}
	
	public List<NavTree> getChildren() {
		return children;
	}
	public void setChildren(List<NavTree> children) {
		this.children = children;
	}
	public void addChildren(NavTree navTree) {
		getChildren().add(navTree);
	}
	
	@Override
	public String toString() {
		return "NavTree [id=" + id + ", text=" + text + ", state=" + state + ", checked=" + checked + ", attributes="
				+ attributes + ", children=" + children + "]";
	}
}
