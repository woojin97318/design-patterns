package creational.factorymethod.after

// Product: 생성될 객체들의 공통 인터페이스
interface FileExporter {
    fun export(data: String): String
}

// ConcreteProduct: 실제 생성될 객체들
class CsvExporter : FileExporter {
    override fun export(data: String): String {
        println("Exporting data to CSV file...")
        return "CSV FILE: $data"
    }
}

class PdfExporter : FileExporter {
    override fun export(data: String): String {
        println("Exporting data to PDF file...")
        return "PDF FILE: $data"
    }
}

// Creator: 팩토리 메서드를 정의하는 인터페이스 (또는 추상 클래스)
interface ExporterFactory {
    // 이 메서드가 바로 '팩토리 메서드' 입니다.
    fun createExporter(): FileExporter

    // 팩토리에서 생성한 객체를 사용하는 로직 (필요에 따라 포함)
    fun exportData(data: String): String {
        val exporter = createExporter()
        return exporter.export(data)
    }
}

// ConcreteCreator: 팩토리 메서드를 구현하여 실제 객체를 생성하는 클래스들
class CsvExporterFactory : ExporterFactory {
    override fun createExporter(): FileExporter {
        return CsvExporter()
    }
}

class PdfExporterFactory : ExporterFactory {
    override fun createExporter(): FileExporter {
        return PdfExporter()
    }
}

fun main() {
    // 클라이언트 코드는 이제 구체적인 Exporter 클래스를 알 필요가 없습니다.
    // 오직 필요한 Factory를 선택하기만 하면 됩니다.

    val csvFactory: ExporterFactory = CsvExporterFactory()
    val csvResult = csvFactory.exportData("Sample Data 1")
    println(csvResult)

    println("-".repeat(20))

    val pdfFactory: ExporterFactory = PdfExporterFactory()
    val pdfResult = pdfFactory.exportData("Sample Data 2")
    println(pdfResult)
}