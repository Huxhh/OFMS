package org.classsix.ofms.common;

import org.classsix.ofms.status.inter.StatusBase;

/**
 * Created by huxh on 2017/5/1.
 */
public class ResponseMessage {
    private int code;
    private String msg;
    private Object body;

    public ResponseMessage() {
    }

    public ResponseMessage(int code, String msg, Object body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public ResponseMessage(StatusBase statusBase, Object object) {
        this.code = statusBase.getStatus();
        this.msg = statusBase.name();
        this.body = object;
    }

    public ResponseMessage(StatusBase statusBase) {
        this.code = statusBase.getStatus();
        this.msg = statusBase.name();
        this.body = null;
    }


    public ResponseMessage(Builder builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.body = builder.body;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getBody() {
        return body;
    }

    public static class Builder {
        private int code = 0;
        private String msg = "请求成功！";
        private Object body = null;

        public Builder code(int code) {
            this.code = code;
            return this;
        }

        public Builder msg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder body(Object body) {
            this.body = body;
            return this;
        }

        public ResponseMessage build() {
            return new ResponseMessage(this);
        }

    }

    @Override
    public String toString() {
        return "ResponseMessage{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", body=" + body +
                '}';
    }
}
