package com.example.ch4.files.csv;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;

public class CsvProcessor {

    static class Employee {
        int id;
        String name;
        int age;
        String department;

        public Employee(int id, String name, int age, String department) {
            this.id = id;
            this.name = name;
            this.age = age;
            this.department = department;
        }

        @Override
        public String toString() {
            return id + "," + name + "," + age + "," + department;
        }
    }

    public static List<Employee> readCsv(String filePath) {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false; // 헤더 스킵
                    continue;
                }
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String department = parts[3];
                employees.add(new Employee(id, name, age, department));
            }
        } catch (IOException e) {
            System.err.println("파일 읽기 오류: " + e.getMessage());
        }
        return employees;
    }

    public static boolean validateRow(String[] row) {
        if (row.length != 4) return false;
        try {
            Integer.parseInt(row[0]); // ID 검증
            Integer.parseInt(row[2]); // 나이 검증
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * 파일이 읽히지 않음. maven 확인. maven 에 csv 파일 처리 추가. 후 빌드 필요. > target에 넘어 갔는지 확인 필요
     * @param employees
     * @param condition
     * @return
     */
    public static List<Employee> filterByCondition(List<Employee> employees, Predicate<Employee> condition) {
        List<Employee> filtered = new ArrayList<>();
        for (Employee emp : employees) {
            if (condition.test(emp)) {
                filtered.add(emp);
            }
        }
        return filtered;
    }

    public static List<Employee> sortData(List<Employee> employees, Comparator<Employee> comparator) {
        employees.sort(comparator);
        return employees;
    }

    public static void writeCsv(String filePath, List<Employee> employees, boolean includeHeader) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            if (includeHeader) {
                bw.write("id,name,age,department");
                bw.newLine();
            }
            for (Employee emp : employees) {
                bw.write(emp.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }
    }

    // 리소스 파일 경로 가져오기
    private static String getResourceFilePath(String fileName) {
        ClassLoader classLoader = CsvProcessor.class.getClassLoader();
        if (classLoader.getResource(fileName) != null) {
            return classLoader.getResource(fileName).getPath();
        }
        return null;
    }

    public static void main(String[] args) {
        String inputFilePath = getResourceFilePath("data.csv");
        String outputFilePath = "filtered_data.csv";

        // 1. CSV 읽기
        List<Employee> employees = readCsv(inputFilePath);
        System.out.println("전체 데이터:");
        employees.forEach(System.out::println);

        // 2. 데이터 필터링 (Engineering 부서)
        List<Employee> engineeringEmployees = filterByCondition(employees, emp -> emp.department.equalsIgnoreCase("Engineering"));
        System.out.println("\n필터링된 데이터 (Engineering 부서):");
        engineeringEmployees.forEach(System.out::println);

        // 3. 정렬 (나이 기준 오름차순)
        sortData(engineeringEmployees, Comparator.comparingInt(emp -> emp.age));
        System.out.println("\n정렬된 데이터 (나이 기준 오름차순):");
        engineeringEmployees.forEach(System.out::println);

        // 4. CSV 쓰기
        writeCsv(outputFilePath, engineeringEmployees, true);
        System.out.println("\n필터링된 데이터가 '" + outputFilePath + "'에 저장됨");
    }
}
