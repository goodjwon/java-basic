package com.example.ch4.exception;

class NetworkTimeoutException extends Exception {
    NetworkTimeoutException(String msg) {
        super(msg);
    }
}

class NetworkDisconnectedException extends Exception {
    NetworkDisconnectedException(String msg) {
        super(msg);
    }
}

public class CustomNetworkExceptionExample {
    public static void main(String[] args) {
        try {
            fetchDataFromServer();
        } catch (NetworkTimeoutException e) {
            System.err.println("네트워크 타임아웃 예외: " + e.getMessage());
        } catch (NetworkDisconnectedException e) {
            System.err.println("네트워크 연결 끊김 예외: " + e.getMessage());
        }
    }

    static void fetchDataFromServer() throws NetworkTimeoutException, NetworkDisconnectedException {
        boolean timeout = false;  // 가정: 네트워크 요청 타임아웃 발생 상황
        boolean disconnected = true; // 가정: 네트워크 연결 끊김 여부

        if (timeout) {
            throw new NetworkTimeoutException("서버 응답 지연으로 타임아웃 발생");
        } else if (disconnected) {
            throw new NetworkDisconnectedException("네트워크 연결 끊김");
        }

        // 정상적 데이터 수신 로직 가정
    }
}

/*
코드 설명:
- 네트워크 관련 두 가지 커스텀 예외(NetworkTimeoutException, NetworkDisconnectedException) 정의
- fetchDataFromServer 메서드에서 가상의 타임아웃, 연결 끊김 상황 발생 시 해당 예외 발생
- 호출 측에서 다양한 네트워크 예외 상황을 명확히 구분하여 처리
- 커스텀 예외를 통해 특정 문제 상황 식별 및 대응 전략 수립 용이
*/
