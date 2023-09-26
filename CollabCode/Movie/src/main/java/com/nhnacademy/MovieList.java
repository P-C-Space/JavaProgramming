package com.nhnacademy;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class MovieList {

    // movieId, movie
    private final HashMap<Integer, Movie> movieList;

    //String -> 영화 제목, Integer -> movieId
    private Map<String, Integer> movieIndexList;

    public MovieList(String path) {
        this.movieList = new HashMap<>();
        this.movieIndexList = new HashMap<>();

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
                        }

                        else {
                            result.add(token.substring(1, token.length() - 1));
                            continue;
                        }
                    }

                    result.add(token);
                }

                addMovie(result);

            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private static String LineBreaking(String line, BufferedReader bufferedReader) throws IOException {
        int index = 0;
        index = line.indexOf("...");
        if (index != -1 && index + 3 == line.length()) {
            line = line + bufferedReader.readLine();
        }
        return line;
    }

    private String hasNull(String line){
        int index = 0;
        while (index != -1) {
            index = line.indexOf(",,");
            if (index != -1) {
                line = line.substring(0, index + 1) + "NULL" + line.substring(index + 1, line.length());
            }
        }
        return line;
    }
    
    private void addMovie(List<String> datas){
        Movie movie = new Movie();
        movie.addDatas(datas);

        this.movieList.put(movie.getMovieId(), movie);
        if (!(movie.getKoreanTitle().equals("NULL"))) {
            this.movieIndexList.put(movie.getKoreanTitle(), movie.getMovieId());
        }
        this.movieIndexList.put(movie.getTitle(), movie.getMovieId());
    }

    public Movie searchMovie(String searchName) {
        int index = 0;

        if (movieIndexList.get(searchName) == null) {
            return null;
        } else {
            index = movieIndexList.get(searchName);
        }

        Movie searchedMovie = movieList.get(index);
        return searchedMovie;
    }
}