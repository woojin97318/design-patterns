package creational.singleton.classic

// Phase 1: 클래식(Java 스타일) 싱글턴 구현
class DatabaseConnector private constructor() {

    init {
        // 실제 DB 연결 시 시간이 걸리는 것을 시뮬레이션
        println("Initializing Database connection (Classic)...")
        Thread.sleep(1000)
    }

    fun connect() {
        println("(${Thread.currentThread().name}) Connected to the database!")
    }

    companion object {
        @Volatile
        private var instance: DatabaseConnector? = null

        fun getInstance(): DatabaseConnector {
            // Double-checked locking
            if (instance == null) {
                synchronized(this) {
                    if (instance == null) {
                        instance = DatabaseConnector()
                    }
                }
            }
            return instance!!
        }
    }
}

fun main() {
    println("--- Classic Singleton Test ---")
    // 여러 스레드에서 동시에 getInstance()를 호출하는 상황을 시뮬레이션
    val thread1 = Thread {
        val db1 = DatabaseConnector.getInstance()
        db1.connect()
    }

    val thread2 = Thread {
        val db2 = DatabaseConnector.getInstance()
        db2.connect()
    }

    thread1.start()
    thread2.start()
}