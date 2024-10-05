import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ZipTests {
    private ClassLoader cl = JsonTests.class.getClassLoader();
    PDF pdfFile = null;
    XLS excelFile = null;
    CSVReader csvFile = null;

    @Test
    void pdfCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )){
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null){
                if (entry.getName().endsWith(".pdf")){
                    pdfFile = new PDF(zipFile);
                    break;
                }
            }

            assertThat(pdfFile.numberOfPages).isEqualTo(10);
        }
    }

    @Test
    void csvCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )){
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null){
                if (entry.getName().endsWith(".csv")){
                    csvFile = new CSVReader(new InputStreamReader(zipFile));
                    break;
                }
            }

            List<String[]> csvData = csvFile.readAll();

            assertThat(csvData.get(0)).contains("Банан");
            assertThat(csvData.get(1)).contains("Лимон");
            assertThat(csvData.get(2)).contains("Яблоко");
        }
    }

    @Test
    void excelCheckTest() throws Exception {
        try (ZipInputStream zipFile = new ZipInputStream(
                cl.getResourceAsStream("test.zip")
        )){
            ZipEntry entry;

            while ((entry = zipFile.getNextEntry()) != null){
                if (entry.getName().endsWith(".xlsx")){
                    excelFile = new XLS (zipFile);
                    break;
                }
            }

            String firstColumnValue = excelFile.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
            assertThat(firstColumnValue).contains("Овощи");
            String secondColumnValue = excelFile.excel.getSheetAt(0).getRow(0).getCell(1).getStringCellValue();
            assertThat(secondColumnValue).contains("Количество");
        }
    }

}
