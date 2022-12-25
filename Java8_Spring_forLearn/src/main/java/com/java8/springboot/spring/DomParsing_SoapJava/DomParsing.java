package com.java8.springboot.spring.DomParsing_SoapJava;

// Cái này dùng để lấy 1 thẻ trong 1 chuỗi xml mà soap trả về
public class DomParsing {
	
	public String reponse_NeedParsing() {
		String soapReponse = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "    <s:Body>\r\n"
				+ "        <GetCustomerDetailsByDeviceNumberResponse xmlns=\"http://services.domain.com/SelfCare\">\r\n"
				+ "            <GetCustomerDetailsByDeviceNumberResult xmlns:a=\"http://datacontracts.domain.com/SelfCare\" \r\n"
				+ "              xmlns:i=\"http://www.w3.org/2001/XMLSchema-instance\">\r\n"
				+ "                <a:AuditReferenceNumber i:nil=\"true\"/>\r\n"
				+ "                    <a:accounts>\r\n"
				+ "                        <a:Account>\r\n"
				+ "                            <a:lastInvoiceAmount>0</a:lastInvoiceAmount>\r\n"
				+ "                        <a:lastInvoiceDate>0001-01-01T00:00:00</a:lastInvoiceDate>\r\n"
				+ "                    </a:Account>\r\n"
				+ "                </a:accounts>\r\n"
				+ "            </GetCustomerDetailsByDeviceNumberResult>\r\n"
				+ "        </GetCustomerDetailsByDeviceNumberResponse>\r\n"
				+ "    </s:Body>\r\n"
				+ "</s:Envelope>";
		return soapReponse;
	}
	
	

}
