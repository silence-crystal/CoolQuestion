package com.example.czz.coolquestion.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2017/3/1.
 */

public class Language {

    /**
     * result : success
     * typeList : [{"typeDescribe":"1","typeId":1,"typeName":"JAVA","typePic":""},{"typeDescribe":"1","typeId":2,"typeName":"C语言","typePic":""},{"typeDescribe":"1","typeId":3,"typeName":"C++","typePic":""},{"typeDescribe":"1","typeId":4,"typeName":"Android","typePic":""},{"typeDescribe":"1","typeId":5,"typeName":"Object-C","typePic":""},{"typeDescribe":"1","typeId":6,"typeName":"PHP","typePic":""},{"typeDescribe":"1","typeId":7,"typeName":".net","typePic":"image/food6.PNG"}]
     */

    private String result;
    private List<TypeListBean> typeList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<TypeListBean> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<TypeListBean> typeList) {
        this.typeList = typeList;
    }

    public static class TypeListBean implements Serializable {
        /**
         * typeDescribe : 1
         * typeId : 1
         * typeName : JAVA
         * typePic :
         */

        private String typeDescribe;
        private int typeId;
        private String typeName;
        private String typePic;
        private boolean language_select;

        public String getTypeDescribe() {
            return typeDescribe;
        }

        public void setTypeDescribe(String typeDescribe) {
            this.typeDescribe = typeDescribe;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getTypePic() {
            return typePic;
        }

        public void setTypePic(String typePic) {
            this.typePic = typePic;
        }

        public boolean isLanguage_select() {
            return language_select;
        }

        public void setLanguage_select(boolean language_select) {
            this.language_select = language_select;
        }
    }
}
