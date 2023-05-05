package dungnt.ptit.spring_mogodb.repository.custom;

import java.util.Date;

public interface EmployeeRepositoryCustom {

    public String getMaxEmpId();
    
    public long updateEmployee(String empNo, String fullName, Date hireDate);
    
}