package creational.singleton.idiomatic

// Phase 2: 코틀린스러운(Idiomatic) 싱글턴 구현
object DatabaseConnector {

    init {
        // 이 코드는 DatabaseConnector가 처음 사용될 때 단 한 번만 실행됩니다.
        println("Initializing Database connection...")
        Thread.sleep(1000)
    }

    fun connect() {
        println("(${Thread.currentThread().name}) Connected to the database!")
    }
}

fun main() {
    // 여러 스레드에서 동시에 접근해도 동일한 인스턴스를 사용합니다.
    val thread1 = Thread {
        val db1 = DatabaseConnector // .getInstance() 호출이 필요 없습니다.
        db1.connect()
    }

    val thread2 = Thread {
        val db2 = DatabaseConnector
        db2.connect()
    }

    thread1.start()
    thread2.start()
}