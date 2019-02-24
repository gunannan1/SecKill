package com.gnn.seckill.enums;

public class MessageStatus {

    public static  final Integer ZORE = 0;
    /**
     * 消息类型
     */
    public  enum  messageType {
        maiosha_message(0,"秒杀消息"),
        buy_message(1,"购买消息"),
        system_message(2,"系统消息");

        private String message;
        private int type;

        private messageType(int type,String message){
            this.type=type;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public int getType(){
            return type;
        }
    }

    /**
     * 消息内容
     */
    public  enum  ContentEnum {
        system_message_register(7000,"尊敬的用户你好，你已经成功注册！");

        private int code;
        private String message;

        private ContentEnum(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }


}
