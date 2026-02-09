package com.bootcamp.paymentdemo.common;

public class Constants {

    private Constants() {
    }

    public static final String ADMIN_SESSION_NAME = "loginAdmin";
    //region 도메인 오류 메세지
    public static final String MSG_NOT_FOUND_MEMBER = "회원을 찾을 수 없습니다";
    public static final String MSG_NOT_FOUND_ORDER = "주문을 찾을 수 없습니다";
    public static final String MSG_NOT_FOUND_PRODUCT = "상품을 찾을 수 없습니다";

    // 환불 관련 오류 메시지
    public static final String MSG_NOT_FOUND_PAYMENT = "존재하지 않는 결제 정보입니다";
    public static final String MSG_ALREADY_REFUNDED = "이미 환불 처리된 결제입니다";
    public static final String MSG_INVALID_REFUND_STATUS = "환불 가능한 결제 상태가 아닙니다";

    //endregion 도메인 오류 메세지

    //region 서버 오류 메세지
    public static final String MSG_NOT_VALID_VALUE = "유효하지 않은 값이 입력되었습니다";
    public static final String MSG_DATA_INSERT_FAIL = "데이터 등록에 실패하였습니다";
    public static final String MSG_SERVER_ERROR_OCCUR = "서버 오류가 발생하였습니다, 잠시 후 다시 시도 바랍니다";
    //endregion

    // member 오류 메세지
    public static final String MSG_DUPLICATE_EMAIL = "중복 된 이메일입니다";
}


