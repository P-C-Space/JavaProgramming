package com.nhnacademy;

import java.util.List;
import java.util.Scanner;

public class Main {

    // TODO: csv -> 최상위
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String path = "src/main/resources/Movie.csv"; // 터미널 입력시 파일 경로

        MovieList movieList = new MovieList(path); // 파일 읽기
        System.out.println(path + "파일을 읽었습니다.");


        // TODO: 사용자 검색하는 부분 구현
        String input;
        int option = 1;
        while (true) {
            System.out.println();
            System.out.println(
                    "검색옵션 | 1: 영화 제목 | 2: 영화 한글 제목 | 3: 영화 년도 | 현재 옵션 : " + option + "\n검색할 영화 제목을 입력하세요 (종료: 엔터)");

            input = sc.nextLine();

            if ( 0 < Integer.parseInt(input) && Integer.parseInt(input) < 4) {
                option = Integer.parseInt(input);
                continue;
            }

            if (input.isEmpty()) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            List<Movie> searchedMovies = movieList.searchMovie(input, option);

            if (searchedMovies.isEmpty()) {
                System.out.println("찾으시는 영화가 없습니다. \n다시 입력해주세요");
                continue;
            }

            for(Movie movie : searchedMovies)
                System.out.println("찾으시는 영화\n" + movie);

            System.out.println("찾으시는 영화 개수 : " + searchedMovies.size());

        }

        sc.close();
    }




}