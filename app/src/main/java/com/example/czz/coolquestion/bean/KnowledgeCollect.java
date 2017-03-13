package com.example.czz.coolquestion.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 * 知识点收藏实体类
 */

public class KnowledgeCollect {

    /**
     * knowledgeCollectList : [{"knowledge":{"knowledgecontent":"java函数的使用","knowledgedescribe":"函 数：为了提高代码的复用性，可以将其定义成一个单独的功能，该功能的体现就是java中的函数。函数就是体现之一。 java中的函数的定义格式： 修饰符 返回值类型 函数名(参数类型 形式参数1，参数类型 形式参数1，\u2026){ 执行语句； return 返回值； } 当函数没有具体的返回值时，返回的返回值类型用void关键字表示。 如果函数的返回值类型是void时，return语句可以省略不写的，系统会帮你自动加上。 return的作用：结束函数。结束功能。 如何定义一个函数？ 函数其实就是一个功能，定义函数就是实现功能，通过两个明确来完成： 1）、明确该功能的运算完的结果，其实是在明确这个函数的返回值类型。 2）、在实现该功能的过程中是否有未知内容参与了运算，其实就是在明确这个函数的参数列表(参数类型&参数个数)。 函数的作用： 1）、用于定义功能。 2）、用于封装代码提高代码的复用性。 注意：函数中只能调用函数，不能定义函数。 主函数： 1）、保证该类的独立运行。 2）、因为它是程序的入口。 3）、因为它在被jvm调用。 函数定义名称是为什么呢？ 答：1）、为了对该功能进行标示，方便于调用。 2）、为了通过名称就可以明确函数的功能，为了增加代码的阅读性。 重载的定义是：在一个类中，如果出现了两个或者两个以上的同名函数，只要它们的参数的个数，或者参数的类型不同，即可称之为该函数重载了。 如何区分重载：当函数同名时，只看参数列表。和返回值类型没关系。","knowledgeid":19,"knowledgetitle":"java函数","tid":1},"knowledgecollectid":1}]
     * result : success
     */

    private String result;
    private List<KnowledgeCollectListBean> knowledgeCollectList;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<KnowledgeCollectListBean> getKnowledgeCollectList() {
        return knowledgeCollectList;
    }

    public void setKnowledgeCollectList(List<KnowledgeCollectListBean> knowledgeCollectList) {
        this.knowledgeCollectList = knowledgeCollectList;
    }

    public static class KnowledgeCollectListBean {
        /**
         * knowledge : {"knowledgecontent":"java函数的使用","knowledgedescribe":"函 数：为了提高代码的复用性，可以将其定义成一个单独的功能，该功能的体现就是java中的函数。函数就是体现之一。 java中的函数的定义格式： 修饰符 返回值类型 函数名(参数类型 形式参数1，参数类型 形式参数1，\u2026){ 执行语句； return 返回值； } 当函数没有具体的返回值时，返回的返回值类型用void关键字表示。 如果函数的返回值类型是void时，return语句可以省略不写的，系统会帮你自动加上。 return的作用：结束函数。结束功能。 如何定义一个函数？ 函数其实就是一个功能，定义函数就是实现功能，通过两个明确来完成： 1）、明确该功能的运算完的结果，其实是在明确这个函数的返回值类型。 2）、在实现该功能的过程中是否有未知内容参与了运算，其实就是在明确这个函数的参数列表(参数类型&参数个数)。 函数的作用： 1）、用于定义功能。 2）、用于封装代码提高代码的复用性。 注意：函数中只能调用函数，不能定义函数。 主函数： 1）、保证该类的独立运行。 2）、因为它是程序的入口。 3）、因为它在被jvm调用。 函数定义名称是为什么呢？ 答：1）、为了对该功能进行标示，方便于调用。 2）、为了通过名称就可以明确函数的功能，为了增加代码的阅读性。 重载的定义是：在一个类中，如果出现了两个或者两个以上的同名函数，只要它们的参数的个数，或者参数的类型不同，即可称之为该函数重载了。 如何区分重载：当函数同名时，只看参数列表。和返回值类型没关系。","knowledgeid":19,"knowledgetitle":"java函数","tid":1}
         * knowledgecollectid : 1
         */

        private KnowledgeBean knowledge;
        private int knowledgecollectid;

        public KnowledgeBean getKnowledge() {
            return knowledge;
        }

        public void setKnowledge(KnowledgeBean knowledge) {
            this.knowledge = knowledge;
        }

        public int getKnowledgecollectid() {
            return knowledgecollectid;
        }

        public void setKnowledgecollectid(int knowledgecollectid) {
            this.knowledgecollectid = knowledgecollectid;
        }

        public static class KnowledgeBean implements Serializable {
            /**
             * knowledgecontent : java函数的使用
             * knowledgedescribe : 函 数：为了提高代码的复用性，可以将其定义成一个单独的功能，该功能的体现就是java中的函数。函数就是体现之一。 java中的函数的定义格式： 修饰符 返回值类型 函数名(参数类型 形式参数1，参数类型 形式参数1，…){ 执行语句； return 返回值； } 当函数没有具体的返回值时，返回的返回值类型用void关键字表示。 如果函数的返回值类型是void时，return语句可以省略不写的，系统会帮你自动加上。 return的作用：结束函数。结束功能。 如何定义一个函数？ 函数其实就是一个功能，定义函数就是实现功能，通过两个明确来完成： 1）、明确该功能的运算完的结果，其实是在明确这个函数的返回值类型。 2）、在实现该功能的过程中是否有未知内容参与了运算，其实就是在明确这个函数的参数列表(参数类型&参数个数)。 函数的作用： 1）、用于定义功能。 2）、用于封装代码提高代码的复用性。 注意：函数中只能调用函数，不能定义函数。 主函数： 1）、保证该类的独立运行。 2）、因为它是程序的入口。 3）、因为它在被jvm调用。 函数定义名称是为什么呢？ 答：1）、为了对该功能进行标示，方便于调用。 2）、为了通过名称就可以明确函数的功能，为了增加代码的阅读性。 重载的定义是：在一个类中，如果出现了两个或者两个以上的同名函数，只要它们的参数的个数，或者参数的类型不同，即可称之为该函数重载了。 如何区分重载：当函数同名时，只看参数列表。和返回值类型没关系。
             * knowledgeid : 19
             * knowledgetitle : java函数
             * tid : 1
             */

            private String knowledgecontent;
            private String knowledgedescribe;
            private int knowledgeid;
            private String knowledgetitle;
            private int tid;

            public String getKnowledgecontent() {
                return knowledgecontent;
            }

            public void setKnowledgecontent(String knowledgecontent) {
                this.knowledgecontent = knowledgecontent;
            }

            public String getKnowledgedescribe() {
                return knowledgedescribe;
            }

            public void setKnowledgedescribe(String knowledgedescribe) {
                this.knowledgedescribe = knowledgedescribe;
            }

            public int getKnowledgeid() {
                return knowledgeid;
            }

            public void setKnowledgeid(int knowledgeid) {
                this.knowledgeid = knowledgeid;
            }

            public String getKnowledgetitle() {
                return knowledgetitle;
            }

            public void setKnowledgetitle(String knowledgetitle) {
                this.knowledgetitle = knowledgetitle;
            }

            public int getTid() {
                return tid;
            }

            public void setTid(int tid) {
                this.tid = tid;
            }
        }
    }
}
