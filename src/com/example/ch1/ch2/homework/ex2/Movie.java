package com.example.ch1.ch2.homework.ex2;

public class Movie {
    // 필드
    private String title;
    private String director;
    private int releaseYear;

    // 생성자
    public Movie(String title, String director, int releaseYear) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
    }

    // 메서드
    public void printInfo() {
        System.out.println("제목: " + title);
        System.out.println("감독: " + director);
        System.out.println("개봉 연도: " + releaseYear);
    }

    // 메인 메서드 (실행 예시)
    public static void main(String[] args) {
        Movie movie = new Movie("기생충", "봉준호", 2019);
        movie.printInfo();
    }
}
