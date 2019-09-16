package com.css.aq.base;

import lombok.Data;
import net.sf.jsqlparser.expression.StringValue;
import org.omg.CORBA.OBJECT_NOT_EXIST;

import java.io.Serializable;
import java.util.Map;

/**
 *   接口返回数据格式
 */

@Data
public class Result<T> implements Serializable {


	private static final long serialVersionUID = 1L;


	/**  返回成功状态码200 OK*/
	public static String successCode = String.valueOf(Constant.SC_OK_200);
	/**  返回成功状态码200 OK*/
	public static String failCode = String.valueOf(Constant.SC_INTERNAL_SERVER_ERROR_500);


	public static String Msg ="操作成功";

	/**
	 * 返回数据对象 data
	 */
	public static Object data;

	public Result() {

	}

	/*public static Result success() {
		return new Result();
	}

	public static Result success(String msg) {
		Result r = new Result();
		r.setMsg(msg);
		return r;
	}

	public static Result success(Object data) {
		Result r = new Result();
		r.setData(data);
		return r;
	}

	/*public static Result error(String msg) {
		return error(String.valueOf(Constant.SC_INTERNAL_SERVER_ERROR_500), msg);
	}

	public static Result error(String code, String msg) {
		Result r = new Result();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}*/

}