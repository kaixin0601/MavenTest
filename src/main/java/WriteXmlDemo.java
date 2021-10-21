import org.dom4j.io.XMLWriter;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用DOM4J生成一个xml文档
 */
public class WriteXmlDemo {
    public static class WriteXmlDeo{
        public static void main(String[] args) {
            List<Emp> list=new ArrayList<Emp>();
            list.add(new Emp(1,"张三",22,"男",5000));
            list.add(new Emp(2,"李四",23,"女",6000));
            list.add(new Emp(3,"王五",24,"男",7000));
            list.add(new Emp(4,"赵六",25,"女",8000));
            list.add(new Emp(5,"钱七",26,"男",9000));


            /**
             * 生成XML的大致步骤：
             * 1：创建一个Document对象，表示一个空白文档
             * 2：向Document中天健根元素
             * 3：向按照XML文档结构从根标签开始逐级添加子标签及对应数据
             * 4：创建XmlWriter
             * 5：使用XmlWriter写出Document来生成文档
             */
            try{
                /**
                 * 1：创建一个Document对象，表示一个空白文档
                 */
                Document doc= DocumentHelper.createDocument();
                /**
                 * 2：Document提供了添加根元素的方法：
                 * Element addElement(String name)
                 * 添加后会将根标签以一个Element实例形式返
                 * 回，以便于我们对其继续操作。注意，这个
                 * 方法只能被调用一次。
                 */
                Element root=doc.addElement("list");
                /**
                 * Element也提供了添加相关信息的方法:
                 * Element addElemnt(String name)
                 * 向当前标签中添加给定名字的子标签
                 *
                 * Element addText(String text)
                 * 向当前标签中间添加指定文本。返回值还是当前
                 * 标签(这样做的好处是可以连续操作当前标签)
                 */
                for(Emp emp:list){
                    //向根标签中添加<emp>子标签
                    Element empEle=root.addElement("emp");
                    //添加名字
                    Element nameEle=empEle.addElement("name");
                    nameEle.addText(emp.getName());
                    //添加年龄
                    empEle.addElement("age").addText(emp.getAge()+"");
                    //添加性别
                    empEle.addElement("gender").addText(emp.getGender());
                    //添加工资
                    empEle.addElement("salary").addText(emp.getSalary()+"");
                    //添加属性
                    empEle.addAttribute("id",emp.getId()+"");
                }

                /**
                 * XMLWriter符合java高级流用法。它负责将
                 * Document对象以XML文档格式进行写出，并
                 * 通过其连接的文件流写入到文件中。
                 */
                XMLWriter writer;
                writer = new XMLWriter(new FileOutputStream("xmlWriterDeo.xml")
                        , OutputFormat.createPrettyPrint());
                //将Document写出以生成XML文档
                writer.write(doc);
                System.out.println("写出完毕!");
                writer.close();

            }catch(Exception e){
                e.printStackTrace();
            }



        }
    }
}
