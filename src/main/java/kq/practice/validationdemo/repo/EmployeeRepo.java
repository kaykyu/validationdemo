package kq.practice.validationdemo.repo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import kq.practice.validationdemo.model.Employee;

@Repository
public class EmployeeRepo {

    final String dirPath = "C://data";
    final String fileName = "employee.txt";

    private List<Employee> employees;

    public EmployeeRepo() throws ParseException {
        if (employees == null) {
            employees = new ArrayList<>();
        }

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date dt = df.parse("1965-08-09");
        Employee emp = new Employee("Johnny", "Walker",
                "johnnywalker@spirits.com", "91234567", 10000, dt, 123456);
        employees.add(emp);

        emp = new Employee("Jim", "Highball",
                "jimhighball@spirits.com", "81234567", 10000, dt, 123455);
        employees.add(emp);
    }

    public List<Employee> findAll() {

        return employees;
    }

    public boolean delete(Employee employee) {

        boolean result = false;
        int employeeIndex = employees.indexOf(employee);

        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            result = true;
        }

        return result;
    }

    public Employee findByEmail(String email) {

        return employees.stream().filter(emp -> emp.getEmail().equals(email)).findFirst().get();
    }

    public boolean updateEmployee(Employee employee) {

        boolean result = false;
        Employee empObj = employees.stream().filter(emp -> emp.getEmail().equals(employee.getEmail())).findFirst()
                .get();
        int employeeIndex = employees.indexOf(empObj);

        if (employeeIndex >= 0) {

            employees.get(employeeIndex).setFirstName(employee.getFirstName());
            employees.get(employeeIndex).setLastName(employee.getLastName());
            employees.get(employeeIndex).setEmail(employee.getEmail());
            employees.get(employeeIndex).setPhoneNo(employee.getPhoneNo());
            employees.get(employeeIndex).setSalary(employee.getSalary());
            employees.get(employeeIndex).setBirthDate(employee.getBirthDate());
            employees.get(employeeIndex).setPostalCode(employee.getPostalCode());

            result = true;
        }
        return result;
    }

    public boolean save(Employee employee) throws FileNotFoundException {

        boolean result = false;
        result = employees.add(employee);

        File f = new File(dirPath + "/" + fileName);
        OutputStream os = new FileOutputStream(f, true);
        PrintWriter pw = new PrintWriter(os);
        pw.println(employee.toString());
        pw.flush();
        pw.close();

        return result;
    }
}
