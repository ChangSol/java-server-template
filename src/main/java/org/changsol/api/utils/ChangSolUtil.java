package org.changsol.api.utils;

import com.google.common.collect.Maps;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

/**
 * 유틸 클래스
 */
public class ChangSolUtil {

	private ChangSolUtil() {
	}

	// region isEmpty

	/**
	 * Object 체크
	 *
	 * @param obj Object
	 * @return boolean
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof Collection<?>) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof Map<?, ?>) {
			return ((Map<?, ?>) obj).isEmpty();
		}
		return false;
	}

	/**
	 * String 체크(공백허용)
	 *
	 * @param obj String
	 * @return boolean
	 */
	public static boolean isEmpty(String obj) {
		return obj == null || obj.length() == 0;
	}

	/**
	 * Collection 체크
	 *
	 * @param obj Collection
	 * @return boolean
	 */
	public static boolean isEmpty(Collection<?> obj) {
		return obj == null || obj.isEmpty();
	}

	/**
	 * Map 체크
	 *
	 * @param obj Map
	 * @return boolean
	 */
	public static boolean isEmpty(Map<?, ?> obj) {
		return obj == null || obj.isEmpty();
	}
	// endregion

	// region isNotEmpty

	/**
	 * Object 체크
	 *
	 * @param obj Object
	 * @return boolean
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}

	/**
	 * String 체크(공백허용)
	 *
	 * @param obj String
	 * @return boolean
	 */
	public static boolean isNotEmpty(String obj) {
		return !isEmpty(obj);
	}

	/**
	 * Collection 체크
	 *
	 * @param obj Collection
	 * @return boolean
	 */
	public static boolean isNotEmpty(Collection<?> obj) {
		return !isEmpty(obj);
	}

	/**
	 * Map 체크
	 *
	 * @param obj Map
	 * @return boolean
	 */
	public static boolean isNotEmpty(Map<?, ?> obj) {
		return !isEmpty(obj);
	}
	// endregion

	// region isBlank

	/**
	 * String 체크(공백 미허용)
	 *
	 * @param obj String
	 * @return boolean
	 */
	public static boolean isBlank(String obj) {
		return obj == null || obj.trim().length() == 0;
	}
	// endregion

	// region isNotBlank

	/**
	 * String 체크(공백 미허용)
	 *
	 * @param obj 문자열
	 * @return boolean
	 */
	public static boolean isNotBlank(String obj) {
		return !isBlank(obj);
	}
	// endregion

	// region Password Validation

