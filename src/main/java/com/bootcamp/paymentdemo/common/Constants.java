package com.bootcamp.paymentdemo.common;

public class Constants {

    private Constants() {
    }

    public static final String ADMIN_SESSION_NAME = "loginAdmin";
    //region 도메인 오류 메세지
    public static final String MSG_NOT_FOUND_MEMBER = "회원을 찾을 수 없습니다";
    //endregion 도메인 오류 메세지

    //region 서버 오류 메세지
    public static final String MSG_NOT_VALID_VALUE = "유효하지 않은 값이 입력되었습니다";
    public static final String MSG_DATA_INSERT_FAIL = "데이터 등록에 실패하였습니다";
    public static final String MSG_SERVER_ERROR_OCCUR = "서버 오류가 발생하였습니다, 잠시 후 다시 시도 바랍니다";
    //endregion

    // member 오류 메세지
    public static final String MSG_DUPLICATE_EMAIL = "중복 된 이메일입니다";
}


