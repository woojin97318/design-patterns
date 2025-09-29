package structural.adapter.before

// --- 우리 시스템의 표준 인터페이스 ---
// ID와 PW로 로그인하는 방식
interface UserLogin {
    fun login(id: String, pass: String): String
}

class StandardLoginService : UserLogin {
    override fun login(id: String, pass: String): String {
        println("로그인 성공! 유저 ID: $id")
        return "TOKEN_$id"
    }
}


// --- 우리가 연결해야 할 외부 라이브러리 또는 레거시 시스템 (수정 불가) ---
// Email 정보로 인증하는 방식
class LegacyAuth {
    fun authenticate(email: String): String {
        println("레거시 시스템 인증 성공! 이메일: $email")
        return "LEGACY_TOKEN_FOR_$email"
    }
}


fun main() {
    val id = "my_id"
    val pass = "my_password"
    val email = "my_id@example.com"

    // 우리 시스템의 표준 로그인은 문제 없이 동작한다.
    val standardLogin: UserLogin = StandardLoginService()
    standardLogin.login(id, pass)

    println("-".repeat(20))

    // 하지만 LegacyAuth는 UserLogin 타입이 아니라서 호환되지 않는다.
    // val legacyLogin: UserLogin = LegacyAuth() // 컴파일 에러 발생!

    // 그래서 어쩔 수 없이 클라이언트 코드에서 직접 분기 처리해야 한다.
    val legacySystem = LegacyAuth()
    legacySystem.authenticate(email)
}