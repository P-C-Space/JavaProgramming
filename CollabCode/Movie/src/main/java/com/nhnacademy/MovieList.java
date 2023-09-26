package com.nhnacademy;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class MovieList {

    // movieId, movie
    private List<Movie> movieList;


    public MovieList(String path) {
        this.movieList = new LinkedList<>();
        readFile(path);
    }

    /**
     * @param path 파일 경로를 받아서 영화파일을 읽어와서 movielist에
     *             추가하는 메서드
     */
    private void readFile(String path) {
        File file = new File(path);

        try {
            FileReader csvData = new FileReader(file);
            if (csvData.read() == -1) {
                throw new IOException("잘못된 경로입니다.");
            }

            BufferedReader bufferedReader = new BufferedReader(csvData);
            bufferedReader.readLine(); // 첫 줄 버림
            StringTokenizer stringTokenizer;

            String token;
            String token2;
            String line;
            List<String> result = new LinkedList<>();

            StringBuilder stringBuilder;

            while ((line = bufferedReader.readLine()) != null) {

                line = LineBreaking(line, bufferedReader); // "..."이후 줄바꿈 라인 체크
                line = hasNull(line); // ",," 존재 체크

                // 초기화
                stringTokenizer = new StringTokenizer(line, ",");
                result.clear();


                while (stringTokenizer.hasMoreTokens()) {

                    token = stringTokenizer.nextToken();

                    if (token.charAt(0) == '"') {
                        stringBuilder = new StringBuilder();

                        if (token.charAt(token.length() - 1) != '"') {

                            stringBuilder.append(token.substring(1, token.length()));

                            while (true) {
                                token2 = stringTokenizer.nextToken();
                                if (token2.charAt(token2.length() - 1) == '"') {
                                    stringBuilder.append(", " + token2.substring(0, token2.length() - 1));
                                    break;
                                }
                                stringBuilder.append(", " + token2);
                            }
                            result.add(stringBuilder.toString());
                            continue;
                        } else {
                            result.add(token.substring(1, token.length() - 1));
                            continue;
                        }
                    }

                    result.add(token);
                }

                addMovie(result);

            }

            this.movieList = new ArrayList<>(movieList);


        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private String LineBreaking(String line, BufferedReader bufferedReader) throws IOException {
        int index = 0;
        index = line.indexOf("...");
        if (index != -1 && index + 3 == line.length()) {
            line = line + bufferedReader.readLine();
        }
        return line;
    }

    private String hasNull(String line) {
        int index = 0;
        while (index != -1) {
            index = line.indexOf(",,");
            if (index != -1) {
                line = line.substring(0, index + 1) + "NULL" + line.substring(index + 1, line.length());
            }
        }
        return line;
    }

    private void addMovie(List<String> datas) {
        Movie movie = new Movie();
        movie.addData(datas);

        this.movieList.add(movie);
    }

    public List<Movie> searchMovie(String searchName, int option) {

        int index;

        List<Movie> findMovie = new LinkedList<>();
        binarySearch(searchName, option, findMovie);

        return findMovie;
    }

    private void binarySearch(String searchName, int option, List<Movie> findMovie) {
        switch (option) {
            case 1:
                binarySearchTitle(searchName, findMovie);
                break;
            case 2:
                binarySearchKoreanTitle(searchName, findMovie);
                break;
            case 3:
                String regExp = "^[0-9]{4}$";
                if (!searchName.matches(regExp)) {
                    System.out.println("연도는 4자리 숫자입니다.");
                    return;
                }
                binarySearchYear(searchName, findMovie);
                break;
            default:
                System.out.println("존재 하지 않는 옵션");
                break;
        }
    }

    private void binarySearchTitle(String searchName, List<Movie> findMovie) {
        movieList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        int index = (movieList.size() - 1) / 2 + 1;
        int num = movieList.size() / 4;
        while (0 < index && index < movieList.size()) {
            if (movieList.get(index).getTitle().equals(searchName)) {
                findMovie.add(movieList.get(index));
                return;
            } else if (movieList.get(index).getTitle().compareTo(searchName) > 0) {
                index -= num;
            } else {
                index += num;
            }
            num = (num / 2) + 1;
        }

    }

    private void binarySearchKoreanTitle(String searchName, List<Movie> findMovie) {
        movieList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getKoreanTitle().compareTo(o2.getKoreanTitle());
            }
        });

        int index = (movieList.size() - 1) / 2 + 1;
        int num = movieList.size() / 4;
        while (0 < index && index < movieList.size()) {
            if (movieList.get(index).getKoreanTitle().equals(searchName)) {
                findMovie.add(movieList.get(index));
                return;
            } else if (movieList.get(index).getKoreanTitle().compareTo(searchName) > 0) {
                index -= num;
            } else {
                index += num;
            }
            num = (num / 2) + 1;
        }
    }

    private void binarySearchYear(String searchName, List<Movie> findMovie) {

        movieList.sort(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getReleaseYear() - o2.getReleaseYear();
            }
        });

        int index = (movieList.size() - 1) / 2 + 1;
        int num = movieList.size() / 4;
        int year = Integer.parseInt(searchName);
        while (0 < index && index < movieList.size()) {
            if (movieList.get(index).getReleaseYear() == year) {
                nextIndexfindMovie(index,findMovie,year);
                return;
            } else if ((movieList.get(index).getReleaseYear() - year) > 0) {
                index -= num;
            } else {
                index += num;
            }
            num = (num / 2) + 1;
        }
        return;
    }

    private void nextIndexfindMovie(int index, List<Movie> findMovie, int year){

        int left = index - 1;
        int right = index + 1;

        findMovie.add(movieList.get(index));
        while(left > 0){
            if (movieList.get(left).getReleaseYear() != year) {
                break;
            }
            findMovie.add(movieList.get(left));
            left--;
        }

        while(right < movieList.size()){
            if (movieList.get(right).getReleaseYear() != year) {
                break;
            }
            findMovie.add(movieList.get(right));
            right++;
        }

    }
}