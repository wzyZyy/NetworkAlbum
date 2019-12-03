package cn.zuel.wlyw.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseAdmin<M extends BaseAdmin<M>> extends Model<M> implements IBean {

	public void setAId(java.lang.Integer aId) {
		set("a_id", aId);
	}
	
	public java.lang.Integer getAId() {
		return getInt("a_id");
	}

	public void setAAccount(java.lang.String aAccount) {
		set("a_account", aAccount);
	}
	
	public java.lang.String getAAccount() {
		return getStr("a_account");
	}

	public void setAPwd(java.lang.String aPwd) {
		set("a_pwd", aPwd);
	}
	
	public java.lang.String getAPwd() {
		return getStr("a_pwd");
	}

	public void setAAddtime(java.util.Date aAddtime) {
		set("a_addtime", aAddtime);
	}
	
	public java.util.Date getAAddtime() {
		return get("a_addtime");
	}

	public void setAUpdatetime(java.util.Date aUpdatetime) {
		set("a_updatetime", aUpdatetime);
	}
	
	public java.util.Date getAUpdatetime() {
		return get("a_updatetime");
	}

}
