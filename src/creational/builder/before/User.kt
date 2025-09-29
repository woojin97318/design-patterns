package creational.builder.before

// 점층적 생성자 패턴의 문제점을 보여주는 코드
class User {
    val email: String
    val nickname: String
    val age: Int?
    val address: String?

    // 필수 필드를 위한 생성자
    constructor(email: String, nickname: String) {
        this.email = email
        this.nickname = nickname
        this.age = null
        this.address = null
    }

    // 필수 + age
    constructor(email: String, nickname: String, age: Int) {
        this.email = email
        this.nickname = nickname
        this.age = age
        this.address = null
    }

    // 필수 + address
    constructor(email: String, nickname: String, address: String) {
        this.email = email
        this.nickname = nickname
        this.age = null
        this.address = address
    }

    // ... age와 address를 모두 받는 생성자 등등... 계속 추가해야 함

    override fun toString(): String {
        return "User(email='$email', nickname='$nickname', age=$age, address='$address')"
    }
}

fun main() {
    val user1 = User("user1@example.com", "user1")
    println(user1)

    val user2 = User("user2@example.com", "user2", 30)
    println(user2)

    val user3 = User("user3@example.com", "user3", "Seoul")
    println(user3)
}