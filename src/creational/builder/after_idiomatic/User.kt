package creational.builder.after_idiomatic

// data class와 기본값 파라미터를 사용한 가장 코틀린다운 방식
data class User(
    val email: String,
    val nickname: String,
    val age: Int? = null,
    val address: String? = null
)

fun main() {
    // 필수 파라미터만으로 객체 생성
    val user1 = User(email = "user1@example.com", nickname = "user1")
    println(user1)

    // 이름 있는 인자를 사용하여 원하는 값만 설정
    val user2 = User(
        email = "user2@example.com",
        nickname = "user2",
        age = 30
    )
    println(user2)

    val user3 = User(
        email = "user3@example.com",
        nickname = "user3",
        address = "Seoul",
        age = 25
    )
    println(user3)
}