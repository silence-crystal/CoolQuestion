package com.example.czz.coolquestion.bean;

/**
 * Created by Administrator on 2017/3/9.
 */

public class UserInfo {

    /**
     * result : success
     * userInfo : {"userAccount":"zyw","userAddress":"","userId":7,"userImg":"","userName":"","userPhone":"","userPwd":"123456","userQQ":""}
     */

    private String result;
    private UserInfoBean userInfo;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * userAccount : zyw
         * userAddress :
         * userId : 7
         * userImg :
         * userName :
         * userPhone :
         * userPwd : 123456
         * userQQ :
         */

        private String userAccount;
        private String userAddress;
        private int userId;
        private String userImg;
        private String userName;
        private String userPhone;
        private String userPwd;
        private String userQQ;

        public String getUserAccount() {
            return userAccount;
        }

        public void setUserAccount(String userAccount) {
            this.userAccount = userAccount;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserPwd() {
            return userPwd;
        }

        public void setUserPwd(String userPwd) {
            this.userPwd = userPwd;
        }

        public String getUserQQ() {
            return userQQ;
        }

        public void setUserQQ(String userQQ) {
            this.userQQ = userQQ;
        }
    }
}
