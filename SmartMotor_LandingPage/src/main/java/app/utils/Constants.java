package vn.viettel.app.utils;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class Constants {

    // dd-MM-yyyy, dd/MM/yyyy, dd.MM.yyyy
    public final static String DATE_PATTERN_DD_MM_YYYY = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    public final static String EMAIL_PATTERN = "^(([^<>()[\\]\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@(([^<>()[\\]\\.,;:\\s@\\\"]+\\.)+[^<>()[\\]\\.,;:\\s@\\\"]{2,})$";
    public final static String PHONE_NUMBER_REGEX = "";

    //  Password must contain at least one digit [0-9].
    //  Password must contain at least one lowercase Latin character [a-z].
    //  Password must contain at least one special character like ! @ # & ( ).
    //  Password must contain a length of at least 8 characters and a maximum of 20 characters.
    public final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    public static final String PATH_TEMPLATE = "classpath:template/";
    public static final String INVESTIGATION_PHONE_NUMBER_TEMPLATE = "investigation_phone_number_template.xlsx";
    public static final String IMPORT_PHONE_TEMPLATE = "import-phone.xlsx";
    public static final String IMPORT_UNIT_TEMPLATE = "import-unit.xlsx";
    public static final String MES_DOC_DIRECTORY = "MES-QLSX/";
    public static final String MES_IMAGE_DIRECTORY = "DANH_SACH_DIEU_TRA/";
    public static final long MAX_UPLOAD_SIZE_IN_MB = 2 * 1024 * 1024;

    public enum EXPORT_TEMPLATE_TYPE {
        EXAMPLE(1), USER(2), UNIT(3), PHONE_SENSITIVE(4), INVESTIGATION_INPUT(99), UNIT_REPORT(5), DOCUMENTARY_REPORT(6), PHONE_REPORT(7);
        private int value;

        EXPORT_TEMPLATE_TYPE(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static final class STATUS_EQUIP_TYPE {

        private STATUS_EQUIP_TYPE() {
        }

        public static final Long WAITING_APPROVE = 0L;
        public static final Long ACTIVE = 1L;
        public static final Long INACTIVE = -1L;
    }
    public static final class STATUS_EQUIPMENT {

        private STATUS_EQUIPMENT() {
        }

        public static final Long OUTL = 0L;
        public static final Long INL = 1L;
    }
    public static final class SITUATION {

        private SITUATION() {
        }

        public static final Long GOOD = 1L;
        public static final Long BAD = 0L;
    }
    public static final class COMPLETE {

        private COMPLETE() {
        }

        public static final Long COMPLETE = 1L;
        public static final Long UNCMOPLETE = 0L;
    }
    public static final class EXPORT_EXPOSE_HEADER {

        private EXPORT_EXPOSE_HEADER() {
        }

        public static final String TOTAL = "total";
        public static final String TOTAL_ERROR = "totalErr";
    }

    public static final class MEDIA_TYPE {

        private MEDIA_TYPE() {
        }

        public static final String MS_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    }

    public static final class FILE_EXTENSION {

        private FILE_EXTENSION() {
        }

        public static final String HSSF = ".xls";
        public static final String XSSF = ".xlsx";
    }

    public class LOCAL_IP {

        public static final String LOCALHOST_IPV4 = "127.0.0.1";
        public static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    }

    public enum USER_VIETTEL {
        TRUE(Short.valueOf("1")), FALSE(Short.valueOf("0"));

        @Getter
        private Short value;

        USER_VIETTEL(Short value) {
            this.value = value;
        }
    }

    public enum STATUS {
        ACTIVE(1L, "Hoạt động"), INACTIVE(-1L, "Không hoạt động");
        @Getter
        private Long code;
        @Getter
        private String messCode;

        STATUS(Long code, String messCode) {
            this.code = code;
            this.messCode = messCode;
        }

        public static boolean isExistCode(Long code) {
            for (STATUS status : STATUS.values()) {
                if (status.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static class ROW_STATUS {

        public static final Short ACTIVE = 1;
        public static final Short IN_ACTIVE = 0;
    }

    public static class INVESTIGATE_MODE {

        public static final String NEW = "NEW";
        public static final String UPDATE = "UPDATE";
    }

    public static class PHONE_SENSITIVE {

        public static final Short IS_SENSITIVE = 1;
        public static final Short NON_SENSITIVE = 0;
    }

    public static class DOCUMENTARY_STATUS {

        public static final Short DRAFT = -1; // trạng thái nháp
        public static final Short DELETED = 0; // Đã xóa
        public static final Short WAITING_APPROVE = 1; // chờ phe duyet
        public static final Short PART_PROCESSING = 2; // đang xử lý 1 phần
        public static final Short FULL_PROCESSING = 3; // đang xử lý toàn bộ
        public static final Short PART_PROCESSED = 4; // đã xử lý 1 phần
        public static final Short FULL_PROCESSED = 5; // đã xử lý toàn bộ
        public static final Short DONE = 6; // Đã hoàn thành
        public static final Short REFUSE = 7; // Đã từ chối
        public static final Short WAITING_PROCESS = 8; // chờ xử lý
    }

    public static final String DOCUMENTARY_DIRECTORY = "CONG_VAN/";
    public static final String PHONE_INVESTIGATIONS_DIRECTORY = "DANH_SACH_DIEU_TRA/";


    public static class DOCUMENT_ATTACH_TYPE {

        public static final String CONG_VAN = "Công văn";
        public static final String DANH_SACH_DIEU_TRA = "Danh sách điều tra";
    }

    public enum DOCUMENTARY_SOURCE {
        CONG_VAN_THUONG("CONG_VAN_THUONG"), CONG_VAN_DON_LE("CONG_VAN_DON_LE"), CONG_VAN_KHAN("CONG_VAN_KHAN"), CONG_VAN_GUI_VE("CONG_VAN_GUI_VE");

        @Getter
        private String value;

        DOCUMENTARY_SOURCE(String value) {
            this.value = value;
        }
    }

    public enum PHONE_SENSITIVE_SOURCE {
        HR_VIETTEL("HR_VIETTEL"), VIP("VIP"), BCA_BQP("BQP");

        @Getter
        private String value;

        PHONE_SENSITIVE_SOURCE(String value) {
            this.value = value;
        }
    }

    // Nghiệp vụ điều tra
    public enum INVESTIGATE_TYPE {
        DIEU_TRA_DANH_TINH("DIEU_TRA_DANH_TINH"), DIEU_TRA_LIEN_LAC("DIEU_TRA_LIEN_LAC"), DIEU_TRA_CELL_IMEI("DIEU_TRA_CELL_IMEI"), DIEU_TRA_CELL("DIEU_TRA_CELL"), DIEU_TRA_IMEI("DIEU_TRA_IMEI"), DIEU_TRA_THOAI("DIEU_TRA_THOAI"), DIEU_TRA_SMS("DIEU_TRA_SMS"), DIEU_TRA_THOAI_SMS("DIEU_TRA_THOAI_SMS");

        @Getter
        private String value;

        INVESTIGATE_TYPE(String value) {
            this.value = value;
        }
    }

    public enum REPORT_FILE_TYPE {
        EXCEL("EXCEL"), PDF("PDF");

        @Getter
        private String value;

        REPORT_FILE_TYPE(String value) {
            this.value = value;
        }
    }

    //Điều tra với
    public enum INVESTIGATE_WITH {
        SO_THUE_BAO("SO_THUE_BAO"), CMND("CMND"), CCCD("CCCD"), GPLX("GPLX"), GPKD("GPKD"), DIA_CHI_THUE_BAO("DIA_CHI_THUE_BAO"), TEN_CHU_THUE_BAO("TEN_CHU_THUE_BAO"), SO_IMEI("SO_IMEI"), SO_CELL("SO_CELL"), SMS("SMS"), VOICE("VOICE"), VOICE_AND_SMS("VOICE_AND_SMS"), NAME_AND_DOB("NAME_AND_DOB");

        @Getter
        private String value;

        INVESTIGATE_WITH(String value) {
            this.value = value;
        }
    }

    public enum INPUT_INVESTIGATE_STATUS {
        ACTIVE(Short.valueOf("1")), PENDING(Short.valueOf("2")), PROCESSING(Short.valueOf("3")), PROCESSED(Short.valueOf("4")), DELETED(Short.valueOf("0"));

        @Getter
        private Short value;

        INPUT_INVESTIGATE_STATUS(Short value) {
            this.value = value;
        }
    }

    public enum WORK_STATUS {
        ACTIVE(Short.valueOf("1")), INACTIVE(Short.valueOf("0")), DRAFF(Short.valueOf("-1"));

        @Getter
        private Short value;

        WORK_STATUS(Short value) {
            this.value = value;
        }
    }

    public enum NOTIFICATION_TYPE {
        SMS("SMS"), WEB("WEB");

        @Getter
        private String value;

        NOTIFICATION_TYPE(String value) {
            this.value = value;
        }
    }

    public enum NOTIFICATION_STATUS {
        UN_SENT(Short.valueOf("0")), SENT(Short.valueOf("1")), READ(Short.valueOf("2")), UN_READ(Short.valueOf("3"));

        @Getter
        private Short value;

        NOTIFICATION_STATUS(Short value) {
            this.value = value;
        }
    }

    public enum WORK_PRIORITY {
        URGENT(Short.valueOf("1"), "Khẩn cấp"), IMPORTANT(Short.valueOf("2"), "Quan trọng"), NORMAL(Short.valueOf("3"), "Bình thường");
        @Getter
        private Short code;
        @Getter
        private String messCode;

        WORK_PRIORITY(Short code, String messCode) {
            this.code = code;
            this.messCode = messCode;
        }

        public static boolean isExistPriority(Short code) {
            for (WORK_PRIORITY status : WORK_PRIORITY.values()) {
                if (status.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
        }
    }

    public enum WORK_REMIND_DEADLINE {
        THREE_DAYS_LATER(Short.valueOf("1"), "Cách deadline 3 ngày"), ONE_DAY_LATER(Short.valueOf("2"), "Cách deadline 1 ngày"), TODAY(Short.valueOf("3"), "Ngày hiện tại");
        @Getter
        private Short code;
        @Getter
        private String messCode;

        WORK_REMIND_DEADLINE(Short code, String messCode) {
            this.code = code;
            this.messCode = messCode;
        }

        public static boolean isRemindPriority(Short code) {
            for (WORK_PRIORITY status : WORK_PRIORITY.values()) {
                if (status.getCode().equals(code)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static final List<String> FIX_DATE = Arrays.asList("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN");

    public static class UNIT_LEVEL {

        public static final Long LEVEL_1 = 1L;
        public static final Long LEVEL_2 = 2L;
        public static final Long LEVEL_3 = 3L;
    }

    public static final class ActionCode {

        private ActionCode() {
        }

        public static final String ACTION_AUDIT_ADD = "ADD";
        public static final String ACTION_AUDIT_UPDATE = "UPDATE";
        public static final String ACTION_AUDIT_DELETE = "DELETE";
        public static final String ACTION_DELETE_LOGIC = "DELETE_LOGIC";
    }

    public static final List<DynamicColumnDTO> dynamicColumn11 = Arrays.asList(new DynamicColumnDTO("Số đi", "isdnFrom", 50), new DynamicColumnDTO("Số đến", "isdnTo", 50), new DynamicColumnDTO("Thời gian", "startTime", 75), new DynamicColumnDTO("Giây", "duration", 26), new DynamicColumnDTO("IMSI", "imsi", 70), new DynamicColumnDTO("IMEI", "imei", 70), new DynamicColumnDTO("Tỉnh", "provinceCode", 27), new DynamicColumnDTO("TYPE", "type", 30), new DynamicColumnDTO("Direction", "direction", 45), new DynamicColumnDTO("Địa chỉ trạm", "btsAddress", 107));

    public enum ROLE {
        ADMIN , USER
    }
}
