package structural.adapter.after

// --- Target: 우리 시스템의 표준 인터페이스 ---
interface UserLogin {
    fun login(id: String, pass: String): String
}

class StandardLoginService : UserLogin {
    override fun login(id: String, pass: String): String {
        println("로그인 성공! 유저 ID: $id")
        return "TOKEN_$id"
    }
}

// --- Adaptee: 우리가 연결해야 할 외부 라이브러리 (수정 불가) ---
class LegacyAuth {
    fun authenticate(email: String): String {
        println("레거시 시스템 인증 성공! 이메일: $email")
        return "LEGACY_TOKEN_FOR_$email"
    }
}

// --- ✨ Adapter: LegacyAuth를 UserLogin 인터페이스에 맞춰주는 어댑터 ---
class LegacyAuthAdapter(
    // 내부에 Adaptee(LegacyAuth)의 인스턴스를 가짐 (Composition)
    private val legacyAuth: LegacyAuth
) : UserLogin { // Target(UserLogin) 인터페이스를 구현함

    // UserLogin 인터페이스의 메서드를 구현해야 한다.
    override fun login(id: String, pass: String): String {
        // 클라이언트의 요청(id, pass)을 Adaptee가 이해할 수 있는 방식(email)으로 변환한다.
        val email = "$id@example.com" // 여기서는 간단히 id를 email로 변환

        // 내부의 Adaptee에게 실제 작업을 위임한다.
        return legacyAuth.authenticate(email)
    }
}


fun main() {
    val id = "my_id"
    val pass = "my_password"

    // 1. 표준 로그인 사용
    val standardLogin: UserLogin = StandardLoginService()
    standardLogin.login(id, pass)

    println("-".repeat(20))

    // 2. 어댑터를 통해 레거시 시스템 사용
    // 클라이언트 코드는 LegacyAuth의 존재를 전혀 알 필요가 없다.
    // 그냥 똑같은 UserLogin 타입으로 사용하면 된다!
    val legacySystem = LegacyAuth()
    val legacyLoginAdapter: UserLogin = LegacyAuthAdapter(legacySystem)

    legacyLoginAdapter.login(id, pass)
}