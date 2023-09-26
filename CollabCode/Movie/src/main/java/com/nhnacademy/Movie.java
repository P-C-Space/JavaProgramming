package com.nhnacademy;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private int movieId;
    private String title;
    private String koreanTitle;
    private String plot;
    private int releaseYear;
    private int runningTime;
    private int gradeID;
    private int gradInKoreaID;
    private String poster;
    private LocalDate date;
    private long boxOfficeWWGross;
    private long boxOfficeUSGross;
    private long budget;
    private String originalAuthor;
    private String originalSource;

    public Movie() {
    }

    private boolean isNull(String data) {
        if (data.equals("NULL")){
            return true;
        }
        return false;
    }


    private void setMovieId(String movieId) {
        if(isNull(movieId)){
            throw new IllegalArgumentException("movieId가 null 일수는 없습니다.");
        }
        this.movieId = Integer.parseInt(movieId);
    }

    private void setTitle(String title) {
        if(isNull(title)){
            throw new IllegalArgumentException("title이 null 일수는 없습니다.");
        }
        this.title = title;
    }

    private void setKoreanTitle(String koreanTitle) {
        this.koreanTitle = koreanTitle;
    }

    private void setPlot(String plot) {
        this.plot = plot;
    }

    private void setReleaseYear(String releaseYear) {
        if(isNull(releaseYear)) {
            this.releaseYear = 0;
        }else {
            this.releaseYear = Integer.parseInt(releaseYear);
        }
    }

    private void setRunningTime(String runningTime) {
        if(isNull(runningTime)) {
            this.runningTime = 0;
            return;
        }
        this.runningTime = Integer.parseInt(runningTime);
    }

    private void setGradeID(String gradeID) {
        if(isNull(gradeID)){
            this.gradeID = 0;
            return;
        }
        this.gradeID = Integer.parseInt(gradeID);
    }

    private void setGradInKoreaID(String gradInKoreaID) {
        if(isNull(gradInKoreaID)) {
            this.gradInKoreaID = 0;
            return;
        }
        this.gradInKoreaID = Integer.parseInt(gradInKoreaID);
    }

    private void setPoster(String poster) {
        this.poster = poster;
    }

    private void setDate(String date) {
        if (isNull(date)) {
            this.date = null;
            return;
        }
        this.date = LocalDate.parse(date); // 작동하는지 모름
    }

    private void setBoxOfficeWWGross(String boxOfficeWWGross) {
        if(isNull(boxOfficeWWGross))
        {
            this.boxOfficeWWGross = 0l;
            return;
        }
        this.boxOfficeWWGross = Long.parseLong(boxOfficeWWGross);
    }

    private void setBoxOfficeUSGross(String boxOfficeUSGross) {
        if(isNull(boxOfficeUSGross)){
            this.boxOfficeUSGross = 0l;
            return;
        }
        this.boxOfficeUSGross = Long.parseLong(boxOfficeUSGross);
    }

    private void setBudget(String budget) {
        if(isNull(budget)) {
            this.budget = 0;
            return;
        }
        this.budget = Integer.parseInt(budget);
    }

    private void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    private void setOriginalSource(String originalSource) {
        this.originalSource = originalSource;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getKoreanTitle() {
        return koreanTitle;
    }

    public String getPlot() {
        return plot;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public int getGradeID() {
        return gradeID;
    }

    public int getGradInKoreaID() {
        return gradInKoreaID;
    }

    public String getPoster() {
        return poster;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getBoxOfficeWWGross() {
        return boxOfficeWWGross;
    }

    public long getBoxOfficeUSGross() {
        return boxOfficeUSGross;
    }

    public long getBudget() {
        return budget;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public String getOriginalSource() {
        return originalSource;
    }

    @Override
    public String toString() {
        return "영화 제목 : " + this.koreanTitle + "\n"
                + "영화 영어 제목 : " + this.title + "\n"
                + "줄거리 : " + this.plot + "\n"
                + "개봉 연도 : " + this.releaseYear + "\n"
                + "상영 시간 : " + this.runningTime + "\n"
                + "한국 개봉 일자 : " + this.date + "\n"
                + "BoxOfficeWWGross : " + this.boxOfficeWWGross + "\n"
                + "BoxOfficeUsGross : " + this.boxOfficeUSGross + "\n"
                + "budget : " + this.budget + "\n"
                + "원작자 : " + this.originalAuthor + "\n"
                + "원작 : " + this.originalSource + "\n";
    }

    public void addData(List<String> data) {
        int count = 0;
        setMovieId(data.get(count++));
        setTitle(data.get(count++));
        setKoreanTitle(data.get(count++));
        setPlot(data.get(count++));
        setReleaseYear(data.get(count++));
        setRunningTime(data.get(count++));
        setGradeID(data.get(count++));
        setGradInKoreaID(data.get(count++));
        setPoster(data.get(count++));
        setDate(data.get(count++));
        setBoxOfficeWWGross(data.get(count++));
        setBoxOfficeUSGross(data.get(count++));
        setBudget(data.get(count++));
        setOriginalAuthor(data.get(count++));
        setOriginalSource(data.get(count));
    }

}
