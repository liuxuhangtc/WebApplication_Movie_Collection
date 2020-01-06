package me.xuhang.movie.entity;

import lombok.Data;
import me.xuhang.movie.qiniu.QiniuUtils;

import org.json.JSONObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by xuhang on 2019/11/20.
 */
@SuppressWarnings("unused")
@Data
@Entity
@Table(name = "subject")
public class Subject {

    @Id
    @Column(length = 8)
    private String id;

    private String title;

    private String originalTitle;

    private int ratingCount;

    private double totalRating;

    private String directors;

    private String casts;

    private String writers;

    private Date pubDate;

    private short year;

    private String languages;

    private String durations;

    private String genres;

    private String countries;

    @Column(length = 1000)
    private String summary;
    
    @Column(length = 1000)
    private String imageurl;

    private int commentCount;


    
    
    public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public double getRating() {
        return ratingCount == 0 ? 0 : (totalRating / ratingCount);
    }

    public String getImage() {
        JSONObject object = new JSONObject(summary) ;
        if(object.getJSONObject("images")==null){
            return "";
        }else{
            return object.getJSONObject("images").getString("small");
        }
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginalTitle() {
		return originalTitle;
	}

	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}

	public int getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}

	public double getTotalRating() {
		return totalRating;
	}

	public void setTotalRating(double totalRating) {
		this.totalRating = totalRating;
	}

	public String getDirectors() {
		return directors;
	}

	public void setDirectors(String directors) {
		this.directors = directors;
	}

	public String getCasts() {
		return casts;
	}

	public void setCasts(String casts) {
		this.casts = casts;
	}

	public String getWriters() {
		return writers;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public short getYear() {
		return year;
	}

	public void setYear(short year) {
		this.year = year;
	}

	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}

	public String getDurations() {
		return durations;
	}

	public void setDurations(String durations) {
		this.durations = durations;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}

	public String getCountries() {
		return countries;
	}

	public void setCountries(String countries) {
		this.countries = countries;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}


    
}