	/**
	 * 패스워드 검증
	 *
	 * @param password     패스워드
	 * @param minLength    패스워드 최소 자리수
	 * @param maxLength    패스워드 최대 자리수
	 * @param isContinuous 3자리 이상 연속되는 문자/숫자 체크여부 ex) 123,321, abc, cba
	 * @param isSame       3자리 이상 같은 문자/숫자 체크여부 ex) 111, aaa
	 * @param isSpecialMix 특수문자 혼합 체크여부 ex) 문자,숫자,특수문자 혼합 체크 (특수문자 범위 !,@,#,$,%,^,&,*,(,) )
	 * @return Map&lt;String, Object&gt;
	 * <br>성공 => "status": true, "message": "success"
	 * <br>공백 실패 => "status": false, "message": "비밀번호가 존재하지 않습니다."
	 * <br>문자열 자리수 실패 => "status": false, "message": "비밀번호는 min~max자리까지만 가능합니다."
	 * <br>3자리 이상 연속되는 문자/숫자 실패 => "status": false, "message": "3자리 이상 연속되는 문자 또는 숫자가 포함되어 있습니다."
	 * <br>3자리 같은 문자/숫자 실패 => "status": false, "message": "3자리 같은 문자 또는 숫자가 포함되어 있습니다."
	 * <br>특수문자 혼합 실패 => "status": false, "message": "비밀번호는 영어(대소문자포함), 숫자, 특수문자를 모두 포함해야 합니다."
	 */
	public static Map<String, Object> passwordValidation(String password,
														 int minLength,
														 int maxLength,
														 boolean isContinuous,
														 boolean isSame,
														 boolean isSpecialMix) {

		Map<String, Object> returnValue = Maps.newHashMap();

		// 공백 체크
		if (isBlank(password)) {
			returnValue.put("status", false);
			returnValue.put("message", "비밀번호가 존재하지 않습니다.");
			return returnValue;
		}

		// 패스워드 Upper
		final String UPPER_PASSWORD = password.toUpperCase();
		final int PASSWORD_LENGTH = UPPER_PASSWORD.length();

		// region 문자열 자리수 체크
		if (minLength > maxLength) {
			maxLength = minLength;
		}

		if (PASSWORD_LENGTH < minLength || PASSWORD_LENGTH > maxLength) {
			returnValue.put("status", false);
			returnValue.put("message", "비밀번호는 " + minLength + "~" + maxLength + "자리까지만 가능합니다.");
			return returnValue;
		}
		// endregion

		// region 3자리 이상 연속되는 문자/숫자 체크
		if (isContinuous) {
			int[] tempArray = new int[PASSWORD_LENGTH];

			// 아스키코드 Array 삽입
			for (int i = 0; i < PASSWORD_LENGTH; i++) {
				tempArray[i] = UPPER_PASSWORD.charAt(i);
			}

			// 체크
			for (int i = 0; i < PASSWORD_LENGTH - 2; i++) {
				// 첫번째 문자가 0-9 또는 A-Z
				if ((tempArray[i] > 47 && tempArray[i + 2] < 58)
					|| (tempArray[i] > 64 && tempArray[i + 2] < 91)) {
					// 배열의 연속된 수 검사
					// 3번째 글자 - 2번째 글자 = 1, 3번째 글자 - 1번째 글자 = 2

					// 아스키코드로 3번째 문자 - 1번째 문자가 2이고 3번째 문자 - 2번째 문자가 1이면 연속된 숫자
					if (Math.abs(tempArray[i + 2] - tempArray[i]) == 2
						&& Math.abs(tempArray[i + 2] - tempArray[i + 1]) == 1) {
						returnValue.put("status", false);
						returnValue.put("message", "3자리 이상 연속되는 문자 또는 숫자가 포함되어 있습니다.");
						return returnValue;
					}
				}
			}
		}
		// endregion

		Matcher matcher;

		// region 3자리 같은 문자/숫자 체크
		if (isSame) {
			// 3자리 같은 문자 정규식
			String sameReg = "(\\w)\\1\\1";

			matcher = Pattern.compile(sameReg).matcher(UPPER_PASSWORD);

			// 체크
			if (matcher.find()) {
				returnValue.put("status", false);
				returnValue.put("message", "3자리 같은 문자 또는 숫자가 포함되어 있습니다.");
				return returnValue;
			}
		}
		// endregion

		// region 특수문자 혼합 체크
		if (isSpecialMix) {
			// 영어, 숫자, 특수문자 포함한 정규식
			String pwdReg = "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{" + minLength + "," + maxLength + "})$";

			matcher = Pattern.compile(pwdReg).matcher(UPPER_PASSWORD);

			// 체크
			if (!matcher.find()) {
				returnValue.put("status", false);
				returnValue.put("message", "비밀번호는 영어(대소문자포함), 숫자, 특수문자를 모두 포함해야 합니다.");
				return returnValue;
			}
		}
		// endregion

		returnValue.put("status", true);
		returnValue.put("message", "success");
		return returnValue;
	}
	// endregion

	// region UUID
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	// endregion

	// region DEVICE

	/**
	 * 모바일 여부
	 *
	 * @return boolean
	 */
	public static boolean isMobile(HttpServletRequest httpServletRequest) {
		Device device = DeviceUtils.getCurrentDevice(httpServletRequest);
		return !device.isNormal();
	}
	// endregion

	// region 법정동코드

	/**
	 * 법정동코드는 10자리로 이루어져 있음<br>
	 * 알맞는 법정동 코드 반환<br>
	 * 시,도 => 2자리 , 세종특별자치시는 5자리부터 시도 (36110)<br>
	 * 시,군,구 => 5자리<br>
	 * 읍,면,동 => 8자리<br>
	 * 리 => 10자리
	 *
	 * @param fullAreaCode 법정동코드 10자리
	 * @return String<br> ex) 11 => 서울특별시, 11110 => 서울특별시 종로구
	 */
	public static String getAreaCode(String fullAreaCode) {
		if (isBlank(fullAreaCode) || fullAreaCode.length() < 10) {
			return null;
		}

		// 시도
		String sidoCode = fullAreaCode.substring(0, 2).replace("00", "");
		// 시군구
		String sggCode = fullAreaCode.substring(2, 5).replace("000", "");
		// 읍면동
		String umdCode = fullAreaCode.substring(5, 8).replace("000", "");
		// 리
		String riCode = fullAreaCode.substring(8, 10).replace("00", "");

		return String.format("%s%s%s%s", sidoCode, sggCode, umdCode, riCode);
	}
	// endregion
}
