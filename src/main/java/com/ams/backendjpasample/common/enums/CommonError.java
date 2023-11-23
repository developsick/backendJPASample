/**
 * 
 */
package com.ams.backendjpasample.common.enums;

import lombok.Getter;

/**
 * 공통 영역 에러 코드
 */
@Getter
public enum CommonError implements EnumError {
	
	SUCCESS						( "CS000", "성공" ),
	
	UNAUTHORIZED				( "CE001", "인증 정보가 유효하지 않습니다." ),
	FORBIDDEN					( "CE002", "접근 권한이 없습니다." ),
	INVALID_PARAMETERS			( "CE003", "파라미터가 유효하지 않습니다."),
	DUPLICATE_DATA				( "CE004", "중복된 데이터 입니다."),
	INVALID_DOMAIN				( "CE005", "잘못된 Domain 입니다"),
	UNKNOWN						( "CE006", "알 수 없는 에러가 발생했습니다."),

	BIZ_RUNTIME					( "BZ001", "비즈니스 로직 수행 중 오류"),

	// 데이터 조회 Response
	DATA_NOT_FOUND				("DI001", "조회되는 데이터가 없습니다"),
	INVALID_DATA_INPUT			("DI002", "잘못된 데이터 입력입니다"),
	DATASOURCE_CONNECTION_FAIL	("DI003", "데이터소스 연결이 실패하였습니다."),
	DATA_DUPLICATED				("DI001", "중복되는 데이터가 있습니다"),


	;

	private String code;
	private String msg;
	
	private CommonError(String code, String msg ) {
		this.code = code;
		this.msg = msg;
	}

}
