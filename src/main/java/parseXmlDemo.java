import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用DOM4J解析XML文件
 */
public class parseXmlDemo {
    public static void main(String[] args) {
        /*
        将xmlDeo.xml文件中所有员工信息读取出来
         */
        List<Emp>list=new ArrayList<>();
        /**
         * 使用dom4j解析XML的大致步骤:
         * 1：创建SAXReader对象
         * 2：使用SAXReader读取xml文档并生成Document对象
         *    这一步也是dom解析耗时耗资源的地方，因为要先将稳当所有数据读取完毕，
         *    并且以一个Document对象形式保存在内存中
         * 3、通过Document对象获取根元素
         * 4、按照XML文档结构从根元素开始逐级获取子元素以达到遍历XML文档数据的目的
         */
        try {
            //1、创建SAXReader对象
            SAXReader reader=new SAXReader();
            //2、使用SAXReader读取xml文档并生成Document对象
            Document doc=reader.read(new FileInputStream
                    ("xmlDeo.xml"));
            /**
             * 3、Document提供了获取根元素的方法：
             *
             *    Element getRootElement();
             *
             *    而Element的每一个实例用于表示当前xml文档中的一个元素(一对标签)
             *    它提供了获取其表示的元素的相关信息的方法：
             * (1)String getName();获取当前标签的名字
             *
             * (2)String getText();获取当前标签中间的文本
             *
             * (3)Element element(String name);获取当前标签下指定名字的字标签
             *
             * (4)List element();获取当前标签下指定名字的所有同名字标签
             *
             * (5)Attribute attribute(String name);获取当前标签下指定名字的属性
             *    Attribute的每一个实例表示一个属性，它有两个常用方法：
             *    String getName();获取属性名
             *    String getValue();获取属性值
             *
             *
             */
            Element root=doc.getRootElement();
            /**
             * 获取根标签<list>下的所有员工标签<emp>
             */
            List<Element>empList=root.elements("emp");
            /**
             * 遍历获取每个员工信息
             */
            for(Element element:empList){
                //获取名字<name>标签
                Element nameEle=element.element("name");
                //获取<name>标签中间的文本
                String name=nameEle.getText();
                //获取年龄<age>标签
                Element ageEle=element.element("age");
                //获取<age>标签中间的文本
                int age=Integer.parseInt(ageEle.getText());
                //获取性别<gender>标签中间的文本
                String gender=element.elementText("gender");
                //获取工资<salary>标签中间的文本
                int salary=Integer.parseInt(element.elementText("salary"));
                //获取ID<id>标签中间的文本
                int id=Integer.parseInt(element.attributeValue("id"));
                //实例化emp对象，传入id,name,age,gender,salary参数
                Emp emp=new Emp(id,name,age,gender,salary);
                //打印emp对象
                System.out.println(emp);
                //将emp加入list集合中
                list.add(emp);
            }
            System.out.println("解析完毕!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

