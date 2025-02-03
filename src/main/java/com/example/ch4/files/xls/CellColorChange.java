package com.example.ch4.files.xls;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CellColorChange {

    static class StudentData {
        String name;
        String subject;
        double score;

        public StudentData(String name, String subject, double score) {
            this.name = name;
            this.subject = subject;
            this.score = score;
        }
    }

    /**
     * 원본 엑셀 파일을 열고(inputPath 사용), 첫 번째 시트를 가져와서 모든 행을 순회하는 부분
     * createRedCellStyle 메서드에서 빨간색 셀 스타일 생성
     * processRow 메서드에서 각 행의 데이터를 읽고, 조건(점수 < 50)일 때 스타일 적용
     * 작업이 끝나면 modified_grades.xlsx라는 새 파일로 저장
     * @param inputPath
     * @return
     */
    public static List<StudentData> processWorkbook(String inputPath) {
        List<StudentData> studentData = new ArrayList<>();

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(inputPath))) {
            CellStyle redStyle = createRedCellStyle(workbook);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rows = sheet.iterator();
            if (rows.hasNext()) rows.next(); // 헤더 스킵

            while (rows.hasNext()) {
                Row row = rows.next();
                processRow(row, studentData, redStyle);
            }

            // 변경 사항을 새로운 파일로 저장
            String outputPath = ExcelUtils.getOutputPath("modified_grades.xlsx");
            try (FileOutputStream fos = new FileOutputStream(outputPath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new ExcelUtils.ExcelException("파일 처리 실패: " + inputPath, e);
        }
        return studentData;
    }

    /**
     * 현재 행(Row)의 이름, 과목, 점수 데이터를 추출
     * 세 번째 셀(점수 셀)이 없거나 빈 셀이면 Row.MissingCellPolicy.CREATE_NULL_AS_BLANK 옵션으로 생성 후 0으로 초기화
     * scoreCell.setCellType(CellType.NUMERIC) 후 scoreCell.setCellValue(0.0) 호출로 실제 숫자 셀로 만든 뒤 0 대입
     * 그 후 ExcelUtils.getNumericValue로 점수(double) 추출
     * 점수가 50 미만이면 redStyle을 셀에 적용
     * @param row
     * @param dataList
     * @param redStyle
     */
    private static void processRow(
            Row row,
            List<StudentData> dataList,
            CellStyle redStyle
    ) {
        try {
            String name = ExcelUtils.getStringValue(row, 0);
            String subject = ExcelUtils.getStringValue(row, 1);

            // 점수 셀 강제 생성
            Cell scoreCell = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            // 빈 셀이면 0으로 초기화
            if (scoreCell.getCellType() == CellType.BLANK) {
                scoreCell.setCellType(CellType.NUMERIC);
                scoreCell.setCellValue(0.0);
            }

            double score = ExcelUtils.getNumericValue(row, 2);

            dataList.add(new StudentData(name, subject, score));

            // 50 미만이면 빨간색
            if (score < 50) {
                scoreCell.setCellStyle(redStyle);
            }

        } catch (Exception e) {
            System.err.printf("Row %d 처리 실패: %s%n",
                    row.getRowNum() + 1, e.getMessage());
        }
    }

    /**
     * XSSFCellStyle 인스턴스 생성
     * XSSFColor를 사용해 RGB(255,0,0) 값을 넣어 빨간색 지정
     * 스타일 패턴(SOLID_FOREGROUND)을 적용해서 셀에 완전한 배경색이 들어가도록 설정
     * @param workbook
     * @return
     */
    private static XSSFCellStyle createRedCellStyle(Workbook workbook) {
        XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
        XSSFColor redColor = new XSSFColor(new java.awt.Color(255, 0, 0), new DefaultIndexedColorMap());
        style.setFillForegroundColor(redColor);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return style;
    }

    public static void main(String[] args) {
        try {
            String inputPath = ExcelUtils.getResourcePath("grades.xlsx");
            List<StudentData> data = processWorkbook(inputPath);

            // ExcelUtils를 이용해 데이터 검증 및 저장 (필요시 사용)
            /*
            ExcelUtils.writeExcel(
                    "validated_grades.xlsx",
                    data,
                    new String[]{"이름", "과목", "점수"},
                    (student, row) -> {
                        row.createCell(0).setCellValue(student.name);
                        row.createCell(1).setCellValue(student.subject);
                        row.createCell(2).setCellValue(student.score);
                    }
            );
            */

            System.out.println("처리 완료! 결과 파일: " + ExcelUtils.getOutputPath("modified_grades.xlsx"));

        } catch (ExcelUtils.ExcelException e) {
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }
}