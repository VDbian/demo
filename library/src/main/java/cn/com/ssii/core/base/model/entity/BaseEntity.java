package cn.com.ssii.core.base.model.entity;

/**
 * Description:网络请求返回数据实体类泛型
 * Author chencheng
 * Time 2018/12/14
 */
public class BaseEntity<T> {
    protected int code;
    protected String message;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
