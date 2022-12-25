package com.java8.springboot.java.java8.SuppliervsComsumer;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import org.springframework.util.NumberUtils;

public class supplier {   
	class Programing {
	    public String language;
	    public int experience;
	 
	    public Programing() {
	        this.language = "Java";
	        this.experience = 5;
	    }
	 
	    public Programing(String language, int experience) {
	        this.language = language;
	        this.experience = experience;
	    }
	 
	    public void print() {
	        System.out.println("Language: " + language + " - Experience: " + experience);
	    }
	 
	    public String getDefaulLanguage() {
	        return "Java";
	    }
	}
	
	public void example1() {
		Supplier<String> supplier = () -> "Welcome to gpcoder.com";
        String hello = supplier.get();
        System.out.println(hello);
	}
	
	public void example2() {
        Supplier<Programing> supplier1 = Programing::new;
        Programing lang = supplier1.get();
        lang.print();
        Supplier<String> supplier2 = lang::getDefaulLanguage;
        String defaultLang = supplier2.get();
        System.out.println("Default Language: " + defaultLang);
	}
	
//	public void example3() {
//		 BooleanSupplier bs = NumberUtils::getBooleanValue;
//	        System.out.println("Boolean Value: " + bs.getAsBoolean());
//	 
//	        IntSupplier dbs = NumberUtils::getIntValue;
//	        System.out.println("Integer Value: " + dbs.getAsInt());
//	 
//	        LongSupplier ls = NumberUtils::getLongValue;
//	        System.out.println("Long Value: " + ls.getAsLong());
//	 
//	        DoubleSupplier ds = NumberUtils::getDoubleValue;
//	        System.out.println("Double Value: " + ds.getAsDouble());
//	}
    public static void main(String[] args) {   
        supplier sp = new supplier();
        sp.example1();
        sp.example2();
    }
}
//
//private List<CheckSalePointDTO> querySalePoints(SalePointsSearchDTO searchSalePoints, Pageable pageable) {
//    List<ParamDTO> paramDTOS = new ArrayList<>();
//    StringBuilder queryString = new StringBuilder();
//    queryString.append(" select a.STAFF_CODE      as 'ma nhan vien kiem tra',");
//    queryString.append(" a.STAFF_NAME      as 'ten nhan vien kiem tra',");
//    queryString.append(" a.SALE_POINT_CODE as 'ma diem ban',");
//    queryString.append(" a.SALE_POINT_ID,");
//    queryString.append("  b.SHOP_NAME       as 'ten diem ban',");
//    queryString.append(" b.SHOP_ID,");
//    queryString.append(" a.DATE_CARE_PLAN  as 'ngay cham so',");
//    queryString.append(" a.STATUS          as 'trang thai',");
//    queryString.append(" b.PROVINCE_NAME   as 'tinh thanh pho',");
//    queryString.append(" b.PROVINCE_CODE,");
//    queryString.append(" b.DISTRICT_NAME   as 'quan huyen',");
//    queryString.append(" b.DISTRICT_CODE,");
//    queryString.append(" b.VILLAGE_NAME    as 'xa phuong',");
//    queryString.append(" b.VILLAGE_CODE,");
//    queryString.append(" b.ADDRESS         as 'dia chi',");
//    queryString.append(" a.NOTE            as 'ghi chú',");
//    queryString.append(" a.CREATE_DATE     as  'ngay tao',");
//    queryString.append(" a.APPROVE_DATE    as  'ngay phe duyet',");
//    queryString.append(" a.USER_APPROVE    as 'nguoi phe duyet'");
//    queryString.append(" from check_sale_point a");
//    queryString.append("             LEFT JOIN mv_bds_staff b ON a.sale_point_code = b.sale_point_code");
//    whereFilterSalePoint(searchSalePoints, queryString, paramDTOS);
//    if (pageable.getSort() != null && pageable.getSort().stream().count() > 0) {
//      queryString.append(" order by ");
//      String sorted = pageable.getSort().stream().map(sort -> sort.getProperty() + " " + sort.getDirection().name()).collect(Collectors.joining(","));
//      queryString.append(sorted);
//
//    } else {
//      queryString.append(" order by a.id desc ");
//    }
//
//    Query selectQuery = em.createNativeQuery(queryString.toString());
//
//    if (paramDTOS.size() > 0) {
//      for (ParamDTO paramDTO : paramDTOS) {
//        selectQuery.setParameter(paramDTO.getKey(), paramDTO.getValue());
//      }
//    }
//    if (!pageable.isUnpaged()) {
//      int size = pageable.getPageSize();
//      int page = pageable.getPageNumber();
//      selectQuery.setFirstResult(page * size);
//      selectQuery.setMaxResults(size);
//    }
//    List<Object[]> rows = selectQuery.getResultList();
//    List<CheckSalePointDTO> data = new ArrayList<>();
//    for (Object[] row : rows) {
//      CheckSalePointDTO checkSalePoint = new CheckSalePointDTO();
//      if (row[0] != null) {
//        checkSalePoint.setStaffCode(DataUtils.safeToString(row[0]));
//      }
//      if (row[1] != null) {
//        checkSalePoint.setStaffName(DataUtils.safeToString(row[1]));
//      }
//      if (row[2] != null) {
//        checkSalePoint.setSalePointCode(DataUtils.safeToString(row[3]));
//      }
//      if (row[3] != null) {
//        checkSalePoint.setSalePointId(DataUtils.safeToLong(row[4]));
//      }
//      if (row[4] != null) {
//        checkSalePoint.setShopName(DataUtils.safeToString(row[4]));
//      }
//      if (row[5] != null) {
//        checkSalePoint.setShopId(DataUtils.safeToLong(row[5]));
//      }
//      if (row[6] != null) {
//        checkSalePoint.setDateCarePlan(((Timestamp) row[6]).toInstant());
//      }
//      if (row[7] != null) {
//        checkSalePoint.setStatus(DataUtils.safeToInteger(row[7]));
//      }
//      if (row[8] != null) {
//        checkSalePoint.setProvinceName(DataUtils.safeToString(row[8]));
//      }
//      if (row[9] != null) {
//        checkSalePoint.setProvinceCode(DataUtils.safeToString(row[9]));
//      }
//      if (row[10] != null) {
//        checkSalePoint.setDistrictName(DataUtils.safeToString(row[10]));
//      }
//      if (row[11] != null) {
//        checkSalePoint.setDistrictCode(DataUtils.safeToString(row[11]));
//      }
//      if (row[12] != null) {
//        checkSalePoint.setVillageName(DataUtils.safeToString(row[12]));
//      }
//      if (row[13] != null) {
//        checkSalePoint.setVillageCode(DataUtils.safeToString(row[13]));
//      }
//      if (row[14] != null) {
//        checkSalePoint.setAddress(DataUtils.safeToString(row[14]));
//      }
//      if (row[15] != null) {
//        checkSalePoint.setNote(DataUtils.safeToString(row[15]));
//      }
//      if (row[16] != null) {
//        checkSalePoint.setCreateDate(((Timestamp) row[16]).toInstant());
//      }
//      if (row[17] != null) {
//        checkSalePoint.setApproveDate(((Timestamp) row[17]).toInstant());
//      }
//      if (row[18] != null) {
//        checkSalePoint.setUserApprove(DataUtils.safeToString(row[18]));
//      }
//      data.add(checkSalePoint);
//    }
//    return data;
//  }
