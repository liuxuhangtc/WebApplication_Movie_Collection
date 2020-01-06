package me.xuhang.movie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by xuhang on 2019/11/20.
 */
@Data
@Entity
@Table(name = "playing")
@AllArgsConstructor
@NoArgsConstructor
public class Playing {
    @Id
    @Column(length = 8)
    private String id;

    public Playing() {
    	
    }
    public Playing(String id) {
    	this.id = id;
    }
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
}
