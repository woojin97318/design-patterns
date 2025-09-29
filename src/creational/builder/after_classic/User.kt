package creational.builder.after_classic

class User private constructor(
    val email: String,
    val nickname: String,
    val age: Int?,
    val address: String?
) {

    // 1. User를 생성하는 Builder 클래스 (보통 내부 클래스로 만듭니다)
    class Builder(
        private val email: String,      // 필수 필드는 Builder의 생성자로 받습니다.
        private val nickname: String
    ) {
        // 선택 필드들은 nullable var로 선언합니다.
        private var age: Int? = null
        private var address: String? = null

        // 2. 선택 필드를 설정하는 메서드들 (메서드 체이닝을 위해 Builder를 반환합니다)
        fun age(age: Int): Builder {
            this.age = age
            return this
        }

        fun address(address: String): Builder {
            this.address = address
            return this
        }

        // 3. 최종적으로 User 객체를 생성하여 반환하는 build() 메서드
        fun build(): User {
            return User(email, nickname, age, address)
        }
    }

    override fun toString(): String {
        return "User(email='$email', nickname='$nickname', age=$age, address='$address')"
    }
}

fun main() {
    // 빌더 패턴을 사용한 객체 생성
    val user1 = User.Builder("user1@example.com", "user1").build()
    println(user1)

    // 메서드 체이닝을 통해 원하는 값만 설정
    val user2 = User.Builder("user2@example.com", "user2")
        .age(30)
        .build()
    println(user2)

    val user3 = User.Builder("user3@example.com", "user3")
        .age(25)
        .address("Seoul")
        .build()
    println(user3)
}