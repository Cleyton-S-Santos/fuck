package com.batch.demo.writers;

import com.batch.demo.domain.UserDomain;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class UserExcelItemWriter implements ItemWriter<UserDomain> {

    @Override
    public void write(Chunk<? extends UserDomain> users) throws Exception {
        // Crie um novo livro de trabalho do Excel
        Workbook workbook = new HSSFWorkbook();

        // Crie uma nova planilha no livro de trabalho
        Sheet sheet = workbook.createSheet("Users");

        // Crie um cabeçalho na planilha
        Row headerRow = sheet.createRow(0);
        String[] columns = {"ID", "Name", "Email"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Escreva os dados dos usuários nas linhas seguintes
        int rowNum = 1;
        for (UserDomain user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(user.getName());
            row.createCell(2).setCellValue(user.getEmail());
        }

        // Escreva o conteúdo do livro de trabalho em um arquivo Excel
        try (FileOutputStream outputStream = new FileOutputStream("users.xlsx")) {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new Exception("Erro ao escrever no arquivo Excel", e);
        }

        // Libere os recursos
        workbook.close();
    }
}
