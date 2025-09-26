package creational.factorymethod.before

// 각 파일 형식에 대한 인터페이스와 구현체
interface FileExporter {
    fun export(data: String): String
}

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

// 문제가 있는 서비스 클래스
class ExportService {
    // 파일 형식에 따라 객체 생성 로직이 분기 처리된다.
    fun export(format: String, data: String): String {
        val exporter: FileExporter = if (format.equals("CSV", ignoreCase = true)) {
            CsvExporter()
        } else if (format.equals("PDF", ignoreCase = true)) {
            PdfExporter()
        } else {
            throw IllegalArgumentException("Unsupported format")
        }
        return exporter.export(data)
    }
}

fun main() {
    val service = ExportService()
    val csvResult = service.export("csv", "Sample Data 1")
    println(csvResult)

    println("-".repeat(20))

    val pdfResult = service.export("pdf", "Sample Data 2")
    println(pdfResult)
}