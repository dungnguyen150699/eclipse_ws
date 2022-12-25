package com.java8.springboot.java.jaxb;



import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;


public class MainTestJaxb {

   private static final String XML_FILE = "dept-info.xml";

   public static void main(String[] args) {

       Employee emp1 = new Employee("E01", "Tom", null);
       Employee emp2 = new Employee("E02", "Mary", "E01");
       Employee emp3 = new Employee("E03", "John", null);

       List<Employee> list = new ArrayList<Employee>();
       list.add(emp1);
       list.add(emp2);
       list.add(emp3);

       Department dept = new Department("D01", "ACCOUNTING", "NEW YORK");
       //
       dept.setEmployees(list);

       try {
           // Tạo đối tượng JAXB context.
           JAXBContext context = JAXBContext.newInstance(Department.class);

           // (1) Marshaller : Chuyển đối tượng Java thành XML
           Marshaller m = context.createMarshaller();
           m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

           // Ghi đối tượng java (dept) ra màn hình Console (System.out)
           m.marshal(dept, System.out);

           // Ghi đối tượng Java (dept) ra file.
           File outFile = new File(XML_FILE);
           m.marshal(dept, outFile);

           System.err.println("Write to file: " + outFile.getAbsolutePath());

           // (2) Unmarshaller : Chuyển dữ liệu XML thành đối tượng Java.
           Unmarshaller um = context.createUnmarshaller();

           // Phân tích file XML vừa được tạo ra ở bước trước.
           Department deptFromFile = (Department) um.unmarshal(new FileReader(
                   XML_FILE));
           List<Employee> emps = deptFromFile.getEmployees();
           for (Employee emp : emps) {
               System.out.println("Employee: " + emp.getEmpName());
           }

       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
 